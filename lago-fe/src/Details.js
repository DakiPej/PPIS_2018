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
						<label  className="col-sm-8  ">Korisničko ime:</label>
								<span>sbecirovic</span>
				 	</div>
					<hr className="separator"/>
			  <div className="form-group profil-form-group">
			    <label  className="col-sm-8  ">Ime i prezime:</label>
							<span>Šeila Bećirović</span>
			 </div>
		<hr className="separator"/>
		<div className="form-group profil-form-group">
		 <label  className="col-sm-8  ">Broj računa:</label>
				 <span>0120984029840</span>

	 </div>
	<hr className="separator"/>
	<div className="form-group profil-form-group">
 	 <label  className="col-sm-8  ">Telefon:</label>
 			 <span>061451122</span>
 	 </div>
	 <hr className="separator"/>
 	<div className="form-group profil-form-group">
		<label  className="col-sm-8  ">Email:</label>
				<span>seilabecirovic</span>
 	</div>
	 <hr className="separator"/>
</div>

</div>
    );
  }
}

export default Details;
