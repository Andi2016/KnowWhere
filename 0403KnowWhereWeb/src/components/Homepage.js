import React from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';
import axios from 'axios';
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";
import history from './history';


class HomePage extends React.Component {  
  constructor(props){
    super(props);

    this.state={
        email:'',
        password:''
    };

    this.onSubmit = this.onSubmit.bind(this);
    this.onEmailChange = this.onEmailChange.bind(this);
    this.onPasswordChange = this.onPasswordChange.bind(this);
  }

  onSubmit(e){
    e.preventDefault();
    const user = {
      email: this.state.email,
      password: this.state.password
    }
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
    axios.get(`/user/email/${this.state.email}`, axiosConfig)
         .then(function(response){
          console.log(response);
          console.log(response.data.password);
          console.log(response.status);
          
          if(response.status==200){
            if(response.data.password == user.password ){
              console.log("login successful")
              history.push('/nearby');
            }
          }
         })
         
         .catch(function(error){
          console.log(error);
         })
    console.log('email: ', this.state.email);
    console.log('password: ', this.state.password);
  }

  onEmailChange(e){
    this.setState({
      email: e.target.value
    });
  }

  onPasswordChange(e){
    this.setState({
      password: e.target.value
    });
  }

  render(){
      return(       
        <div className="box-layout">
          <div className="box-layout__box">
            <p className="header__login">Know Where Your Friends Are</p>
            <form name="form" onSubmit={this.onSubmit} onChange={this.onChange}>
             
            <FormGroup controlId="email">
             <ControlLabel>Email: </ControlLabel>
             <FormControl autoFocus 
             type="email" 
             value={this.state.email}
             onChange={this.onEmailChange}
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
  };
};



export default HomePage;