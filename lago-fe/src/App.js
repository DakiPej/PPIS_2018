import React, { Component } from 'react';
import logo from './Images/logo.svg';
import './Style/App.css';

import {
  BrowserRouter as Router,
  Route,
  NavLink,
} from 'react-router-dom';

import Nav from './common/Nav';
import Main from './Main';

class App extends Component {
  render() {
    return (
      <div>
        <Nav />
        <Main />
      </div>
    );
  }
}

export default App;
