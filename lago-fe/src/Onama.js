import React, { Component } from 'react';
import './Style/Onama.css';
import {
  BrowserRouter as Router,
  Route,
  NavLink,
} from 'react-router-dom';


class Onama extends Component {
  render() {
    return (
      <div className="container-image">
        <div className="about">
          <h1>O nama</h1>
          <p> Banka Lago već 45 godina posluje širom Bosne i Hercegovine i ostalih zemalja Balkanskog poluostrva s cljem sigurnog i pouzdanog upravljanja novčanim sredstvima.
          Lago nudi lokalnu stručnost, kao i međunarodni doseg i prati i podupire svoje klijente širom svijeta.
</p>
        </div>
      </div>

    );
  }
}

export default Onama;
