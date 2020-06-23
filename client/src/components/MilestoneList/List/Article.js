import React from "react";
import styled from "styled-components";

const ArticleWrap = styled.div`
  width: 1200px;
  display: flex;
  justify-content: space-between;
  padding: 10px 15px;
  border: 1px solid #d1d5da;
  margin-top: -1px;
`;

const LeftSide = styled.div`
  flex-grow: 1;
  flex-basis: 0;
`;

const Title = styled.p`
  width: fit-content;
  height: fit-content;
  font-size: 28px;
  font-weight: bold;
  color: #000;
  padding: 3px 5px;
  border-radius: 6px;

  * {
    color: #fff;
  }
`;

const DueDate = styled.p`
  width: fit-content;
  height: fit-content;
  font-size: 14px;
  color: gray;
  padding: 3px 5px;
  border-radius: 6px;
`;

const Description = styled.p`
  width: fit-content;
  height: fit-content;
  font-size: 14px;
  color: gray;
  padding: 3px 5px;
  border-radius: 6px;
`;

const RightSide = styled.div`
  flex-grow: 1;
  flex-basis: 0;
`;

const TotalArea = styled.div`
  height: 10px;
  margin: 5px;
  background-color: lightgray;
`;

const CompleteArea = styled.div`
  width: ${(props) => (props.closeCount/(props.closeCount + props.openCount))*100}%;
  height: 10px;
  background-color: green;
`;

const InformationWrap = styled.div`
  padding: 5px 5px;

  span + span {
    padding-left: 15px;
  }
`;

const CompleteRate = styled.span`
  font-size: 14px;
  border-radius: 6px;
`;

const OpenCount = styled.span`
  font-size: 14px;
  border-radius: 6px;
`;

const CloseCount = styled.span`
  font-size: 14px;
  border-radius: 6px;
`;

const ButtonsWrap = styled.div`
  display: flex;
  align-items: center;
  flex-grow: 0.8;
  flex-basis: 0;
  padding: 5px 5px;

  button + button {
    padding-left: 15px;
  }
`;

const Button = styled.button`
  font-size: 14px;
  border: none;
  color: ${(props) => props.color ? props.color : "#0366d6"};
  background-color: transparent;

  &:hover {
    cursor: pointer;
    text-decoration: underline;
  }

  &:focus {
    outline: none;
  }
`;

const Article = ({
  title,
  dueDate,
  description,
  openCount,
  closeCount,
  isOpen
}) => {

  return (
    <>
      <ArticleWrap>
        <LeftSide>
          <Title>Title</Title>
          <DueDate>DueDate</DueDate>
          <Description>이번 배포를 위한</Description>
        </LeftSide>
        <RightSide>
          <TotalArea>
            <CompleteArea openCount={2} closeCount={1}></CompleteArea>
          </TotalArea>
          <InformationWrap>
            <CompleteRate>{Math.floor((1 / 3) * 100)}% complete</CompleteRate>
            <OpenCount>2 open</OpenCount>
            <CloseCount>1 closed</CloseCount>
          </InformationWrap>
          <ButtonsWrap>
            <Button>Edit</Button>
            <Button>Close</Button>
            <Button color="#cb2431">Delete</Button>
          </ButtonsWrap>
        </RightSide>
      </ArticleWrap>
    </>
  );
};

export default Article;
