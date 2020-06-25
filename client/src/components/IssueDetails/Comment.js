import React, { useState } from 'react';
import MarkdownIt from 'markdown-it';
import MdEditor from 'react-markdown-editor-lite';
import 'react-markdown-editor-lite/lib/index.css';
import styled from 'styled-components';
import defaultProfileImage from '../../libs/images/default_profile.jpg';

const mdParser = new MarkdownIt();

const Avatar = styled.div`
  overflow: hidden;
  width: 40px;
  height: 40px;
  border-radius: 5px;
  img {
    max-width: 100%;
  }
`;
const CommentWrap = styled.div`
  position: relative;
  flex-grow: 1;
  margin-left: 20px;
  border: 1px solid #c0d3eb;
  border-radius: 3px;
`;
const CommentHead = styled.div`
  position: relative;
  padding: 10px;
  font-size: 13px;
  color: #666;
  border-bottom: 1px solid #c0d3eb;
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
    left: -5px;
    width: 8px;
    height: 8px;
    border: 1px solid #c0d3eb;
    border-top: 0;
    border-right: 0;
    background: #f6f8fa;
    transform: rotate(45deg);
  }
`;
const CommentBody = styled.div`
  min-height: 150px;
  background: #fff;
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
    ${CommentWrap} {
      border: 1px solid #eee;
    }
    ${CommentHead} {
      border-bottom: 1px solid #eee;
      background: #f6f8fa;
      &::before {
        border: 1px solid #eee;
        border-top: 0;
        border-right: 0;
      }
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
        {!author.avatarURL ? (
          <img src={defaultProfileImage} alt={author.userId} />
        ) : (
          <img src={author.avatarURL} alt={author.userId} />
        )}
      </Avatar>
      <CommentWrap>
        <CommentHead>
          <strong>{author.userId}</strong>
          commented {createdAt}
        </CommentHead>
        <CommentBody>
          <MdEditor value={contents} renderHTML={text => mdParser.render(text)} config={config} />
        </CommentBody>
      </CommentWrap>
    </Wrap>
  );
};

export default Comment;
