import React, { Component } from 'react';

import './Style/Login.css';




class Login extends Component {
  constructor(props){
    super(props);
    this.state = {username: '', password: ''};
    this.onLogin = this.onLogin.bind(this);
    this.onChange = this.onChange.bind(this);
  }

  onLogin(){
   sessionStorage.setItem("username", this.state.username);
   sessionStorage.setItem("rola", "Odjel");
   window.location='/dashboard';
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
                      <input className=" colors login-input" placeholder="Korisničko ime" onFocus="{this.placeholder = ''}" onBur="{this.placeholder = 'Korisničko ime'}"
                          type="text"  onChange={this.onChange}/>
                      <i className="glyphicon glyphicon-user form-control-feedback pull-left" aria-hidden="true" />
                  </div>
                  <div className="form-group has-feedback">
                      <input className="colors login-input" placeholder="Lozinka" onfocus="{this.placeholder = ''}" onblur="{this.placeholder = 'Lozinka'}"
                          type="password" onChange={this.onChange}/>
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
