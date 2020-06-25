import React from 'react';
import IssueListContainer from '../containers/IssueListContainer';

const IssueListPage = ({ location }) => {
  return (
    <>
      <IssueListContainer query={location.search}/>
    </>
  );
};

export default IssueListPage;
