import React, { Component } from 'react';
import { Sidebar, SidebarItem } from 'react-responsive-sidebar';
import './Style/UserProfile.css';


import Details from './Details';
import {
  BrowserRouter as Router,
  Route,
  NavLink,
} from 'react-router-dom';
const navigationItems = [
  <NavLink className="link" to="/profile" activeClassName="active">
    <SidebarItem>
      <span className="nav-title">Korisnički podaci</span>
    </SidebarItem>
  </NavLink>,
  <NavLink className="link" to="/zahtjevi" activeClassName="active">
    <SidebarItem>
      <span className="nav-title">Zahtjevi</span>
    </SidebarItem>
  </NavLink>,
  <NavLink className="link" to="/incidenti" activeClassName="active">
    <SidebarItem>
      <span className="nav-title">Incidenti</span>
    </SidebarItem>
  </NavLink>,
  <NavLink className="link" to="/nesto" activeClassName="active">
    <SidebarItem>
      <span className="nav-title">Novi zahtjev</span>
    </SidebarItem>
  </NavLink>,
  <NavLink className="link" to="/" activeClassName="active">
    <SidebarItem>
      <span className="nav-title" >Novi incident</span>
    </SidebarItem>
  </NavLink>,
];



class UserProfile extends Component {
  render() {

      return (
          <div className="body">

            <Router>
              <div id="main-content-wrapper">
                <Sidebar  content={navigationItems}
                  margin-left="20px"
                  background="rgba(52,58,64,0.9)"
                  z-index="3"
                  color="white"
                  width="200">
                </Sidebar>
                <div id="inner-content-wrapper">
                  <Route path="/profile" component={Details}/>

                </div>
              </div>
            </Router>
          </div>

    );
  }
}

export default UserProfile;
