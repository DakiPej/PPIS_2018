import React, { Component } from 'react';
import axios from 'axios';
import { ListGroup,  ListGroupItem, Button, Glyphicon, Row, Col, Panel } from 'react-bootstrap';
import { Collapse } from 'reactstrap';
import DodjelaZahtjeva from './DodjelaZahtjeva';
import EskalacijaZahtjeva from './EskalacijaZahtjeva';
import Poruke from './Poruke';


class Zahtjev extends Component {

    state = {
        data: {},
        id: this.props.match.params.id,
        openOpis: false
    }
   /* odbijZatvaranje=(event)=> {
            event.preventDefault();
            axios.post(PATH_BASE + PATH_INCIDENTS + PATH_CLOSE_INCIDENT, {
                id: this.state.id,
                username: sessionStorage.getItem("username"),
                close: false
            }
            )
                .then(this.closeRejectSucess.bind(this))
                .catch(this.handleError.bind(this));

        }*/

        /*closeRejectSucess=(response) =>{
            alert("Incident je ponovno otvoren");
            this.setState(prevState => ({
                data:
                    {
                        ...prevState.data,
                        status: "u obradi"
                    }
            }));
        }*/
        prihvatiZatvaranje=(event)=> {
            event.preventDefault();
            axios.post("http://localhost:8080/requests/close" /*PATH_BASE + PATH_INCIDENTS + PATH_CLOSE_INCIDENT*/, {
                requestId: this.state.id,
                username: sessionStorage.getItem("username"),
                //close: true
            }
            )
                .then(this.closeSucess.bind(this))
                .catch(this.handleError.bind(this));

        }

        closeSucess=(response)=> {
            alert("Zahtjev je zatvoren");
            this.getZahtjevi() ; 
        }

    componentDidMount(){
        this.getZahtjevi();
    }

    getZahtjevi= () => {
        console.log("http://localhost:8080/requests/" + sessionStorage.getItem("username")+"/"+this.props.match.params.id);
        axios.get("http://localhost:8080/requests/" + sessionStorage.getItem("username")+"/"+this.props.match.params.id)
            .then(this.handleSuccessZahtjevi)
            .catch(this.handleError);
    }

    handleSuccessZahtjevi = (response) => {
        console.log("USPJELO....") ;
        console.log(response) ;
        this.setState({
            data: response.data
        });
    }

    handleError = (error) => {
        console.log(error);
    }

    toggleOpis = () => {
        this.setState({
            openOpis: !this.state.openOpis
        });
    }

    render() {

        let role = sessionStorage.getItem("rola");
        console.log(this.props.tip);

        return (
            <div className="panel panel-primary ">
                <div className="panel-heading d-flex w-100 justify-content-between">
                    <h1 className="panel-title">{this.state.data.title}</h1>
                    <Button bsSize="large">
                        <Glyphicon glyph="download-alt" />
                    </Button>
                </div>
                <div className="panel-body">

                    {
                        //PODACI
                    }

                    <ListGroup>
                        <Row>
                            <Col md={4} lg={4}>
                                {role !== 'Korisnik' ? <ListGroupItem header="Prijavio">{this.state.data.creatorUsername}</ListGroupItem> : ""}
                                {role === 'Administrator' && this.props.tip ? <ListGroupItem header="Rješava">{this.state.data.resolverUsername}</ListGroupItem> : ""}
                                <ListGroupItem header="Hitnost">{this.state.data.urgency}</ListGroupItem>
                            </Col>
                            <Col md={4} lg={4}>
                                {role === 'Administrator' && this.props.tip !== 'Nedodijeljen' ? <ListGroupItem header="Odjel">{this.state.data.departmentName}</ListGroupItem> : ""}
                                <ListGroupItem header="Status">{this.state.data.status}</ListGroupItem>
                            </Col>
                            <Col md={4} lg={4}>
                                <ListGroupItem header="Datum kreiranja">{this.state.data.createdDate}</ListGroupItem>
                                <ListGroupItem header="Datum zatvaranja">{this.state.data.closedDate}</ListGroupItem>
                                <ListGroupItem header="Kontakt">{this.state.data.contactMethod}</ListGroupItem>
                            </Col>
                        </Row>
                    </ListGroup>
                    <Panel style={{ marginTop: "20px" }}>
                        <Panel.Heading>
                            <Panel.Title onClick={this.toggleOpis}>
                                Opis
                                </Panel.Title>
                        </Panel.Heading>
                        <Collapse isOpen={this.state.openOpis}>
                            <Panel.Body>
                                {this.state.data.description}
                            </Panel.Body>
                        </Collapse>
                    </Panel>
                    {this.state.data.status === 'u obradi' && role === 'Korisnik' ?
                       <Panel bsStyle="info" id="collapsible-panel-example-2">
                       <br/>
                           {/* <Row>
                               <br />
                               <Col md={6} style={{ textAlign: "right" }} xsOffset={3}>
                                   <Button type="submit" bsStyle="primary" className="btn-block btn-lg" onClick={this.odbijZatvaranje} bsSize="lg">
                                       Otvori ponovo</Button>
                                   <br />
                               </Col>
                           </Row> */}
                           <Row>
                               <Col md={6} xsOffset={3}>
                                   <Button className="btn-block btn-lg" bsStyle="primary" bsSize="large"
                                       onClick={this.prihvatiZatvaranje} >
                                       Zatvori zahtjev
                </Button>
                                   <br />
                               </Col>
                           </Row>
                           <br />
                       </Panel>
                       : null}


                    {
                        //DODJELJIVANJE ZAHTJEVA
                    }

                    <DodjelaZahtjeva data={this.state.data} />

                    {
                        //ESKALACIJA ZAHTJEVA <EskalacijaZahtjeva data={this.state.data} />
                    }

                    <br/>
                    {
                        //PORUKE
                    }


                    {this.state.data.status != 'nedodijeljen'?
                    <Poruke Id={this.state.id} />:null}

                </div>
            </div>
        );
    }
}

export default Zahtjev;
