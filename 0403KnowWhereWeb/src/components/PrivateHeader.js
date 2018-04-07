import React from 'react';
import { NavLink } from 'react-router-dom';
import NearbyPage from './NearbyPage';
import ContactsPage from './ContactsPage';
import ChatPage from './ChatPage';

const PrivateHeader = () => (
  <header className="header-private">
  <div className="content-container">
    <div className="header-private__content">
    <NavLink className="header__title" to="/nearby" activeClassName="is-active" exact={true}><h6>Post</h6></NavLink>
    <NavLink className="header__title" to="/contacts" activeClassName="is-active"><h6>Nearby</h6></NavLink>
    <NavLink className="header__title" to="/chat" activeClassName="is-active"><h6>Contact</h6></NavLink> 
    <NavLink className="header__title" to="/profile" activeClassName="is-active"><h6>Profile</h6></NavLink>  
    </div>
  </div>
  </header>
);

export default PrivateHeader;
