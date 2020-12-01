import React, { Component } from 'react'
import { Formik, Form, Field, ErrorMessage } from 'formik';
import EntryDataService from '../../../frontend-app/src/service/EntryDataService';


class EntryComponent extends Component { 
    constructor(props) {
    super(props)
    this.state = {
        id: this.props.match.params.id,
        firstName: '',
        lastName: '',
        street: '',
        zipcode: '',
        city: '',
        email: '',
        phone: ''
    }
    this.onSubmit = this.onSubmit.bind(this)
    this.validate = this.validate.bind(this)
}

    componentDidMount() {
        console.log(this.state.id)
        // eslint-disable-next-line
        if (this.state.id == -1) {
            return
        }

        EntryDataService.retrieveEntries(this.state.id)
            .then(response => this.setState({
                firstName: response.data.firstName,
                lastName: response.data.lastName,
                street: response.data.street,
                zipcode: response.data.zipcode,
                city: response.data.city,
                email: response.data.email,
                phone: response.data.phone
            }))
    }

    validate(values) {
        let errors = {}
        if (!values.email) {
            errors.email = 'Enter a Description'
        } else if (values.email.length < 5) {
            errors.email = 'Enter at least 5 Characters in Description'
        }
        return errors
    }

    onSubmit(values) {
        let entry = {
            id: this.state.id,
            firstName: values.firstName,
            lastName: values.lastName,
            street: values.street,
            zipcode: values.zipcode,
            city: values.city,
            email: values.email,
            phone: values.phone,
            targetDate: values.targetDate
        }

        if (this.state.id === -1) {
            EntryDataService.createEntries(entry)
                .then(() => this.props.history.push('/entries'))
        } else {
            EntryDataService.updateEntries( this.state.id, entry)
                .then(() => this.props.history.push('/entries'))
        }

        console.log(values);
    }

    render() {
        let { phone, email, city,zipcode,street,firstName,lastName, id } = this.state
        return (
            <div>
                <h3>Entry</h3>
                <div className="container">
                    <Formik
                        initialValues={{ phone, email, city,zipcode,street,firstName,lastName, id  }}
                        onSubmit={this.onSubmit}
                        validateOnChange={false}
                        validateOnBlur={false}
                        validate={this.validate}
                        enableReinitialize={true}
                    >{(props) => (
                        <Form>
                            <ErrorMessage name="description" component="div"
                                          className="alert alert-warning" />
                            <fieldset className="form-group">
                                <label>Id</label>
                                <Field className="form-control" type="text" name="id" disabled />
                            </fieldset>
                            <fieldset className="form-group">
                                <label>FirstName</label>
                                <Field className="form-control" type="text" name="firstName" />
                            </fieldset>
                            <fieldset className="form-group">
                                <label>LastName</label>
                                <Field className="form-control" type="text" name="lastName" />
                            </fieldset>
                            <fieldset className="form-group">
                                <label>Street</label>
                                <Field className="form-control" type="text" name="street" />
                            </fieldset>
                            <fieldset className="form-group">
                                <label>Zipcode</label>
                                <Field className="form-control" type="text" name="zipcode" />
                            </fieldset>
                            <fieldset className="form-group">
                                <label>City</label>
                                <Field className="form-control" type="text" name="city" />
                            </fieldset>
                            <fieldset className="form-group">
                                <label>E-Mail</label>
                                <Field className="form-control" type="text" name="email" />
                            </fieldset>
                            <fieldset className="form-group">
                                <label>Phone</label>
                                <Field className="form-control" type="text" name="phone" />
                            </fieldset>
                            <button className="btn btn-success" type="submit">Save</button>
                        </Form>
                    )
                    }
                    </Formik>

                </div>
            </div>
        )
    }
}

export default EntryComponent