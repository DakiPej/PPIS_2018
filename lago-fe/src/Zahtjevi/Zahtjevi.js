import React, { Component } from 'react';
import {Switch, Route} from 'react-router-dom';

import ListaZahtjeva from './ListaZahtjeva';
import Zahtjev from './Zahtjev';
import '../Style/UserProfile.css';

class Zahtjevi extends Component{
    render(){
        return(
            <div if="content">
            <Switch>
                <Route exact path = {this.props.match.url} component={ListaZahtjeva}/>
                <Route path ={this.props.match.url+'/:id'} component={Zahtjev}/>
            </Switch>
            </div>
        );
    }
}

export default Zahtjevi;
