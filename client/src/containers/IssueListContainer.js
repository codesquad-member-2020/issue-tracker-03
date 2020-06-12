import React, { useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { getIssueList, clickCheckbox } from '../modules/issueList';
import Search from '../components/IssueList/Search';
import List from '../components/IssueList/List';
import Filters from '../components/IssueList/Filters';

const IssueListContainer = () => {
  const { data, loading, error } = useSelector(
    state => state.issueList.issueList,
  );

  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(getIssueList());
  }, []);

  if (loading && !data) return <div>로딩중...</div>;
  if (error) return <div>에러 발생!</div>;
  if (!data) return null;

  const list = data.issueResponses.reverse();
  const label = new Map();

  data.label.forEach(data =>
    label.set(data.name, { description: data.description, color: data.color }),
  );

  const onCheckboxClickHandler = (id, checked) =>
    dispatch(clickCheckbox({ id, checked }));

  return (
    <>
      <Search />
      <Filters />
      <List list={list} label={label} onCheckbox={onCheckboxClickHandler} />
    </>
  );
};

export default IssueListContainer;
