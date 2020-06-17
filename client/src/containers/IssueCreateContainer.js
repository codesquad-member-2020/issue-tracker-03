import React, { useState } from 'react';
import styled from 'styled-components';

import IssueCreate from '@Components/IssueCreate/index'

const IssueCreateContainerWrap = styled.div`
  width: 1200px;
  display: flex;
  justify-content: space-between;
  padding: 10px 15px;
`;

const ContentsWrap = styled.div`
  flex-grow: 7;
  border: 1px solid lightgray;
  padding: 10px 15px;
`;

const SideBarWrap = styled.div`
  flex-grow: 3;
  padding: 10px 15px;
`;

const IssueCreatecontainer = () => {
  let title = '';
  let contents = '';

  const setTitle = (text) => {
    title = text;
  }

  const setContents = (text) => {
    contents = text;
  }

  const onTitleChangeHandler = (title) => {
    setTitle(title);
  }

  const onContentsChangeHandler = (contents) => {
    setContents(contents);
  }

  const onCancelButtonClickHandler = () => {
    console.log(title);
  }

  const onSubmitButtonClickHandler = () => {
    console.log(contents);
  }

  return (
    <IssueCreateContainerWrap>
      <ContentsWrap>
        <IssueCreate
          onTitleChange={onTitleChangeHandler}
          onContentsChange={onContentsChangeHandler}
          onCancelButtonClick={onCancelButtonClickHandler}
          onSubmitButtonClick={onSubmitButtonClickHandler}
        />
      </ContentsWrap>
      <SideBarWrap></SideBarWrap>
    </IssueCreateContainerWrap>
  );
};

export default IssueCreatecontainer;
