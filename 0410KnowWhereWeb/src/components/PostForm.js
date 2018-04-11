import React from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';
import axios from 'axios';
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";

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

export default class PostForm extends React.Component{
    constructor(props){
        super(props);
        this.state = {
            username: props.username,
            postText: props.postText ? props.postText : '',
            lattitude: '',
            longitude: ''
        };
        this.onTextChange = this.onTextChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
    }
    onTextChange(e){
        const text = e.target.value;
        this.setState({ postText: text})
    }
    onSubmit(e){
        e.preventDefault();
        const postText = this.state.postText;
        console.log('onsubmit')
        console.log(this.state.username);
        axios.put(`/user/${this.state.username}/whatsup`, postText, axiosConfig)
             .then((response)=>{
                 console.log(response);
             })
             .catch((error)=>{
                 console.log(error);
             })
             //console.log(navigator.geolocation.getCurrentPosition);
    }
    render(){
        return (
            <div>
            <FormGroup controlId="postTextarea" onSubmit={this.onSubmit}>
            <FormControl type="text" 
              componentClass="textarea" 
              className="post-input"
              placeholder="Share your post" 
              value={this.state.postText}
              onChange={this.onTextChange}
            />
           </FormGroup>
          <Button type="submit" className="button">POST</Button> <p>select friends to share:</p>
          </div>
        );
    }
}