import React from 'react';
import styled from 'styled-components';

const StyledCancelButton = styled.button`
  width: fit-content;
  height: 34px;
  border: none;
  padding: 4px 10px;
  font-size: 14px;
  font-weight: bold;
  background-color: transparent;
  color: gray;

  &:hover {
    color: #555;
    cursor: pointer;
  }

  &:focus {
    outline: none;
  }

  &:active {
    color: #000;
  }
`;

const CancelButton = ({ onButtonClick }) => {
  const onCancelButtonClickHandler = (e) => {
    onButtonClick();
  }

  return (
      <StyledCancelButton onClick={onCancelButtonClickHandler}>
        Cancel
      </StyledCancelButton>
  );
};

export default CancelButton;
