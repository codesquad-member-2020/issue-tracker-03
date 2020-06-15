const SET_LOGIN_INFO = 'login/SET_LOGIN_INFO';
const RESET_LOGIN_INFO = 'login/RESET_LOGIN_INFO';

export const setLoginInfo = userData => ({
  type: SET_LOGIN_INFO,
  payload: userData,
});

export const resetLoginInfo = () => ({
  type: RESET_LOGIN_INFO,
});

const initialLoginState = {
  loginStateInfo: null
};

export default function login(state = initialLoginState, action) {
  switch (action.type) {
    case SET_LOGIN_INFO:
      const loginStateInfo = {
        id: action.payload.id,
        avatarUrl: action.payload.avatarUrl,
      };

      return {
        ...state,
        loginStateInfo: loginStateInfo,
      };

    case RESET_LOGIN_INFO:
      return {
        initialLoginState
      };
    default:
      return state;
  }
}