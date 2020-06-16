import React from 'react';
import styled from 'styled-components';
import AppRouter from '@Routers/AppRouter';

import Cookies from 'universal-cookie';
import { setLoginInfo, resetLoginInfo } from './modules/login';
import { useDispatch } from 'react-redux';

import HeaderContainer from '@Containers/HeaderContainer';
import FooterContainer from '@Containers/FooterContainer';

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
      <HeaderContainer />
      <ContentsWrap>
        <AppRouter />
      </ContentsWrap>
      <FooterContainer />
    </>
  );
}

export default App;
