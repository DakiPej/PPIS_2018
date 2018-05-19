import React, { Component } from 'react';
import { Sidebar, SidebarItem } from 'react-responsive-sidebar';
import { Switch, Route, NavLink} from 'react-router-dom';

import './Style/UserProfile.css';

import Details from './Details';
import Zahtjevi from './Zahtjevi/Zahtjevi';
import Incidenti from './Incidenti/Incidenti';
import IncidentForm from './Incidenti/IncidentForm';
import ZahtjevForm from './Zahtjevi/ZahtjevForm';




//rgba(52,58,64,0.9)
class Dashboard extends Component{

    render(){
      const NZahtjevi = () => <Zahtjevi tip='Nedodijeljeni'/>;
      const NIncidenti = () => <Incidenti tip='Nedodijeljeni'/>;

        var navigationItems=[];

        var rola =sessionStorage.getItem("rola");
        switch(rola){
          case 'Korisnik':
          navigationItems = [
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
              <NavLink className="link" to="/dashboard/newzahtjev" activeClassName="active">
                <SidebarItem>
                  <span className="nav-title">Novi zahtjev</span>
                </SidebarItem>
              </NavLink>,
              <NavLink className="link" to="/dashboard/newIncident" activeClassName="active">
                <SidebarItem>
                  <span className="nav-title" >Novi incident</span>
                </SidebarItem>
              </NavLink>,
            ];
            break;
            case 'Administrator':
            navigationItems = [
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
              ];
              break;
            case 'Odjel':
            navigationItems = [
                <NavLink className="link" to="/dashboard/profile" activeClassName="active">
                  <SidebarItem>
                    <span className="nav-title">Korisnički podaci</span>
                  </SidebarItem>
                </NavLink>,
                <NavLink className="link" to="/dashboard/nzahtjevi" activeClassName="active">
                  <SidebarItem>
                    <span className="nav-title">Nedodijeljeni zahtjevi</span>
                  </SidebarItem>
                </NavLink>,
                <NavLink className="link" to="/dashboard/nincidenti" activeClassName="active">
                  <SidebarItem>
                    <span className="nav-title">Nedodijeljeni incidenti</span>
                  </SidebarItem>
                </NavLink>,
                <NavLink className="link" to="/dashboard/zahtjevi" activeClassName="active">
                  <SidebarItem>
                    <span className="nav-title">Preuzeti zahtjevi</span>
                  </SidebarItem>
                </NavLink>,
                <NavLink className="link" to="/dashboard/incidenti" activeClassName="active">
                  <SidebarItem>
                    <span className="nav-title" >Preuzeti incidenti</span>
                  </SidebarItem>
                </NavLink>,
              ];
              break;
        }
        return(
            <div id="main-content-wrapper">
                <div class="navigation">
                <Sidebar  content={navigationItems}
                    margin-left="20px"
                    background="#222"
                    z-index="3"
                    color="white"
                    width="15%"
                    font-size="1em"
                    height="100%"
                    >
                </Sidebar>
                </div>
                <div id="inner-content-wrapper">
                <div id="content">
                <Switch>
                    <Route exact path='/dashboard' component={Details}/>
                    <Route path="/dashboard/profile" component={Details}/>
                    <Route path="/dashboard/zahtjevi" component= {Zahtjevi}/>
                    <Route path="/dashboard/nzahtjevi" component={NZahtjevi}/>
                    <Route path="/dashboard/newzahtjev" component={ZahtjevForm}/>
                    <Route path="/dashboard/incidenti" component={Incidenti}/>
                    <Route path="/dashboard/nincidenti" component={NIncidenti}/>
                    <Route path="/dashboard/newIncident" component={IncidentForm}/>
                </Switch>
                </div>
                </div>
            </div>
        );
    }

}

export default Dashboard;
