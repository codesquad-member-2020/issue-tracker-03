import React from 'react';
import ReactDOM from 'react-dom';
import { createStore, applyMiddleware } from 'redux';
import { Provider } from 'react-redux';
import { composeWithDevTools } from 'redux-devtools-extension';
import logger from 'redux-logger';
import ReduxThunk from 'redux-thunk';
import rootReducer from './modules';
import App from './App';
import { BrowserRouter as Router } from 'react-router-dom';
import { createBrowserHistory } from 'history';
import { GlobalStyle } from '$Libs/styles/GlobalStyle';

const customHistory = createBrowserHistory();

const store = createStore(
  rootReducer,
  composeWithDevTools(
    applyMiddleware(
      ReduxThunk.withExtraArgument({ history: customHistory }),
      logger,
    ),
  ),
);

window.store = store;
console.log('[index] : ', store.getState());

ReactDOM.render(
  <Router history={customHistory}>
    <Provider store={store}>
      <GlobalStyle />
      <App />
    </Provider>
  </Router>,
  document.getElementById('root'),
);
