import React, { Component } from 'react';
import { PageHeader, Row, Panel, Col } from 'react-bootstrap';

class Incident extends Component{

    constructor(props){
        super(props);
        this.state = {
            id:-1,
            naziv:'',
            korisnik:'',
            opis:'',
            prioritet:'',
            status:'',
            odjel:null,
            datum:null
        };
    }

    render(){

        var test = {
            id:111,
            naziv:'Naziv incidenta',
            korisnik:'Korisnik Korisniković',
            opis:'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.',
            prioritet:2,
            status:'U obradi',
            odjel:'Odjel 1',
            datumprijave:'11/11/11',
            datumrjesenja:'N/A',
        };

        return(
            <Panel bsStyle="primary" style={{background:"#babec4"}}>
                <Panel.Heading> 
                    <Panel.Heading componentClass="h2">{test.naziv}
                    <small>
                        #{test.id}
                    </small>
                    </Panel.Heading> 
                </Panel.Heading>
                <div style={{padding:"1%"}}>
                <Panel bsStyle="info">
                    <Panel.Heading>
                        <Panel.Title componentClass="h4">Korisnik</Panel.Title>
                    </Panel.Heading>
                    <Panel.Body>{test.korisnik}</Panel.Body>
                </Panel>
                <Row>
                    <Col md={3}>
                        <Panel bsStyle="info">
                            <Panel.Heading>
                                <Panel.Title componentClass="h4">Odjel</Panel.Title>
                            </Panel.Heading>
                            <Panel.Body>{test.odjel}</Panel.Body>
                        </Panel>
                    </Col>
                    <Col md={3}>
                        <Panel  bsStyle="success">
                            <Panel.Heading>
                                <Panel.Title componentClass="h4">Datum prijave</Panel.Title>
                            </Panel.Heading>
                            <Panel.Body>{test.datumprijave}</Panel.Body>
                        </Panel>
                    </Col>
                    <Col md={3}>
                        <Panel  bsStyle="info">
                            <Panel.Heading>
                                <Panel.Title componentClass="h4">Datum rješenja</Panel.Title>
                            </Panel.Heading>
                            <Panel.Body>{test.datumrjesenja}</Panel.Body>
                        </Panel>
                    </Col>
                    <Col md={3}>
                        <Panel bsStyle="success">
                            <Panel.Heading>
                                <Panel.Title componentClass="h5">Prioritet</Panel.Title>
                            </Panel.Heading>
                            <Panel.Body>{test.prioritet}</Panel.Body>
                        </Panel>
                    </Col>
                </Row>
                <Panel>
                    <Panel.Heading>
                        <Panel.Title componentClass="h5">Opis</Panel.Title>
                    </Panel.Heading>
                    <Panel.Body>{test.opis}</Panel.Body>
                </Panel>
                </div>
            </Panel>
        );
    }

}

export default Incident;