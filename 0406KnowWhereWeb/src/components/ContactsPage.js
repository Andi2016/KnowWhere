import React from 'react';
import PrivateHeader from './PrivateHeader';
import { Button, FormGroup, FormControl, ControlLabel, ListGroup, ListGroupItem, Image } from "react-bootstrap";
import { connect } from 'react-redux';
import { Link } from 'react-router-dom';
import { Router } from 'react-router-dom';
import { getGroupname, updateGroup} from '../actions/userAction';
import axios from 'axios';
import ChatPage from './ChatPage';
import { Route,hashHistory} from 'react-router';  

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
            username: props.username ? props.username : "Alice",
            friends:[],
            groups:[],
            groupname: props.firstname ? props.firstname : ""
        };
        this.onClick = this.onClick.bind(this);
    }
    componentDidMount(){
        console.log("componentDidMount");
        const uname = this.state.username;
        console.log("username: ", uname);
        axios.get(`/user/${uname}/friendObject`, axiosConfig)
             .then((response)=>{
                 console.log(response);
                 console.log(response.data);
                 this.setState({ friends: response.data})
                 //this.friendlist = response.data;
                 // console.log(this.state.friends);
             })
             .catch((error)=>{
                 console.log(error);
             })
    }
    onClick(friend){
        const uname = this.state.username;
        console.log("unameText: ", uname);
        console.log(friend.username)
        let groupname;
        if(friend.username < uname){
            groupname = friend.username + "|" + uname;
            console.log("groupnameTest: ", groupname);
        }else{
            groupname = uname + "|" + friend.username;
            console.log("groupnameTest: ", groupname);
        };
        this.props.updateGroup(groupname);
        //console.log('chatpage groupname: ', this.props.groupname);
        console.log('contactPage firstname: ', this.props.firstname);
        this.props.history.push("/ChatWindow");
    }
  
    render(){
        //let {friendslist} = this.state.friends;
        return (
            <div>
            <PrivateHeader />          
            {
                  this.state.friends.map((friend) => 
                  
                  <ListGroup className="list-item">
                  <ListGroupItem onClick={()=>{this.onClick(friend)}}>
                  <p><Image src = {friend.photoUrl} className="img-fluid" alt="Responsive image" width={50}/>        {friend.username}</p>
                  </ListGroupItem>      
                </ListGroup>)
                }
                
            </div>
        );
    }
}

const mapStateToProps = state => ({
    username:state.user.username,
    password:state.user.password,
    firstname: state.user.firstname
})

const mapDispatchToProps = (dispatch) => ({
    //getGroupname: (friend) => dispatch(getGroupname(friend)),
    updateGroup: (friend) => dispatch(updateGroup(friend))
});


export default connect(mapStateToProps, mapDispatchToProps)(ContactsPage);

/**
 *                     <Button
                    onClick={(friend)=>{this.props.getGroupname({friend}
                    )
                    this.props.history.push(`/chat`)
                    }}>chat</Button>
 */