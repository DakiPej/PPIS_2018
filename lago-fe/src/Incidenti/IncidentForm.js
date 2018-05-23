import React,{Component} from 'react';
import {
    FormGroup,
    ControlLabel,
    FormControl,
    FieldGroup,
    Radio,
    Checkbox,
    Button,
    Row,
    Col,
    Grid,
    Panel} from 'react-bootstrap';
import {PATH_BASE,PATH_INCIDENTS,PATH_CREATE_INCIDENT} from '../globals';

import axios from 'axios';
class IncidentForm extends Component{

    constructor(props){
        super(props);
        this.state = {
          naziv: '',
          opis:'',
          email:0,
          telefon:0,
          hitnost: 0,
          servis: ''
        };
        this.handleChange = this.handleChange.bind(this);
        this.onPrijavi = this.onPrijavi.bind(this);
    }

    handleChange(e) {
      if(e.target.name == "telefon" || e.target.name == "email"){
          var name = 'email';
          if(e.target.id == 'email') name = "telefon";
          this.setState(
              {
                  [e.target.id]:true,
                  [name]:false
              }
          );
      }else this.setState({ [e.target.name]: e.target.value });
    }

    onPrijavi(event){

      event.preventDefault();
      var kontakt;
      if (this.state.email) kontakt="email";
      else kontakt="phone";
         if(this.state.naziv && this.state.opis &&(this.state.email || this.state.telefon)&& kontakt)
                 axios.post(PATH_BASE+PATH_INCIDENTS+PATH_CREATE_INCIDENT, {
                   username: sessionStorage.getItem("username"),
                   contactMethodName: kontakt,
                   serviceName: this.state.servis,
                   title: this.state.naziv,
					         description: this.state.opis,
					         urgency: this.state.hitnost } )
                   .then(this.handleSuccess.bind(this))
                   .catch(this.handleError.bind(this));

		}

   handleSuccess(response) {
            console.log("hej",response.data);
            alert("Incident uspješno prijavljen");
        }

        handleError(error) {
            console.log(error);
        }

    render(){

        return(
            <Panel bsStyle="primary">
            <Panel.Heading>
                    <Panel.Title componentClass="h2">
                        Novi incident
                    </Panel.Title>
                </Panel.Heading>
            <form style={{padding:"2%"}}>
                <Grid>
                <Row>
                <Col md={12}>
                <FormGroup controlId="formIncident">
                    <ControlLabel> Naziv </ControlLabel>
                    <FormControl
                        type="text"
                        name="naziv"
                        value={this.state.naziv}
                        placeholder="Naziv incidenta"
                        onChange={this.handleChange}
                    />
                    <ControlLabel> Opis </ControlLabel>
                    <FormControl
                        type="text"
                        componentClass="textarea"
                        name="opis"
                        value={this.state.opis}
                        placeholder="Opis incidenta"
                        onChange={this.handleChange}
                        bsSize="large"
                    />
                </FormGroup>
                </Col>
                </Row>
                <br/>
                <Row>
                  <FormGroup  controlId="formIncident">
                    <Col md={6}>
                        <ControlLabel> Odaberi servis </ControlLabel>
                        <FormControl componentClass="select" onChange={this.handleChange} name="servis"  >
                            <option value="" selected disabled>Servis</option>
                            <option value="SMS bankarstvo">SMS bankarstvo</option>
                            <option value="Viber bankarstvo">Viber bankarstvo</option>
                            <option value="Internet bankarstvo">Internet bankarstvo</option>
                            <option value="Mobilno bankarstvo">Mobilno bankarstvo</option>
                        </FormControl>
                    </Col>
                    <Col md={6}>
                        <ControlLabel> Odaberi hitnost </ControlLabel>
                        <FormControl componentClass="select" name="hitnost"  onChange={this.handleChange}>
                            <option value="" selected disabled>Hitnost</option>
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
                <Col md={6}>
                <FormGroup  controlId="formIncident">
                <ControlLabel>   Način obavještavanja o razrješenju incidenta </ControlLabel>
                    <Radio inline
                        id = "email"
                        name="email"
                        onChange = {this.handleChange}
                    >
                        E-mail
                    </Radio>{'   '}
                    <Radio inline
                        id = "telefon"
                        name="email"
                        onChange = {this.handleChange}
                    >
                        Telefon
                    </Radio>
                </FormGroup>
                 </Col>
                 </Row>

                 <Row>
                 <Col md={12} style={{textAlign:"right"}}>
                 <Button type="submit" bsStyle="primary" className="pull-right" bsSize="lg"onClick={this.onPrijavi}>Prijavi incident</Button>
                 </Col>
                 </Row>
                 </Grid>
            </form>
            </Panel>
        );

    }

}

export default IncidentForm;
