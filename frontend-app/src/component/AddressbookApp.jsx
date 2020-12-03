import React, { Component } from 'react';
import EntryListComponent from './EntryListComponent';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import EntryComponent from './EntryComponent';
import ImageComponent from './ImageComponent';
import logo from '../utils/contact-icon.png';

class AddressbookApp extends Component {
    render() {
        return (
            <Router>
                <>
                    <div className={"title"}>
                        <img width="120"  src={logo} alt="Logo" />
                        <h1>~ address book application ~</h1></div>

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