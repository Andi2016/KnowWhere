import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'react-redux';
import AppRouter from './routers/AppRouter';
import userReducer from './reducers/userReducer';
import storeConfig from './store/storeConfig';
import 'normalize.css/normalize.css';
import './styles/styles.scss';

const store = storeConfig();
const state = store.getState();

const jsx = (
  <Provider store={store}>
    <AppRouter />
  </Provider>
);
console.log('store.getState(): ', store.getState())
console.log('state.user: ', state.user);
window.store = store;


ReactDOM.render(jsx, document.getElementById('app'));
