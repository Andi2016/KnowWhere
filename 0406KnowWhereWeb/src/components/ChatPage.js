import React from 'react';
//import {firebase} from '../firebase/firebase';

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
            <p>ChatPage</p>
            {console.log(username)}
            {console.log(firstname)}
            
            </div>
        );
    }
}


const mapStateToProps = state => ({
    username: state.user.username,
    firstname: state.user.firstname
});



export default connect(mapStateToProps)(ChatPage);