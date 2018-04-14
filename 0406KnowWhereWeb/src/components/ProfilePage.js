import React from 'react';
import PrivateHeader from './PrivateHeader';
import { connect } from "react-redux";

class ProfilePage extends React.Component{
    constructor(props){
        super(props);
    }
    render(){
        return (
            <div>
            <PrivateHeader />
            <p>ChatPage</p>
            <p>{console.log("username: ", this.props.username)}</p>
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    return{
        username: state.user.username,
        password: state.user.password
    }
}

export default connect(mapStateToProps)(ProfilePage);