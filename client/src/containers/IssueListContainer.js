import React, { useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import {
  getIssueList,
  clickCheckbox,
  resetCheckbox,
  allCheckbox,
} from '../modules/issueList';
import Search from '@Components/IssueList/Search';
import List from '@Components/IssueList/List';
import Filters from '@Components/IssueList/Filters';

const IssueListContainer = () => {
  const { data, loading, error } = useSelector(
    state => state.issueList.issueList,
  );

  const { checkbox } = useSelector(state => state.issueList);

  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(getIssueList());
  }, [dispatch]);

  if (loading && !data) return <div>로딩중...</div>;
  if (error) return <div>에러 발생!</div>;
  if (!data) return null;

  const list = data.response;

  const onCheckboxClickHandler = (id, checked) =>
    dispatch(clickCheckbox({ id, checked }));

  return (
    <>
      <Search />
      <Filters />
      <List
        list={list}
        onCheckboxClick={onCheckboxClickHandler}
        checkbox={checkbox}
      />
    </>
  );
};

export default IssueListContainer;
