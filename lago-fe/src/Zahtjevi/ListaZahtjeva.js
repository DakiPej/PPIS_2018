import React, { Component } from 'react';
import {
    ListGroup,
    ListGroupItem,
    Button,
    Row,
    Col,
    Grid,
    FormGroup,
    FormControl,
    Table,
    Panel,
    Glyphicon
} from 'react-bootstrap';
import { BootstrapTable, TableHeaderColumn } from 'react-bootstrap-table';
import { bootstrapUtils } from 'react-bootstrap/lib/utils';
import { PATH_BASE, PATH_REQUESTS } from '../globals';

import axios from 'axios';

bootstrapUtils.addStyle(ListGroup, 'custom');


function enumFormatter(cell, row, enumObject) {
    return enumObject[cell];
}

class ListaZahtjeva extends Component {

    constructor(props) {
        super(props);

        this.state = {
            sortName: undefined,
            sortOrder: undefined,
            requests: []
        }
        this.role = sessionStorage.getItem("rola");
        this.getZahtjevi = this.getZahtjevi.bind(this);
        this.onSortChange = this.onSortChange.bind(this);
        this.onRowClick = this.onRowClick.bind(this);
    }

    componentDidMount() {
        this.getZahtjevi();
    }
    onRowClick(row) {
        console.log("ID reda" + row.id);
        window.location = '/dashboard/zahtjevi/' + row.id;
    }

    onSortChange(sortName, sortOrder) {
        console.info('onSortChange', arguments);
        this.setState({
            sortName,
            sortOrder
        });
    }

    getZahtjevi() {
        if (this.props.tip === "Nedodijeljeni") {
            axios.get("http://localhost:8080/requests/unsigned/" + sessionStorage.getItem("username"))
                .then(this.handleSuccess.bind(this))
                .catch(this.handleError.bind(this));
        }
        else {
            axios.get("http://localhost:8080/requests" + sessionStorage.getItem("username"))
                .then(this.handleSuccess.bind(this))
                .catch(this.handleError.bind(this));
        }

    }

    handleSuccess(response) {
        console.log(response.data);
    }

    handleError(error) {
        console.log(error);
    }

