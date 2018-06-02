import React, { Component } from 'react';
import axios from 'axios';
import { FormControl, FormGroup, ControlLabel,Col, Row, Button } from 'react-bootstrap';

class DodjelaZahtjeva extends Component {

    state = {
        dName: '',
    }

    handleChange = (e) => {
        this.setState({ [e.target.name]: e.target.value });
    }

    assignReq = () => {
        axios.put("http://localhost:8080/requests/unassignedRequests_ByDepartments",
            {
                departmentName: this.state.dName,
                requestId: this.props.data.id,
                priority: null
            })
            .then(this.handleAssign(this))
            .catch(this.handleError(this));
    }

    handleAssign = (response) => {
        alert("Zahtjev je uspješno dodjeljen!");
        window.location = "/dashboard/zahtjevi/" + this.props.data.id;
    }

    handleError = (error) => {
        console.log(error);
    }

    preuzmi = () => {
        axios.put("http://localhost:8080/requests//unassignedRequests_ToResolvers",
            {
                requestId: this.props.data.id,
                username: sessionStorage.getItem("username")
            })
            .then(this.handlePreuzimanje(this))
            .catch(this.handleError(this));
    }

    handlePreuzimanje = () => {
        alert("Uspješno ste preuzeli zahtjev!");
        window.location = "/dashboard/zahtjevi/" + this.props.data.id;
    }

    render() {

        let role = sessionStorage.getItem("rola");
        console.log(this.props.data);

        return(
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
                                <br />
                            </Col>
                        </Row>
                        
                        <Row> 
                            <Col md={12} style={{ textAlign: "right" }}>
                            <br />
                                <Button type="submit" bsStyle="primary" className="btn-block btn-lg" bsSize="lg" onClick={this.assignReq}>Dodijeli</Button>
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

export default DodjelaZahtjeva;