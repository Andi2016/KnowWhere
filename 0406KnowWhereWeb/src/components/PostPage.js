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
            username: props.username ? props.username : "Alice",
            postText:{ whatsup: props.postText ? props.postText : ''},
            lattitude: '',
            longitude: '',
            status: { status: ''}
        };
        this.onTextChange = this.onTextChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
        this.onStatusChange = this.onStatusChange.bind(this);
        this.onStatusSubmit = this.onStatusSubmit.bind(this);
        //this.handleStatus = this.handleStatus.bind(this);
    }
    onTextChange(e){
        const text = e.target.value;
        this.setState({ postText: text})
    }
    /*
    onStatusChange(e){
        const status = e.target.value;
        this.setState({ status: status});
    }*/
    onSubmit(e){
        e.preventDefault();
        console.log('onPostSubmit');
        const postText = this.state.postText;
        axios.put(`/user/${this.state.username}/whatsup`, {whatsup: postText}, axiosConfig)
             .then((response)=>{
                 console.log(response);
             })
             .catch((error)=>{
                 console.log(error);
             })
        console.log("whatsup: ", postText);
    }
    onStatusChange(e){
        const status = e.target.value;
        this.setState({
            status: status
        })
        console.log("update status: ", this.state.status);
    }
   
    onStatusSubmit(e){
        e.preventDefault();
        const statusText = this.state.status
        console.log('onStatusSubmit')
        axios.put(`/user/${this.state.username}/status`, {status: statusText}, axiosConfig)
        .then((response)=>{
            console.log(response);
        })
        .catch((error)=>{
            console.log(error);
        })
        console.log("status: ", statusText);
    }

    render(){
        return (
            <div>           
            <PrivateHeader />
            <div className="content-container">
            <form name="statusform" onSubmit={this.onStatusSubmit}>
            <div className="content-container">
            <select name='status' value={this.state.status} onChange={this.onStatusChange}>
              <option value="available">Online</option>
              <option value="offline">Offline</option>
            </select>
            </div>
            <div className="content-container">
            <Button type="submit" className="button">UpdateStatus</Button>
            </div>
            </form>
            </div>

            <div className="content-container">
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
            </div>
        );

    }
}

const mapStateToProps = state => ({
    username:state.user.username,
    status: state.user.status
});

export default connect(mapStateToProps)(PostPage);

