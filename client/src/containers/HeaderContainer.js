import React from 'react';
import styled from 'styled-components';
import { useSelector, useDispatch } from 'react-redux';
import Cookies from 'universal-cookie';
import { resetLoginInfo } from '@Modules/login';
import GlobalTitles from '../components/Common/GlobalTitles';

const HeaderWrap = styled.header`
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 15px;
  background: #24292e;
  * {
    color: #fff;
  }
`;

const HeaderContainer = () => {
  const dispatch = useDispatch();
  const { loginStateInfo } = useSelector(({ login }) => login);
  const cookies = new Cookies();

  function onSignoutHandler() {
    console.log('onSignoutHandler called');

    cookies.remove('jwt');
    dispatch(resetLoginInfo());
  }

  return (
    <>
      <HeaderWrap>
        <h1>Header</h1>
        {!loginStateInfo && (
          <a href={process.env.REACT_APP_API_LOGIN}>sign in</a>
        )}
        {loginStateInfo && <span onClick={onSignoutHandler}>sign out</span>}
      </HeaderWrap>
      <GlobalTitles />
    </>
  );
};

export default HeaderContainer;
