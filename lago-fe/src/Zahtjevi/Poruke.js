import React, { Component } from 'react';
import { Tab, Tabs, Modal, FormGroup, ControlLabel, PageHeader, Row, Panel, Col, FormControl, Button, PanelGroup, ListGroup, ListGroupItem, Glyphicon } from 'react-bootstrap';
import { Collapse } from 'reactstrap';

import {
    PATH_BASE, PATH_INCIDENTS, PATH_ONE_INCIDENT, PATH_ESCALATION_ADMIN, PATH_ASSIGN_ADMIN,
    PATH_RESOLVE_INCIDENT, PATH_ASSIGN_RESOLVER, PATH_ESCALATION_RESOLVER,
    PATH_CLOSE_INCIDENT, PATH_INCIDENT_MESSAGE, PATH_GET_INCIDENT_MESSAGE, PATH_SEND_INCIDENT_MESSAGE
} from '../globals';

import axios from 'axios';

class Poruke extends Component {

    state = {
        id: this.props.Id,
        openPoruke: false,
        openNovaPoruka: false,
        poruke: [],
        receiver: 'odjel'
    }

    togglePoruke = () => {
        this.setState({
            openPoruke: !this.state.openPoruke
        });
    }

    toggleNova = () => {
        this.setState({
            openNovaPoruka: !this.setState.openNovaPoruka
        });
    }

    handleChange = (e) => {
        this.setState({ [e.target.name]: e.target.value });
    }

    handleError = (error) => {
        console.log(error);
    }

    getMessages = () => {
        axios.get("http://localhost:8080/requestMessages/getAll", {
            params: {
                userId: sessionStorage.getItem("id")
            }
        }
        )
            .then(this.handleSuccessMessage.bind(this))
            .catch(this.handleError.bind(this));

    }

    handleSuccessMessage = (response) => {
        console.log(response.data);
        var id = this.props.Id;
        console.log(id);
        var poruke = response.data.filter(poruka => poruka.incident.id == id);

        this.setState({ poruke: poruke });
        console.log(poruke);
    }

    sendMessage = (event) => {
        event.preventDefault();
        console.log(this.state.receiver);
        axios.post("http://localhost:8080/requestMessages/send", {
            message: this.state.message,
            requestOrIncidentId: this.props.Id,
            senderRole: sessionStorage.getItem("rola").toLowerCase(),
            receiverRole: this.state.receiver,
            username: sessionStorage.getItem("username")
        }
        )
            .then(this.handleSuccessSendMessage.bind(this))
            .catch(this.handleError.bind(this));

    }
    handleSuccessSendMessage = () => {
        alert("Uspješno ste poslali poruku.");
        this.getMessages();
    }

    render() {

        let role = sessionStorage.getItem("rola");

        var listItems = <div></div>;
        var listItems2 = <div></div>;
        var poruke = this.state.poruke;
        if (role === 'Odjel') {
            listItems = poruke.filter((poruka => poruka.receiver.userType.typeName === 'Administrator' ||
                poruka.sender.userType.typeName === 'Administrator')).map((poruka) =>
                    <ListGroupItem header={poruka.message}> <div class="d-flex w-100 justify-content-between">
                        <h6 class="mb-2">{poruka.sender.username}</h6>
                        <small>{poruka.date}</small>
                    </div></ListGroupItem>
                );
            listItems2 = poruke.filter((poruka => poruka.receiver.userType.typeName === 'Korisnik' ||
                poruka.sender.userType.typeName === 'Korisnik')).map((poruka) =>
                    <ListGroupItem header={poruka.message}> <div class="d-flex w-100 justify-content-between">
                        <h6 class="mb-2">{poruka.sender.username}</h6>
                        <small>{poruka.date}</small>
                    </div></ListGroupItem>
                );
        }
        else {
            listItems = poruke.map((poruka) =>
                <ListGroupItem header={poruka.message}> <div class="d-flex w-100 justify-content-between">
                    <h6 class="mb-2">{poruka.sender.username}</h6>
                    <small>{poruka.date}</small>
                </div></ListGroupItem>
            );
        }

        return (
            <div>
                <Panel bsStyle="info" >
                    <Panel.Heading>
                        <Panel.Title onClick={this.togglePoruke}>Poruke</Panel.Title>
                    </Panel.Heading>
                    <Collapse isOpen={this.state.openPoruke}>
                        <Panel.Body >
                            {role === "Odjel" ?
                                <Tabs defaultActiveKey={2} id="uncontrolled-tab-example">
                                    <Tab eventKey={1} title="Administrator">
                                        <ListGroup className="list-group-flush">
                                            {listItems}
                                        </ListGroup>
                                    </Tab>
                                    <Tab eventKey={2} title="Korisnik">
                                        <ListGroup className="list-group-flush">
                                            {listItems2}
                                        </ListGroup>
                                    </Tab>
                                </Tabs>
                                :
                                <ListGroup className="list-group-flush">
                                    {listItems}
                                </ListGroup>
                            }

                        </Panel.Body>
                        <div style={{ padding: "2.5%" }}>
                            <Panel>
                                <Panel.Heading>
                                    <Panel.Title onClick={this.toggleNova} >Nova poruka</Panel.Title>
                                </Panel.Heading>
                                <Collapse isOpen={this.state.openNovaPoruka}>
                                    <Panel.Body>
                                        <FormGroup controlId="formZahtjev">
                                            <ControlLabel> Primalac: {role != 'Odjel' ? "Odjel" : null} </ControlLabel>
                                            {role === "Odjel" ?
                                                <FormControl name="receiver" componentClass="select" onChange={this.handleChange} placeholder="Primalac">
                                                    <option value="" selected disabled>Primalac</option>
                                                    <option value="korisnik">Korisnik</option>
                                                    <option value="administrator">Administrator</option>
                                                </FormControl>
                                                : null}
                                            <br />
                                            <ControlLabel> Tekst poruke: </ControlLabel>
                                            <FormControl name="message" onChange={this.handleChange} componentClass="textarea" placeholder="Poruka" />
                                        </FormGroup>
                                        <br />
                                        <Button bsStyle="info" onClick={this.sendMessage} bsSize="lg" className="pull-right">Pošalji</Button>
                                    </Panel.Body>
                                </Collapse>
                            </Panel>
                        </div>
                    </Collapse>
                </Panel>
            </div>
        );
    }

}

export default Poruke;