import React, { Component } from 'react';
import {Tab,Tabs,Modal, FormGroup,ControlLabel,PageHeader, Row, Panel, Col, FormControl, Button, PanelGroup,ListGroup, ListGroupItem,Glyphicon } from 'react-bootstrap';
import {PATH_BASE,PATH_INCIDENTS,PATH_ONE_INCIDENT,PATH_ESCALATION_ADMIN,PATH_ASSIGN_ADMIN,
  PATH_RESOLVE_INCIDENT,PATH_ASSIGN_RESOLVER,PATH_ESCALATION_RESOLVER,
  PATH_CLOSE_INCIDENT, PATH_INCIDENT_MESSAGE,PATH_GET_INCIDENT_MESSAGE,PATH_SEND_INCIDENT_MESSAGE} from '../globals';

import axios from 'axios';
class Incident extends Component{

    constructor(props){
        super(props);
        this.state = {
            id: this.props.match.params.id,
            data:'',
            message:'',
            poruke:[],
            receiver:'odjel',
            departmentName:'',
            dName:'',
            priority:'',
            esc:''
        };
        this.handleHide = this.handleHide.bind(this);
        this.escalationConfirm=this.escalationConfirm.bind(this);
        this.ponistiEskalaciju=this.ponistiEskalaciju.bind(this);
        this.handleChange=this.handleChange.bind(this);
        this.assignSuccess=this.assignSuccess.bind(this);
        this.promjenaStatusa=this.promjenaStatusa.bind(this);
        this.preuzmiIncident=this.preuzmiIncident.bind(this);
        this.suggestEscalation=this.suggestEscalation.bind(this);
        this.odbijZatvaranje=this.odbijZatvaranje.bind(this);
        this.prihvatiZatvaranje=this.prihvatiZatvaranje.bind(this);
        this.getMessages=this.getMessages.bind(this);
        this.sendMessage=this.sendMessage.bind(this);
    }
    odbijZatvaranje(event){
      event.preventDefault();
      axios.post(PATH_BASE+PATH_INCIDENTS+PATH_CLOSE_INCIDENT,{
        id: this.state.id,
        username: sessionStorage.getItem("username"),
        close: false
      }
    )
    .then(this.closeRejectSucess.bind(this))
    .catch(this.handleError.bind(this));

    }

    closeRejectSucess(response){
      alert("Incident je ponovno otvoren");
      this.setState(prevState => ({data:
        {...prevState.data,
          status:"u obradi"}}));
    }
    prihvatiZatvaranje(event){
      event.preventDefault();
      axios.post(PATH_BASE+PATH_INCIDENTS+PATH_CLOSE_INCIDENT,{
        id: this.state.id,
        username: sessionStorage.getItem("username"),
        close: true
      }
    )
    .then(this.closeSucess.bind(this))
    .catch(this.handleError.bind(this));

    }

    closeSucess(response){
      alert("Incident je zatvoren");
      this.setState(prevState => ({data:
        {...prevState.data,
          status:"zatvoren"}}));
    }


    suggestEscalation(event){
      event.preventDefault();
      axios.post(PATH_BASE+PATH_INCIDENTS+PATH_ESCALATION_RESOLVER,{
        id: this.state.id,
        username: sessionStorage.getItem("username")
      }
    )
    .then(this.escalationSuccess.bind(this))
    .catch(this.handleError.bind(this));

    }

    escalationSuccess(response){
      alert("Incident je eskaliran");
      this.setState(prevState => ({data:
        {...prevState.data,
          escalation:true}}));
    }

    preuzmiIncident(event){
      event.preventDefault();
      event.preventDefault();
      axios.post(PATH_BASE+PATH_INCIDENTS+PATH_ASSIGN_RESOLVER,{
        id: this.state.id,
        username: sessionStorage.getItem("username")
      }
    )
    .then(this.preuzmiSucess.bind(this))
    .catch(this.handleError.bind(this));
    }

    preuzmiSucess(response){
      alert("Incident je preuzet");
      window.location="/dashboard/nincidenti";
    }



