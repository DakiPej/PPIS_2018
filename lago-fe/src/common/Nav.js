import React from 'react';
import {
  Collapse,
  Navbar,
  Nav,
  NavItem,
  NavLink,
  UncontrolledDropdown,
  DropdownToggle,
  DropdownMenu,
  DropdownItem } from 'react-bootstrap';
import logo from '../Images/lago.png';



class Navi extends React.Component {
  constructor(props) {
    super(props);

    this.toggle = this.toggle.bind(this);
        this.state = {
          isOpen: false
        };
      }
      toggle() {
        this.setState({
          isOpen: !this.state.isOpen
        });
      }
  render() {
    return (
      <div>
        <Navbar inverse style={{marginBottom: "0"}}  className="navbar navbar-expand-md">
          <Navbar.Brand href="/" > <img height="50px"src={logo}/>
          </Navbar.Brand>
          <Navbar.Toggle onClick={this.toggle} />
          <Navbar.Collapse isOpen={this.state.isOpen} navbar>
            <Nav className="ml-auto" navbar>
              <NavItem href="/">
                <b>Početna</b>
              </NavItem>
              <NavItem href="/ponuda">
                <b>Ponuda</b>
              </NavItem>
              <NavItem href="/onama">
                <b>O nama</b>
              </NavItem>
              {sessionStorage.getItem("username") !== null ?
                <NavItem href="/dashboard">
                  <b>Dashboard</b>
              </NavItem>:<div/>} 
               {sessionStorage.getItem("username") !== null ?
                 <NavItem href="javascript:sessionStorage.clear();window.location='/';">
                <b>Logout</b>
                </NavItem> :
              <NavItem href="/prijava">
                <b>Prijava</b>
              </NavItem>
            }
            </Nav>
          </Navbar.Collapse>
        </Navbar>
      </div>
      
    );
  }
}
export default Navi;
