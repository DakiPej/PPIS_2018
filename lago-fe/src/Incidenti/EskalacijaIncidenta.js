import React, { Component } from 'react';
import { Tab, Tabs, FormGroup, ControlLabel, PageHeader, Row, Panel, Col, FormControl, Button, PanelGroup, ListGroup, ListGroupItem, Glyphicon } from 'react-bootstrap';
import {Modal, ModalHeader, ModalBody, ModalFooter} from 'reactstrap';
import {
    PATH_BASE, PATH_INCIDENTS, PATH_ONE_INCIDENT, PATH_ESCALATION_ADMIN, PATH_ASSIGN_ADMIN,
    PATH_RESOLVE_INCIDENT, PATH_ASSIGN_RESOLVER, PATH_ESCALATION_RESOLVER,
    PATH_CLOSE_INCIDENT, PATH_INCIDENT_MESSAGE, PATH_GET_INCIDENT_MESSAGE, PATH_SEND_INCIDENT_MESSAGE
} from '../globals';
import axios from 'axios';


class EskalacijaIncidenta extends Component {

    constructor(props){
        super(props);
    }

    state = {
        data: this.props.data,
        show: false,
        show_esk:false,
        departmentName: ''
    }

    handleHide = () => {
        this.setState({ show: false });
    }

    escalationConfirm = (event) => {
        event.preventDefault();


        axios.post(PATH_BASE + PATH_INCIDENTS + PATH_ESCALATION_ADMIN, {
            escalation: true,
            id: this.props.data.id,
            departmentName: this.state.departmentName
        }
        )
            .then(this.confirmSuccess.bind(this))
            .catch(this.handleError.bind(this));

    }
    confirmSuccess = (response) => {
        this.setState({ show: false });
        alert("Eskalacija je prihvaćena");
        window.location = '/dashboard/incidenti/' + this.props.data.id;
    }

    promjenaStatusa = (event) => {
        event.preventDefault();
        axios.post(PATH_BASE + PATH_INCIDENTS + PATH_RESOLVE_INCIDENT, {
            id: this.props.data.id,
            username: sessionStorage.getItem("username")
        }
        )
            .then(this.resolveSucess.bind(this))
            .catch(this.handleError.bind(this));
    }

    resolveSucess = (response) => {
        alert("Incident je riješen");
        window.location = '/dashboard/incidenti/' + this.props.data.id;
    }

    suggestEscalation = (event) => {
        event.preventDefault();
        axios.post(PATH_BASE + PATH_INCIDENTS + PATH_ESCALATION_RESOLVER, {
            id: this.props.data.id,
            username: sessionStorage.getItem("username")
        }
        )
            .then(this.escalationSuccess.bind(this))
            .catch(this.handleError.bind(this));

    }

    escalationSuccess = (response) => {
        alert("Incident je eskaliran");
        window.location = '/dashboard/incidenti/' + this.props.data.id;
    }

    ponistiEskalaciju = (event) => {
        event.preventDefault();

        axios.post(PATH_BASE + PATH_INCIDENTS + PATH_ESCALATION_ADMIN, {
            escalation: false,
            id: this.props.data.id
        }
        )
            .then(this.cancelSuccess.bind(this))
            .catch(this.handleError.bind(this));

    }

    cancelSuccess = (response) => {
        alert("Eskalacija je poništena");
        window.location = '/dashboard/incidenti/' + this.props.data.id;
    }

    handleError = (error) => {
        console.log(error);
    }

    toggle = () =>{
        this.setState({
            show: !this.state.show
          });
    }

    toggle_esk = () =>{
        this.setState({
            show_esk: !this.state.show_esk
          });
    }

    render() {
        let role = sessionStorage.getItem("rola");
        
        return (
            <div>
                {role == 'Administrator' && this.props.data.escalation ? <div>
                    <Button bsStyle="primary"
                        bsSize="large" className=" btn-lg btn-block" onClick={this.ponistiEskalaciju}>Poništi eskalaciju</Button>
                    <br />
                    <Button className="btn-block btn-lg" bsStyle="primary" bsSize="large"
                        onClick={() => this.setState({ show_esk: true })} >
                        Eskaliraj
                    </Button>
                    <br />
                    <Modal
                        isOpen={this.state.show_esk}
                        toggle={this.toggle_esk}
                        aria-labelledby="contained-modal-title" >
                        <ModalHeader toggle={this.toggle_esk}>
                                Eskalacija
                        </ModalHeader>
                        <ModalBody>
                            <Row>
                                <FormGroup controlId="formZahtjev">
                                    <Col md={12}>
                                        <ControlLabel> Odaberite odjel </ControlLabel>
                                        <FormControl componentClass="select" name="departmentName" onChange={this.handleChange} placeholder="Odjel">
                                            <option value="Odjel1">Odjel1</option>
                                            <option value="Odjel2">Odjel2</option>
                                            <option value="Odjel3">Odjel3</option>
                                        </FormControl>  
                                    </Col>
                                </FormGroup>
                            </Row>
                        </ModalBody>
                        <ModalFooter>
                            <Button onClick={this.toggle_esk}>Close</Button>
                            <Button bsStyle="primary" onClick={this.escalationConfirm}>Eskaliraj</Button>
                        </ModalFooter>
                    </Modal>
                </div> : null}
                {role === 'Odjel' && (this.props.data.escalation === false || this.props.data.status === "u obradi") ?
                    <Panel bsStyle="info" id="collapsible-panel-example-2" defaultClosed>
                        <br />
                        <Row>
                            <br />
                            {this.props.data.status === "u obradi" ?
                                <Col md={6} style={{ textAlign: "right" }} xsOffset={3}>
                                    <Button type="submit" bsStyle="primary" className="btn-block btn-lg" onClick={this.promjenaStatusa} bsSize="lg">
                                        Riješen</Button>

                                    <br />
                                </Col> : null}
                        </Row>
                        <Row>
                            {this.props.data.escalation === false ? <Col md={6} xsOffset={3}>
                                <Button className="btn-block btn-lg" bsStyle="danger" bsSize="large"
                                    onClick={() => this.setState({ show: true })} >
                                    Predloži eskalaciju
                                </Button>
                                <br />
                            </Col> : null}
                        </Row>
                        <br />
                    </Panel> : null}
                {role === 'Odjel' && this.props.data.escalation === false ? <div>
                    <Modal
                        isOpen={this.state.show}
                        toggle={this.toggle}>
                        <ModalHeader toggle={this.toggle}>
                                Eskalacija
                        </ModalHeader>
                        <ModalBody>
                            Da li ste sigurni da želite predložiti eskalaciju?
                        </ModalBody>
                        <ModalFooter>
                            <Button onClick={this.toggle}>Ne</Button>
                            <Button bsStyle="primary" onClick={this.suggestEscalation}>Eskaliraj</Button>
                        </ModalFooter>
                    </Modal>
                </div> : null}
            </div>
        );
    }
}

export default EskalacijaIncidenta;