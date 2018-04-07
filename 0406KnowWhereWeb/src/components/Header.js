import React from 'react';
import { NavLink } from 'react-router-dom';
import NearbyPage from './NearbyPage';
import ContactsPage from './ContactsPage';
import ProfilePage from './ProfilePage';

const Header = () => (
  <header className="header-define">
  <div className="content-container">
    <div className="header__content">
    <NavLink className="header__title" to="/" exact={true}><h3>KnowWhere</h3></NavLink>
    </div>
  </div>
  </header>
);

export default Header;
