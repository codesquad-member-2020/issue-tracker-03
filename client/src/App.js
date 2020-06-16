import React from 'react';
import styled from 'styled-components';
import AppRouter from '@Routers/AppRouter';

import Cookies from 'universal-cookie';
import { setLoginInfo, resetLoginInfo } from './modules/login';
import { useDispatch } from 'react-redux';

const ContentsWrap = styled.div`
  max-width: 1200px;
  margin: 0 auto;
`;

function App() {
  const dispatch = useDispatch();

  const cookies = new Cookies();
  const loginInfo = cookies.get('jwt');

  if (loginInfo !== undefined) {
    dispatch(setLoginInfo(loginInfo));
  } else {
    dispatch(resetLoginInfo());
  }

  return (
    <>
      <ContentsWrap>
        <AppRouter />
      </ContentsWrap>
    </>
  );
}

export default App;
