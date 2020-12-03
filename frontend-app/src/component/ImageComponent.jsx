import React, { Component } from 'react';
import EntryDataService from '../../../frontend-app/src/service/EntryDataService';

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
        let data = new FormData();
        data.append('file', this.state.file);
        EntryDataService.uploadImage(this.state.id, data).then(response => {
            console.log(response);
            this.setState({message: "File successfully uploaded"});
            setTimeout(() => {this.props.history.push(`/entries`)},1000);
        }).catch( error => {
            if (error.response) {
                this.setState({message: error.response.data.message});
            }
        });
    }
    render() {
        return (
            <div id="container">
                <h3>Upload a image</h3>
                {this.state.message && <div class="alert alert-info">{this.state.message}</div>}
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