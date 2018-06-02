import React,{ Component }  from "react";
import ReactChartkick, { LineChart, PieChart } from 'react-chartkick'
import Chart from 'chart.js'
import { Button, Glyphicon, ListGroup, ListGroupItem, Row, Col, Panel, FormControl,ControlLabel} from 'react-bootstrap';
import {Form, FormGroup, Label, Input, FormText } from 'reactstrap';

import axios from 'axios';
ReactChartkick.addAdapter(Chart)



class Izvjestaji extends Component {


		state = {
				data:[],
				dataEsc:[],
        type:'',
        fromDate:'',
        toDate:'',
				ukupno: ''
    }

    getReport=(event)=>{
      if (this.state.type==="incidenti"){
      // !!!!!!!!!!!!!! OVO JE ZA REPORT ZA INCIDENTE !!!!!!!!!!!!!!!!!!!
      axios.post("http://localhost:8080/incident/report", {
        startDate: this.state.fromDate,
        endDate: this.state.toDate
      }).then(this.handleSuccess)
        .catch(this.handleError) ;
}else if (this.state.type==="zahtjevi"){
      // !!!!!!!!!!!!!! OVO JE ZA REPORT ZA ZAHTJEVE !!!!!!!!!!!!!!!!!!!!
      axios.post("http://localhost:8080/requests/report", {
        startDate: this.state.fromDate,
        endDate: this.state.toDate
      }).then(this.handleSuccess)
        .catch(this.handleError) ;
}
    }
    handleSuccess=(response)=>{
      console.log(response.data) ;
			var objekat= response.data;
			if (this.state.type==="zahtjevi")
			{
      this.setState({data: [
				["Zatvoreno - " + objekat.closedNumber ,objekat.closedNumber],
				["Nedodijeljeno - " + objekat.notWorkingOnNumber ,objekat.notWorkingOnNumber],
				["U obradi - " + objekat.workingOnNumber ,objekat.workingOnNumber],

			],ukupno: objekat.createdNumber,
			dataEsc:[["Eskalirano - " + objekat.escalatedNumber ,objekat.escalatedNumber],
			["Nije eskalirano - " + (objekat.createdNumber - objekat.escalatedNumber) ,objekat.createdNumber - objekat.escalatedNumber]]});
		}
		else if (this.state.type==="incidenti")
		{

      this.setState({data: [
				["Zatvoreno - " + objekat.closedNumber ,objekat.closedNumber],
				["Nedodijeljeno - " + objekat.notWorkingOnNumber ,objekat.notWorkingOnNumber],
				["U obradi - " + objekat.workingOnNumber ,objekat.workingOnNumber],
				["Riješeno - " + objekat.resolvedNumber ,objekat.resolvedNumber]
			],ukupno: objekat.createdNumber,
		dataEsc: [				["Eskalirano - " + objekat.escalatedNumber ,objekat.escalatedNumber],
["Nije eskalirano - " + objekat.notEscalatedNumber ,objekat.notEscalatedNumber]]
});
		}
    }
    handleError=(e)=>{
      console.log(e);
    }
    handleChange=(e)=>
    {
      this.setState({ [e.target.name]: e.target.value });
      console.log(e.target.value);

    }
  render(){
    return(
      <div>
      <div className="panel panel-primary ">
          <div className="panel-heading d-flex w-100 justify-content-between">
              <h1 className="panel-title">Izvještaji</h1>
              <Button bsSize="large">
                  <Glyphicon glyph="download-alt" />
              </Button>
          </div>
          <div className="panel-body">
            <FormGroup>
              <ControlLabel> Odaberite odjel </ControlLabel>
              <FormControl componentClass="select" name="type" onChange={this.handleChange} placeholder="Tip izvjestaja">
                  <option value="" selected disabled>Tip</option>
                  <option value="zahtjevi">Zahtjev</option>
                  <option value="incidenti">Incident</option>
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
					<FormGroup>
								 <ControlLabel> Izvještaj {this.state.type} bez eskalacije: </ControlLabel>
                <PieChart data={this.state.data} label={this.state.type + " - "+this.state.ukupno} label={true} legend={true} legend="bottom"
                 messages={{empty: "Nema podataka"}} download={true} download={this.state.type}/>
								  <ControlLabel> Izvještaj {this.state.type} - eskalacija: </ControlLabel>
								 <PieChart data={this.state.dataEsc} label={this.state.type + " - "+this.state.ukupno} label={true} legend={true} legend="bottom"
									messages={{empty: "Nema podataka"}} download={true} download={this.state.type}/>
									</FormGroup>

      </div>
      </div>

    )
  }
}

export default Izvjestaji;
