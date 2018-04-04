import React from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';

class RegisterPage extends React.Component {
    constructor(props) {
        super(props);
 
        this.state = {
            user: {
                email: '',
                username: '',
                password: ''
            },
            submitted: false
        };
 
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }
 
    handleChange(event) {
        const { name, value } = event.target;
        const { user } = this.state;
        this.setState({
            user: {
                ...user,
                [name]: value
            }
        });
    }
 
    handleSubmit(event) {
        event.preventDefault();
 
        this.setState({ submitted: true });
        const { user } = this.state;
        const { dispatch } = this.props;
        if (user.email && user.username && user.password) {
            dispatch(userActions.register(user));
        }
    }
 
    render() {
        const { registering  } = this.props;
        const { user, submitted } = this.state;
        return (
            <div>
                <h3>Register</h3>
                <form name="form" onSubmit={this.handleSubmit}>
                    <div >
                        <label>Username</label>
                        <input type="text" className="text-input" name="username" placeholder = "Username" value={user.username} onChange={this.handleChange} />
                        {submitted && !user.username &&
                            <div className="help-block">Username is required</div>
                        }
                    </div>
                    <div>
                        <label>Email</label>
                        <input type="text" className="text-input" name="email" placeholder="Email" value={user.email} onChange={this.handleChange} />
                        {submitted && !user.email &&
                            <div className="help-block">Email is required</div>
                        }
                    </div>
                    <div>
                        <label>Password</label>
                        <input type="password" className="form-control" name="password" value={user.password} onChange={this.handleChange} />
                        {submitted && !user.password &&
                            <div className="help-block">Password is required</div>
                        }
                    </div>
                    <div>
                        <button className="btn btn-primary">Register</button>
                        <Link to="/login" className="btn btn-link">Cancel</Link>
                    </div>
                </form>
            </div>
        );
    }
}
 
function mapStateToProps(state) {
    const { registering } = state.registration;
    return {
        registering
    };
}
 
const connectedRegisterPage = connect(mapStateToProps)(RegisterPage);
export { connectedRegisterPage as RegisterPage };