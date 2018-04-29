import React, { Component } from 'react';
import { Sidebar, SidebarItem } from 'react-responsive-sidebar';
import { Switch, Route, NavLink} from 'react-router-dom';

import './Style/UserProfile.css';

import Details from './Details';
import Zahtjevi from './Forms/Zahtjevi';
import Incidenti from './Incidenti/Incidenti'; 


const navigationItems = [
    <NavLink className="link" to="/dashboard/profile" activeClassName="active">
      <SidebarItem>
        <span className="nav-title">Korisnički podaci</span>
      </SidebarItem>
    </NavLink>,
    <NavLink className="link" to="/dashboard/zahtjevi" activeClassName="active">
      <SidebarItem>
        <span className="nav-title">Zahtjevi</span>
      </SidebarItem>
    </NavLink>,
    <NavLink className="link" to="/dashboard/incidenti" activeClassName="active">
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
  

class Dashboard extends Component{

    render(){
        return(
            <div id="main-content-wrapper">
                <Sidebar  content={navigationItems}
                    margin-left="20px"
                    background="rgba(52,58,64,0.9)"
                    z-index="3"
                    color="white"
                    width="200"
                    height="100%">
                </Sidebar>
                <div id="inner-content-wrapper">
                <Switch>
                    <Route exact path='/dashboard' component={Details}/>
                    <Route path="/dashboard/profile" component={Details}/>
                    <Route path="/dashboard/zahtjevi" component= {Zahtjevi}/>
                    <Route path="/dashboard/incidenti" component={Incidenti}/>
                </Switch>
                </div>
            </div>
        );
    }

}

export default Dashboard;

