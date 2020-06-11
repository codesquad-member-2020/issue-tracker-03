import React, { useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { getIssueList } from '../modules/issueList';
import { toggleCheckbox, setCheckbox } from '../modules/checkbox';
import Search from '../components/IssueList/Search';
import List from '../components/IssueList/List';
import Filters from '../components/IssueList/Filters';

const IssueListContainer = () => {
  const { data, loading, error } = useSelector(
    state => state.issueList.issueList,
  );
  const { issueList } = useSelector(({ checkbox }) => checkbox);

  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(getIssueList());
  }, []);

  if (loading && !data) return <div>로딩중...</div>;
  if (error) return <div>에러 발생!</div>;
  if (!data) return null;

  //dispatch(setCheckbox(data.issueResponses));
  console.log(issueList);

  const list = data.issueResponses.reverse();
  const label = new Map();

  data.label.forEach((data, index) =>
    label.set(data.name, { description: data.description, color: data.color }),
  );

  function onCheckboxClickHandler(id) {
    dispatch(toggleCheckbox(id));
  }

  return (
    <>
      <Search />
      <Filters />
      <List
        list={list}
        label={label}
        onCheckbox={() => onCheckboxClickHandler}
      />
    </>
  );
};

export default IssueListContainer;
