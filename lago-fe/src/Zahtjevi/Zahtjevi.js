import React, { Component } from 'react';
import {Switch, Route} from 'react-router-dom';

import ListaZahtjeva from './ListaZahtjeva';
import Zahtjev from './Zahtjev2';
import '../Style/UserProfile.css';

class Zahtjevi extends Component{
    render(){
      const NZahtjevi = () => <ListaZahtjeva tip='Nedodijeljeni'/>;
      const NZahtjev = (props) => <Zahtjev tip ='Nedodijeljen' {...props}/>;
        return(
            <div if="content">
            <Switch>
                <Route exact path = "/dashboard/zahtjevi" component={ListaZahtjeva}/>
                <Route exact path = "/dashboard/nzahtjevi" component={NZahtjevi}/>
                <Route path ={"/dashboard/zahtjevi"+'/:id'} component={Zahtjev}/>
                <Route path ={"/dashboard/nzahtjevi"+'/:id'} component={NZahtjev}/>
            </Switch>
            </div>
        );
    }
}

export default Zahtjevi;
