import React from 'react';
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";
import { connect } from 'react-redux';
import PostForm from './PostForm';
import PrivateHeader from './PrivateHeader';

const PostPage = (props)=>(
    <div>
    post page
    <PrivateHeader/>
    <PostForm onSubmit={()=>{
        console.log(props.username)
    }}/>
    </div>
);

const mapStateToProps = (state) => {
    return {
        username: state.user.username
    };
}

export default connect(mapStateToProps)(PostPage);