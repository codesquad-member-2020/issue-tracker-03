import React from 'react';
import styled from 'styled-components';
import CommentForm from './CommentForm';
import Comment from './Comment';

const Wrap = styled.div`
  position: relative;
  &::before {
    content: '';
    position: absolute;
    top: 0;
    left: 80px;
    width: 2px;
    height: 100%;
    background: #eee;
    z-index: -1;
  }
`;

const IssueDetails = ({ issue }) => {
  const { author, comments } = issue;

  return (
    <Wrap>
      {comments.map(item => (
        <Comment key={item.id} contents={item.contents} author={item.author} createdAt={item.createdAt} />
      ))}
      <CommentForm author={author} />
    </Wrap>
  );
};

export default IssueDetails;
