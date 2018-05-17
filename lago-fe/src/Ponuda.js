import React,{Component}  from 'react';
import { Card, Button, CardImg, CardTitle, CardText, CardDeck,
 CardSubtitle, CardBody } from 'reactstrap';
import './Style/Ponuda.css';
import a1 from './Images/a1.jpg';
import a2 from './Images/a2.jpg';
import a3 from './Images/a3.jpg';
import a4 from './Images/a4.jpeg';


class Ponuda extends Component{
  constructor(props){
      super(props);
      this.state = {
          naziv:'',
      };
      this.dodaj = this.dodaj.bind(this);
  }
  dodaj(e){
    switch(e.target.id){
      case "IB":
        this.state.naziv="Internet bankarstvo";
        break;
      case "MB":
        this.state.naziv="Mobilno bankarstvo";
        break;
      case "SB":
        this.state.naziv="SMS bankarstvo";
        break;
      case "VB":
        this.state.naziv="Viber bankarstvo";
        break;
    }
    console.log(this.state.naziv);
    localStorage.setItem("nazivZahtjeva", this.state.naziv);
    window.location='/dashboard/newzahtjev';
  }
    render(){
  return (
    <div className="Ponude">
    <CardDeck>
    <Card>
      <CardImg top width="100%" src={a1} alt="Card image cap" />
      <CardBody>
        <CardTitle className="text-primary">Internet bankarstvo</CardTitle>
        <CardText className="text-white">Putem internet aplikacije, korisnicima banke omogućene su usluge pregleda stanja i prometa po računima, prenos sredstava, pregled statusa elektronskih naloga, razmjenu elektronskih poruka s Bankom.</CardText>
        {sessionStorage.getItem("rola") ==="Korisnik" ?
        <Button className="btna" color="primary" size="lg" id="IB" onClick={this.dodaj}>Odaberi</Button>:<div/>}
      </CardBody>
    </Card>
      <Card>
        <CardImg top width="100%" src={a2} alt="Card image cap" />
        <CardBody>
          <CardTitle className="text-primary">Mobilno bankarstvo</CardTitle>
          <CardText className="text-white">Putem mobilne aplikacije, korisnicima banke omogućene su usluge pregleda stanja i detalja računa, obavljanje transakcija između vlastitih računa i računa fizičkih i pravnih lica u Lago banci.</CardText>
          {sessionStorage.getItem("rola") ==="Korisnik" ?
             <Button className="btna" color="primary" size="lg" id="MB" onClick={this.dodaj}>Odaberi</Button>:<div/>}
        </CardBody>
      </Card>
      <Card>
        <CardImg top width="100%" src={a3} alt="Card image cap" />
        <CardBody>
          <CardTitle className="text-primary">Bankarstvo putem Viber-a</CardTitle>
          <CardText className="text-white">Putem Viber aplikacije, korisnicima banke omogućene su usluge slanja novca svim osobama iz korisničkog Viber imenika koji imaju otvoren račun u BiH.</CardText>
          {sessionStorage.getItem("rola") ==="Korisnik" ?
          <Button className="btna" color="primary" size="lg" id="VB"onClick={this.dodaj} >Odaberi</Button>:<div/>}
        </CardBody>
      </Card>
      <Card>
        <CardImg top width="100%" src={a4} alt="Card image cap" />
        <CardBody>
          <CardTitle className="text-primary">Bankarstvo putem SMS-a</CardTitle>
          <CardText className="text-white">Putem SMS poruka, klijentima Lago banke omogućen je pregled stanja svih računa otvorenih u Lago banci, te pružanje usluge informisanja. </CardText>
          {sessionStorage.getItem("rola") ==="Korisnik" ?
          <Button className="btna"  color="primary" size="lg" id="SB" onClick={this.dodaj} >Odaberi</Button>:<div/>}
        </CardBody>
      </Card>
    </CardDeck>
    </div>
  );
};
}
export default Ponuda;
