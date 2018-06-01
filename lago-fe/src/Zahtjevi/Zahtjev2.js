import React, { Component } from 'react';
import axios from 'axios';
import { ListGroup,  ListGroupItem, Button, Glyphicon, Row, Col, Panel } from 'react-bootstrap';
import { Collapse } from 'reactstrap';
import DodjelaZahtjeva from './DodjelaZahtjeva';
import EskalacijaZahtjeva from './EskalacijaZahtjeva';
import Poruke from './Poruke';


class Zahtjev extends Component {

    state = {
        data: '',
        openOpis: false
    }

    getZahtjevi() {
        axios.get("htttp://localhost:8080/zahtjevi/" + sessionStorage.getItem("username"))
            .then(this.handleSuccessZahtjevi(this))
            .catch(this.handleError(this));
    }

    handleSuccessZahtjevi = (response) => {
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
                                {role === 'Administrator' && this.props.tip !== 'Nedodjeljen' ? <ListGroupItem header="Odjel">{this.state.data.departmentName}</ListGroupItem> : ""}
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

                    {
                        //DODJELJIVANJE ZAHTJEVA
                    }

                    <DodjelaZahtjeva data={this.state.data} />

                    {
                        //ESKALACIJA ZAHTJEVA
                    }

                    <EskalacijaZahtjeva data={this.state.data} />

                    {
                        //PORUKE
                    }

                    <Poruke Id={this.state.id} />

                </div>
            </div>
        );
    }
}

export default Zahtjev;