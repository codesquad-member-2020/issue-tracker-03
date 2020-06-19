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
  const { contents, createdAt, author } = issue;
  console.log(issue);

  return (
    <Wrap>
      <Comment contents={contents} author={author} createdAt={createdAt} />
      <Comment contents={contents} author={author} createdAt={createdAt} />
      <CommentForm author={author} />
    </Wrap>
  );
};

export default IssueDetails;
