import React from 'react';
import { connect } from 'react-redux';
import { Link } from 'react-router-dom';

class ContactsPage extends React.Component{
    
    render(){
        //const {user, users} = this.props;
        return(
            <div className="content-container">
              <h3 className="page-header__title"> Contact !</h3>
              
                <Link to="/">Logout</Link>
            </div>
        );
    }
};


    

//const connectedContactsPage = connect(mapStateToProps)(ContactsPage);
//export { connectedContactsPage as ContactsPage };
export default ContactsPage;