import React from 'react';
import PrivateHeader from './PrivateHeader';
import firebase from '../firebase/firebase';
import axios from 'axios';
import { connect } from 'react-redux';
import {Link} from 'react-router-dom';

axios.defaults.baseURL = 'http://143.215.113.90:8080';
axios.defaults.headers.get['Content-Type'] = 'application/json';
axios.defaults.headers.get['Access-Control-Allow-Origin'] = '*';

let axiosConfig = {
    headers: {
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Method': 'POST, GET, OPTIONS, PUT, DELETE'
    },
    withCredentials: false
};

class ChatPage extends React.Component{

    constructor(props){
        super(props);
        this.state = {
            username: 'Alice',
            groups: []
        };
    }

    componentDidMount() {
        let messageRef = firebase.database().ref(`/${this.state.username}/message`);
        messageRef.on('child_added', () => {
            //let groupname = snapshot.val().groupname;
            axios.get(`/user/${this.state.username}/groupChatTime`, axiosConfig)
                .then((response) => {
                    this.setState({groups: response.data});
                })
                .catch((error) => {
                    console.log(error);
                });
        });
    }

    render(){
        return (
            <div>
                <PrivateHeader />
                <ul>
                    { /* Render the list of messages */
                        this.state.groups.map( group => {
                            let groupname = group.groupname;
                            if (groupname.includes('|')) {
                                let useranmes = groupname.split('|');
                                let objectUser = useranmes[0] === this.state.username ? useranmes[1] : useranmes[0];
                                return <li key={group.groupname}>
                                    <Link to={'/chatWindow'}>{objectUser}: {group.content}</Link>
                                </li>;
                            }
                            if (groupname.includes('%7C')) {
                                let useranmes = groupname.split('%7C');
                                let objectUser = useranmes[0] === this.state.username ? useranmes[1] : useranmes[0];
                                return <li key={group.groupname}><Link to={'/chatWindow'}>{objectUser}: {group.content}</Link></li>;
                            }
                            return <li key={group.groupname}><Link to={'/chatWindow'}>{groupname}: {group.content}</Link></li>;
                        })
                    }
                </ul>
            </div>
        );
    }
}

const mapStateToProps = state => ({
    username: state.user.username,
    firstname: state.user.firstname
});



export default connect(mapStateToProps)(ChatPage);