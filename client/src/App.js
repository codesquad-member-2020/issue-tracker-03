import React from 'react';
import styled from 'styled-components';
import AppRouter from '@Routers/AppRouter';

import Cookies from 'universal-cookie';
import { setLoginInfo, resetLoginInfo } from './modules/login';
import { useDispatch } from 'react-redux';

import HeaderContainer from '@Containers/HeaderContainer';
import FooterContainer from '@Containers/FooterContainer';

import * as jwtDecode from 'jwt-decode';

const ContentsWrap = styled.div`
  max-width: 1200px;
  margin: 0 auto;
`;

function App() {
  const dispatch = useDispatch();

  const cookies = new Cookies();
  const jwtToken = cookies.get('jwt');
  let decodedInfo = null;
  
  if (jwtToken) {
    console.log("Decoded Info:", decodedInfo)
    decodedInfo = jwtDecode(jwtToken);
  }

  if (decodedInfo) {
    dispatch(setLoginInfo(decodedInfo));
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
