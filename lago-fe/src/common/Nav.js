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
        <Navbar color="dark" className="navbar-dark navbar-expand-md">
          <NavbarBrand href="/" > <img height="50px"src={logo}/>
          </NavbarBrand>
          <NavbarToggler onClick={this.toggle} />
          <Collapse isOpen={this.state.isOpen} navbar>
            <Nav className="ml-auto" navbar>
              <NavItem>
                <NavLink className="text-white 18px" href="/"><b>Početna</b></NavLink>
              </NavItem>
              <NavItem>
                <NavLink className="text-white 18px" href="/ponuda"><b>Ponuda</b></NavLink>
              </NavItem>
              <NavItem>
                <NavLink className="text-white 18px" href="/onama"><b>O nama</b></NavLink>
              </NavItem>
              {this.state.ulogovan ?
                <NavItem>
                  <NavLink className="text-info 18px" href="/prijava"><b>Ma nemoj</b></NavLink>
                </NavItem> :
              <NavItem>
                <NavLink className="text-info 18px" href="/prijava"><b>Prijava</b></NavLink>
              </NavItem>
            }
            </Nav>
          </Collapse>
        </Navbar>
      </div>
    );
  }
}
export default Navi;
