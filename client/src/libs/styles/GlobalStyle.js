import { createGlobalStyle } from 'styled-components';
import GothicA1Bold from '$Libs/fonts/GothicA1-Bold.ttf';
import GothicA1Medium from '$Libs/fonts/GothicA1-Medium.ttf';
import GothicA1Regular from '$Libs/fonts/GothicA1-Regular.ttf';
import GothicA1Light from '$Libs/fonts/GothicA1-Light.ttf';
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
    font-family: 'GothicA1';
    font-style: normal;
    font-weight: 700;
    src: local('GothicA1 Bold'), local('GothicA1-Bold'),
      url(${GothicA1Bold});
  }
  @font-face {
    font-family: 'GothicA1';
    font-style: normal;
    font-weight: 500;
    src: local('GothicA1 Medium'), local('GothicA1-Medium'),
      url(${GothicA1Medium});
  }
  @font-face {
    font-family: 'GothicA1';
    font-style: normal;
    font-weight: 400;
    src: local('GothicA1 Regular'), local('GothicA1-Regular'),
      url(${GothicA1Regular});
  }
  @font-face {
    font-family: 'GothicA1';
    font-style: normal;
    font-weight: 300;
    src: local('GothicA1 Light'), local('GothicA1-Light'),
      url(${GothicA1Light});
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
    padding: 0;
    margin: 0;
    font-family:'GothicA1', 'Montserrat', 'Roboto', sans-serif;
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
