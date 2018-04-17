import React from 'react';
import PrivateHeader from './PrivateHeader';
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";
import { connect } from 'react-redux';
import { Link } from 'react-router-dom';
import axios from 'axios';
import NearbyItem from './NearbyItem';

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
  
  render(){
    return (
      <div>
      <PrivateHeader />
      {
        this.state.nearbyArray.map((nearby) =>{
          return <NearbyItem key={nearby.username}{...nearby} />
          })
      }    
    </div>
    );
  }
}
    
const mapStateToProps = state => ({
    username: state.user.username
  });


export default connect(mapStateToProps)(NearbyPage);