import React from 'react';
import PrivateHeader from './PrivateHeader';
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";
import { connect } from 'react-redux';
import { Link } from 'react-router-dom';
import { Router } from 'react-router-dom';
import { getGroupname } from '../actions/userAction';
import axios from 'axios';
import ChatPage from './ChatPage';
import { Route,hashHistory} from 'react-router';
import firebase from '../firebase/firebase';

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
            friends:['Joe', 'Tina'],
            messages: []
        };
        this.onClick = this.onClick.bind(this);
    }
    onClick(){
        console.log("onClick");
        let dbmessages = firebase.database().ref('/Alice/message');
        dbmessages.on('child_added', snapshot => {
            console.log(snapshot.val());
            this.setState({messages: [snapshot.val().content].concat(this.state.messages)});
        });
        console.log(this.state.messages);
        
        const uname = this.state.username;
        console.log('user', uname);
        axios.get(`/user/${uname}/friend`, axiosConfig)
             .then((response)=>{
                 console.log(response);
                 console.log(response.data);
                 this.setState({ friends: response.data});
                 console.log(this.state.friends);
             })
             .catch((error)=>{
                 console.log(error);
             })
    }
  
    render(){

        return (
            <div>
            <PrivateHeader />
            ContactsPage
            {this.props.username}
            <button onClick={this.onClick}>button</button>
                <ul>
                    {
                        this.state.friends.map((friend) => <li key={friend}> {friend}</li>)
                    }
              </ul>
                <ul>
                    {
                        this.state.messages.map(msg => <li> {msg} </li>)
                    }
                </ul>

                
            </div>
        );
    }
}

const mapStateToProps = state => ({
    username:state.user.username,
    password:state.user.password,
    firstname: state.user.firstname
});

const mapDispatchToProps = (dispatch) => ({
    getGroupname: (friend) => dispatch(getGroupname(friend))
});


export default connect(mapStateToProps, mapDispatchToProps)(ContactsPage);

/**
 *                     <Button
                    onClick={(friend)=>{this.props.getGroupname({friend}
                    )
                    this.props.history.push(`/chat`)
                    }}>chat</Button>
 */