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
    Panel,
    Col,
    Grid} from 'react-bootstrap';
import {PATH_BASE,PATH_REQUESTS} from '../globals';

import axios from 'axios';

class ZahtjevForm extends Component{

    constructor(props){
        super(props);
        if (localStorage.getItem("nazivZahtjeva") !== null)
          var naziv=localStorage.getItem("nazivZahtjeva");
        else naziv='';
        localStorage.removeItem("nazivZahtjeva");
        this.state = {
            naziv: naziv,
            opis:'',
            email:0,
            telefon:0,
            hitnost: 0
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
         if(this.state.naziv && this.state.opis &&(this.state.email || this.state.telefon) && kontakt)
                 axios.post(PATH_BASE+PATH_REQUESTS, {
                   registeredUserUsername: sessionStorage.getItem("username"),
                   contactMethod: kontakt,
					         title: this.state.naziv,
					         description: this.state.opis,
					         urgency: this.state.hitnost } )
                   .then(this.handleSuccess.bind(this))
                   .catch(this.handleError.bind(this));

		}

   handleSuccess(response) {
            console.log("hej",response.data);
            alert("Zahtjev uspješno prijavljen");
        }

        handleError(error) {
            console.log(error);
        }

    render(){

        return(
          <Panel bsStyle="primary">
          <Panel.Heading>
                  <Panel.Title componentClass="h2">
                      Novi zahtjev
                  </Panel.Title>
              </Panel.Heading>
            <form style={{padding:"2%"}}>
                <Grid>
                <Row>
                <Col md={12}>
                <FormGroup controlId="formZahtjev">
                    <ControlLabel> Naziv </ControlLabel>
                    <FormControl
                        type="text"
                        name="naziv"
                        value={this.state.naziv}
                        placeholder="Naziv zahtjeva"
                        onChange={this.handleChange}
                    />
                    <ControlLabel> Opis </ControlLabel>
                    <FormControl
                        type="text"
                        componentClass="textarea"
                        name="opis"
                        value={this.state.opis}
                        placeholder="Opis zahtjeva"
                        onChange={this.handleChange}
                        bsSize="large"
                    />
                </FormGroup>
                </Col>
                </Row>
                <br/>
                <Row>
                <FormGroup controlId="formZahtjev">
                    <Col md={6} xsOffset={3}>
                        <ControlLabel> Odaberi hitnost </ControlLabel>
                        <FormControl componentClass="select" name="hitnost"  onChange={this.handleChange}>
                            <option value="" selected disabled>Hitnost</option>
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                        </FormControl>
                    </Col>
                </FormGroup>
                </Row>
                <br/>
                <Row>
                <Col md={6}>
                <FormGroup  controlId="formZahtjev">
                <ControlLabel>   Način obavještavanja o razrješenju zahtjeva </ControlLabel>
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
                 <Button type="submit" bsStyle="primary" className="pull-right" bsSize="lg" onClick={this.onPrijavi}>Prijavi zahtjev</Button>
                 </Col>
                 </Row>
                 </Grid>
            </form>
              </Panel>
        );

    }

}

export default ZahtjevForm;
