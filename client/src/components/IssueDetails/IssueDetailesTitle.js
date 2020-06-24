import React, { useState } from 'react';
import styled from 'styled-components';
import SubmitButton from '@Components/Common/Button/SubmitButton';
import CancelButton from '@Components/Common/Button/CancelButton';

const Wrap = styled.div`
  padding-bottom: 30px;
  margin-bottom: 30px;
  border-bottom: 1px solid #eee;
`;

const TitleWrap = styled.div`
  display: flex;
  justify-content: space-between;
  h1 {
    font-weight: 500;
    font-size: 32px;
    letter-spacing: -1px;
    span {
      color: #999;
    }
  }
`;

const TitleEditWrap = styled.div`
  form {
    display: flex;
    justify-content: space-between;
  }
  input {
    width: calc(100% - 150px);
    height: 30px;
    padding: 0 10px;
    margin-bottom: 9px;
  }
`;
const HeaderDescription = styled.div`
  margin-top: 5px;
  font-size: 13px;
  color: #666;
`;

const Title = ({ title, id, onClickEdit }) => {
  return (
    <TitleWrap>
      <h1>
        {title} <span>#{id}</span>
      </h1>
      <SubmitButton onButtonClick={onClickEdit} buttonText="Edit" buttonEnabled />
    </TitleWrap>
  );
};

const TitleEdit = ({ title = '', onClickSave, onClickClose }) => {
  const [inputTitle, setInputTitle] = useState(title);
  const onChange = e => setInputTitle(e.target.value);
  const onClickTest = e => {
    e.preventDefault();
    onClickSave(inputTitle);
    onClickClose();
  };
  return (
    <TitleEditWrap>
      <form onSubmit={onClickTest}>
        <input type="text" defaultValue={inputTitle} autoFocus onChange={onChange} />
        <div>
          <SubmitButton buttonText="Save" buttonEnabled />
          <CancelButton onButtonClick={onClickClose} buttonText="Cancel" buttonEnabled />
        </div>
      </form>
    </TitleEditWrap>
  );
};

const IssueDetailesTitle = ({ issue, title, onClickEdit, onClickSave, onClickClose, isEdit }) => {
  const { id, open, author, createdAt } = issue;
  return (
    <Wrap>
      {isEdit ? (
        <TitleEdit title={title} onClickClose={onClickClose} onClickSave={onClickSave} />
      ) : (
        <Title id={id} title={title} onClickEdit={onClickEdit} />
      )}
      <HeaderDescription>
        {open ? 'Open' : 'Close'} {author.userId} {createdAt} 코멘트
      </HeaderDescription>
    </Wrap>
  );
};

export default IssueDetailesTitle;
