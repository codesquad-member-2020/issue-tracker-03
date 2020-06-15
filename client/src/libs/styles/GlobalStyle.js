import { createGlobalStyle } from 'styled-components';
import NotoSansKRBold from '$Libs/fonts/NotoSansKR-Bold.woff2';
import NotoSansKRMedium from '$Libs/fonts/NotoSansKR-Medium.woff2';
import NotoSansKRRegular from '$Libs/fonts/NotoSansKR-Regular.woff2';
import NotoSansKRLight from '$Libs/fonts/NotoSansKR-Light.woff2';
import MontserratBold from '$Libs/fonts/Montserrat-Bold.ttf';
import MontserratMedium from '$Libs/fonts/Montserrat-Medium.ttf';
import MontserratRegular from '$Libs/fonts/Montserrat-Regular.ttf';
import MontserratLight from '$Libs/fonts/Montserrat-Light.ttf';
import RobotoBold from '$Libs/fonts/Roboto-Bold.ttf';
import RobotoMedium from '$Libs/fonts/Roboto-Medium.ttf';
import RobotoRegular from '$Libs/fonts/Roboto-Regular.ttf';
import RobotoLight from '$Libs/fonts/Roboto-Light.ttf';

export const GlobalStyle = createGlobalStyle`
  @font-face {
    font-family: 'NotoSansKR';
    font-style: normal;
    font-weight: 700;
    src: local('NotoSansKR Bold'), local('NotoSansKR-Bold'),
      url(${NotoSansKRBold});
  }
  @font-face {
    font-family: 'NotoSansKR';
    font-style: normal;
    font-weight: 500;
    src: local('NotoSansKR Medium'), local('NotoSansKR-Medium'),
      url(${NotoSansKRMedium});
  }
  @font-face {
    font-family: 'NotoSansKR';
    font-style: normal;
    font-weight: 400;
    src: local('NotoSansKR Regular'), local('NotoSansKR-Regular'),
      url(${NotoSansKRRegular});
  }
  @font-face {
    font-family: 'NotoSansKR';
    font-style: normal;
    font-weight: 300;
    src: local('NotoSansKR Light'), local('NotoSansKR-Light'),
      url(${NotoSansKRLight});
  }
  @font-face {
    font-family: 'Montserrat';
    font-style: normal;
    font-weight: 700;
    src: local('Montserrat Bold'), local('Montserrat-Bold'),
      url(${MontserratBold});
  }
  @font-face {
    font-family: 'Montserrat';
    font-style: normal;
    font-weight: 500;
    src: local('Montserrat Medium'), local('Montserrat-Medium'),
      url(${MontserratMedium});
  }
  @font-face {
    font-family: 'Montserrat';
    font-style: normal;
    font-weight: 400;
    src: local('Montserrat Regular'), local('Montserrat-Regular'),
      url(${MontserratRegular});
  }
  @font-face {
    font-family: 'Montserrat';
    font-style: normal;
    font-weight: 300;
    src: local('Montserrat Light'), local('Montserrat-Light'),
      url(${MontserratLight});
  }
  @font-face {
    font-family: 'Roboto';
    font-style: normal;
    font-weight: 700;
    src: local('Roboto Bold'), local('Roboto-Bold'),
      url(${RobotoBold});
  }
  @font-face {
    font-family: 'Roboto';
    font-style: normal;
    font-weight: 500;
    src: local('Roboto Medium'), local('Roboto-Medium'),
      url(${RobotoMedium});
  }
  @font-face {
    font-family: 'Roboto';
    font-style: normal;
    font-weight: 400;
    src: local('Roboto Regular'), local('Roboto-Regular'),
      url(${RobotoRegular});
  }
  @font-face {
    font-family: 'Roboto';
    font-style: normal;
    font-weight: 300;
    src: local('Roboto Light'), local('Roboto-Light'),
      url(${RobotoLight});
  }
  * {
    font-family:'NotoSansKR', 'Montserrat', 'Roboto', sans-serif;
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
