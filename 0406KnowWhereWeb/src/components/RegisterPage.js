import React from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';
import axios from 'axios';
import Fetch from 'react-fetch';
import history from './history';
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";

  export default class RegisterPage extends React.Component {
    
    constructor(props) {
        super(props);
 
        this.state = {           
                username: props.username?props.username:'',
                firstname:props.firstname?props.firstname:'',
                lastname:props.lastname?props.lastname:'',
                password: props.password?props.password:'',
                email: props.email? props.email : ''
        };
 
        //this.handleChange = this.handleChange.bind(this);
        this.onEmailChange = this.onEmailChange.bind(this);
        this.onFirstnameChange = this.onFirstnameChange.bind(this);
        this.onLastnameChange = this.onLastnameChange.bind(this);
        this.onPasswordChange = this.onPasswordChange.bind(this);
        this.onUsernameChange = this.onUsernameChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
    }
 
    onUsernameChange(e) {
        this.setState({username: e.target.value});   
    }
   
    onEmailChange(event) {
        this.setState({
            email: event.target.value
        });   
    }

    onPasswordChange(event) {
        this.setState({
            password: event.target.value
        });  
    }

    onFirstnameChange(event) {
        this.setState({
            firstname: event.target.value
        });
    }

    onLastnameChange(event) {
        this.setState({
            lastname: event.target.value
        });
    }

    onSubmit(event) {
        event.preventDefault();
        //console.log("user:");
        //console.log(this.state);
        const user = {
            username: this.state.username,
            password: this.state.password,
            email:this.state.email,
            firstname:this.state.firstname,
            lastname:this.state.lastname
        }; 
        console.log(user);
        axios.defaults.baseURL = 'http://143.215.113.90:8080';
        axios.defaults.headers.get['Content-Type'] = 'application/json';
        axios.defaults.headers.get['Access-Control-Allow-Origin'] = '*';

        let axiosConfig = {
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Method': 'POST, GET, OPTIONS, PUT, DELETE'
        },
            withCredentials: true
        };
        axios.post(`/user`, user, axiosConfig)
        .then(function(response) {
            console.log(response);
            console.log(response.data);
        })
        .catch(function(error){
            console.log(error);
        })
        this.props.history.push('/');
    }
 
    //onClick(){
        //window.location.href = "./";
    //}
    render() {
        return (
            <div>
                <h3>Register</h3>
                <form name="form" onSubmit={this.onSubmit}>
                    <FormGroup controlId="username" >
                        <ControlLabel>Username: </ControlLabel>
                        <FormControl autoFocus 
                        type="text" 
                        className="text-input" 
                        placeholder = "Username"
                        value= {this.state.username}
                        onChange={this.onUsernameChange} 
                        />
                    </FormGroup>

                    <FormGroup controlId="email">
                        <ControlLabel>Email:</ControlLabel>
                        <FormControl autoFocus 
                        type="email" 
                        className="text-input" 
                        placeholder="Email" 
                        value={this.state.email}
                        onChange={this.onEmailChange} 
                        />
                    </FormGroup>

                    <FormGroup>
                        <ControlLabel>Password: </ControlLabel>
                        <FormControl autoFocus 
                        type="text" 
                        className="text-input"  
                        placeholder="Password" 
                        value={this.state.password}
                        onChange={this.onPasswordChange} 
                        />
                    </FormGroup>

                    <FormGroup controlId="firstname">
                        <ControlLabel>Firstname: </ControlLabel>
                        <FormControl autoFocus 
                        type="text" 
                        className="text-input" 
                        placeholder="Firstname" 
                        value={this.state.firstname}
                        onChange={this.onFirstnameChange} 
                        />
                    </FormGroup>

                    <FormGroup>
                        <ControlLabel>Lastname: </ControlLabel>
                        <FormControl autoFocus 
                        type="text" 
                        className="text-input"  
                        placeholder="Lastname"
                        value={this.state.lastname} 
                        onChange={this.onLastnameChange} 
                        />
                    </FormGroup>
                    <div>
                        <Button type="submit" className="btn btn-primary">Register</Button>
                        <Link to="/" className="btn btn-link">Cancel</Link>
                    </div>
                </form>
            </div>
        );
    }
}

