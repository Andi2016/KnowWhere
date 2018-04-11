import React from 'react';
import LogInForm from './LogInForm';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';
import axios from 'axios';
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";
import history from './history';
import { logIn } from '../actions/userAction';


class HomePage extends React.Component{
    constructor(props){
        super(props);
    }

    render(){
        return (
            <div className="box-layout">
            <div className="box-layout__box">
             <p className="header__login">Know Where Your Friends Are</p>
             <LogInForm 
                onSubmit={(user)=>{
                    this.props.dispatch(logIn(user));
                    this.props.history.push('/profile')
                }} />
             <Link to="/register">Register</Link>
            </div>
           </div>
        );
    }
}

const mapStateToProps = state => ({
    username:state.username,
    password:state.password
})

const mapDispatchToProps = (dispatch) => ({
    logIn: (user) => dispatch(logIn(user))
});

export default connect(mapStateToProps, mapDispatchToProps)(HomePage);
//export default connect()(HomePage)