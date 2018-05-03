import React, { Component } from 'react';

class Incident extends Component{

    constructor(props){
        super(props);
        this.state = {
            id:-1,
            naziv:'',
            korisnik:'',
            opis:'',
            prioritet:'',
            status:'',
            odjel:'',
            datum:null
        };
    }

    render(){
        return(
            <div>
            </div>
        );
    }

}

export default Incident;