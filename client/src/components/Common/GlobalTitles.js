import React from 'react';
import styled from 'styled-components';

const Wrap = styled.div`
  padding: 20px 0;
  margin-bottom: 50px;
  border-bottom: 1px solid #e0e0e0;
  background: #fafafa;
`;
const Inner = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 1200px;
  margin: 0 auto;
`;
const Title = styled.h1`
  font-family: 'Montserrat';
  font-weight: 500;
  font-size: 15px;
  color: #666;
  letter-spacing: 3px;
  span {
    display: block;
    margin-top: 5px;
    font-weight: 700;
    font-size: 18px;
  }
`;
const Description = styled.pre`
  padding: 10px 15px;
  font-family: 'Montserrat';
  font-weight: 300;
  font-size: 12px;
  color: #666;
  letter-spacing: 1px;
  border: 1px solid #eee;
  border-radius: 5px;
  background: #fcfcfc;
`;

const GlobalTitles = () => {
  return (
    <Wrap>
      <Inner>
        <Title>
          CODESQUAD MEMBER 2020, <span>TEAM#3 [PROJECT : ISSUE TRACKER]</span>
        </Title>
        <Description>{`<Developer>
        <Back-End>@Han, @Sunny</Back-End>
        <Front-End>@Zello, @baekCo</Front-End>
</Developer>`}</Description>
      </Inner>
    </Wrap>
  );
};

export default GlobalTitles;
