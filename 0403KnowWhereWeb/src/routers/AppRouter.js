import React from 'react';
import { connect } from 'react-redux';
import { BrowserRouter, Route, Switch, Link, NavLink, Router } from 'react-router-dom';
import NearbyPage from '../components/NearbyPage';
import ContactsPage from '../components/ContactsPage';
import ChatPage from '../components/ChatPage';
import HomePage from '../components/Homepage';
import Header from '../components/Header';
import RegisterPage from '../components/RegisterPage';
import ProfilePage from '../components/ProfilePage';
import history from '../components/history';


const AppRouter= () => (
  <BrowserRouter>
  <div>    
  <Header />
  <Switch>
        <Route path="/" component={HomePage} exact={true} />
          <Route history={history} path="/nearby" component={NearbyPage} />
          <Route history={history} path="/contacts" component={ContactsPage} />
          <Route history={history} path="/chat" component={ChatPage} />
          <Route history={history} path="/register" component={RegisterPage} />
          <Route history={history} path="/profile" component={ProfilePage} />
        </Switch>
    </div>
    </BrowserRouter>
);
  
export default AppRouter;
