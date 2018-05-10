import React, { Component } from 'react';

import './Style/Login.css';




class Login extends Component {
  constructor(props){
    super(props);
    this.state = {username: '', password: ''};
    this.onChangePassword = this.onChangePassword.bind(this);
    this.onChangeUsername = this.onChangeUsername.bind(this);
    this.onLogin = this.onLogin.bind(this);

  }
  onChangePassword(event){
    this.setState({password: event.target.value});
  }
  onChangeUsername(event){
    this.setState({username: event.target.value});
  }

  onLogin(){
      this.state.ulogovan=true;
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
                      <input className=" colors login-input" placeholder="Korisničko ime" onFocus="{this.placeholder = ''}" onBlur="{this.placeholder = 'Korisničko ime'}"
                          type="text" onChange={this.onChangeUsername} />
                      <i className="glyphicon glyphicon-user form-control-feedback pull-left" aria-hidden="true" />
                  </div>
                  <div className="form-group has-feedback">
                      <input className="colors login-input" placeholder="Lozinka" onFocus="{this.placeholder = ''}" onBlur="{this.placeholder = 'Lozinka'}"
                          type="password" onChange={this.onChangePassword}/>
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
