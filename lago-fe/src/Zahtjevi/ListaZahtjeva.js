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
import html2canvas from 'html2canvas';
import jsPDF from 'jspdf';

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
            axios.get("http://localhost:8080/requests/unassigned/" + sessionStorage.getItem("username"))
                .then(this.handleSuccess.bind(this))
                .catch(this.handleError.bind(this));
        }
        else {
            axios.get("http://localhost:8080/requests/" + sessionStorage.getItem("username"))
                .then(this.handleSuccess.bind(this))
                .catch(this.handleError.bind(this));
        }

    }

    handleSuccess(response) {
        console.log(response.data);
        this.setState({
            data: response.data
        });
    }

    handleError(error) {
        console.log(error);
    }

    printDocument = () => {
        const input = document.getElementById('divToPrint');
        html2canvas(input)
          .then((canvas) => {
            const imgData = canvas.toDataURL('image/png');
            const pdf = new jsPDF({orientation: 'landscape'});
            pdf.text("Lista zahtjeva",50,20);
            pdf.addImage(imgData, 'JPEG', 30, 30);
            // pdf.output('dataurlnewwindow');
            pdf.save("download.pdf");
          })
        ;
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

        const pType = {
            1: "Nizak",
            2: "Srednji",
            3: "Visok"
        };
        const hType = {
            1: "Niska hitnost",
            2: "Srednja hitnost",
            3: "Visoka hitnost",
        }

        const sType = {
            'nedodijeljen': 'Nedodijeljen',
            'u obradi': 'U obradi',
            'not closed': 'Nije zatvoren',
            'zatvoren': 'Zatvoren'
        }
        const eType = {
            'true': 'Da',
            'false': 'Ne'
        }

        switch (this.role) {
            case 'Administrator':
                table =
                    <BootstrapTable data={this.state.data} options={options}>
                        <TableHeaderColumn hidden isKey dataField='id' dataSort>#</TableHeaderColumn>
                        <TableHeaderColumn dataField='title' dataSort filter={{ type: 'TextFilter', delay: 500 }}>Naslov</TableHeaderColumn>
                        <TableHeaderColumn dataField='creatorUsername' dataSort filter={{ type: 'TextFilter', delay: 500 }}>Prijavio</TableHeaderColumn>
                        <TableHeaderColumn dataField='resolverUsername' dataSort filter={{ type: 'TextFilter', delay: 500 }}>Rješava</TableHeaderColumn>
                        <TableHeaderColumn dataField='urgency' dataSort
                            filterFormatted dataFormat={enumFormatter} formatExtraData={hType}
                            filter={{ type: 'SelectFilter', options: hType }}
                        >Hitnost
                        </TableHeaderColumn>
                        <TableHeaderColumn dataField='createdDate' dataSort filter={{ type: 'TextFilter', delay: 500 }}>Datum prijave</TableHeaderColumn>
                        <TableHeaderColumn dataField='closedDate' dataSort filter={{ type: 'TextFilter', delay: 500 }}>Datum rješavanja</TableHeaderColumn>
                        <TableHeaderColumn dataField='departmentName' dataSort filter={{ type: 'TextFilter', delay: 500 }} >Odjel</TableHeaderColumn>
                        <TableHeaderColumn dataField='status' dataSort
                            filterFormatted dataFormat={enumFormatter} formatExtraData={sType}
                            filter={{ type: 'SelectFilter', options: sType }}
                        >Status</TableHeaderColumn>
                    </BootstrapTable>;
                break;
            case 'Korisnik':
                table =
                    <BootstrapTable data={this.state.data} options={options}>
                        <TableHeaderColumn isKey dataField='id' dataSort hidden>#</TableHeaderColumn>
                        <TableHeaderColumn dataField='title' dataSort filter={{ type: 'TextFilter', delay: 500 }}>Naslov</TableHeaderColumn>
                        <TableHeaderColumn dataField='urgency' dataSort
                            filterFormatted dataFormat={enumFormatter} formatExtraData={hType}
                            filter={{ type: 'SelectFilter', options: hType }}
                        >Hitnost
                        </TableHeaderColumn>
                        <TableHeaderColumn dataField='createdDate' dataSort filter={{ type: 'TextFilter', delay: 500 }}>Datum prijave</TableHeaderColumn>
                        <TableHeaderColumn dataField='closedDate' dataSort filter={{ type: 'TextFilter', delay: 500 }}>Datum rješavanja</TableHeaderColumn>
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
                            <TableHeaderColumn dataField='title' dataSort filter={{ type: 'TextFilter', delay: 500 }}>Naslov</TableHeaderColumn>
                            <TableHeaderColumn dataField='creatorUsername' dataSort filter={{ type: 'TextFilter', delay: 500 }}>Korisnik</TableHeaderColumn>
                            <TableHeaderColumn dataField='urgency' dataSort
                                filterFormatted dataFormat={enumFormatter} formatExtraData={hType}
                                filter={{ type: 'SelectFilter', options: hType }}
                            >Hitnost
                      </TableHeaderColumn>
                            <TableHeaderColumn dataField='createdDate' dataSort filter={{ type: 'TextFilter', delay: 500 }}>Datum prijave</TableHeaderColumn>
                        </BootstrapTable>;
                else
                    table =
                        <BootstrapTable data={this.state.data} options={options}>
                            <TableHeaderColumn hidden isKey dataField='id' dataSort>#</TableHeaderColumn>
                            <TableHeaderColumn dataField='title' dataSort filter={{ type: 'TextFilter', delay: 500 }}>Naslov</TableHeaderColumn>
                            <TableHeaderColumn dataField='creatorUsername' dataSort filter={{ type: 'TextFilter', delay: 500 }}>Prijavio</TableHeaderColumn>
                            <TableHeaderColumn dataField='urgency' dataSort
                                filterFormatted dataFormat={enumFormatter} formatExtraData={hType}
                                filter={{ type: 'SelectFilter', options: hType }}
                            >Hitnost
                  </TableHeaderColumn>
                            <TableHeaderColumn dataField='createdDate' dataSort filter={{ type: 'TextFilter', delay: 500 }}>Datum prijave</TableHeaderColumn>
                            <TableHeaderColumn dataField='closedDate' dataSort filter={{ type: 'TextFilter', delay: 500 }}>Datum rješavanja</TableHeaderColumn>
                            <TableHeaderColumn dataField='status' dataSort
                                filterFormatted dataFormat={enumFormatter} formatExtraData={sType}
                                filter={{ type: 'SelectFilter', options: sType }}
                            >Status</TableHeaderColumn>
                        </BootstrapTable>;
        }

        return (
            <div id= "divToPrint"s>
                <Panel bsStyle="primary">
                    <Panel.Heading>
                        <div class="d-flex w-100 justify-content-between">
                            <Panel.Title componentClass="h2">Lista zahtjeva </Panel.Title>
                            <Button onClick={this.printDocument}>
                                <Glyphicon glyph="download-alt" />
                            </Button>
                        </div>
                    </Panel.Heading>
                    <div >
                    {table}
                    </div>
                </Panel>
            </div>
        );

    }

}

export default ListaZahtjeva;
