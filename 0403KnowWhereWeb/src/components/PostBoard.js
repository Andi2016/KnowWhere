import React from 'react';
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";
import { startAddPost } from '../actions/postAction';

export default class PostBoard extends React.Component{
  constructor(props){
    super(props);

    this.state={
      postText: '',
      coordinates: ''
    }
    this.onSubmit = this.onSubmit.bind(this);
    this.onTextChange = this.onTextChange.bind(this);
  }

  onSubmit = (e) => {
    e.preventDefault();
    console.log("user可以传递吗？", this.props.user)
    this.props.startAddPost(post);
    console.log("user可以传递吗？", this.props.user)
  }

  onTextChange(e){
    this.setState({postText: e.target.value});
    console.log("user可以传递吗？", this.props.user);
  }
  render(){
    return (
    <div className="page-header">

      <div className = "content-container">
       <FormGroup controlId="postTextarea" onSubmit={this.onSubmit}>
        <FormControl type="text" 
          componentClass="textarea" 
          className="post-input"
          placeholder="Share your post" 
          value={this.state.postText}
          onChange={this.onTextChange}
        />
       </FormGroup>
      <button type="submit" className="button">POST</button> <p>select friends to share:</p>
      </div>

      <div className="content-container">
       <FormGroup controlId="selectPostShare">
        <FormControl componentClass="select" multiple>
         <option value="friend1">friend 1</option>
         <option value="friend2">friend 2</option>
        </FormControl>
       </FormGroup>
      </div>
    </div>
    );
  }
};

