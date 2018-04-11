import React from 'react';
import { LogInForm } from './LogInForm';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';
import axios from 'axios';
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";
import history from './history';
import { logIn } from '../actions/userAction'

axios.defaults.baseURL = 'http://143.215.114.174:8080';
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

export class HomePagetest extends React.Component {
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
    this.setState({ username });   
}

onPasswordChange(e) {
    const passwor d= e.target.value;
    this.setState({ password });  
}

onSubmit(e){
    e.preventDefault();
    /** 
     * 
    axios.get(`/user/${this.state.username}`, axiosConfig)
         .then((response)=>{
           console.log(response.status);
             })
         .catch((error)=>{
           console.log('axios error: ', error);
         })
    */
    
    this.props=({
     username:this.state.username,
     password:this.state.password
    });
    const user = {
      username: this.props.username,
      password: this.props.password
    }
    this.props.logIn(user);
    console.log('this.props: ', this.props);
  }
    render(){
        return (
        <div className="box-layout">
          <div className="box-layout__box">
            <p className="header__login">Know Where Your Friends Are</p>

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
            <Link to="/register">Register</Link>  
          </div>
        </div>
        );
    }
};




export default HomePagetest);