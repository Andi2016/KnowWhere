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
                    this.props.logIn(user);
                    //console.log(user);
                    this.props.history.push('/nearby')
                }} />
             <Link to="/register">Register</Link>
            </div>
           </div>
        );
    }
}



const mapDispatchToProps = (dispatch) => ({
    logIn: (user) => dispatch(logIn(user))
});

export default connect(undefined, mapDispatchToProps)(HomePage);
//export default connect()(HomePage)