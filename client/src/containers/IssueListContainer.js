import React, { useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { getIssueList, clickCheckbox, resetCheckbox, allCheckbox } from '../modules/issueList';
import Search from '../components/IssueList/Search';
import List from '../components/IssueList/List';
import Filters from '../components/IssueList/Filters';

const IssueListContainer = () => {
  const { data, loading, error } = useSelector(
    state => state.issueList.issueList,
  );

  const { checkbox } = useSelector(
    state => state.issueList,
  );

  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(getIssueList());
  }, [dispatch]);

  if (loading && !data) return <div>로딩중...</div>;
  if (error) return <div>에러 발생!</div>;
  if (!data) return null;

  const list = data.response.issueResponses;
  const labelColors = new Map();

  data.response.label.forEach(data =>
    {
      labelColors.set(data.name, { description: data.description, color: data.color })
    }
  );

  const onCheckboxClickHandler = (id, checked) =>
    dispatch(clickCheckbox({ id, checked }));

  return (
    <>
      <Search />
      <Filters />
      <List list={list} labelColors={labelColors} onCheckboxClick={onCheckboxClickHandler} checkbox={checkbox} />
    </>
  );
};

export default IssueListContainer;
