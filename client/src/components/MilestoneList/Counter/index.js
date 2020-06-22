import React from 'react';
import styled from 'styled-components'

const CounterWrap = styled.div`
  display: flex;
  align-items: center;
  padding: 0px 15px;
  background-color: #f6f8fa;
  border: 1px solid #d1d5da;
  
  div + div {
    padding-left: 10px;
  }
`;

const Count = styled.div`
  font-size: 14px;
  font-weight: bold;
  padding: 10px 0px;
`;

const Counter = ({ openCount, closeCount }) => {
  return (
    <CounterWrap>
      <Count>{openCount} Open</Count>
      <Count>{closeCount} Close</Count>
    </CounterWrap>
  );
};

export default Counter;
