import React from 'react';
import styled from 'styled-components';
import CommentForm from './CommentForm';
import Comment from './Comment';

const Wrap = styled.div``;

const IssueDetails = ({ issue }) => {
  const { id, title, contents, createdAt, author, open } = issue;
  console.log(issue);

  return (
    <Wrap>
      <Comment contents={contents} />
      <CommentForm />
    </Wrap>
  );
};

export default IssueDetails;
