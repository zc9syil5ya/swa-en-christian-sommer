import React, { Component } from 'react';
import { Form, Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";
import EntryDataService from '../../../frontend-app/src/service/EntryDataService';

class EntryListComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            entries: [],
            message: null
        }
        this.deleteEntryClicked = this.deleteEntryClicked.bind(this)
        this.updateEntryClicked = this.updateEntryClicked.bind(this)
        this.addEntryClicked = this.addEntryClicked.bind(this)
        this.refreshEntries = this.refreshEntries.bind(this)
    }

    componentDidMount() {
        this.refreshEntries();
    }

    refreshEntries() {
        EntryDataService.retrieveAllEntries()//HARDCODED
            .then(
                response => {
                    //console.log(response);
                    this.setState({ entries: response.data })
                }
            )
    }

    deleteEntryClicked(id) {
        EntryDataService.deleteEntries(id)
            .then(
                response => {
                    this.setState({ message: `Delete of entry ${id} Successful` })
                    this.refreshEntries()
                }
            )

    }

    addEntryClicked() {
        this.props.history.push(`/entries/-1`)
    }

    updateEntryClicked(id) {
        console.log('update ' + id)
        this.props.history.push(`/entries/${id}`)
    }

    render() {
        console.log('render')
        return (
            <div className="container">
                {this.state.message && <div class="alert alert-success">{this.state.message}</div>}
                <div className="container">
                    <table className="table">
                        <thead>
                        <tr>
                            <th>id</th>
                            <th>firstName</th>
                            <th>lastName</th>
                            <th>email</th>
                            <th></th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            this.state.entries.map(
                                entry =>
                                    <tr key={entry.id}>
                                        <td>{entry.id}</td>
                                        <td>{entry.firstName}</td>
                                        <td>{entry.lastName}</td>
                                        <td>{entry.email}</td>
                                        <td><button className="btn btn-success" onClick={() => this.updateEntryClicked(entry.id)}>
                                            <svg width="1em" height="1em" viewBox="0 0 16 16"
                                                 className="bi bi-pencil-square" fill="currentColor"
                                                 xmlns="http://www.w3.org/2000/svg">
                                                <path
                                                    d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456l-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
                                                <path fill-rule="evenodd"
                                                      d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
                                            </svg></button></td>
                                        <td><button className="btn btn-warning" onClick={() => this.deleteEntryClicked(entry.id)}>
                                            <svg width="1em" height="1em" viewBox="0 0 16 16"
                                                 className="bi bi-trash-fill" fill="currentColor"
                                                 xmlns="http://www.w3.org/2000/svg">
                                                <path fill-rule="evenodd"
                                                      d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5a.5.5 0 0 0-1 0v7a.5.5 0 0 0 1 0v-7z"/>
                                            </svg></button></td>
                                    </tr>
                            )
                        }
                        </tbody>
                    </table>
                    <div className="row">
                        <button className="btn btn-success" onClick={this.addEntryClicked}>Add</button>
                    </div>
                </div>
            </div>
        )
    }
}

export default EntryListComponent