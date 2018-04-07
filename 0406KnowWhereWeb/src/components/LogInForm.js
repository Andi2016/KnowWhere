import React from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';
import axios from 'axios';
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";

export default class LogInForm extends React.Component{
    constructor(props){
        super(props);
        this.state={
            username: props.username?props.username:'',
            password: props.password?props.password:''
        };

        this.onPasswordChange = this.onPasswordChange.bind(this);
        this.onUsernameChange = this.onUsernameChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);        
    }

    onUsernameChange(e) {
        const username = e.target.value;
        this.setState({username: username});   
    }
   
    onPasswordChange(e) {
        const password = e.target.value;
        this.setState({password: password});  
    }

    onSubmit(e){
        e.preventDefault();
        this.props.onSubmit({
            username: this.state.username,
            password: this.state.password
        })
    }
    render(){
        return (
            <form name="form" onSubmit={this.onSubmit}>              
            <FormGroup controlId="email">
            <ControlLabel>Username: </ControlLabel>
            <FormControl autoFocus 
            type="text" 
            value={this.state.username}
            onChange={this.onUsernameChange}
            /></FormGroup>

            <FormGroup controlId="password">
            <ControlLabel>Password: </ControlLabel>
            <FormControl autoFocus 
            type="text" 
            value={this.state.password}
            onChange={this.onPasswordChange}
            /></FormGroup>

            <Button block type="submit">Login</Button>
            </form>      
        );
    }
}
