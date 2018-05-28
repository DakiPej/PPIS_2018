import React, { Component } from 'react';
import {PATH_BASE,PATH_LOGIN} from './globals';
import './Style/Login.css';

import axios from 'axios';




class Login extends Component {
  constructor(props){
    super(props);
    this.state = {username: '', password: ''};
    this.onLogin = this.onLogin.bind(this);
    this.onChange = this.onChange.bind(this);
  }

  onLogin(event){

    event.preventDefault();

       if(this.state.username && this.state.password) {
               axios.post(PATH_BASE+PATH_LOGIN, {
                   username: this.state.username,
                   password: this.state.password
               })
               .then(this.handleSuccess.bind(this))
               .catch(this.handleError.bind(this));
           }

  }
  handleSuccess(response) {
        sessionStorage.setItem("rola", response.data);
        sessionStorage.setItem("username",this.state.username);
        window.location = '/dashboard';
    }

    handleError(error) {
        console.log(error);
    }

  onChange(e) {
      this.setState({[e.target.name]:e.target.value});
  }

  render() {
    return (
      <div>
        <div className="LoginPage">
          <div className="container-fluid">
            <div className="forma text-center">

             <form className="v-center form-horizontal ">

                <div className="login-input-wrapper">
                  <div className="form-group has-feedback">
                      <input className=" colors login-input" placeholder="Korisničko ime" onfocus="{this.placeholder = ''}" onblur="{this.placeholder = 'Korisničko ime'}"
                          name="username" type="text"  onChange={this.onChange}/>
                      <i className="glyphicon glyphicon-user form-control-feedback pull-left" aria-hidden="true" />
                  </div>
                  <div className="form-group has-feedback">
                      <input className="colors login-input" placeholder="Lozinka" onfocus="{this.placeholder = ''}" onblur="{this.placeholder = 'Lozinka'}"
                        name="password"  type="password" onChange={this.onChange}/>
                      <i className="glyphicon glyphicon-lock form-control-feedback pull-left" aria-hidden="true" />
                  </div>
               </div>
                <div className="form-group">
                  <button type="button" className="btn btn-primary btn-outline btn-login" onClick={this.onLogin}>LOGIN</button>
                </div>

              </form>
          </div>
          </div>
        </div>
      </div>
    );
  }
}

export default Login;
