import React from 'react';
import styled, { css } from 'styled-components';

const StyledNavigationButton = styled.button`
  padding: 6px 14px;
  border: 1px solid #e1e4e8;
  font-size: 14px;
  font-weight: bold;
  background-color: #fff;
  user-select: none;

  &:focus {
    outline: none;
  }

  ${(props) =>
    props.selected &&
    css`
      color: #fff;
      background-color: #0366d6;
      pointer-events: none;
    `};

  ${(props) =>
    !props.selected &&
    css`
      color: #586069;
      background-color: #fff;

      &:hover {
        cursor: pointer;
        background-color: #f6f8fa;
      }
    `};
`;

const StyledSpan = styled.span`
  margin: 5px 5px;
  padding: 4px 5px;
  font-size: 12px;
  line-height: 1;
  font-weight: bold;
  background-color: rgba(27,31,35,.15);
  border-radius: 20px;
  user-select: none;

  ${(props) =>
    !props.count &&
    css`
      display: none;
    `};
`;

const NavigationButton = ({ onButtonClick, buttonText, selected, count }) => {
  const onNavigationButtonClickHandler = (e) => {
    if (onButtonClick) onButtonClick();
  }

  return (
    <StyledNavigationButton
      onClick={onNavigationButtonClickHandler}
      selected={selected}
    >
      {buttonText}
      <StyledSpan>{count}</StyledSpan>
    </StyledNavigationButton>
  );
};

export default NavigationButton;