    render() {

        var table = <div></div>;
        //var role = sessionStorage.getItem("rola");

        const options = {
            sortName: this.state.sortName,
            sortOrder: this.state.sortOrder,
            onSortChange: this.onSortChange,
            onRowClick: this.onRowClick
        };

        const pType ={
            1:"Nizak",
           2:"Srednji",
            3:"Visok"
        };
        const hType={
          1:"Niska hitnost",
          2:"Srednja hitnost",
          3:"Visoka hitnost",
        }

        const sType = {
            'nedodijeljen':'Nedodijeljen',
            'u obradi':'U obradi',
            'rijesen':'Rijesen',
            'zatvoren':'Zatvoren'
        }
        const eType = {
            'true':'Da',
            'false':'Ne'
        }

        switch (this.role) {
            case 'Administrator':
                table =
                    <BootstrapTable data={this.state.data} options={options}>
                        <TableHeaderColumn hidden isKey dataField='id' dataSort>#</TableHeaderColumn>
                        <TableHeaderColumn dataField='naslov' dataSort filter={{ type: 'TextFilter', delay: 500 }}>Naslov</TableHeaderColumn>
                        <TableHeaderColumn dataField='korisnik' dataSort filter={{ type: 'TextFilter', delay: 500 }}>Prijavio</TableHeaderColumn>
                        <TableHeaderColumn dataField='korisnik' dataSort filter={{ type: 'TextFilter', delay: 500 }}>Rješava</TableHeaderColumn>
                        <TableHeaderColumn dataField='prioritet' dataSort
                            filterFormatted dataFormat={enumFormatter} formatExtraData={pType}
                            filter={{ type: 'SelectFilter', options: pType }}
                        >Prioritet
                        </TableHeaderColumn>
                        <TableHeaderColumn dataField='prioritet' dataSort
                            filterFormatted dataFormat={enumFormatter} formatExtraData={pType}
                            filter={{ type: 'SelectFilter', options: pType }}
                        >Hitnost
                        </TableHeaderColumn>
                        <TableHeaderColumn dataField='datum' dataSort filter={{ type: 'TextFilter', delay: 500 }}>Datum prijave</TableHeaderColumn>
                        <TableHeaderColumn dataField='datum' dataSort filter={{ type: 'TextFilter', delay: 500 }}>Datum rješavanja</TableHeaderColumn>
                        <TableHeaderColumn dataField='odjel' dataSort filter={{ type: 'TextFilter', delay: 500 }} >Odjel</TableHeaderColumn>
                        <TableHeaderColumn dataField='status' dataSort
                            filterFormatted dataFormat={enumFormatter} formatExtraData={sType}
                            filter={{ type: 'SelectFilter', options: sType }}
                        >Status</TableHeaderColumn>
                        <TableHeaderColumn dataField='eskalacija' dataSort
                            filterFormatted dataFormat={enumFormatter} formatExtraData={eType}
                            filter={{ type: 'SelectFilter', options: eType }}
                        >Eskalacija</TableHeaderColumn>
                    </BootstrapTable>;
                break;
            case 'Korisnik':
                table =
                    <BootstrapTable data={this.state.data} options={options}>
                        <TableHeaderColumn isKey dataField='id' dataSort hidden>#</TableHeaderColumn>
                        <TableHeaderColumn dataField='naslov' dataSort filter={{ type: 'TextFilter', delay: 500 }}>Naslov</TableHeaderColumn>
                        <TableHeaderColumn dataField='prioritet' dataSort
                            filterFormatted dataFormat={enumFormatter} formatExtraData={pType}
                            filter={{ type: 'SelectFilter', options: pType }}
                        >Hitnost
                        </TableHeaderColumn>
                        <TableHeaderColumn dataField='datum' dataSort filter={{ type: 'TextFilter', delay: 500 }}>Datum prijave</TableHeaderColumn>
                        <TableHeaderColumn dataField='datum' dataSort filter={{ type: 'TextFilter', delay: 500 }}>Datum rješavanja</TableHeaderColumn>
                        <TableHeaderColumn dataField='status' dataSort
                            filterFormatted dataFormat={enumFormatter} formatExtraData={sType}
                            filter={{ type: 'SelectFilter', options: sType }}
                        >Status</TableHeaderColumn>
                    </BootstrapTable>;
                break;
            case 'Odjel':
                if (this.props.tip === 'Nedodijeljeni')
                    table =
                        <BootstrapTable data={this.state.data} options={options}>
                            <TableHeaderColumn isKey dataField='id' dataSort hidden>#</TableHeaderColumn>
                            <TableHeaderColumn dataField='naslov' dataSort filter={{ type: 'TextFilter', delay: 500 }}>Naslov</TableHeaderColumn>
                            <TableHeaderColumn dataField='korisnik' dataSort filter={{ type: 'TextFilter', delay: 500 }}>Korisnik</TableHeaderColumn>
                            <TableHeaderColumn dataField='prioritet' dataSort
                                filterFormatted dataFormat={enumFormatter} formatExtraData={pType}
                                filter={{ type: 'SelectFilter', options: pType }}
                            >Prioritet
                      </TableHeaderColumn>
                            <TableHeaderColumn dataField='datum' dataSort filter={{ type: 'TextFilter', delay: 500 }}>Datum prijave</TableHeaderColumn>
                        </BootstrapTable>;
                else
                    table =
                        <BootstrapTable data={this.state.data} options={options}>
                            <TableHeaderColumn hidden isKey dataField='id' dataSort>#</TableHeaderColumn>
                            <TableHeaderColumn dataField='naslov' dataSort filter={{ type: 'TextFilter', delay: 500 }}>Naslov</TableHeaderColumn>
                            <TableHeaderColumn dataField='korisnik' dataSort filter={{ type: 'TextFilter', delay: 500 }}>Prijavio</TableHeaderColumn>
                            <TableHeaderColumn dataField='prioritet' dataSort
                                filterFormatted dataFormat={enumFormatter} formatExtraData={pType}
                                filter={{ type: 'SelectFilter', options: pType }}
                            >Prioritet
                  </TableHeaderColumn>
                            <TableHeaderColumn dataField='datum' dataSort filter={{ type: 'TextFilter', delay: 500 }}>Datum prijave</TableHeaderColumn>
                            <TableHeaderColumn dataField='datum' dataSort filter={{ type: 'TextFilter', delay: 500 }}>Datum rješavanja</TableHeaderColumn>
                            <TableHeaderColumn dataFieldKorisnik='status' dataSort
                                filterFormatted dataFormat={enumFormatter} formatExtraData={sType}
                                filter={{ type: 'SelectFilter', options: sType }}
                            >Status</TableHeaderColumn>
                            <TableHeaderColumn dataField='eskalacija' dataSort
                                filterFormatted dataFormat={enumFormatter} formatExtraData={eType}
                                filter={{ type: 'SelectFilter', options: eType }}
                            >Eskalacija</TableHeaderColumn>
                        </BootstrapTable>;
        }

        return (
            <div>
                <Panel bsStyle="primary">
                    <Panel.Heading>
                        <div class="d-flex w-100 justify-content-between">
                            <Panel.Title componentClass="h2">Lista zahtjeva </Panel.Title>
                            <Button >
                                <Glyphicon glyph="download-alt" />
                            </Button>
                        </div>
                    </Panel.Heading>
                    {table}
                </Panel>
            </div>
        );

    }

}

