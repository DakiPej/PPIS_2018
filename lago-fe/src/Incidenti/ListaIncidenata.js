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
import html2canvas from 'html2canvas';
import jsPDF from 'jspdf';
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

    printDocument = () => {
        const input = document.getElementById('divToPrint');
        html2canvas(input)
          .then((canvas) => {
            const imgData = canvas.toDataURL('image/png');
            const pdf = new jsPDF({orientation: 'landscape'});
            pdf.addImage(imgData, 'JPEG', 0, 0);
            // pdf.output('dataurlnewwindow');
            pdf.save("download.pdf");
          })
        ;
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
            <div >
                <Panel bsStyle="primary">
                    <Panel.Heading>
                        <div class="d-flex w-100 justify-content-between">
                            <Panel.Title componentClass="h2">Lista zahtjeva </Panel.Title>
                            <Button onClick={this.printDocument}>
                                <Glyphicon glyph="download-alt" />
                            </Button>
                        </div>
                    </Panel.Heading>
                    <div id="divToPrint">
                    {table}
                    </div>
                </Panel>
            </div>
        );

    }

}

export default ListaIncidenata;

