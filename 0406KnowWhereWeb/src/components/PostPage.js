import React from 'react';
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";
import { connect } from 'react-redux';
import { Link } from 'react-router-dom';
import axios from 'axios';
import PostForm from './PostForm';
import PrivateHeader from './PrivateHeader';


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
/**const PostPage = (props)=>(
    
    <div>
    post page
    <PrivateHeader onSubmit={this.onSubmit}/>
    <PostForm />
    </div>
);


export default PostPage; */

class PostPage extends React.Component {

    constructor(props){
        super(props);
        this.state = {
            username: props.username,
            postText: props.postText ? props.postText : '',
            lattitude: '',
            longitude: '',
            status: ''
        };
        this.onTextChange = this.onTextChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
        this.onStatusChange = this.onStatusChange.bind(this);
        this.onStatusSubmit = this.onStatusSubmit.bind(this);
    }
    onTextChange(e){
        const text = e.target.value;
        this.setState({ postText: text})
    }
    onStatusChange(e){
        const status = e.target.value;
        this.setState({ status: status});
    }
    onSubmit(e){
        e.preventDefault();
        console.log('onPostSubmit');
        const postText = this.state.postText;
        axios.put(`/user/${this.state.username}/whatsup`, postText, axiosConfig)
             .then((response)=>{
                 console.log(response);
             })
             .catch((error)=>{
                 console.log(error);
             })
        console.log("whatsup: ", postText);
    }

    onStatusSubmit(e){
        e.preventDefault();
        const statusText = this.state.status
        console.log('onsubmit')
        console.log(this.state.username);
        axios.put(`/user/${this.state.username}/status`, statusText, axiosConfig)
        .then((response)=>{
            console.log(response);
        })
        .catch((error)=>{
            console.log(error);
        })
        console.log("status", statusText);
    }

    render(){
        return (
            <div>           
            <PrivateHeader />
            <form name="statusform" onSubmit={this.onStatusSubmit}>
            <FormGroup controlId="postTextarea">
            <FormControl type="text" 
              componentClass="textarea" 
              className="post-input"
              placeholder="Choose status from online, offline, busy" 
              value={this.state.status}
              onChange={this.onStatusChange}
            />
            <Button type="submit" className="button">STATUS</Button> 
            </FormGroup>
            </form>
           
            <form name="postform" onSubmit={this.onSubmit}>
            <FormGroup controlId="postTextarea">
            <FormControl type="text" 
              componentClass="textarea" 
              className="post-input"
              placeholder="Share your post in this area. " 
              value={this.state.postText}
              onChange={this.onTextChange}
            />
            <Button type="submit" className="button">ShareYourPost</Button> 
            </FormGroup>
            </form>
            </div>
        );

    }
}

const mapStateToProps = state => ({
    username:state.user.username
});

export default connect(mapStateToProps)(PostPage);