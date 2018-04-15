import React from 'react';
import PrivateHeader from './PrivateHeader';
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";
import { connect } from 'react-redux';
import { Link } from 'react-router-dom';
import axios from 'axios';

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

export default class ContactsPage extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            username: 'david',
            friends:[]
        };
        this.onClick = this.onClick.bind(this);
    }
    onClick(){
        console.log("onClck")
        axios.get(`/user/${this.state.username}/friend`, axiosConfig)
             .then((response)=>{
                 console.log(response);
                 //console.log(response.data);
                 this.setState({ friends: response.data})
                 console.log(this.state.friends);
             })
             .catch((error)=>{
                 console.log(error);
             })
    }

    
    render(){
        //let {friendslist} = this.state.friends;
        return (
            <div>
            <PrivateHeader />
            ContactsPage
            <button onClick={this.onClick}>button</button>
              {
                  this.state.friends.map((friend) => <li key={friend}> {friend} <Link to="/chat/:{friend}">CHAT</Link></li>)
              }
            </div>
        );
    }
}