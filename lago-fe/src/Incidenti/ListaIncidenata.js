import React, {Component} from 'react';
import {ListGroup, ListGroupItem, Button, Row, Col, Grid} from 'react-bootstrap';
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
            prioritet: props.item.prioritet
        }
    }

    deleteIncident(){
        alert("Incident je izbrisan!");
        window.location.reload();
    }

    render(){

        //var date = new Date(this.state.datum);

        
        return(
            <ListGroupItem key={this.state.id}>
            <Grid>
            <Row className="show-grid">
                <Col md={1} >{this.state.id}</Col>
                <Col md={3}>{this.state.naslov}</Col>
                <Col md={3}>{this.state.datum}</Col>
                <Col md={3}>{this.state.status}</Col>
                <Col md={1}><Button bsStyle="danger" onClick={this.deleteIncident.bind(this)}>&#xe020;</Button></Col>
            </Row>
            </Grid>
        </ListGroupItem>
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
                odjel: null,
                prioritet: null
            },
            {
                id: 2,
                naslov: 'Naslov',
                datum: '11/11/11',
                status: 'Poslano',
                odjel: null,
                prioritet: null
            },
            {
                id: 3,
                naslov: 'Naslov',
                datum: '11/11/11',
                status: 'Poslano',
                odjel: null,
                prioritet: null
            },
            {
                id: 4,
                naslov: 'Naslov',
                datum: '11/11/11',
                status: 'Poslano',
                odjel: null,
                prioritet: null
            }
        ];
        
        var list = test.map((incident)=><IncidentItem item={incident}/>);

        return(
            <div>
                <style type="text/css">{`
                    .list-group-custom {
                        width: auto;
                    }
                    `}
                </style>
                <ListGroup bsStyle="custom">
                    {list}
                </ListGroup>
            </div>
        );
    
    }

}

export default ListaIncidenata;