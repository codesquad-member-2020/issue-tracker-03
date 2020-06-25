import React, { useEffect } from 'react';
import { useHistory } from "react-router-dom";
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
import ButtonPanel from '@Components/Common/ButtonPanel'

const IssueListContainer = ({ query }) => {
  const history = useHistory();
  const { data, loading, error } = useSelector(
    state => state.issueList.issueList,
  );

  const { checkbox } = useSelector(state => state.issueList);
  const { loginStateInfo } = useSelector(({ login }) => login);

  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(getIssueList(query));
  }, [dispatch, query]);

  if (loading && !data) return <div>로딩중...</div>;
  if (error) return <div>에러 발생!</div>;
  if (!data) return null;

  const list = data.response;

  const onCheckboxClickHandler = (id, checked) =>
    dispatch(clickCheckbox({ id, checked }));

  const onNewIssueButtonClickHandler = () => {
    history.push('/issue-create')
  }

  const onMilestoneButtonClickHandler = () => {
    history.push('/milestone-list')
  }

  const onLabelButtonClickHandler = () => {
    history.push('/label-list')
  }

  const onOpenButtonClickHandler = () => {
    history.push('?q=is:open')
  }

  const onClosedButtonClickHandler = () => {
    console.log("Wefwefewf");
    history.push('?q=is:closed')
  }

  return (
    <>
      <ButtonPanel
        onSubmitButtonClick={onNewIssueButtonClickHandler}
        submitButtonText="New issue"
        submitButtonEnabled={loginStateInfo}
        onMilestoneButtonClick={onMilestoneButtonClickHandler}
        onLabelButtonClick={onLabelButtonClickHandler}
      />
      <Filters
        isOpen={!(query === '?q=is:closed')}
        openButtonClick={onOpenButtonClickHandler}
        closedButtonClick={onClosedButtonClickHandler}
      />
      <List
        list={list}
        onCheckboxClick={onCheckboxClickHandler}
        checkbox={checkbox}
      />
    </>
  );
};

export default IssueListContainer;