export default ListaZahtjeva;

// Backup if needed

/*

class Zahtjevitem extends Component{

    constructor(props){
        super(props);

        this.state = {
            id: props.item.id,
            naslov: props.item.naslov,
            datum: props.item.datum,
            status: props.item.status,
            odjel: props.item.odjel,
            prioritet: props.item.prioritet,
            odjel: props.item.odjel,
            korisnik: props.item.korisnik
        }

        this.onSelectChange = this.onSelectChange.bind(this);
    }

    deleteIncident(){
        alert("Incident je izbrisan!");
        window.location.reload();
    }

    onSelectChange(e){
        var selected = e.target.options.selectedIndex;
        console.log(e.target.name + ' ' + e.target.options[selected].value);
        this.setState({[e.target.name]:e.target.options[selected].value});
    }

    render(){

        //var date = new Date(this.state.datum);

        var incident = null;
        var rola = "Korisnik";

        switch (rola){
            case "Korisnik":
                incident =
                        <tr>
                            <td>{this.state.id}</td>
                            <td>{this.state.naslov}</td>
                            <td>{this.state.datum}</td>
                            <td>{this.state.status}</td>
                            <td><Button bsStyle="danger" onClick={this.deleteIncident.bind(this)}><Glyphicon glyph="trash" /></Button></td>
                        </tr>
                break;
            case "Administrator":
                incident =
                        <tr>
                            <td >{this.state.id}</td>
                            <td>{this.state.korisnik}</td>
                            <td>{this.state.naslov}</td>
                            <td>{this.state.datum}</td>
                            <td>{this.state.status}</td>
                            <td>
                            <FormGroup controlId="formControlsSelect">
                                <FormControl componentClass="select" name="odjel" placeholder="Odjel" onChange={this.onSelectChange}>
                                    <option value="Odjel 1">Odjel 1</option>
                                    <option value="Odjel 2">Odjel 2</option>
                                    <option value="Odjel 3">Odejl 3</option>
                                </FormControl>
                            </FormGroup>
                            </td>
                            <td>
                            <FormGroup controlId="formControlsSelect">
                                <FormControl componentClass="select" name="prioritet" placeholder="Prioritet" onChange={this.onSelectChange}>
                                    <option value="Visok">Visok</option>
                                    <option value="Normalan">Normalan</option>
                                    <option value="Nizak">Nizak</option>
                                </FormControl>
                            </FormGroup>
                            </td>
                            <td><Button bsStyle="info" style={{width:"100%"}} onClick={this.deleteIncident.bind(this)}><Glyphicon glyph="eye-open" style={{color:"white", fontSize:"18px"}} /></Button></td>
                        </tr>
            break;
        }


        return(
            incident
        );
    }

}

*/
