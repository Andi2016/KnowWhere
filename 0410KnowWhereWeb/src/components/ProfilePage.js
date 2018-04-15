import React from 'react';
import PrivateHeader from './PrivateHeader';
import { connect } from "react-redux";

class ProfilePage extends React.Component{
    render(){
        return (
            <div>
            <PrivateHeader />
            <p>ProdfilePage</p>
            <p>{console.log("username: ", this.props.username)}</p>
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    return{
        username: state.username,
        password: state.password
    }
}

export default connect(mapStateToProps)(ProfilePage);