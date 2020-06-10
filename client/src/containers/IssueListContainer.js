import React, { useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { getIssueList } from '../modules/issueList';
import Search from '../components/IssueList/Search';
import List from '../components/IssueList/List';
import Filters from '../components/IssueList/Filters';

const IssueListContainer = () => {
  const { data, loading, error } = useSelector(state => state.issueList.issueList);
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(getIssueList());
  }, [dispatch]);

  if (loading && !data) return <div>로딩중...</div>;
  if (error) return <div>에러 발생!</div>;
  if (!data) return null;

  return (
    <>
      <Search />
      <Filters />
      <List />
    </>
  );
};

export default IssueListContainer;
