import React from 'react';
import firebase from '../firebase/firebase';


class ChatPage extends React.Component{

    constructor(props){
        super(props);
        this.state = {
            username: props.username,
            friendname: 'Bob'
        }
    }

    render(){
        //const friendname = this.state.friendname;
        //const friendname = this.props.params.friend;
        //console.log(friendname);
        return (
            <div>
            <p>ChatPage</p>
            {console.log(friendname)}
            </div>
        );
    }
}


const mapStateToProps = state => ({
    username: this.state.username
    //friend: this.state.friend
});



export default connect(mapStateToProps)(ChatPage);