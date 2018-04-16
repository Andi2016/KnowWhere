import React from 'react';
import PrivateHeader from './PrivateHeader';
import firebase from '../firebase/firebase';
import axios from 'axios';
import { connect } from 'react-redux';
import {Link} from 'react-router-dom';
import {updateGroup} from '../actions/userAction';

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
        this.onClick = this.onClick.bind(this);
    }

    componentWillMount() {
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

    onClick(groupname) {
       this.props.updateGroup(groupname);
       console.log(this.props.firstname);
       this.props.history.push("/ChatWindow");
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
                                        <div onClick={() => this.onClick(groupname)}>{objectUser}: {group.content}</div>
                                      </li>;
                            }
                            if (groupname.includes('%7C')) {
                                let useranmes = groupname.split('%7C');
                                let objectUser = useranmes[0] === this.state.username ? useranmes[1] : useranmes[0];
                                return <li key={group.groupname}>
                                        <div onClick={() => this.onClick(groupname)}>{objectUser}: {group.content}</div>
                                      </li>;
                            }
                            return <li key={group.groupname}>
                                      <div onClick={() => this.onClick(groupname)}>{groupname}: {group.content}</div>
                                    </li>;
                        })
                    }
                </ul>
            </div>
        );
    }
}

const mapDispatchToProps = (dispatch) => ({
    updateGroup: (friend) => dispatch(updateGroup(friend))

});

const mapStateToProps = state => ({
    username: state.user.username,
    firstname: state.user.firstname
});



export default connect(mapStateToProps, mapDispatchToProps)(ChatPage);