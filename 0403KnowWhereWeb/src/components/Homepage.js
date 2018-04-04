import React from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';
import { Button, FormGroup, FormControl, ControlLabel } from "react-bootstrap";


class HomePage extends React.Component {  
  constructor(props){
      super(props);
      this.state={
          email: '',
          password: '',
          submitted: false
      }
      this.handleChange = this.handleChange.bind(this);
      this.handleSubmit = this.handleSubmit.bind(this);
  }
  validForm(){
      return this.state.email.length > 0 && this.state.password.length >0
  }
  handleChange = (e) => {
      const {name, value} = e.target;
      this.setState({[name]:value})
  };
  handleSubmit=(e)=>{
      e.preventDefault();
      this.setState({ submitted: true});
  };
  render(){
    const { username, password, submitted } = this.states;
      return(       
        <div className="box-layout">
          <div className="box-layout__box">
            <p className="header__login">Know Where Your Friends Are</p>

            <form onSubmit = {this.handleSubmit}>
              <FormGroup controlID="emial">
                <ControlLabel>Email</ControlLabel>
                <FormControl
                  autoFocus
                  type="email"
                  value={this.state.email}
                  onChange={this.handleChange}
                />
                </FormGroup>
              <FormGroup controlId="password">
                <ControlLabel>Password</ControlLabel>
                <FormControl
                  value={this.state.password}
                  onChange={this.handleChange}
                  type="password"
                />            
              </FormGroup>
                <Button className="button-login"
                        disabled={!this.validForm()}
                        type="submit"
                >LogIn
                </Button> 
            
            <Link to="/register" className="button-login">Register</Link>  
        </form>
        </div>
      </div>
      );
  };
};

const mapStateToProps = (state)=>{
    const { loggingIn } = state.authentication;
    return {
        loggingIn
    };
};

const connectedLoginPage = connect(mapStateToProps)(HomePage);
export { connectedLoginPage as HomePage };