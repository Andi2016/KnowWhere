import React from 'react';
import NearbyList from './NearbyList';
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

class NearbyPage extends React.Component{
  constructor(props){
    super(props);
    this.state={
      username: 'david',
      friends: [],
      friendsPosts: {
        friend: '',
        friendPostText: '',
        friendLocation:'',
        friendStatus: ''
      }
    }
    this.onClick = this.onClick.bind(this);
  }
  onClick(){
    console.log("onClick");
    
    axios.get(`/user/${this.state.username}/friend`, axiosConfig)
         .then((response)=>{
           this.setState({ friends: response.data});
         })
         .catch((error)=>{
           console.log(error);
         });
    console.log(this.state.friends);
    
    const friendslist =  this.state.friends;
    
//const friendslist = ["robert","michael","james","maria"];

    friendslist.map((friend)=>{
      axios.get(`/user/${friend}/whatsup`, axiosConfig)
           .then((responser)=>{
             this.setState({ friendsPosts: {
               friend: {friend},
               friendPostText: response.data
             }})
           })
           .catch((error)=>{
             console.log(error)
           })
    })
    
    friendlist.map((friend)=>{
      axios.get(`/user/${friend}/`)
    })
    console.log(this.state.friendsPosts);
  }
  render(){
    return (
      <div>
      <PrivateHeader />
      <p>NearbyPage</p>
      <button onClick={this.onClick}>button</button>
    </div>
    );
  }
}
    


export default NearbyPage;