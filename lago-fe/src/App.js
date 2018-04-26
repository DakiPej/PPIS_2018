import React, { Component } from 'react';
import logo from './Images/logo.svg';
import './Style/App.css';

import {
  BrowserRouter as Router,
  Route,
  NavLink,
} from 'react-router-dom';

import Nav from './Nav';
import Pocetna from './Pocetna';
import Login from './Login';
import Onama from './Onama';
import Ponuda from './Ponuda';
import UserProfile from './UserProfile';

class App extends Component {
  render() {
    return (
      <div>
        <div className="Nav">
          <Nav />
        </div>

          <Router>
            <div className="Element">
              <Route exact path="/" component={Pocetna}/>
              <Route exact path="/ponuda" component={Ponuda}/>
              <Route exact path="/onama" component={Onama}/>
              <Route exact path="/prijava" component={Login}/>
              <Route exact path="/profile" component={UserProfile}/>
            </div>
          </Router>

      </div>

    );
  }
}

export default App;
