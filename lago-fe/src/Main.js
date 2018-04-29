import React from 'react';
import { Switch, Route} from 'react-router-dom';

import Pocetna from './Pocetna';
import Onama from './Onama';
import Ponuda from './Ponuda';
import Login from './Login';    
import Dashboard from './Dashboard';
import Details from './Details';
import Zahtjevi from './Forms/Zahtjevi';
import Incidenti from './Forms/Incidenti';

const Main = () => (

    <main>
        <Switch>
            <Route exact path="/" component={Pocetna}/>
            <Route exact path="/onama" component={Onama}/>
            <Route exact path="/ponuda" component={Ponuda}/>
            <Route exact path="/prijava" component={Login}/>
            <Route path="/dashboard" component={Dashboard}/>
        </Switch>
    </main>

)

export default Main;
