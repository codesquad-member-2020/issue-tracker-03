import React from 'react';
import styled from 'styled-components'

const ArticleWrap = styled.div`
  width: 1200px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 10px 15px;
  border: 1px solid #d1d5da;

  * {
    color: #000;
  }
`;

const NameWrap = styled.div`
  flex-grow: 3;
  flex-basis: 0;
`;

const Name = styled.div`
  width: fit-content;
  height: fit-content;
  font-size: 14px;
  font-weight: bold;
  color: #fff;
  padding: 2px 5px;
  border-radius: 6px;
  background-color: ${props => props.color};
`;

const DescriptionWrap = styled.div`
  flex-grow: 7;
  flex-basis: 0;
`;

const Description = styled.span`
  width: fit-content;
  height: fit-content;
  font-size: 14px;
`;

const ButtonsWrap = styled.div`
  flex-grow: 0.8;
  flex-basis: 0;
  display: flex;
  align-items: center;
  justify-content: space-between;
`;

const Button = styled.button`
  font-size: 14px;
  border: none;
  background-color: transparent;

  &:hover {
    cursor: pointer;
    color: blue;
    text-decoration: underline;
  }

  &:focus {
    outline: none;
  }
`;


const Article = ({ id, name, description, color }) => {
  return (
    <ArticleWrap>
      <NameWrap>
        <Name color={color}>{name}</Name>
      </NameWrap>
      <DescriptionWrap><Description>{description}</Description></DescriptionWrap>
      <ButtonsWrap><Button>Edit</Button><Button>Delete</Button></ButtonsWrap>
    </ArticleWrap>
  );
};

export default Article;
