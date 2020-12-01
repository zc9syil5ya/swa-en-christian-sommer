import React, { Component } from 'react';
import EntryListComponent from './EntryListComponent';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import EntryComponent from './EntryComponent';
import ImageComponent from './ImageComponent';

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
                        <Route path="/upload/:id" component={ImageComponent} />
                    </Switch>
                </>
            </Router>
        )
    }
}
export default AddressbookApp