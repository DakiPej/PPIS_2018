import React, { Component } from 'react';
import { PageHeader, Modal, Tabs, Tab, Row, Panel, Col, FormControl, ControlLabel, FormGroup, Button, PanelGroup, ListGroup, ListGroupItem, Glyphicon } from 'react-bootstrap';

class Zahtjev extends Component {

    constructor(props) {
        super(props);
        this.state = {
            id: -1,
            naziv: '',
            korisnik: '',
            rjesava: '',
            opis: '',
            prioritet: '',
            status: '',
            odjel: null,
            datum: null,
            poruke: null,
            show: false
        };
        this.handleHide = this.handleHide.bind(this);
    }
    handleHide() {
        this.setState({ show: false });
    }

    render() {

        var test = {
            id: 111,
            naziv: 'Naziv zahtjeva',
            korisnik: 'Korisnik Korisniković',
            rjesava: null,
            opis: 'Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industrys standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.',
            hitnost: 2,
            status: 'U obradi',
            kontakt: 'E-mail',
            datumprijave: '11/11/11',
            datumrjesenja: 'N/A',
            odjel: 'Odjel 1',
            eskalacija: 'Da'
        };
        var poruke = [{
            autor: "Admin", datum: "11/11/11", tekst: "Nesto"
        }, {
            autor: "user", datum: "11/11/11", tekst: "Odgovor na Nesto"
        }, {
            autor: "Admin", datum: "11/11/11", tekst: "Dosadan si"
        }];
        var poruke2 = [{
            autor: "user", datum: "11/11/11", tekst: "Odgovor na Nesto"
        }, {
            autor: "Admin", datum: "11/11/11", tekst: "......."
        }];
        const listItems = poruke.map((poruka) =>
            <ListGroupItem header={poruka.tekst}> <div class="d-flex w-100 justify-content-between">
                <h6 class="mb-2">{poruka.autor}</h6>
                <small>{poruka.datum}</small>
            </div></ListGroupItem>
        );
        const listItems2 = poruke2.map((poruka) =>
            <ListGroupItem header={poruka.tekst}> <div class="d-flex w-100 justify-content-between">
                <h6 class="mb-2">{poruka.autor}</h6>
                <small>{poruka.datum}</small>
            </div></ListGroupItem>
        );
        var table = <div></div>;
        var role = sessionStorage.getItem("rola");
        switch (role) {
            case 'Administrator':
                if (test.odjel === null)
                    table = <div style={{ padding: "1%", overflowY: "scroll", maxHeight: "60vh" }}>
                        <Row>
                            <Col md={3}>
                                <Panel bsStyle="info">
                                    <Panel.Heading>
                                        <Panel.Title componentClass="h5">Korisnik</Panel.Title>
                                    </Panel.Heading>
                                    <Panel.Body>{test.korisnik}</Panel.Body>
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
                                <Panel bsStyle="info">
                                    <Panel.Heading>
                                        <Panel.Title componentClass="h5">Datum kreiranja</Panel.Title>
                                    </Panel.Heading>
                                    <Panel.Body>{test.datumprijave}</Panel.Body>
                                </Panel>
                            </Col>
                            <Col md={3}>
                                <Panel bsStyle="success">
                                    <Panel.Heading>
                                        <Panel.Title componentClass="h5">Kontakt</Panel.Title>
                                    </Panel.Heading>
                                    <Panel.Body>{test.kontakt}</Panel.Body>
                                </Panel>
                            </Col>

                        </Row>
                        <Panel id="collapsible-panel-example-2" defaultClosed>
                            <Panel.Heading>
                                <Panel.Title toggle componentClass="h5">
                                    Opis
                  </Panel.Title>
                            </Panel.Heading>
                            <Panel.Collapse>
                                <Panel.Body>{test.opis}</Panel.Body>
                            </Panel.Collapse>
                        </Panel>
                        <Panel bsStyle="info" id="collapsible-panel-example-2" defaultClosed>
                            <Row>
                                <FormGroup controlId="formZahtjev">
                                    <Col md={12}>
                                        <ControlLabel> Odaberite odjel </ControlLabel>
                                        <FormControl componentClass="select" placeholder="Odjel">
                                            <option value="Odjel 1">Odjel 1</option>
                                            <option value="Odjel 2">Odjel 2</option>
                                            <option value="Odjel 3">Odjel 3</option>
                                            <option value="Odjel 4">Odjel 4</option>
                                        </FormControl>
                                        <br />
                                        <ControlLabel> Odaberite prioritet </ControlLabel>
                                        <FormControl componentClass="select" placeholder="Prioritet">
                                            <option value="1">1</option>
                                            <option value="2">2</option>
                                            <option value="3">3</option>
                                            <option value="4">4</option>
                                            <option value="5">5</option>
                                            <option value="6">6</option>
                                            <option value="7">7</option>
                                            <option value="8">8</option>
                                            <option value="9">9</option>
                                            <option value="10">10</option>
                                        </FormControl>
                                    </Col>
                                </FormGroup>
                            </Row>
                            <br />
                            <Row>
                                <Col md={12} style={{ textAlign: "right" }}>
                                    <Button type="submit" bsStyle="primary" className="pull-right" bsSize="lg">Dodijeli</Button>
                                </Col>
                            </Row>
                            <br />
                        </Panel>
                    </div>;
                else {
                    table = <div style={{ padding: "1%", overflowY: "scroll", maxHeight: "60vh" }}>
                        <Row>
                            <Col md={3}>
                                <Panel bsStyle="info">
                                    <Panel.Heading>
                                        <Panel.Title componentClass="h5">Prijavio</Panel.Title>
                                    </Panel.Heading>
                                    <Panel.Body>{test.korisnik}</Panel.Body>
                                </Panel>
                            </Col>
                            <Col md={3}>
                                <Panel bsStyle="success">
                                    <Panel.Heading>
                                        <Panel.Title componentClass="h5">Rješava</Panel.Title>
                                    </Panel.Heading>
                                    <Panel.Body>{test.korisnik}</Panel.Body>
                                </Panel>
                            </Col>
                            <Col md={3}>
                                <Panel bsStyle="info">
                                    <Panel.Heading>
                                        <Panel.Title componentClass="h5">Hitnost</Panel.Title>
                                    </Panel.Heading>
                                    <Panel.Body>{test.hitnost}</Panel.Body>
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
                            <Col md={3}>
                                <Panel bsStyle="info">
                                    <Panel.Heading>
                                        <Panel.Title componentClass="h5">Datum kreiranja</Panel.Title>
                                    </Panel.Heading>
                                    <Panel.Body>{test.datumprijave}</Panel.Body>
                                </Panel>
                            </Col>
                            <Col md={3}>
                                <Panel bsStyle="success">
                                    <Panel.Heading>
                                        <Panel.Title componentClass="h5">Datum rješavanja</Panel.Title>
                                    </Panel.Heading>
                                    <Panel.Body>{test.datumprijave}</Panel.Body>
                                </Panel>
                            </Col>
                            <Col md={3}>
                                <Panel bsStyle="info">
                                    <Panel.Heading>
                                        <Panel.Title componentClass="h5">Datum zatvaranja</Panel.Title>
                                    </Panel.Heading>
                                    <Panel.Body>{test.datumprijave}</Panel.Body>
                                </Panel>
                            </Col>
                            <Col md={3}>
                                <Panel bsStyle="success">
                                    <Panel.Heading>
                                        <Panel.Title componentClass="h5">Odjel</Panel.Title>
                                    </Panel.Heading>
                                    <Panel.Body>{test.odjel}</Panel.Body>
                                </Panel>
                            </Col>
                            <Col md={3}>
                                <Panel bsStyle="info">
                                    <Panel.Heading>
                                        <Panel.Title componentClass="h5">Status</Panel.Title>
                                    </Panel.Heading>
                                    <Panel.Body>{test.status}</Panel.Body>
                                </Panel>
                            </Col>
                            <Col md={3}>
                                <Panel bsStyle="success">
                                    <Panel.Heading>
                                        <Panel.Title componentClass="h5">Eskalacija</Panel.Title>
                                    </Panel.Heading>
                                    <Panel.Body>{test.eskalacija}</Panel.Body>
                                </Panel>
                            </Col>
                            <Col md={3}>
                                <Panel bsStyle="info">
                                    <Panel.Heading>
                                        <Panel.Title componentClass="h5">Kontakt</Panel.Title>
                                    </Panel.Heading>
                                    <Panel.Body>{test.kontakt}</Panel.Body>
                                </Panel>
                            </Col>
                            {test.eskalacija === 'Da' ? <Col md={3}>
                                <Button bsStyle="primary"
                                    bsSize="large" className=" btn-lg btn-block">Poništi eskalaciju</Button>
                                <br />
                                <Button className="btn-block btn-lg" bsStyle="primary" bsSize="large"
                                    onClick={() => this.setState({ show: true })} >
                                    Eskaliraj
                    </Button>
                                <br />
                            </Col> : null}
                        </Row>
                        <Panel id="collapsible-panel-example-2" defaultClosed>
                            <Panel.Heading>
                                <Panel.Title toggle componentClass="h5">
                                    Opis
                     </Panel.Title>
                            </Panel.Heading>
                            <Panel.Collapse>
                                <Panel.Body>{test.opis}</Panel.Body>
                            </Panel.Collapse>
                        </Panel>
                        <Panel bsStyle="info" id="collapsible-panel-example-2" defaultClosed>
                            <Panel.Heading>
                                <Panel.Title toggle componentClass="h3" >Poruke</Panel.Title>
                            </Panel.Heading>
                            <Panel.Collapse>
                                <Panel.Body >

                                    <ListGroup className="list-group-flush">
                                        {listItems}
                                    </ListGroup>

                                </Panel.Body>
                                <div style={{ padding: "2.5%" }}>
                                    <Panel id="collapsible-panel-example-2" defaultClosed>
                                        <Panel.Heading>
                                            <Panel.Title toggle componentClass="h3" >Nova poruka</Panel.Title>
                                        </Panel.Heading>
                                        <Panel.Collapse>
                                            <Panel.Body>
                                                <FormGroup controlId="formZahtjev">
                                                    <ControlLabel> Primalac: </ControlLabel>
                                                    <FormControl componentClass="select" placeholder="Odjel">
                                                        <option value="Korisnik">Korisnik</option>
                                                        <option value="Odjel">Odjel</option>
                                                    </FormControl>
                                                    <br />
                                                    <ControlLabel> Tekst poruke: </ControlLabel>
                                                    <FormControl componentClass="textarea" placeholder="Poruka" />
                                                </FormGroup>
                                                <br />
                                                <Button bsStyle="info" bsSize="lg" className="pull-right">Pošalji</Button>
                                            </Panel.Body>
                                        </Panel.Collapse>
                                    </Panel>
                                </div>
                            </Panel.Collapse>
                        </Panel>
                        {test.eskalacija === 'Da' ? <div>
                            <Modal
                                show={this.state.show}
                                onHide={this.handleHide}
                                aria-labelledby="contained-modal-title" >
                                <Modal.Header >
                                    <Modal.Title id="contained-modal-title" >
                                        Eskalacija
                                </Modal.Title>
                                </Modal.Header>
                                <Modal.Body>
                                    <Row>
                                        <FormGroup controlId="formZahtjev">
                                            <Col md={12}>
                                                <ControlLabel> Odaberite odjel </ControlLabel>
                                                <FormControl componentClass="select" placeholder="Odjel">
                                                    <option value="Odjel 1">Odjel 1</option>
                                                    <option value="Odjel 2">Odjel 2</option>
                                                    <option value="Odjel 3">Odjel 3</option>
                                                    <option value="Odjel 4">Odjel 4</option>
                                                </FormControl>
                                            </Col>
                                        </FormGroup>
                                    </Row>
                                </Modal.Body>
                                <Modal.Footer>
                                    <Button onClick={this.handleHide}>Close</Button>
                                    <Button bsStyle="primary">Eskaliraj</Button>
                                </Modal.Footer>
                            </Modal>
                        </div> : null}
                    </div>;
                }
                break;
            case 'Korisnik':
                table = <div style={{ padding: "1%", overflowY: "scroll", maxHeight: "60vh" }}>
                    <Row>
                        <Col md={2}>
                            <Panel bsStyle="info">
                                <Panel.Heading>
                                    <Panel.Title componentClass="h5">Kontakt</Panel.Title>
                                </Panel.Heading>
                                <Panel.Body>{test.kontakt}</Panel.Body>
                            </Panel>
                        </Col>
                        <Col md={2}>
                            <Panel bsStyle="success">
                                <Panel.Heading>
                                    <Panel.Title componentClass="h5">Hitnost</Panel.Title>
                                </Panel.Heading>
                                <Panel.Body>{test.hitnost}</Panel.Body>
                            </Panel>
                        </Col>
                        <Col md={3}>
                            <Panel bsStyle="info">
                                <Panel.Heading>
                                    <Panel.Title componentClass="h5">Datum kreiranja</Panel.Title>
                                </Panel.Heading>
                                <Panel.Body>{test.datumprijave}</Panel.Body>
                            </Panel>
                        </Col>
                        <Col md={3}>
                            <Panel bsStyle="success">
                                <Panel.Heading>
                                    <Panel.Title componentClass="h5">Datum rješavanja</Panel.Title>
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
                    <Panel id="collapsible-panel-example-2" defaultClosed>
                        <Panel.Heading>
                            <Panel.Title toggle componentClass="h5">
                                Opis
                  </Panel.Title>
                        </Panel.Heading>
                        <Panel.Collapse>
                            <Panel.Body>{test.opis}</Panel.Body>
                        </Panel.Collapse>
                    </Panel>
                    <Panel bsStyle="info" id="collapsible-panel-example-2" defaultClosed>
                        <Panel.Heading>
                            <Panel.Title toggle componentClass="h3" >Poruke</Panel.Title>
                        </Panel.Heading>
                        <Panel.Collapse>
                            <Panel.Body >
                                <ListGroup className="list-group-flush">
                                    {listItems}
                                </ListGroup>
                            </Panel.Body>
                            <div style={{ padding: "2.5%" }}>
                                <Panel id="collapsible-panel-example-2" defaultClosed>
                                    <Panel.Heading>
                                        <Panel.Title toggle componentClass="h3" >Nova poruka</Panel.Title>
                                    </Panel.Heading>
                                    <Panel.Collapse>
                                        <Panel.Body>
                                            <FormControl componentClass="textarea" placeholder="Poruka" />
                                            <br />
                                            <Button bsStyle="info" bsSize="lg" className="pull-right">Pošalji</Button>
                                        </Panel.Body>
                                    </Panel.Collapse>
                                </Panel>
                            </div>
                        </Panel.Collapse>
                    </Panel>
                </div>;
                break;
            case 'Odjel':
                if (test.rjesava === null)
                    table = <div style={{ padding: "1%", overflowY: "scroll", maxHeight: "60vh" }}>
                        <Row>
                            <Col md={4}>
                                <Panel bsStyle="info">
                                    <Panel.Heading>
                                        <Panel.Title componentClass="h5">Korisnik</Panel.Title>
                                    </Panel.Heading>
                                    <Panel.Body>{test.korisnik}</Panel.Body>
                                </Panel>
                            </Col>
                            <Col md={4}>
                                <Panel bsStyle="success">
                                    <Panel.Heading>
                                        <Panel.Title componentClass="h5">Prioritet</Panel.Title>
                                    </Panel.Heading>
                                    <Panel.Body>{test.prioritet}</Panel.Body>
                                </Panel>
                            </Col>
                            <Col md={4}>
                                <Panel bsStyle="info">
                                    <Panel.Heading>
                                        <Panel.Title componentClass="h5">Datum kreiranja</Panel.Title>
                                    </Panel.Heading>
                                    <Panel.Body>{test.datumprijave}</Panel.Body>
                                </Panel>
                            </Col>
                        </Row>
                        <Panel id="collapsible-panel-example-2" defaultExpanded>
                            <Panel.Heading>
                                <Panel.Title toggle componentClass="h5">
                                    Opis
                </Panel.Title>
                            </Panel.Heading>
                            <Panel.Collapse>
                                <Panel.Body>{test.opis}</Panel.Body>
                            </Panel.Collapse>
                        </Panel>
                        <Row>
                            <Col md={12} style={{ textAlign: "right" }}>
                                <Button type="submit" bsStyle="primary" className="pull-right" bsSize="lg">Preuzmi</Button>
                            </Col>
                        </Row>
                        <br />
                    </div>;
                else {
                    table = <div style={{ padding: "1%", overflowY: "scroll", maxHeight: "60vh" }}>
                        <Row>
                            <Col md={3}>
                                <Panel bsStyle="info">
                                    <Panel.Heading>
                                        <Panel.Title componentClass="h5">Prijavio</Panel.Title>
                                    </Panel.Heading>
                                    <Panel.Body>{test.korisnik}</Panel.Body>
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

                            <Col md={3}>
                                <Panel bsStyle="info">
                                    <Panel.Heading>
                                        <Panel.Title componentClass="h5">Status</Panel.Title>
                                    </Panel.Heading>
                                    <Panel.Body>{test.status}</Panel.Body>
                                </Panel>
                            </Col>
                            <Col md={3}>
                                <Panel bsStyle="success">
                                    <Panel.Heading>
                                        <Panel.Title componentClass="h5">Eskalacija</Panel.Title>
                                    </Panel.Heading>
                                    <Panel.Body>{test.eskalacija}</Panel.Body>
                                </Panel>
                            </Col>
                            <Col md={3}>
                                <Panel bsStyle="info">
                                    <Panel.Heading>
                                        <Panel.Title componentClass="h5">Datum kreiranja</Panel.Title>
                                    </Panel.Heading>
                                    <Panel.Body>{test.datumprijave}</Panel.Body>
                                </Panel>
                            </Col>
                            <Col md={3}>
                                <Panel bsStyle="success">
                                    <Panel.Heading>
                                        <Panel.Title componentClass="h5">Datum rješavanja</Panel.Title>
                                    </Panel.Heading>
                                    <Panel.Body>{test.datumprijave}</Panel.Body>
                                </Panel>
                            </Col>
                        </Row>
                        <Panel id="collapsible-panel-example-2" defaultExpanded>
                            <Panel.Heading>
                                <Panel.Title toggle componentClass="h5">
                                    Opis
                  </Panel.Title>
                            </Panel.Heading>
                            <Panel.Collapse>
                                <Panel.Body>{test.opis}</Panel.Body>
                            </Panel.Collapse>
                        </Panel>
                        <Panel bsStyle="info" id="collapsible-panel-example-2" defaultClosed>
                            <Row>
                                <FormGroup controlId="formZahtjev">
                                    <Col md={12}>
                                        <ControlLabel> Promjenite status</ControlLabel>
                                        <FormControl componentClass="select" placeholder="Odjel">
                                            <option value="Poslano">Poslano</option>
                                            <option value="U obradi">U obradi</option>
                                            <option value="Rješeno">Rješeno</option>
                                        </FormControl>
                                    </Col>
                                </FormGroup>
                            </Row>
                            <br />
                            <Row>
                                <Col md={6} style={{ textAlign: "right" }} xsOffset={3}>
                                    <Button type="submit" bsStyle="primary" className="btn-block btn-lg" bsSize="lg">Promijeni</Button>

                                    <br />
                                </Col>
                            </Row>
                            <Row>
                                {test.eskalacija === 'Ne' ? <Col md={6} xsOffset={3}>
                                    <Button className="btn-block btn-lg" bsStyle="danger" bsSize="large"
                                        onClick={() => this.setState({ show: true })} >
                                        Predloži eskalaciju
                                    </Button>
                                    <br />
                                </Col> : null}
                            </Row>
                            <br />
                        </Panel>

                        <Panel bsStyle="info" id="collapsible-panel-example-2" defaultClosed>
                            <Panel.Heading>
                                <Panel.Title toggle componentClass="h3" >Poruke</Panel.Title>
                            </Panel.Heading>
                            <Panel.Collapse>
                                <Panel.Body >
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

                                </Panel.Body>
                                <div style={{ padding: "2.5%" }}>
                                    <Panel id="collapsible-panel-example-2" defaultClosed>
                                        <Panel.Heading>
                                            <Panel.Title toggle componentClass="h3" >Nova poruka</Panel.Title>
                                        </Panel.Heading>
                                        <Panel.Collapse>
                                            <Panel.Body>
                                                <FormGroup controlId="formZahtjev">
                                                    <ControlLabel> Primalac: </ControlLabel>
                                                    <FormControl componentClass="select" placeholder="Odjel">
                                                        <option value="Korisnik">Korisnik</option>
                                                        <option value="Administrator">Odjel</option>
                                                    </FormControl>
                                                    <br />
                                                    <ControlLabel> Tekst poruke: </ControlLabel>
                                                    <FormControl componentClass="textarea" placeholder="Poruka" />
                                                </FormGroup>
                                                <br />
                                                <Button bsStyle="info" bsSize="lg" className="pull-right">Pošalji</Button>
                                            </Panel.Body>
                                        </Panel.Collapse>
                                    </Panel>
                                </div>
                            </Panel.Collapse>
                        </Panel>
                        {test.eskalacija === 'Ne' ? <div>
                            <Modal
                                show={this.state.show}
                                onHide={this.handleHide}
                                aria-labelledby="contained-modal-title" >
                                <Modal.Header >
                                    <Modal.Title id="contained-modal-title" >
                                        Eskalacija
                                    </Modal.Title>
                                </Modal.Header>
                                <Modal.Body>
                                    Da li ste sigurni da želite predložiti eskalaciju?
                                </Modal.Body>
                                <Modal.Footer>
                                    <Button onClick={this.handleHide}>Ne</Button>
                                    <Button bsStyle="primary">Eskaliraj</Button>
                                </Modal.Footer>
                            </Modal>
                        </div> : null}
                    </div>;
                }
        }

        return (
            <Panel bsStyle="primary">
                <Panel.Heading>
                    <div class="d-flex w-100 justify-content-between">
                        <Panel.Heading componentClass="h2">{test.naziv}
                            <small>
                                #{test.id}
                            </small>
                        </Panel.Heading>
                        <Button bsSize="large">
                            <Glyphicon glyph="download-alt" />
                        </Button>
                    </div>
                </Panel.Heading>
                {table}
            </Panel>

        );
    }

}

export default Zahtjev;
