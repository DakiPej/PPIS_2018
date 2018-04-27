import React, { Component } from 'react';

class Zahtjevi extends Component{

    constructor(props){
        super(props);
    }

    render(){

        if(this.props.admin == true){
            return(
                <div class="pregled_zahtjeva_incidenata">
             <form>
                 <table id="tabela_zahtjeva_admin" border="1">
                      <tr>
                        <th>#</th>
                        <th>Naziv zahtjeva</th>
                        <th>Datum</th>
                        <th>Prioritet</th>
                        <th>Odjel</th>
                        <th>Status</th>
                        <th></th>
                      </tr>

                      <tr>
                        <td>1</td>
                        <td>Zahtjev 1</td>
                        <td>dd/mm/yyyy</td>
                        <td>
                            <select name="prioritet">
                                <option selected disabled hidden>Prioritet</option>
                                <option value="kriticni">Kritični</option>
                                <option value="veliki">Veliki</option>
                                <option value="srednji">Srednji</option>
                                <option value="mali">Mali</option>
                                <option value="planirano">Planirano</option>
                            </select>
                        </td>
                        <td>
                            <select name="odjel">
                                <option selected disabled hidden>Odjel</option>
                                <option value="dunno">Incident management</option>
                                <option value="sekretarijat">Sekretarijat</option>
                                <option value="marketing_komunikacije">Marketing i komunikacije</option>
                                <option value="IKT">Informaciono komunikacione tehnologije</option>
                                <option value="interna_revizija">Interna revizija</option>
                                <option value="KUPIT">Sprječavanje pranja novca i finansiranja terorizma</option>
                                <option value="rizik_uskladjenost">Kontrola rizika i usklađenosti</option>-->
                            </select>
                        </td>
                        <td>
                            <select name="status">
                                 <option selected disabled hidden>Status</option>
                                  <option value="zavrsen">Obrađen</option>
                                  <option value="u_toku">U obradi</option>
                            </select>
                        </td>
                      </tr>
                </table> 
                 
                <div class="dugmad_opcije">
                    <button type="button" id="potvrda_dugme">Potvrda</button> 
                    <button type="button" id="nazad_dugme">Nazad</button> 
                </div>
            </form>
        </div>
            );
        }else{
            return(
                <div class="pregled_zahtjeva_incidenata">
             <form>
                 <table id="tabela_zahtjeva" border="1">
                      <tr>
                        <th>#</th>
                        <th>Naziv zahtjeva</th>
                        <th>Datum</th>
                        <th>Status</th>
                        <th></th>
                      </tr>

                      <tr>
                        <td>1</td>
                        <td>Zahtjev 1</td>
                        <td>dd/mm/yyyy</td>
                        <td>Obrađen</td>
                      </tr>

                      <tr>
                        <td>2</td>
                        <td>Zahtjev 2</td>
                        <td>dd/mm/yyyy</td>
                        <td>Obrađen</td>
                      </tr>

                      <tr>
                        <td>3</td>
                        <td>Zahtjev 3</td>
                        <td>dd/mm/yyyy</td>
                        <td>U obradi</td>
                      </tr>

                      <tr>
                        <td>4</td>
                        <td>Zahtjev 4</td>
                        <td>dd/mm/yyyy</td>
                        <td>Obrađen</td>
                      </tr>
                </table> 
                 
                <div class="dugmad_opcije">
                    <button type="button" id="dodaj_zahtjev_dugme">+</button> 
                </div>
            </form>
        </div>
            );
        }
    }

}

export default Zahtjevi;