import React from 'react';
import PrivateHeader from './PrivateHeader';
import { Button, FormGroup, FormControl, ControlLabel, ListGroup, ListGroupItem } from "react-bootstrap";
import { connect } from 'react-redux';
import { Route,hashHistory} from 'react-router';
import { Link } from 'react-router-dom';
import axios from 'axios';
import { updateGroup } from '../actions/userAction';

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
      username: props.username? props.username : "Alice",
      nearbyArray: []
    }    
  }
  componentDidMount(){
    console.log("componentDidMount");
    
    axios.get(`/user/${this.state.username}/friendLocation`, axiosConfig)
         .then((response)=>{
           console.log(response.data);
           this.setState({ nearbyArray:response.data });
         })
         .catch((error)=>{
           console.log(error);
         })
  }
  onClick(friend){
    const uname = this.state.username;
    let groupname;
    if(friend<uname){
      groupname = friend + '|' + uname;
    }else{
      groupname = uname + '|' + friend;
    }
    this.props.updateGroup(groupname);
    //console.log('chatpage groupname: ', this.props.groupname);
    //console.log('nearbyPage firstname: ', this.props.firstname);
    this.props.history.push("/ChatWindow");
  };
  
  render(){
    return (
      <div>
      <PrivateHeader />
      {
        this.state.nearbyArray.map((nearby) =>{
          return (
            <ListGroup className="list-item">
            <ListGroupItem onClick={()=>{this.onClick(nearby.username)}} bsStyle="info">
             <p>{nearby.username}  {Math.round(nearby.distance*100)/10}km </p>   
             <p>{nearby.whatsup} </p>      
             </ListGroupItem>      
            </ListGroup>
          )
          })
      }    
    </div>
    );
  }
}
    
const mapStateToProps = state => ({
    username: state.user.username
  });

const mapDispatchToProps = (dispatch) => ({
    updateGroup: (friend) => dispatch(updateGroup(friend))
});
export default connect(mapStateToProps, mapDispatchToProps)(NearbyPage);