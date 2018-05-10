import React, { Component } from 'react';
import { PageHeader, Row, Panel, Col, FormControl, Button, PanelGroup } from 'react-bootstrap';

class Zahtjev extends Component{

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
            naziv:'Naziv zahtjeva',
            korisnik:'Korisnik Korisniković',
            opis:'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.',
            hitnost:2,
            status:'U obradi',
            kontakt:'E-mail',
            datumprijave:'11/11/11',
            datumrjesenja:'N/A',
        };

        return(
            <Panel bsStyle="primary">
                <Panel.Heading>
                    <Panel.Heading componentClass="h2">{test.naziv}
                    <small>
                        #{test.id}
                    </small>
                    </Panel.Heading>
                </Panel.Heading>
                <div style={{padding:"1%", overflowY:"scroll", maxHeight:"60vh"}}>
                <Panel bsStyle="info">
                    <Panel.Heading>
                        <Panel.Title componentClass="h5">Korisnik</Panel.Title>
                    </Panel.Heading>
                    <Panel.Body>{test.korisnik}</Panel.Body>
                </Panel>
                <Row>
                    <Col md={2}>
                        <Panel bsStyle="info">
                            <Panel.Heading>
                                <Panel.Title componentClass="h5">Kontakt</Panel.Title>
                            </Panel.Heading>
                            <Panel.Body>{test.kontakt}</Panel.Body>
                        </Panel>
                    </Col>
                    <Col md={3}>
                        <Panel bsStyle="success">
                            <Panel.Heading>
                                <Panel.Title componentClass="h5">Hitnost</Panel.Title>
                            </Panel.Heading>
                            <Panel.Body>{test.hitnost}</Panel.Body>
                        </Panel>
                    </Col>
                    <Col md={3}>
                        <Panel  bsStyle="info">
                            <Panel.Heading>
                                <Panel.Title componentClass="h5">Datum K</Panel.Title>
                            </Panel.Heading>
                            <Panel.Body>{test.datumprijave}</Panel.Body>
                        </Panel>
                    </Col>
                    <Col md={2}>
                        <Panel  bsStyle="success">
                            <Panel.Heading>
                                <Panel.Title componentClass="h6">Datum R</Panel.Title>
                            </Panel.Heading>
                            <Panel.Body>{test.datumprijave}</Panel.Body>
                        </Panel>
                    </Col>
                    <Col md={2}>
                        <Panel bsStyle="success">
                            <Panel.Heading>
                                <Panel.Title componentClass="h5">Status</Panel.Title>
                            </Panel.Heading>
                            <Panel.Body>{test.status}</Panel.Body>
                        </Panel>
                    </Col>
                </Row>
                <PanelGroup accordion>
                <Panel eventKey="1">
                    <Panel.Heading>
                        <Panel.Title componentClass="h5"  >Opis</Panel.Title>
                    </Panel.Heading>
                    <Panel.Body >{test.opis}</Panel.Body>
                </Panel>
                <Panel bsStyle="info" eventKey="2">
                    <Panel.Heading>
                        <Panel.Title componentClass="h3" >Poruke</Panel.Title>
                    </Panel.Heading>
                    <Panel.Body >
                        <FormControl componentClass="textarea" placeholder="Poruka" />
                        <br/>
                        <Button bsStyle="info" bsSize="lg">Pošalji</Button>
                    </Panel.Body>
                </Panel>
                </PanelGroup>
                </div>
            </Panel>

        );
    }

}

export default Zahtjev;
