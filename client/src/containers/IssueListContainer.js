import React from 'react';
import Search from '../components/IssueList/Search';
import List from '../components/IssueList/List';
import Filters from '../components/IssueList/Filters';

const IssueListContainer = () => {
  return (
    <>
      <Search />
      <Filters />
      <List />
    </>
  );
};

export default IssueListContainer;
