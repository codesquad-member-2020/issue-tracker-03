import React from 'react';
import { useSelector, useDispatch } from 'react-redux';
import Cookies from 'universal-cookie';
import { resetLoginInfo } from '@Modules/login';

const HeaderContainer = () => {
  const dispatch = useDispatch();
  const { loginStateInfo } = useSelector(({ login }) => login);
  const cookies = new Cookies();

  function onSignoutHandler() {
    console.log("onSignoutHandler called");

    cookies.remove('jwt');
    dispatch(resetLoginInfo());
  }

  return (
    <>
      <div>Header</div>
      {!loginStateInfo && <a href={process.env.REACT_APP_API_LOGIN}>sign in</a>}
      {loginStateInfo && <span onClick={onSignoutHandler}>sign out</span>}
    </>
  );
};

export default HeaderContainer;