/**
 * Created by dwyane on 4/14/18.
 */
import React, { Component } from 'react';
import PrivateHeader from './PrivateHeader';
import firebase from '../firebase/firebase';
import axios from 'axios';
import { connect } from 'react-redux';

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

class ChatWindow extends Component {
    constructor(props) {
        super(props);
        this.state = {
            fromUser: 'Alice',
            toUser: 'Bob',
            groupname: 'Alice|Bob',
            messages: [],
            group: props.firstname
        };
        this.addMessage = this.addMessage.bind(this);
    }
    componentWillMount() {
        let messageRef = firebase.database().ref(`/${this.state.fromUser}/message`);
        messageRef.on('child_added', () => {
            //let groupname = snapshot.val().groupname;
            axios.get(`/group/${this.state.groupname}/chat`, axiosConfig)
                .then((response) => {
                    this.setState({messages: response.data});
                })
                .catch((error) => {
                    console.log(error);
                });
        });
    }
    addMessage(e){
        e.preventDefault();
        let msg = {"sender": "Alice", "groupname": "Alice|Bob", "content": this.inputEl.value};
        axios.post(`/group/${this.state.groupname}/chat`, msg, axiosConfig)
            .then((response) => {
                console.log('submitted');
                this.inputEl.value = '';
            })
            .catch((error) => {
                console.log(error);
            });
    }
    render() {
      const group = this.state.group;
        return (
            <div>
                {console.log(this.state.group)}
                <PrivateHeader />
                <form onSubmit={this.addMessage}>
                    <input type="text" ref={ el => this.inputEl = el} />
                    <input type="submit"/>
                    <ul>
                        { /* Render the list of messages */
                            this.state.messages.map( message => <li key={message.timeStamp}>{message.content}</li> )
                        }
                    </ul>
                </form>
            </div>
        );
    }
}

const mapStateToProps = state => ({
    username: state.user.username,
    firstname: state.user.firstname
});

export default connect(mapStateToProps)(ChatWindow);