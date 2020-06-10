import React, { useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { getIssues } from '../modules/issues';
import Search from '../components/IssueList/Search';
import List from '../components/IssueList/List';
import Filters from '../components/IssueList/Filters';

const IssueListContainer = () => {
  const { loading, data, error } = useSelector(state => state.issues.issues);
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(getIssues());
  }, [dispatch]);

  if (loading) return <div>로딩중..</div>;
  if (error) return <div>에러!!</div>;
  if (!data) return null;

  const list = data.reverse();
  return (
    <>
      <Search />
      <Filters />
      <List list={list} />
    </>
  );
};

export default IssueListContainer;
