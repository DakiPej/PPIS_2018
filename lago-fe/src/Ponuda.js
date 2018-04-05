import React from 'react';
import { Card, Button, CardImg, CardTitle, CardText, CardDeck,
 CardSubtitle, CardBody } from 'reactstrap';
import './Style/Ponuda.css';
import a1 from './Images/a1.jpg';
import a2 from './Images/a2.jpg';
import a3 from './Images/a3.jpg';
import a4 from './Images/a4.jpeg';

const Ponuda = (props) => {
  return (
    <div className="Ponude">
    <CardDeck>
    <Card>
      <CardImg top width="100%" src={a1} alt="Card image cap" />
      <CardBody>
        <CardTitle className="text-info">Internet bankarstvo</CardTitle>
        <CardText className="text-white">Putem internet aplikacije, korisnicima banke omogućene su usluge pregleda stanja i prometa po računima, prenos sredstava, pregled statusa elektronskih naloga, razmjenu elektronskih poruka s Bankom.</CardText>
      </CardBody>
    </Card>
      <Card>
        <CardImg top width="100%" src={a2} alt="Card image cap" />
        <CardBody>
          <CardTitle className="text-info">Mobilno bankarstvo</CardTitle>
          <CardText className="text-white">Putem mobilne aplikacije, korisnicima banke omogućene su usluge pregleda stanja i detalja računa, obavljanje transakcija između vlastitih računa i računa fizičkih i pravnih lica u Lago banci.</CardText>
        </CardBody>
      </Card>
      <Card>
        <CardImg top width="100%" src={a3} alt="Card image cap" />
        <CardBody>
          <CardTitle className="text-info">Bankarstvo putem Viber-a</CardTitle>
          <CardText className="text-white">Putem Viber aplikacije, korisnicima banke omogućene su usluge slanja novca svim osobama iz korisničkog Viber imenika koji imaju otvoren račun u BiH.</CardText>
        </CardBody>
      </Card>
      <Card>
        <CardImg top width="100%" src={a4} alt="Card image cap" />
        <CardBody>
          <CardTitle className="text-info">Bankarstvo putem SMS-a</CardTitle>
          <CardText className="text-white">Putem SMS poruka, klijentima Lago banke omogućen je pregled stanja svih računa otvorenih u Lago banci, te pružanje usluge informisanja. </CardText>
        </CardBody>
      </Card>
    </CardDeck>
    </div>
  );
};

export default Ponuda;
