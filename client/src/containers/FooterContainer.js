import React from 'react';
import styled from 'styled-components';

const FooterWrap = styled.div`
  width: 1200px;
  padding: 50px 0;
  margin: 50px auto 0;
  font-family: 'Montserrat';
  font-size: 14px;
  color: #999;
  text-align: center;
  letter-spacing: 3px;
  line-height: 1;
  border-top: 1px solid #eee;
`;

const FooterContainer = () => {
  return (
    <FooterWrap>© 2020 CODESQUAD, TEAM#3 [PROJECT : ISSUE TRACKER]</FooterWrap>
  );
};

export default FooterContainer;
