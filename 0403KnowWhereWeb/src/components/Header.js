import React from 'react';
import { NavLink } from 'react-router-dom';
import NearbyPage from './NearbyPage';
import ContactsPage from './ContactsPage';
import ChatPage from './ChatPage';

const Header = () => (
  <header className="header">
  <div className="content-container">
    <div className="header__content">
    <NavLink className="header__title" to="/" exact={true}><h3>KnowWhere</h3></NavLink>
    <NavLink className="header__title" to="/nearby" activeClassName="is-active" exact={true}><h6>Nearby</h6></NavLink>
    <NavLink className="header__title" to="/contacts" activeClassName="is-active"><h6>Contacts</h6></NavLink>
    <NavLink className="header__title" to="/chat" activeClassName="is-active"><h6>Chat</h6></NavLink>   
    </div>
  </div>
  </header>
);

export default Header;
