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

class IncidentForm extends Component{

    constructor(props){
        super(props);
        this.state = {
            naziv:'',
            opis:'',
            email:true,
            telefon:false
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
                        Novi incident
                    </Panel.Title> 
                </Panel.Heading>
            <form style={{padding:"2%"}}>
                <Grid> 
                <Row>
                <Col md={12}>
                <FormGroup controlId="formIndicdent">
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
                    <Col md={6}>
                        <ControlLabel> Odaberi servis </ControlLabel>
                        <FormControl componentClass="select" placeholder="Servis">
                            <option value="Servis 1">Servis 1</option>
                            <option value="Servis 2">Servis 2</option>
                            <option value="Servis 3">Servis 3</option>
                            <option value="Servis 4">Servis 4</option>
                        </FormControl>
                    </Col>
                    <Col md={6}>
                        <ControlLabel> Odaberi hitnost </ControlLabel>
                        <FormControl componentClass="select" placeholder="Hitnost">
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                        </FormControl>
                    </Col>
                </Row>
                <br/>
                <Row> 
                <Col md={6}>
                <h6>
                        Način obavještavanja o razrješenju incidenta
                </h6>
                <FormGroup>
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
                 <Col md={6} style={{textAlign:"right"}}>
                 <Button type="submit" bsStyle="info" bsSize="lg">Prijavi incident</Button>
                 </Col>
                 </Row>
                 </Grid>
            </form>
            </Panel>
        );

    }

}

export default IncidentForm;