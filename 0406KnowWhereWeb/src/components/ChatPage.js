import React from 'react';
//import {firebase} from '../firebase/firebase';
import PrivateHeader from './PrivateHeader';

import { connect } from 'react-redux';


class ChatPage extends React.Component{

    constructor(props){
        super(props);
        this.state = {
            username: props.username,
            firstname: props.firstname
        }
    }

    render(){
        const firstname = this.state.firstname;
        const username = this.state.username;
        //console.log(friendname);
        //const test = firebase.database().ref('/Alice'+'/message').once('value').then(function(snapshot){
            //console.log(snapshot.val().content);
        ///})
        return (
            <div>
            <PrivateHeader />
            <p>ChatPage</p>
            {console.log(username)}
            {console.log(firstname)}
            </div>
        );
    }
}


const mapStateToProps = state => ({
    username: state.user.username,
    groupname: state.user.groupname
});



export default connect(mapStateToProps)(ChatPage);