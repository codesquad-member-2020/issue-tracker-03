import React from 'react';
import styled from 'styled-components';

const StyledSubmitButton = styled.button`
  width: fit-content;
  height: 34px;
  padding: 4px 10px;
  border-radius: 6px;
  border: 1px solid rgba(27, 31, 35, 0.2);
  font-size: 14px;
  font-weight: bold;
  color: #fff;
  background-color: #28a745;
  background-image: linear-gradient(-180deg,#34d058,#28a745 90%);

  &:hover {
    cursor: pointer;
    border: 1px solid rgba(27, 31, 35, 1);
    background-color: #269f42;
    background-image: linear-gradient(-180deg,#2fcb53,#269f42 90%);
    border: 1px solid rgba(27,31,35,.5);
  }

  &:focus {
    outline: none;
  }

  &:active {
    background-color: #279f43;
    background-image: none;
  }
`;

const SubmitButton = ({ onButtonClick }) => {
  const onSubmitButtonClickHandler = (e) => {
    onButtonClick();
  }

  return (
    <StyledSubmitButton onClick={onSubmitButtonClickHandler}>Submit new issue</StyledSubmitButton>
  );
};

export default SubmitButton;
