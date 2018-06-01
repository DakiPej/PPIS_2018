import React, { Component } from 'react';
import { PATH_BASE, PATH_INCIDENTS, PATH_ASSIGN_ADMIN } from '../globals';
import { Tab, Tabs, Modal, FormGroup, ControlLabel, PageHeader, Row, Panel, Col, FormControl, Button, PanelGroup, ListGroup, ListGroupItem, Glyphicon, ButtonToolbar } from 'react-bootstrap';
import axios from 'axios';

class DodjelaIncidenta extends Component {

    constructor(props) {
        super(props);
    }

    state = {
        dName: '',
        priority: ''
    }


    handleChange = (e) => {
        this.setState({ [e.target.name]: e.target.value });
    }

    assignSuccess = (event) => {
        event.preventDefault();
        console.log(this.state.dName + this.state.priority);
        axios.post(PATH_BASE + PATH_INCIDENTS + PATH_ASSIGN_ADMIN, {
            adminUsername: sessionStorage.getItem("username"),
            departmentName: this.state.dName,
            id: this.props.Id,
            priority: this.state.priority
        }
        )
            .then(this.confirmAssing.bind(this))
            .catch(this.handleError.bind(this));

    }

    confirmAssing = () => {
        alert("Uspješna dodjela incidenta.");
        window.location = '/dashboard/incidenti/' + this.props.Id;
    }

    preuzmi = () => {
        console.log("OKE");
        console.log(sessionStorage.getItem("username"));
        axios.put("http://localhost:8080/incident/resolverPick",
            {
                username: sessionStorage.getItem("username"),
                incidentId: this.props.data.id,
                resolved: false
            })
            .then(() => {
                alert("Uspjesno ste preuzeli incident!");
                window.location = "/dashboard";
            })
            .catch(this.handleError(this));
    }

    handleError = (error) => {
        console.log(error);
    }

    render() {
        let role = sessionStorage.getItem("rola");
        console.log(this.props.data);

        return (
            <div>
                {role === 'Administrator' && this.props.data.departmentName === '' ?
                    <FormGroup>
                        <Row>
                            <Col md={12}>
                                <ControlLabel> Odaberite odjel </ControlLabel>
                                <FormControl componentClass="select" name="dName" onChange={this.handleChange} placeholder="Odjel">
                                    <option value="" selected disabled>Odjel</option>
                                    <option value="Odjel1">Odjel 1</option>
                                    <option value="Odjel2">Odjel 2</option>
                                    <option value="Odjel3">Odjel 3</option>
                                    <option value="Odjel4">Odjel 4</option>
                                </FormControl>
                                <br />
                                <ControlLabel> Odaberite prioritet </ControlLabel>
                                <FormControl componentClass="select" name="priority" onChange={this.handleChange} placeholder="Prioritet">
                                    <option value="" selected disabled>Prioritet</option>
                                    <option value="1">Nizak</option>
                                    <option value="2">Srednji</option>
                                    <option value="3">Visok</option>
                                </FormControl>
                            </Col>
                        </Row>
                        <Row>
                            <Col md={12} style={{ textAlign: "right" }}>
                                <Button type="submit" bsStyle="primary" className="btn-block btn-lg" bsSize="lg" onClick={this.assignSuccess}>Dodijeli</Button>
                            </Col>
                        </Row>
                    </FormGroup>
                    : null}
                {role === 'Odjel' && this.props.data.status === "nedodijeljen" ?
                    <Button bsStyle="primary" className="btn-block btn-lg" onClick={this.preuzmi}> Preuzmi </Button> : null
                }
            </div>
        );
    }
}

export default DodjelaIncidenta;