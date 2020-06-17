import React from 'react';
import IssueDetailsContainer from '../containers/IssueDetailsContainer';

const IssueDetailPage = ({ match }) => {
  const { issueId } = match.params;
  const id = parseInt(issueId, 10);

  return <IssueDetailsContainer issueId={id} />;
};

export default IssueDetailPage;
