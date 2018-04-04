import React from 'react';
import { connect } from 'react-redux';
import { BrowserRouter, Route, Switch, Link, NavLink, Router } from 'react-router-dom';
import NearbyPage from '../components/NearbyPage';
import ContactsPage from '../components/ContactsPage';
import ChatPage from '../components/ChatPage';
import HomePage from '../components/Homepage';
import Header from '../components/Header';
import RegisterPage from '../components/RegisterPage';



const AppRouter= () => (
  <BrowserRouter>
  <div>    
  <Header />
  <Switch>
        <Route path="/" component={HomePage} exact={true} />
        <Route path="/nearby" component={NearbyPage} />
        <Route path="/contacts" component={ContactsPage} />
        <Route path="/chat" component={ChatPage} />
        </Switch>
    </div>
    </BrowserRouter>
);
  
export default AppRouter;
