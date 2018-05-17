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

class ZahtjevForm extends Component{

    constructor(props){
        super(props);
        if (localStorage.getItem("nazivZahtjeva") !== null)
          var naziv=localStorage.getItem("nazivZahtjeva");
        else naziv='';
        localStorage.clear();
        this.state = {
            naziv: naziv,
            opis:'',
            email:0,
            telefon:0
        };
        this.handleChange = this.handleChange.bind(this);

    }

    handleChange(e) {
      console.log(e.target.id + ' : ' + e.target.value);
      if(e.target.name == "telefon" || e.target.name == "email"){
          var name = 'email';
          if(e.target.id == 'email') name = "telefon";
          this.setState(
              {
                  [e.target.id]:true,
                  [name]:false
              }
          );
          console.log(e.target.id + ' ' +this.state.email);
          console.log(name + ' ' +this.state.telefon);
      }else this.setState({ [e.target.name]: e.target.value });
    }

    handleCheckbox(e){
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
                <FormGroup controlId="formIncident">
                    <Col md={6} xsOffset={3}>
                        <ControlLabel> Odaberi hitnost </ControlLabel>
                        <FormControl componentClass="select" placeholder="Hitnost">
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
                 <Button type="submit" bsStyle="primary" className="pull-right" bsSize="lg">Prijavi zahtjev</Button>
                 </Col>
                 </Row>
                 </Grid>
            </form>
              </Panel>
        );

    }

}

export default ZahtjevForm;
