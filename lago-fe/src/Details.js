import React, { Component } from 'react';
import './Style/Details.css';
import {Row,Col,Grid,Panel} from 'react-bootstrap';
import {PATH_BASE,PATH_USER_DETAILS} from './globals';
import axios from 'axios';
class Details extends Component {

	constructor(props){
		super(props);
		this.state = {
				data: ''
		};
		this.getDetails=this.getDetails.bind(this);

	}


	    componentDidMount(){
	        this.getDetails();
	    }
		getDetails(){
			axios.post(PATH_BASE+PATH_USER_DETAILS, {
					username: sessionStorage.getItem("username")
			})
			.then(this.handleSuccess.bind(this))
			.catch(this.handleError.bind(this));
	}

	handleSuccess(response) {
		console.log(response);
        this.setState({data:response.data});
				  sessionStorage.setItem("id", response.data.id);
    }

handleError(error)
{
	console.log(error);
}


	render() {
			var table = <div></div>;
			var role = sessionStorage.getItem("rola");
			switch(role){
					case 'Administrator':
							table =<div className="form-horizontal profil-forma center-block">

									<div className="form-group profil-form-group">
										<label  className="col-sm-8  ">Korisničko ime:</label>
												<span>{this.state.data.username}</span>
								 	</div>
									<hr className="separator"/>
							  <div className="form-group profil-form-group">
							    <label  className="col-sm-8  ">Ime i prezime:</label>
											<span>{this.state.data.firstname} {this.state.data.lastname}</span>
							 </div>
					 <hr className="separator"/>
				 	<div className="form-group profil-form-group">
						<label  className="col-sm-8  ">Email:</label>
								<span>{this.state.data.email}</span>
				 	</div>
					 <hr className="separator"/>
				</div>;
							break;
							case 'Odjel':
									table =<div className="form-horizontal profil-forma center-block">

											<div className="form-group profil-form-group">
												<label  className="col-sm-8  ">Korisničko ime:</label>
														<span>{this.state.data.username}</span>
											</div>
											<hr className="separator"/>
										<div className="form-group profil-form-group">
											<label  className="col-sm-8  ">Ime i prezime:</label>
													<span>{this.state.data.firstname} {this.state.data.lastname}</span>
									 </div>
							 <hr className="separator"/>
							<div className="form-group profil-form-group">
								<label  className="col-sm-8  ">Email:</label>
										<span>{this.state.data.email}</span>
							</div>
							 <hr className="separator"/>
						</div>;
									break;
							case 'Korisnik':
							table = <div className="form-horizontal profil-forma center-block">

									<div className="form-group profil-form-group">
										<label  className="col-sm-8  ">Korisničko ime:</label>
												<span>{this.state.data.username}</span>
								 	</div>
									<hr className="separator"/>
							  <div className="form-group profil-form-group">
							    <label  className="col-sm-8  ">Ime i prezime:</label>
											<span>{this.state.data.firstname} {this.state.data.lastname}</span>
							 </div>
						<hr className="separator"/>
						<div className="form-group profil-form-group">
						 <label  className="col-sm-8  ">Broj računa:</label>
								 <span>{this.state.data.accountNumber}</span>

					 </div>
					<hr className="separator"/>
					<div className="form-group profil-form-group">
				 	 <label  className="col-sm-8  ">Telefon:</label>
				 			 <span>{this.state.data.phoneNumber}</span>
				 	 </div>
					 <hr className="separator"/>
				 	<div className="form-group profil-form-group">
						<label  className="col-sm-8  ">Email:</label>
								<span>{this.state.data.email}</span>
				 	</div>
					 <hr className="separator"/>
				</div>;
				 break;
			 }

        return (
    	<div>
			<Panel bsStyle="primary">
					<Panel.Heading>
						<div class="d-flex w-100 justify-content-between">
							<Panel.Title componentClass="h2">Pregled korisničkog profila</Panel.Title>
						</div>
					</Panel.Heading>
							{table}
			</Panel>
			</div>

    );
  }
}

export default Details;