    promjenaStatusa(event){
      event.preventDefault();
      axios.post(PATH_BASE+PATH_INCIDENTS+PATH_RESOLVE_INCIDENT,{
        id: this.state.id,
        username: sessionStorage.getItem("username")
      }
    )
    .then(this.resolveSucess.bind(this))
    .catch(this.handleError.bind(this));
    }
    resolveSucess(response){
      alert("Incident je riješen");
      this.setState(prevState => ({data:
        {...prevState.data,
          status:"riješen"}}));
    }
    ponistiEskalaciju(event){
      event.preventDefault();

      axios.post(PATH_BASE+PATH_INCIDENTS+PATH_ESCALATION_ADMIN, {
          escalation: false,
          id: this.state.id
      }
      )
      .then(this.cancelSuccess.bind(this))
      .catch(this.handleError.bind(this));

    }
    cancelSuccess(response){
      alert("Eskalacija je poništena");
    this.setState(prevState => ({data:
      {...prevState.data,
        escalation:false}}));
  }
    escalationConfirm(event){
      event.preventDefault();


      axios.post(PATH_BASE+PATH_INCIDENTS+PATH_ESCALATION_ADMIN, {
          escalation: true,
          id: this.state.id,
          departmentName: this.state.departmentName
      }
      )
      .then(this.confirmSuccess.bind(this))
      .catch(this.handleError.bind(this));

    }
    confirmSuccess(response){
      this.setState({ show: false });
      alert("Eskalacija je prihvaćena");
    this.setState(prevState => ({data:
      {...prevState.data,
        escalation:false}}));
    }
    componentDidMount(){
        this.getIncident();
    }
    handleHide() {
     this.setState({ show: false });
    }
    assignSuccess(event){
      event.preventDefault();
      console.log(this.state.dName+this.state.priority);
      axios.post(PATH_BASE+PATH_INCIDENTS+PATH_ASSIGN_ADMIN, {
        adminUsername: sessionStorage.getItem("username"),
        departmentName: this.state.dName,
        id: this.state.id,
        priority: this.state.priority
      }
      )
      .then(this.confirmAssing.bind(this))
      .catch(this.handleError.bind(this));

    }
    confirmAssing()
    {
      alert("Uspješna dodjela incidenta.");
      window.location = '/dashboard/incidenti/'+this.state.id;
    }
    getIncident(){

        axios.post(PATH_BASE+PATH_INCIDENTS+PATH_ONE_INCIDENT, {
            username:  sessionStorage.getItem("username"),
            id: this.state.id
        }
        )
        .then(this.handleSuccess.bind(this))
        .catch(this.handleError.bind(this));

    }
    handleChange(e) {

      this.setState({ [e.target.name]: e.target.value });

        console.log(e.target.name + e.target.value);
    }

    handleSuccess(response){
      this.setState({data:response.data});
      console.log(response.data);
      this.getMessages();
    }

    handleError(error){
        console.log(error);
    }

    getMessages(){
      axios.get(PATH_BASE+PATH_INCIDENT_MESSAGE+PATH_GET_INCIDENT_MESSAGE,  {
        params: {
          userId:  sessionStorage.getItem("id")
        }
      }
      )
      .then(this.handleSuccessMessage.bind(this))
      .catch(this.handleError.bind(this));

    }
    handleSuccessMessage(response){
      console.log(response.data);
      var id = this.state.id;
      console.log(id);
      var poruke = response.data.filter(poruka => poruka.incident.id==id);

      this.setState({poruke:poruke});
      console.log(poruke);
    }

