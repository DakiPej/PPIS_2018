import React from 'react';
import {
  Collapse,
  Navbar,
  NavbarToggler,
  NavbarBrand,
  Nav,
  NavItem,
  NavLink,
  UncontrolledDropdown,
  DropdownToggle,
  DropdownMenu,
  DropdownItem } from 'reactstrap';
import logo from './Images/lago.png';

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
        <Navbar color="dark" className="navbar-dark navbar-expand-md">
          <NavbarBrand href="/" > <img src={logo}/>
          </NavbarBrand>
          <NavbarToggler onClick={this.toggle} />
          <Collapse isOpen={this.state.isOpen} navbar>
            <Nav className="ml-auto" navbar>
              <NavItem>
                <NavLink className="text-white h4" href="/">Početna</NavLink>
              </NavItem>
              <NavItem>
                <NavLink className="text-white h4" href="/ponuda">Ponuda</NavLink>
              </NavItem>
              <NavItem>
                <NavLink className="text-white h4" href="/onama">O nama</NavLink>
              </NavItem>
              <NavItem>
                <NavLink className="text-info h4" href="/prijava">Prijava</NavLink>
              </NavItem>
            </Nav>
          </Collapse>
        </Navbar>
      </div>
    );
  }
}
export default Navi;
