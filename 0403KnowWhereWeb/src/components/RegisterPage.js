import React from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';
import axios from 'axios';
import Fetch from 'react-fetch';
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";

//axios.defaults.baseURL = 'http://143.215.114.174:8080';
//axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
//axios.defaults.headers.post['Access-Control-Allow-Origin'] = '*';
/*
let axiosConfig = {
    headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Method': 'POST, GET'
    },
    withCredentials: true
  };*/
function postData(url, data){
    return fetch(url, {
        body: JSON.stringify(data),
        credentials: 'include',
        headers:{ 'content-type': 'application/json'},
        method: 'POST',
        mode: 'cors',
        redirect: 'follow',
        referrer: 'no-referrer'
    })
    .then(response=> response.json())
}

  export default class RegisterPage extends React.Component {
    constructor(props) {
        super(props);
 
        this.state = {           
                username: '',
                firstname:'',
                lastname:'',
                password: '',
                email: ''
        };
 
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }
 
    handleChange(event) {
        this.setState({
            username: event.target.value.username,
            password: event.target.value.password,
            email: event.target.value.email,
            firstname:'testfirstname',
            lastname:'testlastname'
        });
    }
   
    handleSubmit(event) {
        event.preventDefault();
        const user = {
            username: this.state.username,
            password: this.state.password,
            email: this.state.email,
            firstname:'',
            lastname:''
        }; 
        postData('http://http://143.215.114.174:8080/user', {user});
        //axios.post(`/user`, {user}, axiosConfig)
        //.then(res => {
            //res.setContentType('application/json');
            //console.log(res);
            //console.log(res.data);
        //})
    }
 
    render() {
        const user = this.state;
        return (
            <div>
                <h3>Register</h3>
                <form name="form" onSubmit={this.handleSubmit}>
                    <FormGroup controlId="username" >
                        <ControlLabel>Username: </ControlLabel>
                        <FormControl autoFocus 
                        type="text" 
                        className="text-input" 
                        placeholder = "Username"
                        name="username" 
                        onChange={this.handleChange} 
                        />
                    </FormGroup>

                    <FormGroup controlId="email">
                        <ControlLabel>Email:</ControlLabel>
                        <FormControl autoFocus 
                        type="text" 
                        className="text-input" 
                        placeholder="Email" 
                        name="email"
                        onChange={this.handleChange} 
                        />
                    </FormGroup>

                    <FormGroup>
                        <ControlLabel>Password: </ControlLabel>
                        <FormControl autoFocus 
                        type="text" 
                        className="text-input"  
                        placeholder="Password" 
                        name="password"
                        onChange={this.handleChange} 
                        />
                    </FormGroup>

                    <FormGroup controlId="firstname">
                        <ControlLabel>Firstname: </ControlLabel>
                        <FormControl autoFocus 
                        type="text" 
                        className="text-input" 
                        placeholder="Firstname" 
                        name="firstname"
                        onChange={this.handleChange} 
                        />
                    </FormGroup>

                    <FormGroup>
                        <ControlLabel>Lastname: </ControlLabel>
                        <FormControl autoFocus 
                        type="text" 
                        className="text-input"  
                        placeholder="Lastname" 
                        name="lastname"
                        onChange={this.handleChange} 
                        />
                    </FormGroup>
                    <div>
                        <Button type="submit" className="btn btn-primary">Register</Button>
                        <Link to="/login" className="btn btn-link">Cancel</Link>
                    </div>
                </form>
            </div>
        );
    }
}

