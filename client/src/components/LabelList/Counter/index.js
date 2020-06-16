import React from 'react';
import styled from 'styled-components'

const CounterWrap = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 15px;
  background-color: #f6f8fa;
  border: 1px solid #d1d5da;
`;

const Count = styled.div`
  font-size: 14px;
  font-weight: bold;
  padding: 10px 0px;
`;

const Counter = ({ count }) => {
  return (
    <CounterWrap>
      <Count>{count} {count > 1 ? "Labels" : "Label"}</Count>
    </CounterWrap>
  );
};

export default Counter;
