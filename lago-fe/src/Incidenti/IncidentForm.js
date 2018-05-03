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
    Grid} from 'react-bootstrap';

class IncidentForm extends Component{

    constructor(props){
        super(props);
        this.state = {
            naziv:'',
            opis:'',
            email:0,
            telefon:0
        };
        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(e) {
        console.log(e.target.name + ' : ' + e.target.value);    
        if(e.target.name == "telefon" || e.target.name == "email"){
            var tmp = 0;
            if(e.target.value == 0) tmp = 1; 
            else tmp = 0;
            console.log(tmp);
            this.setState({ [e.target.name]: tmp });
        }else this.setState({ [e.target.name]: e.target.value });
    }

    handleCheckbox(e){
        this.setState
    }

    render(){

        return(
            <form>
                <Grid> 
                <Row>
                <Col md={10}>
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
                <Col md={5}>
                <h6>
                        Način obavještavanja o razrješenju incidenta
                </h6>

                    <Checkbox inline
                        name="email"
                        value={this.state.email}
                        onChange={this.handleChange}
                    >
                        E-mail
                    </Checkbox>{'   '}
                    <Checkbox inline
                        name="telefon"
                        value={this.state.telefon}
                        onChange={this.handleChange}
                    >
                        Telefon
                    </Checkbox>

                 </Col>
                 <Col md={5}>
                 <Button type="submit" bsStyle="info" bsSize="lg">Prijavi incident</Button>
                 </Col>
                 </Row>
                 </Grid>
            </form>
        );

    }

}

export default IncidentForm;