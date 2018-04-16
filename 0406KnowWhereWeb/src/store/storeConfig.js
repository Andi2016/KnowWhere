import { createStore, combineReducers, applyMiddleware, compose } from 'redux';
import thunk from 'redux-thunk';
import createHistory from 'history/createBrowserHistory';
import userReducer from '../reducers/userReducer';
import { routerMiddleware } from 'react-router-redux';

const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;
const history = createHistory();
const middleware = routerMiddleware(history);

export default () => {
  const store = createStore(
    combineReducers({
      user: userReducer
    }),
    composeEnhancers(applyMiddleware(middleware, thunk))
  );

  return store;
};

export { history };
