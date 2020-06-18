import React from 'react';
import { useHistory } from "react-router-dom";
import styled from 'styled-components';
import { useSelector } from 'react-redux';

import IssueCreate from '@Components/IssueCreate/index'
import * as issueCreateAPI from '$API/issueCreate';

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
  const history = useHistory();
  const { loginStateInfo } = useSelector(({ login }) => login);

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
    history.push("/");
  }

  const onSubmitButtonClickHandler = () => {
    (async () => {
      const body = {
        title: title,
        contents: contents,
      };

      let response = await issueCreateAPI.createIssue(body);
      const issueId = response.response.issueId;
      
      history.push("/issue-detail/" + issueId);
    })();
  }

  return (
    <IssueCreateContainerWrap>
      <ContentsWrap>
        <IssueCreate
          onTitleChange={onTitleChangeHandler}
          onContentsChange={onContentsChangeHandler}
          onCancelButtonClick={onCancelButtonClickHandler}
          onSubmitButtonClick={onSubmitButtonClickHandler}
          submitButtonEnabled={loginStateInfo}
        />
      </ContentsWrap>
      <SideBarWrap></SideBarWrap>
    </IssueCreateContainerWrap>
  );
};

export default IssueCreatecontainer;
