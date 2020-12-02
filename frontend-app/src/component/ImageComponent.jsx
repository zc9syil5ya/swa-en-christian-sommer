import React, { Component } from 'react';
import EntryDataService from '../../../frontend-app/src/service/EntryDataService';
import {Form} from "formik";

class ImageComponent extends Component {

    constructor(props) {
        super(props);
        this.state = {file: '', msg: '', id: this.props.match.params.id};
    }

    onFileChange = (event) => {
        this.setState({
            file: event.target.files[0]
        });
    }

    uploadFileData = (event) => {
        event.preventDefault();
        this.setState({msg: ''});

        let data = new FormData();
        data.append('file', this.state.file);
        EntryDataService.uploadImage(this.state.id, data).then(response => {
            console.log("Image successfully uploaded");
            this.setState({msg: "File successfully uploaded"});
            this.props.history.push(`/entries`)
        }).catch(err => {
            this.setState({error: err});
        });

    }

    render() {
        return (
            <div id="container">
                <h3>Upload a image</h3>
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