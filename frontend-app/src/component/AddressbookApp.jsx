import React, { Component } from 'react';
import EntryListComponent from './EntryListComponent';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import EntryComponent from './EntryComponent';

class AddressbookApp extends Component {
    render() {
        return (
            <Router>
                <>
                    <h1>address book application</h1>
                    <Switch>
                        <Route path="/" exact component={EntryListComponent} />
                        <Route path="/entries" exact component={EntryListComponent} />
                        <Route path="/entries/:id" component={EntryComponent} />
                    </Switch>
                </>
            </Router>
        )
    }
}
export default AddressbookApp