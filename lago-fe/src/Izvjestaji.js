import React,{ Component }  from "react";
import ReactChartkick, { LineChart, PieChart } from 'react-chartkick'
import Chart from 'chart.js'
import { Button, Glyphicon, ListGroup, ListGroupItem, Row, Col, Panel, FormControl,ControlLabel} from 'react-bootstrap';
import {Form, FormGroup, Label, Input, FormText } from 'reactstrap';

import axios from 'axios';
ReactChartkick.addAdapter(Chart)



class Izvjestaji extends Component {


		state = {
				data: [["Blueberry", 44], ["Strawberry - 10", 23]],
        type:'',
        fromDate:'',
        toDate:''
    }

    getReport=(event)=>{


    }
    handleSuccess=(response)=>{
      //{[["Blueberry", 44], ["Strawberry - 10", 23]]}

    }
    handleError=(e)=>{
      console.log(e);
    }
    handleChange=(e)=>
    {
      this.setState({ [e.target.name]: e.target.value });
    }
  render(){
    return(
      <div>
      <div className="panel panel-primary ">
          <div className="panel-heading d-flex w-100 justify-content-between">
              <h1 className="panel-title">{this.state.data.title}</h1>
              <Button bsSize="large">
                  <Glyphicon glyph="download-alt" />
              </Button>
          </div>
          <div className="panel-body">
            <FormGroup>
              <ControlLabel> Odaberite odjel </ControlLabel>
              <FormControl componentClass="select" name="type" onChange={this.handleChange} placeholder="Tip izvjestaja">
                  <option value="" selected disabled>Tip</option>
                  <option value="zahtjev">Zahtjev</option>
                  <option value="incident">Incident</option>
              </FormControl>
              <br/>
                 <ControlLabel> Od: </ControlLabel>
                 <Input type="date"  onChange={this.handleChange} name="fromDate" id="fromDate" placeholder="date placeholder" />
                 <br/>
                 <ControlLabel>Do: </ControlLabel>
                 <Input type="date"  onChange={this.handleChange} name="toDate" id="toDate" placeholder="date placeholder" />
                 <br/>
                 <Row>
                     <Col md={6} xsOffset={6}style={{ textAlign: "right" }}>
                         <Button type="submit" bsStyle="primary" className="btn-block btn-lg right pull-right" bsSize="lg" onClick={this.getReport}>Generiši izvještaj</Button>
                     </Col>
                 </Row>
                 </FormGroup>
                 <br/>

          </div>

                <PieChart data={this.state.data} label={this.state.type} legend={true} legend="bottom"
                suffix="%" messages={{empty: "Nema podataka"}} download={true} download={this.state.type}/>

      </div>
      </div>

    )
  }
}

export default Izvjestaji;
