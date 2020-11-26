import React, { Component } from 'react';
import './App.css';
import AddressbookApp from './component/AddressbookApp';

class App extends Component {
  render() {
    return (
        <div className="container">
          <AddressbookApp />
        </div>
    );
  }
}

export default App;
