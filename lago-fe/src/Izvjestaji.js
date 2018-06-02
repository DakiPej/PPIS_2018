import React,{ Component }  from "react";
import ReactChartkick, { LineChart, PieChart } from 'react-chartkick'
import Chart from 'chart.js'

import axios from 'axios';
ReactChartkick.addAdapter(Chart)



class Izvjestaji extends Component {

	constructor(props){
		super(props);
		this.state = {
				data: ''
		};

	}
  render(){
    return(
      <div>
      <PieChart data={[["Blueberry", 44], ["Strawberry - 10", 23]]} label="Incidenti" legend={true} legend="bottom"
      suffix="%" messages={{empty: "Nema podataka"}} download={true} download="Incidenti"/>
      </div>
    )
  }
}

export default Izvjestaji;
