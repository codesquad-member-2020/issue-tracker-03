import { createGlobalStyle } from 'styled-components';

export const GlobalStyle = createGlobalStyle`
  * {
    padding: 0;
    margin: 0;
    color: #333;
    box-sizing: border-box;
  }
  a {
    text-decoration: none
  }
  path {
    fill: currentColor;
  }
`;
