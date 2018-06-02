import React, { Component } from 'react';
import { Button, Glyphicon, ListGroup, ListGroupItem, Row, Col, Panel, FormGroup } from 'react-bootstrap';
import { Collapse } from 'reactstrap';
import axios from 'axios';
import DodjelaIncidenta from './DodjelaIncidenta';
import EskalacijaIncidenta from './EskalacijaIncidenta';
import Poruke from './Poruke';
import {
    PATH_BASE, PATH_INCIDENTS, PATH_ONE_INCIDENT, PATH_ESCALATION_ADMIN, PATH_ASSIGN_ADMIN,
    PATH_RESOLVE_INCIDENT, PATH_ASSIGN_RESOLVER, PATH_ESCALATION_RESOLVER,
    PATH_CLOSE_INCIDENT, PATH_INCIDENT_MESSAGE, PATH_GET_INCIDENT_MESSAGE, PATH_SEND_INCIDENT_MESSAGE
} from '../globals';

class Incident extends Component {

    state = {
        id: this.props.match.params.id,
        data: {},
        openOpis: false,
    }

    componentWillMount() {
        this.getIncident();
    }
    odbijZatvaranje=(event)=> {
            event.preventDefault();
            axios.post(PATH_BASE + PATH_INCIDENTS + PATH_CLOSE_INCIDENT, {
                id: this.state.id,
                username: sessionStorage.getItem("username"),
                close: false
            }
            )
                .then(this.closeRejectSucess.bind(this))
                .catch(this.handleError.bind(this));

        }

        closeRejectSucess=(response) =>{
            alert("Incident je ponovno otvoren");
            this.getIncident();
        }
        prihvatiZatvaranje=(event)=> {
            event.preventDefault();
            axios.post(PATH_BASE + PATH_INCIDENTS + PATH_CLOSE_INCIDENT, {
                id: this.state.id,
                username: sessionStorage.getItem("username"),
                close: true
            }
            )
                .then(this.closeSucess.bind(this))
                .catch(this.handleError.bind(this));

        }

        closeSucess=(response)=> {
            alert("Incident je zatvoren");
            this.getIncident();
        }
    getIncident = () => {
        axios.post('http://localhost:8080/incident/getIncidentDetail',
            {
                username: sessionStorage.getItem("username"),
                id: this.state.id
            })
            .then(this.handleSuccess.bind(this))
            .catch(this.handleError.bind(this));
    }
    handleSuccess = (response) => {
        this.setState({
            data: response.data
        })
        console.log(this.state.data);
    }

    handleError = (error) => {
        console.log(error);
    }

    handleChange = (e) => {
        this.setState({ [e.target.name]: e.target.value });
    }

    toggleOpis = () => {
        this.setState({
            openOpis: !this.state.openOpis
        });
    }

    render() {

        let role = sessionStorage.getItem("rola");
        console.log(this.state.data);
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
                        // GLAVNI DIO INFORMACIJA O INCIDENTU
                    }


                    <ListGroup>
                        <Row>
                            <Col md={4} lg={4}>
                                {role != 'Korisnik' ? <ListGroupItem header="Prijavio">{this.state.data.creatorUsername}</ListGroupItem> : ""}
                                <ListGroupItem header="Servis">{this.state.data.serviceName}</ListGroupItem>
                                {role == 'Administrator' && this.props.tip ? <ListGroupItem header="Rješava">{this.state.data.resolverUsername}</ListGroupItem> : ""}
                                <ListGroupItem header="Hitnost">{this.state.data.urgency}</ListGroupItem>
                            </Col>
                            <Col md={4} lg={4}>
                                {role != 'Korisnik' && this.props.tip != 'Nedodjeljen' ? <ListGroupItem header="Prioritet">{this.state.data.priority}</ListGroupItem> : ""}
                                {role == 'Administrator' && this.props.tip != 'Nedodjeljen' ? <ListGroupItem header="Odjel">{this.state.data.departmentName}</ListGroupItem> : ""}
                                <ListGroupItem header="Status">{this.state.data.status}</ListGroupItem>
                                {role != "Korisnik" ? <ListGroupItem header="Eskalacija">{this.state.data.escalation ? "Da" : "Ne"}</ListGroupItem> : ""}
                            </Col>
                            <Col md={4} lg={4}>
                                <ListGroupItem header="Datum kreiranja">{this.state.data.createdDate}</ListGroupItem>
                                <ListGroupItem header="Posljednje rješavanje">{this.state.data.lastResolveDate}</ListGroupItem>
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

                    {this.state.data.status === 'rijesen' && role === 'Korisnik' ?
                       <Panel bsStyle="info" id="collapsible-panel-example-2">
                           <Row>
                               <br />
                               <Col md={6} style={{ textAlign: "right" }} xsOffset={3}>
                                   <Button type="submit" bsStyle="primary" className="btn-block btn-lg" onClick={this.odbijZatvaranje} bsSize="lg">
                                       Otvori ponovo</Button>
                                   <br />
                               </Col>
                           </Row>
                           <Row>
                               <Col md={6} xsOffset={3}>
                                   <Button className="btn-block btn-lg" bsStyle="primary" bsSize="large"
                                       onClick={this.prihvatiZatvaranje} >
                                       Zatvori incident
                </Button>
                                   <br />
                               </Col>
                           </Row>
                           <br />
                       </Panel>
                       : null}

                    {
                        // DODJELA INCIDENTA
                    }

                    <DodjelaIncidenta Id={this.state.id} data={this.state.data} />

                    {
                        //ESKALACIJA INCIDENTA
                    }

                    <EskalacijaIncidenta data={this.state.data}/>

                    {
                        //PORUKE
                    }

                    <Poruke Id={this.state.id}/>


                </div>
            </div>
        );

    }

}

export default Incident;
