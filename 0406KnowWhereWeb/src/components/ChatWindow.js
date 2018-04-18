/**
 * Created by dwyane on 4/14/18.
 */
import React from 'react';
import PrivateHeader from './PrivateHeader';
import firebase from '../firebase/firebase';
import axios from 'axios';
import { connect } from 'react-redux';
import { Input, Button, MessageBox } from 'react-chat-elements';

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

class ChatWindow extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            messages: [],
            group: props.firstname
        };
        this.addMessage = this.addMessage.bind(this);
    }
    componentWillMount() {
        let messageRef = firebase.database().ref(`/${this.props.username}/message`);
        messageRef.on('child_added', () => {
            axios.get(`/group/${this.props.firstname}/chat`, axiosConfig)
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
        let msg = {"sender": this.props.username, "groupname": this.props.firstname, "content": this.inputEl.value};
        axios.post(`/group/${this.props.firstname}/chat`, msg, axiosConfig)
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
      console.log('chatwindow username: ', this.props.username);
      console.log('chatwindow firstname: ', this.props.firstname);

      return (
            <div>
                <PrivateHeader />
                <form onSubmit={this.addMessage}>
                <div className="post-input">
                    <input className="post-input" type="text" ref={ el => this.inputEl = el} />
                    <input type="submit"/>
                </div>
                    <div>
                        { /* Render the list of messages */
                            this.state.messages.reverse().map( message => {
                                let position = message.sender === this.props.username ? 'right' : 'left';
                                return <MessageBox
                                    position={position}
                                    type={'text'}
                                    text={message.sender + ': ' +message.content}
                                    date = {new Date(message.timeStamp)}
                                    />
                        })
                        }
                    </div>
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