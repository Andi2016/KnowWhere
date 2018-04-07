import React from 'react';
import LogInForm from './LogInForm';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';
import axios from 'axios';
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";
import history from './history';
import { logIn } from '../actions/userAction'


const HomePage = (props) => (
    <div className="box-layout">
    <div className="box-layout__box">
     <p className="header__login">Know Where Your Friends Are</p>
     <LogInForm 
        onSubmit={(user)=>{
            props.dispatch(logIn(user))
            props.history.push('/profile')
        }} />
     <Link to="/register">Register</Link>
    </div>
   </div>
);


//const mapDispatchToProps = (dispatch) => ({
    //logIn: (user) => dispatch(logIn(user))
//});

//export default connect(undefined, mapDispatchToProps)(HomePage);
export default connect()(HomePage)