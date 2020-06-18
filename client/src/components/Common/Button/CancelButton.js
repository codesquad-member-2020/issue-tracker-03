import React from 'react';
import styled from 'styled-components';

const StyledCancelButton = styled.button`
  padding: 6px 14px;
  border-radius: 6px;
  border: 1px solid rgba(27,31,35,.2);
  font-size: 14px;
  font-weight: bold;
  color: #24292e;
  background-color: #eff3f6;
  background-image: linear-gradient(-180deg,#fafbfc,#eff3f6 90%);
  user-select: none;

  &:hover {
    cursor: pointer;
    border: 1px solid rgba(27,31,35,.2);
    background-color: #e6ebf1;
    background-image: linear-gradient(-180deg,#f0f3f6,#e6ebf1 90%);
  }

  &:focus {
    outline: none;
  }

  &:active {
    background-color: #e9ecef;
    background-image: none;
    box-shadow: inset 0 0.15em 0.3em rgba(27,31,35,.15);
  }
`;

const CancelButton = ({ onButtonClick }) => {
  const onCancelButtonClickHandler = (e) => {
    if (onButtonClick) onButtonClick();
  }

  return (
    <StyledCancelButton onClick={onCancelButtonClickHandler}>Cancel</StyledCancelButton>
  );
};

export default CancelButton;
