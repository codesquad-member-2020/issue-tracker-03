import React from 'react';
import ReactDOM from 'react-dom';
import { createStore, applyMiddleware } from 'redux';
import { Provider } from 'react-redux';
import { composeWithDevTools } from 'redux-devtools-extension';
import logger from 'redux-logger';
import ReduxThunk from 'redux-thunk';
import rootReducer from './modules';
import App from './App';
import { GlobalStyle } from '$Libs/styles/GlobalStyle';

const store = createStore(
  rootReducer,
  composeWithDevTools(
    applyMiddleware(
      ReduxThunk,
      logger,
    ),
  ),
);

window.store = store;
console.log('[index] : ', store.getState());

ReactDOM.render(
    <Provider store={store}>
      <GlobalStyle />
      <App />
    </Provider>
  ,
  document.getElementById('root'),
);
