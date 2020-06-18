import React from 'react';
import styled from 'styled-components';

const StyledTitleText = styled.p`
  display: block;
  width: fit-content;
  height: fit-content;
  font-size: 14px;
  font-weight: bold;
  color: #000;
`;

const TitleText = ({ title, placeHolder }) => {
  return (
    <StyledTitleText>{title}</StyledTitleText>
  );
};

export default TitleText;