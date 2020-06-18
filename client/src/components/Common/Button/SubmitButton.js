import React from 'react';
import styled, { css } from 'styled-components';

const StyledSubmitButton = styled.button`
  padding: 6px 14px;
  border-radius: 6px;
  border: 1px solid rgba(27, 31, 35, 0.2);
  font-size: 14px;
  font-weight: bold;
  color: #fff;

  ${(props) =>
    props.enabled &&
    css`
      background-color: #28a745;
      background-image: linear-gradient(-180deg, #34d058, #28a745 90%);

      &:hover {
        cursor: pointer;
        border: 1px solid rgba(27, 31, 35, 1);
        background-color: #269f42;
        background-image: linear-gradient(-180deg, #2fcb53, #269f42 90%);
        border: 1px solid rgba(27, 31, 35, 0.5);
      }

      &:focus {
        outline: none;
      }

      &:active {
        background-color: #279f43;
        background-image: none;
      }
    `};

    ${(props) =>
    !props.enabled &&
    css`
      background-color: #28a745;
      background-image: linear-gradient(-180deg, #34d058, #28a745 90%);
      opacity: 0.5;

      &:focus {
        outline: none;
      }
    `};
`;

const SubmitButton = ({ onButtonClick, buttonText, buttonEnabled }) => {
  const onSubmitButtonClickHandler = (e) => {
    if (onButtonClick) onButtonClick();
  }

  return (
    <StyledSubmitButton onClick={onSubmitButtonClickHandler} enabled={buttonEnabled}>{buttonText}</StyledSubmitButton>
  );
};

export default SubmitButton;
