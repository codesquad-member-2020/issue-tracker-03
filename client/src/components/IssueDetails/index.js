import React, { useState } from 'react';
import styled from 'styled-components';
import MarkdownIt from 'markdown-it';
import MdEditor from 'react-markdown-editor-lite';
import 'react-markdown-editor-lite/lib/index.css';

const Wrap = styled.div``;
const Header = styled.div`
  padding-bottom: 30px;
  margin-bottom: 30px;
  border-bottom: 1px solid #eee;
`;
const HeaderTitle = styled.div`
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
const HeaderTitleEdit = styled.div`
  form {
    display: flex;
    justify-content: space-between;
  }
  input {
    width: calc(100% - 100px);
  }
`;
const HeaderDescription = styled.div`
  margin-top: 5px;
  font-size: 13px;
  color: #666;
`;
const Contents = styled.div`
  .tool-bar {
    display: none;
  }
`;
const CommentForm = styled.div``;
const Title = ({ title, id, onClickEdit }) => {
  return (
    <HeaderTitle>
      <h1>
        {title} <span>#{id}</span>
      </h1>
      <button onClick={onClickEdit}>Edit</button>
    </HeaderTitle>
  );
};
const TitleEdit = ({ title = '', onClickCancel }) => {
  const [inputTitle, setInputTitle] = useState(title);
  return (
    <HeaderTitleEdit>
      <form>
        <input type="text" defaultValue={inputTitle} />
        <div>
          <button>Save</button>
          <button onClick={onClickCancel}>Cancel</button>
        </div>
      </form>
    </HeaderTitleEdit>
  );
};

const mdParser = new MarkdownIt();

const IssueDetails = ({ issue }) => {
  const { id, title, contents, createdAt, author, open } = issue;
  const [titleEdit, setTitleEdit] = useState(false);
  const onClickEdit = () => setTitleEdit(true);
  const onClickCancel = () => setTitleEdit(false);
  console.log(issue);

  return (
    <Wrap>
      <Header>
        {titleEdit ? (
          <TitleEdit title={title} onClickCancel={onClickCancel} />
        ) : (
          <Title id={id} title={title} onClickEdit={onClickEdit} />
        )}
        <HeaderDescription>
          {open ? 'Open' : 'Close'} / {author.userId} / {createdAt} / 코멘트
        </HeaderDescription>
      </Header>
      <Contents>
        <div>
          <MdEditor
            value={contents}
            style={{ height: '500px' }}
            renderHTML={text => mdParser.render(text)}
            config={{ view: { menu: false, md: false, html: true } }}
          />
        </div>
      </Contents>
      <CommentForm>
        <textarea name="" id="" cols="30" rows="10"></textarea>
        <button>Close issue</button>
        <button>Comment</button>
      </CommentForm>
    </Wrap>
  );
};

export default IssueDetails;
