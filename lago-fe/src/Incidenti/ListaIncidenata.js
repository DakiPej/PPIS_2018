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

bootstrapUtils.addStyle(ListGroup, 'custom');


function enumFormatter(cell, row, enumObject) {
    return enumObject[cell];
}

class ListaIncidenata extends Component{

    constructor(props){
        super(props);

        this.state = {
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
        window.location='/dashboard/incidenti/'+row.id;
    }
    onSortChange(sortName, sortOrder) {
        console.info('onSortChange', arguments);
        this.setState({
          sortName,
          sortOrder
        });
    }

    getIncidenti(){
        /*axios.get(

        )
        .then(this.handleSuccess.bind(this))
        .catch(this.handleError.bind(this));
        */
    }

    handleSuccess(response){

    }

    handleError(error){
        console.log(error);
    }

    render(){

        var test = [
            {
                id: 1,
                naslov: 'Naslov',
                datum: '11/11/11',
                status: 'Poslano',
                korisnik: 'Korisnik 1',
                odjel: 'Odjel 1',
                prioritet: 1,
                servis:'Servis',
                eskalacija:'Da'
            },
            {
                id: 2,
                naslov: 'Naslov',
                korisnik: 'Korisnik 1',
                datum: '11/11/11',
                status: 'Poslano',
                odjel: 'Odjel 2',
                prioritet: 2,
                servis:'Servis',
                eskalacija:'Ne'
            },
            {
                id: 3,
                naslov: 'Naslov',
                korisnik: 'Korisnik 1',
                datum: '11/11/11',
                status: 'Poslano',
                odjel: 'Odjel 3',
                prioritet: 2,
                servis:'Servis',
                eskalacija:'Da'
            },
            {
                id: 4,
                naslov: 'Naslov',
                datum: '11/11/11',
                korisnik: 'Korisnik 1',
                status: 'Poslano',
                odjel: 'Odjel 1',
                prioritet: 10,
                servis:'Servis',
                eskalacija:'Da'
            }
        ];

        const options = {
            sortName: this.state.sortName,
            sortOrder: this.state.sortOrder,
            onSortChange: this.onSortChange,
            onRowClick: this.onRowClick
        };

        const pType ={
            1:1,
            2:2,
            3:3,
            4:4,
            5:5,
            6:6,
            7:7,
            8:8,
            9:9,
            10:10
        };

        const sType = {
            'Poslano':'Poslano',
            'U obradi':'U obradi',
            'Rješeno':'Rješeno'
        }
        const eType = {
            'Da':'Da',
            'Ne':'Ne'
        }


        var table = <div></div>;
        var role = 'Administrator';
        switch(role){
            case 'Administrator':
                table =
                    <BootstrapTable data={test} options={options}>
                        <TableHeaderColumn isKey dataField='id' dataSort hidden>#</TableHeaderColumn>
                        <TableHeaderColumn  dataField='naslov' dataSort filter={ { type: 'TextFilter', delay: 500 } }>Naslov</TableHeaderColumn>
                        <TableHeaderColumn  dataField ='korisnik' dataSort filter={ { type: 'TextFilter', delay: 500 } }>Prijavio</TableHeaderColumn>
                        <TableHeaderColumn  dataField ='korisnik' dataSort filter={ { type: 'TextFilter', delay: 500 } }>Rješava</TableHeaderColumn>
                        <TableHeaderColumn  dataField ='servis' dataSort filter={ { type: 'TextFilter', delay: 500 } }>Servis</TableHeaderColumn>
                        <TableHeaderColumn  dataField='prioritet' dataSort
                        filterFormatted dataFormat={ enumFormatter } formatExtraData={ pType }
                        filter={ { type: 'SelectFilter', options: pType }}
                        >Prioritet
                        </TableHeaderColumn>
                        <TableHeaderColumn  dataField='prioritet' dataSort
                        filterFormatted dataFormat={ enumFormatter } formatExtraData={ pType }
                        filter={ { type: 'SelectFilter', options: pType }}
                        >Hitnost
                        </TableHeaderColumn>
                        <TableHeaderColumn  dataField='datum' dataSort filter={ { type: 'TextFilter', delay: 500 } }>Datum prijave</TableHeaderColumn>
                        <TableHeaderColumn  dataField='datum' dataSort filter={ { type: 'TextFilter', delay: 500 } }>Datum rješavanja</TableHeaderColumn>
                        <TableHeaderColumn  dataField='datum' dataSort filter={ { type: 'TextFilter', delay: 500 } }>Datum posljednjeg rješavanja</TableHeaderColumn>
                        <TableHeaderColumn  dataField='odjel' dataSort filter={ { type: 'TextFilter', delay: 500 } } >Odjel</TableHeaderColumn>
                        <TableHeaderColumn  dataField='status' dataSort
                        filterFormatted dataFormat={ enumFormatter } formatExtraData={ sType }
                        filter={ { type: 'SelectFilter', options: sType }}
                        >Status</TableHeaderColumn>
                        <TableHeaderColumn  dataField='eskalacija' dataSort
                        filterFormatted dataFormat={ enumFormatter } formatExtraData={ eType }
                        filter={ { type: 'SelectFilter', options: eType }}
                        >Eskalacija</TableHeaderColumn>
                      </BootstrapTable>;
                    break;
            case 'Korisnik':
                table =
                    <BootstrapTable data={test} options={options}>
                        <TableHeaderColumn isKey dataField='id' dataSort hidden>#</TableHeaderColumn>
                        <TableHeaderColumn  dataField='naslov' dataSort filter={ { type: 'TextFilter', delay: 500 } }>Naslov</TableHeaderColumn>
                        <TableHeaderColumn  dataField='prioritet' dataSort
                        filterFormatted dataFormat={ enumFormatter } formatExtraData={ pType }
                        filter={ { type: 'SelectFilter', options: pType }}
                        >Hitnost
                        </TableHeaderColumn>
                        <TableHeaderColumn  dataField='datum' dataSort filter={ { type: 'TextFilter', delay: 500 } }>Datum kreiranja</TableHeaderColumn>
                        <TableHeaderColumn  dataField='datum' dataSort filter={ { type: 'TextFilter', delay: 500 } }>Datum rješavanja</TableHeaderColumn>
                        <TableHeaderColumn  dataField='datum' dataSort filter={ { type: 'TextFilter', delay: 500 } }>Datum zatvaranja</TableHeaderColumn>
                        <TableHeaderColumn  dataField ='servis' dataSort filter={ { type: 'TextFilter', delay: 500 } }>Servis</TableHeaderColumn>
                        <TableHeaderColumn  dataField='status' dataSort
                        filterFormatted dataFormat={ enumFormatter } formatExtraData={ sType }
                        filter={ { type: 'SelectFilter', options: sType }}
                        >Status</TableHeaderColumn>
                   </BootstrapTable>;
                break;
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
