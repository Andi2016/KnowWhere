import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'react-redux';
import AppRouter from './routers/AppRouter';
import userReducer from './reducers/userReducer';
import storeConfig from './store/storeConfig';
import 'normalize.css/normalize.css';
import './styles/styles.scss';
import firebase from './firebase/firebase';

const store = storeConfig();

//store.dispatch(addExpense({ description: 'Water bill', amount: 4500 }));
//store.dispatch(addExpense({ description: 'Gas bill', createdAt: 1000 }));
//store.dispatch(addExpense({ description: 'Rent', amount: 109500 }));

const state = store.getState();
////const visibleExpenses = getVisibleExpenses(state.expenses, state.filters);
//console.log(visibleExpenses);
//configureFakeBackend();

const jsx = (
  <Provider store={store}>
    <AppRouter />
  </Provider>
);
console.log('store.getState(): ', store.getState())
console.log('state.user: ', state.user);
window.store = store;
//const jsx = (

  //<AppRouter />
//);

ReactDOM.render(jsx, document.getElementById('app'));
