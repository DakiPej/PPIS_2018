import React, { Component } from 'react';
import {Switch, Route} from 'react-router-dom';

import ListaIncidenata from './ListaIncidenata';
import Incident from './Incident';
import '../Style/UserProfile.css';

class Incidenti extends Component{
    render(){
      const NIncidenti = () => <ListaIncidenata tip='Nedodijeljeni'/>;

        return(
            <div if="content">
            <Switch>
                  <Route exact path = "/dashboard/incidenti" component={ListaIncidenata}/>
                  <Route exact path = "/dashboard/nincidenti" component={NIncidenti}/>
                  <Route path ={"/dashboard/incidenti"+'/:id'} component={Incident}/>
                  <Route path ={"/dashboard/nincidenti"+'/:id'} component={Incident}/>
            </Switch>
            </div>
        );
    }
}

export default Incidenti;
