import React, { Component } from 'react';
import EntryDataService from '../../../frontend-app/src/service/EntryDataService';
import {Form} from "formik";

class ImageComponent extends Component {

    constructor(props) {
        super(props);
        this.state = {file: '', message: '', id: this.props.match.params.id};
    }

    onFileChange = (event) => {
        this.setState({
            file: event.target.files[0]
        });
    }

    uploadFileData = (event) => {
        event.preventDefault();
        //this.setState({message: ''});
        let data = new FormData();
        data.append('file', this.state.file);
        EntryDataService.uploadImage(this.state.id, data).then(response => {
            console.log(response);
            this.setState({message: "File successfully uploaded"});
            this.props.history.push(`/entries`)
        }).catch(err => {
            console.log(err.data);
            console.log(err.status);
            console.log(err.statusText);
            console.log(err.headers);

           // console.log(err.config);
           // this.setState({error: err , message:  });
        });
    }

    render() {
        return (
            <div id="container">
                <h3>Upload a image</h3>
                {this.state.message && <div class="alert alert-warning">{this.state.message}</div>}
                <div className="input-group mb-3">
                      <input class="form-control" onChange={this.onFileChange} type="file"></input>
                        <div className="input-group-append">
                            <button className="btn  btn-success" disabled={!this.state.file} onClick={this.uploadFileData}>Upload</button>
                        </div>
                </div>
            </div>
        )
    }

}

export default ImageComponent