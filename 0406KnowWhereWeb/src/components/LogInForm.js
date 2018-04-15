import React from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";

axios.defaults.baseURL = 'http://143.215.113.90:8080';
axios.defaults.headers.get['Content-Type'] = 'application/json';
axios.defaults.headers.get['Access-Control-Allow-Origin'] = '*';

let axiosConfig = {
  headers: {
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*',
      'Access-Control-Allow-Method': 'POST, GET, OPTIONS, PUT, DELETE'
},
  withCredentials: false
};

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
        const uname = this.state.username;
        const pword = this.state.password;
        console.log("input password: ", pword);
        
        axios.get(`/user/${this.state.username}`, uname, axiosConfig)
             .then((response)=>{
                console.log(response.data);
                //correctPassword = response.data.password;
                this.setState({
                    correctPassword: response.data.password
                })
                console.log(this.state.correctPassword);
             })
             .catch((error)=>{console.log(error)});
        setTimeout((props) => {
            console.log("Authenticating...");
            if(this.state.password == this.state.correctPassword){
                this.props.onSubmit({
                    username: this.state.username,
                    password: this.state.password
                })
            }else{
                alert("please input correct password.");
            }  
        }, 1000);      
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
