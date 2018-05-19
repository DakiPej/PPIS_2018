import React, { Component } from 'react';
import './Style/Details.css';
import {Row,Col,Grid,Panel} from 'react-bootstrap';
class Details extends Component {

	constructor(props){
		super(props);
		this.state = {
				username:'',
				ime:'',
				prezime:'',
				racun:'',
				telefon:'',
				email:''
		};

	}

	render() {
		var test = {
			username:'sbecirovic',
			ime:'Seila',
			prezime:'Becirovic',
			racun:'12132112',
			telefon:'123123',
			email:'sbecirovic@gmail.com'
		};
			var table = <div></div>;
			var role = sessionStorage.getItem("rola");
			switch(role){
					case 'Administrator':
							table =<div className="form-horizontal profil-forma center-block">

									<div className="form-group profil-form-group">
										<label  className="col-sm-8  ">Korisničko ime:</label>
												<span>{test.username}</span>
								 	</div>
									<hr className="separator"/>
							  <div className="form-group profil-form-group">
							    <label  className="col-sm-8  ">Ime i prezime:</label>
											<span>{test.ime} {test.prezime}</span>
							 </div>
					 <hr className="separator"/>
				 	<div className="form-group profil-form-group">
						<label  className="col-sm-8  ">Email:</label>
								<span>{test.email}</span>
				 	</div>
					 <hr className="separator"/>
				</div>;
							break;
							case 'Odjel':
									table =<div className="form-horizontal profil-forma center-block">

											<div className="form-group profil-form-group">
												<label  className="col-sm-8  ">Korisničko ime:</label>
														<span>{test.username}</span>
											</div>
											<hr className="separator"/>
										<div className="form-group profil-form-group">
											<label  className="col-sm-8  ">Ime i prezime:</label>
													<span>{test.ime} {test.prezime}</span>
									 </div>
							 <hr className="separator"/>
							<div className="form-group profil-form-group">
								<label  className="col-sm-8  ">Email:</label>
										<span>{test.email}</span>
							</div>
							 <hr className="separator"/>
						</div>;
									break;
							case 'Korisnik':
							table = <div className="form-horizontal profil-forma center-block">

									<div className="form-group profil-form-group">
										<label  className="col-sm-8  ">Korisničko ime:</label>
												<span>{test.username}</span>
								 	</div>
									<hr className="separator"/>
							  <div className="form-group profil-form-group">
							    <label  className="col-sm-8  ">Ime i prezime:</label>
											<span>{test.ime} {test.prezime}</span>
							 </div>
						<hr className="separator"/>
						<div className="form-group profil-form-group">
						 <label  className="col-sm-8  ">Broj računa:</label>
								 <span>{test.racun}</span>

					 </div>
					<hr className="separator"/>
					<div className="form-group profil-form-group">
				 	 <label  className="col-sm-8  ">Telefon:</label>
				 			 <span>{test.telefon}</span>
				 	 </div>
					 <hr className="separator"/>
				 	<div className="form-group profil-form-group">
						<label  className="col-sm-8  ">Email:</label>
								<span>{test.email}</span>
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
