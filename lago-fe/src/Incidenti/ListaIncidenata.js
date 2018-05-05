import React, {Component} from 'react';
import {
    ListGroup,
    ListGroupItem,
    Button,
    Row,
    Col,
    Grid,
    FormGroup,
    FormControl,
    Table,
    Glyphicon} from 'react-bootstrap';
import {bootstrapUtils} from 'react-bootstrap/lib/utils'

bootstrapUtils.addStyle(ListGroup, 'custom');

class IncidentItem extends Component{

    constructor(props){
        super(props);

        this.state = {
            id: props.item.id,
            naslov: props.item.naslov,
            datum: props.item.datum,
            status: props.item.status,
            odjel: props.item.odjel,
            prioritet: props.item.prioritet,
            odjel: props.item.odjel,
            korisnik: props.item.korisnik
        }

        this.onSelectChange = this.onSelectChange.bind(this);
    }

    deleteIncident(){
        alert("Incident je izbrisan!");
        window.location.reload();
    }

    onSelectChange(e){
        var selected = e.target.options.selectedIndex;
        console.log(e.target.name + ' ' + e.target.options[selected].value);
        this.setState({[e.target.name]:e.target.options[selected].value});  
    }

    render(){

        //var date = new Date(this.state.datum);

        var incident = null;
        var rola = "Administrator"; 

        switch (rola){
            case "Korisnik":
                incident = 
                        <tr>
                            <td>{this.state.id}</td>
                            <td>{this.state.naslov}</td>
                            <td>{this.state.datum}</td>
                            <td>{this.state.status}</td>
                            <td><Button bsStyle="danger" onClick={this.deleteIncident.bind(this)}><Glyphicon glyph="trash" /></Button></td>
                        </tr>
                break;
            case "Administrator":
                incident = 
                        <tr>
                            <td >{this.state.id}</td>
                            <td>{this.state.korisnik}</td>
                            <td>{this.state.naslov}</td>
                            <td>{this.state.datum}</td>
                            <td>{this.state.status}</td>
                            <td>
                            <FormGroup controlId="formControlsSelect">
                                <FormControl componentClass="select" name="odjel" placeholder="Odjel" onChange={this.onSelectChange}>
                                    <option value="Odjel 1">Odjel 1</option>
                                    <option value="Odjel 2">Odjel 2</option>
                                    <option value="Odjel 3">Odejl 3</option>
                                </FormControl>
                            </FormGroup>
                            </td>
                            <td>
                            <FormGroup controlId="formControlsSelect">
                                <FormControl componentClass="select" name="prioritet" placeholder="Prioritet" onChange={this.onSelectChange}>
                                    <option value="Visok">Visok</option>
                                    <option value="Normalan">Normalan</option>
                                    <option value="Nizak">Nizak</option>
                                </FormControl>
                            </FormGroup>
                            </td>
                            <td><Button bsStyle="info" style={{width:"100%"}} onClick={this.deleteIncident.bind(this)}><Glyphicon glyph="eye-open" style={{color:"white", fontSize:"18px"}} /></Button></td>
                        </tr>
            break;
        }

        
        return(
            incident
        );
    }

}

class ListaIncidenata extends Component{

    constructor(props){
        super(props);

        this.state = {
            username:'',
            role:'',
            incidenti: []
        }

        this.getIncidenti = this.getIncidenti.bind(this);
    }

    componentDidMount(){
        this.getIncidenti();
    }

    getIncidenti(){
        /*axios.get(

        )
        .then(this.handleSuccess.bind(this))
        .catch(this.handleError.bind(this));
        */
    }

    handleSuccess(response){

    }

    handleError(error){
        console.log(error);
    }

    render(){

        var test = [
            {
                id: 1,
                naslov: 'Naslov',
                datum: '11/11/11',
                status: 'Poslano',
                korisnik: 'Korisnik 1',
                odjel: null,
                prioritet: null
            },
            {
                id: 2,
                naslov: 'Naslov',
                korisnik: 'Korisnik 1',
                datum: '11/11/11',
                status: 'Poslano',
                odjel: null,
                prioritet: null
            },
            {
                id: 3,
                naslov: 'Naslov',
                korisnik: 'Korisnik 1',
                datum: '11/11/11',
                status: 'Poslano',
                odjel: null,
                prioritet: null
            },
            {
                id: 4,
                naslov: 'Naslov',
                datum: '11/11/11',
                korisnik: 'Korisnik 1',
                status: 'Poslano',
                odjel: null,
                prioritet: null
            }
        ];
        
        var list = test.map((incident)=><IncidentItem key={incident.id} item={incident}/>);
        var role = "Administrator";

        var labels = null;
    
        switch(role){
            case "Korisnik":
               labels = <thead>
                    <tr>
                        <th>#</th>
                        <th>Naziv</th>
                        <th>Datum</th>
                        <th>Status</th>
                        <th>Briši</th>
                    </tr>
                </thead>;
                break;
            case "Administrator":
                labels = <thead>
                    <tr>
                        <th>#</th>
                        <th>Korisnik</th>
                        <th>Naziv</th>
                        <th>Datum</th>
                        <th>Status</th>
                        <th>Odjel</th>
                        <th>Prioritet</th>
                        <th>Briši</th>
                    </tr>
                </thead>;
                break;
        }
    
        return(
            <div>
                <Table bordered responsive condensed>
                    {labels}
                    <tbody>        
                    {list}
                    </tbody>
                </Table>
            </div>
        );
    
    }

}

export default ListaIncidenata;