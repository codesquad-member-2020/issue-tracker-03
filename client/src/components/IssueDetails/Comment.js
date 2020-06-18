import React, { useState } from 'react';
import MarkdownIt from 'markdown-it';
import MdEditor from 'react-markdown-editor-lite';
import 'react-markdown-editor-lite/lib/index.css';
import styled from 'styled-components';

const mdParser = new MarkdownIt();

const Avatar = styled.div``;
const CommentWrap = styled.div`
  position: relative;
  flex-grow: 1;
  margin-left: 20px;
  border: 1px solid #eee;
  border-radius: 5px;
`;
const CommentHead = styled.div`
  position: relative;
  padding: 10px;
  font-size: 13px;
  color: #666;
  border-bottom: 1px solid #eee;
  background: #f1f8ff;
  strong {
    margin-right: 5px;
    font-size: 14px;
    color: #333;
  }
  &::before {
    content: '';
    position: absolute;
    top: 12px;
    left: -6px;
    width: 8px;
    height: 8px;
    border: 1px solid #eee;
    border-top: 0;
    border-right: 0;
    background: #f6f8fa;
    transform: rotate(45deg);
  }
`;
const CommentBody = styled.div`
  .rc-md-editor {
    border: 0;
  }
  .tool-bar {
    display: none;
  }
`;
const Wrap = styled.article`
  display: flex;
  margin-bottom: 25px;
  & + & {
    ${CommentHead} {
      background: #f6f8fa;
    }
  }
`;
const Comment = ({ contents, author, createdAt }) => {
  const [config, setConfig] = useState({
    view: {
      menu: false,
      md: false,
      html: true,
    },
  });
  return (
    <Wrap>
      <Avatar>
        <img src={author.avatarURL} alt={author.userId} />
      </Avatar>
      <CommentWrap>
        <CommentHead>
          <strong>{author.userId}</strong>
          commented {createdAt}
        </CommentHead>
        <CommentBody>
          <MdEditor
            value={contents}
            renderHTML={text => mdParser.render(text)}
            config={config}
          />
        </CommentBody>
      </CommentWrap>
    </Wrap>
  );
};

export default Comment;
