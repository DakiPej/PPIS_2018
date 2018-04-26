import React, { Component } from 'react';
import './Style/Details.css';

class Details extends Component {

	constructor(props){
		super(props);}


  	render() {

        return (
    	<div className="body">
				<div className="row">
			<h1 className="main-naslov">Pregled korisničkog profila</h1>
			</div>
	<div className="form-horizontal profil-forma center-block">
					<div className="form-group profil-form-group">
						<label  className="col-sm-2 control-label">Korisničko ime:</label>
								<span>sbecirovic</span>
				 	</div>
					<hr className="separator"/>
			  <div className="form-group profil-form-group">
			    <label  className="col-sm-2 control-label">Ime:</label>
							<span>Šeila</span>
			 </div>
			<hr className="separator"/>
			 <div className="form-group profil-form-group">
				 <label  className="col-sm-2 control-label">Prezime:</label>
						 <span>Bećirović</span>
			</div>
			<hr className="separator"/>
			<div className="form-group profil-form-group">
				<label  className="col-sm-2 control-label">JMBG:</label>
						<span>0112995177652</span>
		 </div>
		 <hr className="separator"/>
		 <div className="form-group profil-form-group">
			 <label  className="col-sm-2 control-label">Datum rođenja:</label>
					 <span>01.12.1995.</span>
		</div>
		<hr className="separator"/>
		<div className="form-group profil-form-group">
		 <label  className="col-sm-2 control-label">Broj kartice:</label>
				 <span>0120984029840</span>
	 </div>
	 <hr className="separator"/>
	 <div className="form-group profil-form-group">
		 <label  className="col-sm-2 control-label">Adresa:</label>
				 <span>R Boskovica 384</span>
	</div>
	<hr className="separator"/>
	<div className="form-group profil-form-group">
 	 <label  className="col-sm-2 control-label">Telefon:</label>
 			 <span>061451122</span>
 	 </div>
	 <hr className="separator"/>
 	<div className="form-group profil-form-group">
		<label  className="col-sm-2 control-label">Email:</label>
				<span>seilabecirovic</span>
 	</div>
	<hr className="separator"/>
</div>

</div>
    );
  }
}

export default Details;
