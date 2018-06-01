import React, {Component} from 'react';
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
    Glyphicon,
    Panel} from 'react-bootstrap';
import {BootstrapTable, TableHeaderColumn} from 'react-bootstrap-table';
import {bootstrapUtils} from 'react-bootstrap/lib/utils';
import {PATH_BASE,PATH_INCIDENTS,PATH_INCIDENTS_GET,PATH_UN_INCIDENTS_GET} from '../globals';

import axios from 'axios';
bootstrapUtils.addStyle(ListGroup, 'custom');


function enumFormatter(cell, row, enumObject) {
    return enumObject[cell];
}

class ListaIncidenata extends Component{

    constructor(props){
        super(props);

        this.state = {
            data: '',
            sortName: undefined,
            sortOrder: undefined
        }

        this.getIncidenti = this.getIncidenti.bind(this);
        this.onSortChange = this.onSortChange.bind(this);
        this.onRowClick = this.onRowClick.bind(this);

    }

    componentDidMount(){
        this.getIncidenti();
    }

    onRowClick(row) {
        console.log("ID reda"+row.id);
        if (this.props.tip==='Nedodijeljeni')
        {
        window.location='/dashboard/nincidenti/'+row.id;}
        else window.location='/dashboard/incidenti/'+row.id;
    }
    onSortChange(sortName, sortOrder) {
        console.info('onSortChange', arguments);
        this.setState({
          sortName,
          sortOrder
        });
    }

    getIncidenti(){

      if (this.props.tip==='Nedodijeljeni')
      {
        axios.post(PATH_BASE+PATH_INCIDENTS+PATH_UN_INCIDENTS_GET, {
            username:  sessionStorage.getItem("username"),
            filter: "svi"
        }
        )
        .then(this.handleSuccess.bind(this))
        .catch(this.handleError.bind(this));
      }
      else {
        axios.post(PATH_BASE+PATH_INCIDENTS+PATH_INCIDENTS_GET, {
            username:  sessionStorage.getItem("username"),
            filter: "svi"
        }
        )
        .then(this.handleSuccess.bind(this))
        .catch(this.handleError.bind(this));
      }

    }

    handleSuccess(response){
      this.setState({data:response.data});
      console.log(this.state.data);
    }

    handleError(error){
        console.log(error);
    }

