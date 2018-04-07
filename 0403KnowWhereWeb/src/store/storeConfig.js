import { createStore, combineReducers, applyMiddleware, compose } from 'redux';
import thunk from 'redux-thunk';
import postReducer from '../reducers/postReducer';
import userReducer from '../reducers/userReducer';

const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;

export default () => {
  const store = createStore(
    combineReducers({
      user: userReducer,
      post: postReducer
    }),
    composeEnhancers(applyMiddleware(thunk))
  );

  return store;
};
