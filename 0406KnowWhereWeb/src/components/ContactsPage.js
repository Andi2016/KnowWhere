import React from 'react';
import PrivateHeader from './PrivateHeader';
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";
import { connect } from 'react-redux';
import { Link } from 'react-router-dom';
import { Router } from 'react-router-dom';
import axios from 'axios';
import ChatPage from './ChatPage';

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

class ContactsPage extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            username: props.username,
            friends:['Joe', 'Tina']
        };
        this.onClick = this.onClick.bind(this);
    }
    onClick(){
        console.log("onClick")
        
        const uname = this.state.username;
        console.log(uname);
        axios.get(`/user/${uname}/friend`, axiosConfig)
             .then((response)=>{
                 console.log(response);
                 console.log(response.data);
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
            {this.props.username}
            <button onClick={this.onClick}>button</button>
              
            {
                  this.state.friends.map((friend) => 
                  <li key={friend}> {friend} 
                    <ChatPage friendname={friend} />
                    <Link to={
                        `/chat/${username}`
                    }>chat</Link>
                    </li>)
                }
            </div>
        );
    }
}

const mapStateToProps = state => ({
    username:state.user.username,
    password:state.user.password
})



export default connect(mapStateToProps)(ContactsPage);