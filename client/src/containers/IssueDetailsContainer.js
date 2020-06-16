import React, { useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { getIssue } from '../modules/issueList';
import { reducerUtils } from '../libs/asyncUtils';
import IssueDetails from '../components/IssueDetails';

function IssueDetailsContainer({ issueId }) {
  const { data, loading, error } = useSelector(
    state => state.issueList.issue[issueId] || reducerUtils.initial(),
  );

  const dispatch = useDispatch();

  useEffect(() => {
    if (data) return;
    dispatch(getIssue(issueId));
  }, [issueId, dispatch]);

  if (loading && !data) return <div>로딩중...</div>;
  if (error) return <div>에러 발생!</div>;
  if (!data) return null;

  return <IssueDetails issue={data} />;
}

export default IssueDetailsContainer;
