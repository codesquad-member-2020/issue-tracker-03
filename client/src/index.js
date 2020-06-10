import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import * as serviceWorker from './serviceWorker';
import { Router } from 'react-router-dom';
import { createBrowserHistory } from 'history';
import { createStore, applyMiddleware } from 'redux';
import { Provider } from 'react-redux';
import rootReducer from './modules';
import ReduxThunk from 'redux-thunk';
import logger from 'redux-logger';

const customHistory = createBrowserHistory();

const store = createStore(
  rootReducer,
  applyMiddleware(
    ReduxThunk.withExtraArgument({ history: customHistory }),
    logger
  )
);

ReactDOM.render(
  <Router history={customHistory}>
    <Provider store={store}>
      <App />
    </Provider>
  </Router>,
  document.getElementById('root'),
);
serviceWorker.unregister();