    sendMessage(event){
      event.preventDefault();
        console.log(this.state.receiver);
      axios.post(PATH_BASE+PATH_INCIDENT_MESSAGE+PATH_SEND_INCIDENT_MESSAGE,  {
          message: this.state.message,
          requestOrIncidentId: this.state.id,
          senderRole: sessionStorage.getItem("rola").toLowerCase(),
          receiverRole: this.state.receiver,
          username:  sessionStorage.getItem("username")
        }
      )
      .then(this.handleSuccessSendMessage.bind(this))
      .catch(this.handleError.bind(this));

    }
    handleSuccessSendMessage()
    {
      alert("Uspješno ste poslali poruku.");
      this.getMessages();
    }
    render(){

        var role = sessionStorage.getItem("rola");
        var listItems=<div></div>;
        var listItems2=<div></div>;
        var poruke = this.state.poruke;
        if (role ==='Odjel')
        {
           listItems = poruke.filter((poruka=> poruka.receiver.userType.typeName==='Administrator' ||
        poruka.sender.userType.typeName==='Administrator')).map((poruka) =>
            <ListGroupItem header={poruka.message}> <div class="d-flex w-100 justify-content-between">
              <h6 class="mb-2">{poruka.sender.username}</h6>
              <small>{poruka.date}</small>
              </div></ListGroupItem>
              );
         listItems2 = poruke.filter((poruka=> poruka.receiver.userType.typeName==='Korisnik' ||
      poruka.sender.userType.typeName==='Korisnik')).map((poruka) =>
          <ListGroupItem header={poruka.message}> <div class="d-flex w-100 justify-content-between">
            <h6 class="mb-2">{poruka.sender.username}</h6>
            <small>{poruka.date}</small>
            </div></ListGroupItem>
            );
        }
        else{
           listItems = poruke.map((poruka) =>
            <ListGroupItem header={poruka.message}> <div class="d-flex w-100 justify-content-between">
              <h6 class="mb-2">{poruka.sender.username}</h6>
              <small>{poruka.date}</small>
              </div></ListGroupItem>
              );
        }
          var table = <div></div>;
          switch(role){
            case 'Administrator':
            if (this.state.data.departmentName==='')
            table = <div style={{padding:"1%", overflowY:"scroll", maxHeight:"60vh"}}>
            <Row>
                <Col md={3}>
                    <Panel bsStyle="info">
                        <Panel.Heading>
                            <Panel.Title componentClass="h5">Korisnik</Panel.Title>
                        </Panel.Heading>
                        <Panel.Body>{this.state.data.creatorUsername}</Panel.Body>
                    </Panel>
                </Col>
                <Col md={3}>
                    <Panel bsStyle="success">
                        <Panel.Heading>
                            <Panel.Title componentClass="h5">Hitnost</Panel.Title>
                        </Panel.Heading>
                        <Panel.Body>{this.state.data.urgency}</Panel.Body>
                    </Panel>
                </Col>
                <Col md={3}>
                    <Panel  bsStyle="info">
                        <Panel.Heading>
                            <Panel.Title componentClass="h5">Datum kreiranja</Panel.Title>
                        </Panel.Heading>
                        <Panel.Body>{this.state.data.createdDate}</Panel.Body>
                    </Panel>
                </Col>
                <Col md={3}>
                    <Panel  bsStyle="success">
                        <Panel.Heading>
                            <Panel.Title componentClass="h5">Kontakt</Panel.Title>
                        </Panel.Heading>
                        <Panel.Body>{this.state.data.contactMethod}</Panel.Body>
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
                  <Panel.Body>{this.state.data.description}</Panel.Body>
                </Panel.Collapse>
            </Panel>
            <Panel bsStyle="info" id="collapsible-panel-example-2" defaultClosed>
            <Row>
              <FormGroup  controlId="formZahtjev">
                <Col md={12}>
                    <ControlLabel> Odaberite odjel </ControlLabel>
                    <FormControl componentClass="select" name="dName" onChange={this.handleChange} placeholder="Odjel">
                    <option value="" selected disabled>Odjel</option>
                        <option value="Odjel1">Odjel 1</option>
                        <option value="Odjel2">Odjel 2</option>
                        <option value="Odjel3">Odjel 3</option>
                        <option value="Odjel4">Odjel 4</option>
                    </FormControl>
                    <br/>
                    <ControlLabel> Odaberite prioritet </ControlLabel>
                    <FormControl componentClass="select" name="priority" onChange={this.handleChange} placeholder="Prioritet">
                    <option value="" selected disabled>Prioritet</option>
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
                <br/>
                <Row>
                <Col md={12} style={{textAlign:"right"}}>
                <Button type="submit" bsStyle="primary" className="pull-right" bsSize="lg" onClick={this.assignSuccess}>Dodijeli</Button>
                </Col>
                </Row>
                <br/>
            </Panel>
            </div>;
            else{table=<div style={{padding:"1%", overflowY:"scroll", maxHeight:"60vh"}}>
            <Row>
                <Col md={3}>
                    <Panel bsStyle="info">
                        <Panel.Heading>
                            <Panel.Title componentClass="h5">Prijavio</Panel.Title>
                        </Panel.Heading>
                        <Panel.Body>{this.state.data.creatorUsername}</Panel.Body>
                    </Panel>
                </Col>
                <Col md={3}>
                    <Panel bsStyle="success">
                        <Panel.Heading>
                            <Panel.Title componentClass="h5">Servis</Panel.Title>
                        </Panel.Heading>
                        <Panel.Body>{this.state.data.serviceName}</Panel.Body>
                    </Panel>
                </Col>
                <Col md={3}>
                    <Panel bsStyle="info">
                        <Panel.Heading>
                            <Panel.Title componentClass="h5">Rješava</Panel.Title>
                        </Panel.Heading>
                        <Panel.Body>{this.state.data.resolverUsername}</Panel.Body>
                    </Panel>
                </Col>
                <Col md={3}>
                    <Panel bsStyle="success">
                        <Panel.Heading>
                            <Panel.Title componentClass="h5">Hitnost</Panel.Title>
                        </Panel.Heading>
                        <Panel.Body>{this.state.data.urgency}</Panel.Body>
                    </Panel>
                </Col>
                <Col md={3}>
                    <Panel bsStyle="info">
                        <Panel.Heading>
                            <Panel.Title componentClass="h5">Prioritet</Panel.Title>
                        </Panel.Heading>
                        <Panel.Body>{this.state.data.priority}</Panel.Body>
                    </Panel>
                </Col>
                <Col md={3}>
                    <Panel  bsStyle="success">
                        <Panel.Heading>
                            <Panel.Title componentClass="h5">Odjel</Panel.Title>
                        </Panel.Heading>
                        <Panel.Body>{this.state.data.departmentName}</Panel.Body>
                    </Panel>
                </Col>
                <Col md={3}>
                    <Panel bsStyle="info">
                        <Panel.Heading>
                            <Panel.Title componentClass="h5">Status</Panel.Title>
                        </Panel.Heading>
                        <Panel.Body>{this.state.data.status}</Panel.Body>
                    </Panel>
                </Col>
                <Col md={3}>
                    <Panel bsStyle="success">
                        <Panel.Heading>
                            <Panel.Title componentClass="h5">Eskalacija</Panel.Title>
                        </Panel.Heading>
                        <Panel.Body>{this.state.data.escalation?"Da":"Ne"}</Panel.Body>
                    </Panel>
                </Col>
                <Col md={3}>
                    <Panel  bsStyle="info">
                        <Panel.Heading>
                            <Panel.Title componentClass="h5">Datum kreiranja</Panel.Title>
                        </Panel.Heading>
                        <Panel.Body>{this.state.data.createdDate}</Panel.Body>
                    </Panel>
                </Col>
                <Col md={3}>
                    <Panel  bsStyle="success">
                        <Panel.Heading>
                            <Panel.Title componentClass="h5">Datum posljednjeg rješavanja</Panel.Title>
                        </Panel.Heading>
                        <Panel.Body>{this.state.data.lastResolveDate}</Panel.Body>
                    </Panel>
                </Col>
                <Col md={3}>
                    <Panel  bsStyle="info">
                        <Panel.Heading>
                            <Panel.Title componentClass="h5">Datum zatvaranja</Panel.Title>
                        </Panel.Heading>
                        <Panel.Body>{this.state.data.closedDate}</Panel.Body>
                    </Panel>
                </Col>

                <Col md={3}>
                    <Panel bsStyle="success">
                        <Panel.Heading>
                            <Panel.Title componentClass="h5">Kontakt</Panel.Title>
                        </Panel.Heading>
                        <Panel.Body>{this.state.data.contactMethod}</Panel.Body>
                    </Panel>
                </Col>
                {this.state.data.escalation? <Col md={6} xsOffset={3}>
                <Button bsStyle="primary"
                bsSize="large" className=" btn-lg btn-block" onClick={this.ponistiEskalaciju}>Poništi eskalaciju</Button>
               <br/>
                <Button  className="btn-block btn-lg"  bsStyle="primary"  bsSize="large"
                onClick={() => this.setState({ show: true })} >
                   Eskaliraj
                 </Button>
                 <br/>
                </Col> : null}
            </Row>
            <Panel id="collapsible-panel-example-2" defaultClosed>
                <Panel.Heading>
                  <Panel.Title toggle componentClass="h5">
                  Opis
                  </Panel.Title>
                </Panel.Heading>
                <Panel.Collapse>
                  <Panel.Body>{this.state.data.description}</Panel.Body>
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
                <div style={{padding:"2.5%"}}>
                <Panel id="collapsible-panel-example-2" defaultClosed>
                <Panel.Heading>
                    <Panel.Title toggle componentClass="h3" >Nova poruka</Panel.Title>
                </Panel.Heading>
                <Panel.Collapse>
                <Panel.Body>
                <FormGroup  controlId="formZahtjev">
                      <ControlLabel> Primalac: Odjel </ControlLabel>
                      <br/>
                      <ControlLabel> Tekst poruke: </ControlLabel>
                  <FormControl onChange={this.handleChange} name="message" componentClass="textarea" placeholder="Poruka" />
                  </FormGroup>
                  <br/>
                  <Button bsStyle="info" onClick={this.sendMessage} bsSize="lg" className="pull-right">Pošalji</Button>
                  </Panel.Body>
                </Panel.Collapse>
              </Panel>
              </div>
              </Panel.Collapse>
            </Panel>
            {this.state.data.escalation?<div>
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
      <FormGroup  controlId="formZahtjev">
        <Col md={12}>
            <ControlLabel> Odaberite odjel </ControlLabel>
            <FormControl componentClass="select" name="departmentName" onChange={this.handleChange} placeholder="Odjel">
                <option value="Odjel1">Odjel 1</option>
                <option value="Odjel2">Odjel 2</option>
                <option value="Odjel3">Odjel 3</option>
                <option value="Odjel4">Odjel 4</option>
            </FormControl>
            </Col>
            </FormGroup>
            </Row>
    </Modal.Body>
    <Modal.Footer>
      <Button onClick={this.handleHide}>Close</Button>
      <Button bsStyle="primary" onClick={this.escalationConfirm}>Eskaliraj</Button>
    </Modal.Footer>
  </Modal>
  </div>:null}
            </div>;
            }
            break;
            case 'Korisnik':
            table=<div style={{padding:"1%", overflowY:"scroll", maxHeight:"60vh"}}>
            <Row >
                <Col md={3}  >
                    <Panel bsStyle="info">
                        <Panel.Heading>
                            <Panel.Title componentClass="h5">Servis</Panel.Title>
                        </Panel.Heading>
                        <Panel.Body>{this.state.data.serviceName}</Panel.Body>
                    </Panel>
                </Col>
                <Col md={3}>
                    <Panel bsStyle="success">
                        <Panel.Heading>
                            <Panel.Title componentClass="h5">Kontakt</Panel.Title>
                        </Panel.Heading>
                        <Panel.Body>{this.state.data.contactMethod}</Panel.Body>
                    </Panel>
                </Col>
                <Col md={3}>
                    <Panel bsStyle="info">
                        <Panel.Heading>
                            <Panel.Title componentClass="h5">Hitnost</Panel.Title>
                        </Panel.Heading>
                        <Panel.Body>{this.state.data.urgency}</Panel.Body>
                    </Panel>
                </Col>
                <Col md={3}>
                    <Panel  bsStyle="success">
                        <Panel.Heading>
                            <Panel.Title componentClass="h6">Datum kreiranja</Panel.Title>
                        </Panel.Heading>
                        <Panel.Body>{this.state.data.createdDate}</Panel.Body>
                    </Panel>
                </Col>
                </Row>
                  <Row>
                <Col md={3} xsOffset={1}>
                    <Panel  bsStyle="info">
                        <Panel.Heading>
                            <Panel.Title componentClass="h5">Datum posljednjeg rješavanja</Panel.Title>
                        </Panel.Heading>
                        <Panel.Body>{this.state.data.lastResolveDate}</Panel.Body>
                    </Panel>
                </Col>
                <Col md={3}>
                    <Panel  bsStyle="success">
                        <Panel.Heading>
                            <Panel.Title componentClass="h6">Datum zatvaranja</Panel.Title>
                        </Panel.Heading>
                        <Panel.Body>{this.state.data.closedDate}</Panel.Body>
                    </Panel>
                </Col>
                <Col md={3}>
                    <Panel bsStyle="info">
                        <Panel.Heading>
                            <Panel.Title componentClass="h5">Status</Panel.Title>
                        </Panel.Heading>
                        <Panel.Body>{this.state.data.status}</Panel.Body>
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
                  <Panel.Body>{this.state.data.description}</Panel.Body>
                </Panel.Collapse>

            </Panel>
            {this.state.data.status==='rijesen'?
            <Panel bsStyle="info" id="collapsible-panel-example-2" defaultClosed>
                <Row>
                <br/>
                <Col md={6} style={{textAlign:"right"}}  xsOffset={3}>
                <Button type="submit" bsStyle="primary" className="btn-block btn-lg" onClick={this.odbijZatvaranje} bsSize="lg">
                Otvori ponovo</Button>
                <br/>
                </Col>
                </Row>
                <Row>
                <Col md={6} xsOffset={3}>
                <Button  className="btn-block btn-lg"  bsStyle="primary"  bsSize="large"
                onClick={this.prihvatiZatvaranje} >
                   Zatvori incident
                 </Button>
                 <br/>
                </Col>
                </Row>
                <br/>
            </Panel>
            : null}

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
                <div style={{padding:"2.5%"}}>
                <Panel id="collapsible-panel-example-2" defaultClosed>
                <Panel.Heading>
                    <Panel.Title toggle componentClass="h3" >Nova poruka</Panel.Title>
                </Panel.Heading>
                <Panel.Collapse>
                <Panel.Body>
                <FormGroup  controlId="formZahtjev">
                      <ControlLabel> Primalac: Odjel </ControlLabel>
                      <br/>
                      <ControlLabel> Tekst poruke: </ControlLabel>
                  <FormControl onChange={this.handleChange} name="message" componentClass="textarea" placeholder="Poruka" />
                  </FormGroup>
                  <br/>
                  <Button bsStyle="info" onClick={this.sendMessage} bsSize="lg" className="pull-right">Pošalji</Button>
                  </Panel.Body>
                </Panel.Collapse>
              </Panel>
              </div>
              </Panel.Collapse>
            </Panel>
            </div>;
            break;
            case 'Odjel':
            if (this.props.tip === 'Nedodijeljen'){
            table = <div style={{padding:"1%", overflowY:"scroll", maxHeight:"60vh"}}>
            <Row>
                <Col md={4}>
                    <Panel bsStyle="info">
                        <Panel.Heading>
                            <Panel.Title componentClass="h5">Korisnik</Panel.Title>
                        </Panel.Heading>
                        <Panel.Body>{this.state.data.creatorUsername}</Panel.Body>
                    </Panel>
                </Col>
                <Col md={4}>
                    <Panel bsStyle="success">
                        <Panel.Heading>
                            <Panel.Title componentClass="h5">Prioritet</Panel.Title>
                        </Panel.Heading>
                        <Panel.Body>{this.state.data.priority}</Panel.Body>
                    </Panel>
                </Col>
                <Col md={4}>
                    <Panel  bsStyle="info">
                        <Panel.Heading>
                            <Panel.Title componentClass="h5">Datum kreiranja</Panel.Title>
                        </Panel.Heading>
                        <Panel.Body>{this.state.data.createdDate}</Panel.Body>
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
                  <Panel.Body>{this.state.data.description}</Panel.Body>
                </Panel.Collapse>
            </Panel>
            <Row>
                <Col md={12} style={{textAlign:"right"}}>
                <Button type="submit" bsStyle="primary" className="pull-right" onClick={this.preuzmiIncident} bsSize="lg">Preuzmi</Button>
                </Col>
                </Row>
                <br/>
            </div>;}
            else {
              table=<div style={{padding:"1%", overflowY:"scroll", maxHeight:"60vh"}}>
              <Row>
                  <Col md={3}>
                      <Panel bsStyle="info">
                          <Panel.Heading>
                              <Panel.Title componentClass="h5">Prijavio</Panel.Title>
                          </Panel.Heading>
                          <Panel.Body>{this.state.data.creatorUsername}</Panel.Body>
                      </Panel>
                  </Col>
                  <Col md={3}>
                      <Panel bsStyle="success">
                          <Panel.Heading>
                              <Panel.Title componentClass="h5">Prioritet</Panel.Title>
                          </Panel.Heading>
                          <Panel.Body>{this.state.data.priority}</Panel.Body>
                      </Panel>
                  </Col>

                  <Col md={3}>
                      <Panel bsStyle="info">
                          <Panel.Heading>
                              <Panel.Title componentClass="h5">Status</Panel.Title>
                          </Panel.Heading>
                          <Panel.Body>{this.state.data.status}</Panel.Body>
                      </Panel>
                  </Col>
                  <Col md={3}>
                      <Panel bsStyle="success">
                          <Panel.Heading>
                              <Panel.Title componentClass="h5">Eskalacija</Panel.Title>
                          </Panel.Heading>
                          <Panel.Body>{this.state.data.escalation?'Da':'Ne'}</Panel.Body>
                      </Panel>
                  </Col>
                  <Col md={3}>
                      <Panel  bsStyle="info">
                          <Panel.Heading>
                              <Panel.Title componentClass="h5">Datum kreiranja</Panel.Title>
                          </Panel.Heading>
                          <Panel.Body>{this.state.data.createdDate}</Panel.Body>
                      </Panel>
                  </Col>
                  <Col md={3}>
                      <Panel  bsStyle="success">
                          <Panel.Heading>
                              <Panel.Title componentClass="h5">Datum rješavanja</Panel.Title>
                          </Panel.Heading>
                          <Panel.Body>{this.state.data.lastResolveDate}</Panel.Body>
                      </Panel>
                  </Col>
                  <Col md={3}>
                      <Panel  bsStyle="success">
                          <Panel.Heading>
                              <Panel.Title componentClass="h5">Datum zatvaranja</Panel.Title>
                          </Panel.Heading>
                          <Panel.Body>{this.state.closedDate}</Panel.Body>
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
                    <Panel.Body>{this.state.data.description}</Panel.Body>
                  </Panel.Collapse>
              </Panel>
              {this.state.data.escalation===false || this.state.data.status==="u obradi"?
              <Panel bsStyle="info" id="collapsible-panel-example-2" defaultClosed>
                  <br/>
                  <Row>
                  <br/>
                  {this.state.data.status==="u obradi"?
                  <Col md={6} style={{textAlign:"right"}}  xsOffset={3}>
                  <Button type="submit" bsStyle="primary" className="btn-block btn-lg" onClick={this.promjenaStatusa} bsSize="lg">
                  Riješen</Button>

                  <br/>
                  </Col>: null}
                  </Row>
                  <Row>
                  {this.state.data.escalation===false? <Col md={6} xsOffset={3}>
                  <Button  className="btn-block btn-lg"  bsStyle="danger"  bsSize="large"
                  onClick={() => this.setState({ show: true })} >
                     Predloži eskalaciju
                   </Button>
                   <br/>
                  </Col> : null}
                  </Row>
                  <br/>
              </Panel>:null}

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
                  <div style={{padding:"2.5%"}}>
                  <Panel id="collapsible-panel-example-2" defaultClosed>
                  <Panel.Heading>
                      <Panel.Title toggle componentClass="h3" >Nova poruka</Panel.Title>
                  </Panel.Heading>
                  <Panel.Collapse>
                  <Panel.Body>
                  <FormGroup  controlId="formZahtjev">
                        <ControlLabel> Primalac: </ControlLabel>
                        <FormControl name="receiver" componentClass="select" onChange={this.handleChange} placeholder="Primalac">
                        <option value="" selected disabled>Primalac</option>
                            <option value="korisnik">Korisnik</option>
                            <option value="administrator">Administrator</option>
                        </FormControl>
                        <br/>
                        <ControlLabel> Tekst poruke: </ControlLabel>
                    <FormControl name="message" onChange={this.handleChange}componentClass="textarea" placeholder="Poruka" />
                    </FormGroup>
                    <br/>
                    <Button bsStyle="info" onClick={this.sendMessage} bsSize="lg" className="pull-right">Pošalji</Button>
                    </Panel.Body>
                  </Panel.Collapse>
                </Panel>
                </div>
                </Panel.Collapse>
              </Panel>
              {this.state.data.escalation===false?<div>
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
        <Button bsStyle="primary" onClick={this.suggestEscalation}>Eskaliraj</Button>
      </Modal.Footer>
    </Modal>
    </div>:null}
              </div>;
            }
          }
        return(
          <Panel bsStyle="primary">
              <Panel.Heading>
              <div class="d-flex w-100 justify-content-between">
                <Panel.Heading componentClass="h2">{this.state.data.title}
                  <small>
                    #{this.state.id}
                  </small>
                  </Panel.Heading>
                  <Button  bsSize="large">
                    <Glyphicon glyph="download-alt" />
                  </Button>
              </div>
              </Panel.Heading>
              {table}
          </Panel>


        );
    }

}

export default Incident;
