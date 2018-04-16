import React from 'react';
import { connect } from 'react-redux';
import { BrowserRouter, Route, Switch, Link, NavLink, Router } from 'react-router-dom';
import NearbyPage from '../components/NearbyPage';
import ContactsPage from '../components/ContactsPage';
import PostPage from '../components/PostPage';
import HomePage from '../components/Homepage';
import Header from '../components/Header';
import RegisterPage from '../components/RegisterPage';
import ChatPage from '../components/ChatPage';
import history from '../components/history';
import ChatWindow from '../components/ChatWindow';


const AppRouter= () => (
  <BrowserRouter>
  <div>    
  <Header />
  <Switch>
        <Route path="/" component={HomePage} exact={true} />
          <Route history={history} path="/nearby" component={NearbyPage} />
          <Route history={history} path="/contacts" component={ContactsPage} />
          <Route history={history} path="/chat/" component={ChatPage} />
          <Route history={history} path="/post" component={PostPage} />
          <Route history={history} path="/register" component={RegisterPage} />
          <Route history={history} path="/ChatWindow" component={ChatWindow}/>
        </Switch>
    </div>
    </BrowserRouter>
);
  
export default AppRouter;
