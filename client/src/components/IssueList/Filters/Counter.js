import React from 'react';
import styled from 'styled-components'

const CounterWrap = styled.div`
  display: flex;
  align-items: center;
  padding: 0px 15px;
  background-color: #f6f8fa;
  border: 1px solid #d1d5da;
  
  button + button {
    margin-left: 10px;
  }
`;

const Button = styled.button`
  font-size: 14px;
  font-weight: ${(props) => props.selected ? "bold" : "light"};
  padding: 10px 0px;
  border: none;
  background-color: transparent;
  pointer-events: ${(props) => props.selected ? "none" : "true"};

  &:hover {
    cursor: pointer;
  }

  &:focus {
    outline: none;
  }
`;

const Counter = ({ isOpen, openButtonClick, closedButtonClick }) => {
  return (
    <CounterWrap>
      <Button selected={isOpen} onClick={openButtonClick}>Open</Button>
      <Button selected={!isOpen} onClick={closedButtonClick}>Close</Button>
    </CounterWrap>
  );
};

export default Counter;