    render(){

        const options = {
            sortName: this.state.sortName,
            sortOrder: this.state.sortOrder,
            onSortChange: this.onSortChange,
            onRowClick: this.onRowClick
        };
        const pType ={
            1:"Nizak prioritet",
            2:"Srednji prioritet",
            3:"Visoki prioritet",
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


        var table = <div></div>;
        var role = sessionStorage.getItem("rola");
        switch(role){
            case 'Administrator':
                table =
                    <BootstrapTable data={this.state.data} options={options}>
                        <TableHeaderColumn  columnTitle={ true } isKey dataField='id' dataSort hidden>#</TableHeaderColumn>
                        <TableHeaderColumn  columnTitle={ true }  dataField='title' dataSort filter={ { type: 'TextFilter', delay: 500 } }>Naslov</TableHeaderColumn>
                        <TableHeaderColumn  columnTitle={ true }  dataField ='creatorUsername' dataSort filter={ { type: 'TextFilter', delay: 500 } }>Prijavio</TableHeaderColumn>
                        <TableHeaderColumn  columnTitle={ true }  dataField ='resolverUsername' dataSort filter={ { type: 'TextFilter', delay: 500 } }>Rješava</TableHeaderColumn>
                        <TableHeaderColumn  columnTitle={ true }  dataField ='serviceName' dataSort filter={ { type: 'TextFilter', delay: 500 } }>Servis</TableHeaderColumn>
                        <TableHeaderColumn  columnTitle={ true }  dataField='priority' dataSort
                        filterFormatted dataFormat={ enumFormatter } formatExtraData={ pType }
                        filter={ { type: 'SelectFilter', options: pType }}
                        >Prioritet
                        </TableHeaderColumn>
                        <TableHeaderColumn  columnTitle={ true }  dataField='urgency' dataSort
                        filterFormatted dataFormat={ enumFormatter } formatExtraData={ hType }
                        filter={ { type: 'SelectFilter', options: hType }}
                        >Hitnost
                        </TableHeaderColumn>
                        <TableHeaderColumn  columnTitle={ true }  dataField='createdDate' dataSort filter={ { type: 'TextFilter', delay: 500 } }>Datum prijave</TableHeaderColumn>
                        <TableHeaderColumn  columnTitle={ true }  dataField='lastResolveDate' dataSort filter={ { type: 'TextFilter', delay: 500 } }>Datum posljednjeg rješavanja</TableHeaderColumn>
                        <TableHeaderColumn  columnTitle={ true }  dataField='closedDate' dataSort filter={ { type: 'TextFilter', delay: 500 } }>Datum zatvaranja</TableHeaderColumn>
                        <TableHeaderColumn  columnTitle={ true }  dataField='departmentName' dataSort filter={ { type: 'TextFilter', delay: 500 } } >Odjel</TableHeaderColumn>
                        <TableHeaderColumn  columnTitle={ true }  dataField='status' dataSort
                        filterFormatted dataFormat={ enumFormatter } formatExtraData={ sType }
                        filter={ { type: 'SelectFilter', options: sType }}
                        >Status</TableHeaderColumn>
                        <TableHeaderColumn  columnTitle={ true }  dataField='escalation' dataSort
                        filterFormatted dataFormat={ enumFormatter } formatExtraData={ eType }
                        filter={ { type: 'SelectFilter', options: eType }}
                        >Eskalacija</TableHeaderColumn>
                      </BootstrapTable>;
                    break;
            case 'Korisnik':
                table =
                    <BootstrapTable data={this.state.data} options={options}>
                        <TableHeaderColumn  columnTitle={ true } isKey dataField='id' dataSort hidden>#</TableHeaderColumn>
                        <TableHeaderColumn  columnTitle={ true }  dataField='title' dataSort filter={ { type: 'TextFilter', delay: 500 } }>Naslov</TableHeaderColumn>
                        <TableHeaderColumn  columnTitle={ true }  dataField='urgency' dataSort
                        filterFormatted dataFormat={ enumFormatter } formatExtraData={ hType }
                        filter={ { type: 'SelectFilter', options: hType }}
                        >Hitnost
                        </TableHeaderColumn>
                        <TableHeaderColumn  columnTitle={ true }  dataField='createdDate' dataSort filter={ { type: 'TextFilter', delay: 500 } }>Datum kreiranja</TableHeaderColumn>
                        <TableHeaderColumn  columnTitle={ true }  dataField='lastResolveDate' dataSort filter={ { type: 'TextFilter', delay: 500 } }>Datum rješavanja</TableHeaderColumn>
                        <TableHeaderColumn  columnTitle={ true }  dataField='closedDate' dataSort filter={ { type: 'TextFilter', delay: 500 } }>Datum zatvaranja</TableHeaderColumn>
                        <TableHeaderColumn  columnTitle={ true }  dataField ='serviceName' dataSort filter={ { type: 'TextFilter', delay: 500 } }>Servis</TableHeaderColumn>
                        <TableHeaderColumn  columnTitle={ true }  dataField='status' dataSort
                        filterFormatted dataFormat={ enumFormatter } formatExtraData={ sType }
                        filter={ { type: 'SelectFilter', options: sType }}
                        >Status</TableHeaderColumn>
                   </BootstrapTable>;
                break;
              case 'Odjel':
              if (this.props.tip==='Nedodijeljeni')
              table =
                  <BootstrapTable data={this.state.data} options={options}>
                      <TableHeaderColumn  columnTitle={ true } isKey dataField='id' dataSort hidden>#</TableHeaderColumn>
                      <TableHeaderColumn  columnTitle={ true }   dataField='title' dataSort filter={ { type: 'TextFilter', delay: 500 } }>Naslov</TableHeaderColumn>
                      <TableHeaderColumn  columnTitle={ true }   dataField='creator' dataSort filter={ { type: 'TextFilter', delay: 500 } }>Korisnik</TableHeaderColumn>
                      <TableHeaderColumn  columnTitle={ true }   dataField='serviceName' dataSort filter={ { type: 'TextFilter', delay: 500 } }>Servis</TableHeaderColumn>
                      <TableHeaderColumn  columnTitle={ true }  dataField='priority' dataSort
                      filterFormatted dataFormat={ enumFormatter } formatExtraData={ pType }
                      filter={ { type: 'SelectFilter', options: pType }}
                      >Prioritet
                      </TableHeaderColumn>
                      <TableHeaderColumn  columnTitle={ true }  dataField='createdDate' dataSort filter={ { type: 'TextFilter', delay: 500 } }>Datum prijave</TableHeaderColumn>
                 </BootstrapTable>;
              else
              table =
              <BootstrapTable data={this.state.data} options={options}>
                  <TableHeaderColumn  columnTitle={ true } hidden isKey dataField='id' dataSort>#</TableHeaderColumn>
                  <TableHeaderColumn  columnTitle={ true }  dataField='title' dataSort filter={ { type: 'TextFilter', delay: 500 } }>Naslov</TableHeaderColumn>
                  <TableHeaderColumn  columnTitle={ true }  dataField ='creator' dataSort filter={ { type: 'TextFilter', delay: 500 } }>Korisnik</TableHeaderColumn>
                  <TableHeaderColumn  columnTitle={ true }   dataField='serviceName' dataSort filter={ { type: 'TextFilter', delay: 500 } }>Servis</TableHeaderColumn>
                  <TableHeaderColumn  columnTitle={ true }  dataField='priority' dataSort
                  filterFormatted dataFormat={ enumFormatter } formatExtraData={ pType }
                  filter={ { type: 'SelectFilter', options: pType }}
                  >Prioritet
                  </TableHeaderColumn>
                  <TableHeaderColumn  columnTitle={ true }  dataField='createdDate' dataSort filter={ { type: 'TextFilter', delay: 500 } }>Datum prijave</TableHeaderColumn>
                  <TableHeaderColumn  columnTitle={ true }  dataField='lastResolveDate' dataSort filter={ { type: 'TextFilter', delay: 500 } }>Datum posljednjeg rješavanja</TableHeaderColumn>
                  <TableHeaderColumn  columnTitle={ true }  dataField='closedDate' dataSort filter={ { type: 'TextFilter', delay: 500 } }>Datum rješavanja</TableHeaderColumn>
                  <TableHeaderColumn  columnTitle={ true }  dataField='status' dataSort
                  filterFormatted dataFormat={ enumFormatter } formatExtraData={ sType }
                  filter={ { type: 'SelectFilter', options: sType }}
                  >Status</TableHeaderColumn>
                  <TableHeaderColumn  columnTitle={ true }  dataField='escalation' dataSort
                  filterFormatted dataFormat={ enumFormatter } formatExtraData={ eType }
                  filter={ { type: 'SelectFilter', options: eType }}
                  >Eskalacija</TableHeaderColumn>
                </BootstrapTable>;
        }

        return(
            <div>
                <Panel bsStyle="primary">
                    <Panel.Heading>
                      <div class="d-flex w-100 justify-content-between">
                        <Panel.Title componentClass="h2">Lista incidenata</Panel.Title>
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

export default ListaIncidenata;

// Backup if needed

/*

class IncidentItem extends Component{

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
