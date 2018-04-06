import React from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";


class HomePage extends React.Component {  
  render(){
      return(       
        <div className="box-layout">
          <div className="box-layout__box">
            <p className="header__login">Know Where Your Friends Are</p>
            <form name="form">
             <FormGroup controlId="email">
             <ControlLabel>Email: </ControlLabel>
             <FormControl autoFocus type="email" /></FormGroup>

             <FormGroup controlId="password">
             <ControlLabel>Password: </ControlLabel>
             <FormControl autoFocus type="text" /></FormGroup>

             <Button block type="submit">Login</Button>
             
            </form>            
            <Link to="/register">Register</Link>  
        </div>
      </div>
      );
  };
};



export default HomePage;