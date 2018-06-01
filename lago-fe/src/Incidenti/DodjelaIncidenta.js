import React, { Component } from 'react';
import {PATH_BASE, PATH_INCIDENTS, PATH_ASSIGN_ADMIN} from '../globals';
import { Tab, Tabs, Modal, FormGroup, ControlLabel, PageHeader, Row, Panel, Col, FormControl, Button, PanelGroup, ListGroup, ListGroupItem, Glyphicon } from 'react-bootstrap';
import axios from 'axios';

class DodjelaIncidenta extends Component{

    constructor(props){
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

    handleError = (error) => {
        console.log(error);
    }

    render(){
        let role = sessionStorage.getItem("rola");
        console.log(this.props.data);

        return(
            <div>
            {role == 'Administrator' && this.props.data.departmentName === '' ?
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
                            <option value="1">Nizak prioritet</option>
                            <option value="2">Srednji prioritet</option>
                            <option value="3">Visoki prioritet</option>
                        </FormControl>
                    </Col>
                </Row>
                <Row>
                    <Col md={12} style={{ textAlign: "right" }}>
                    <br/>
                        <Button type="submit" bsStyle="primary" className="pull-right" bsSize="lg" onClick={this.assignSuccess}>Dodijeli</Button>
                    </Col>
                </Row>
            </FormGroup>
            : ""}
            </div>
        );
    }
}

export default DodjelaIncidenta;
