import React, { useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import {
  getLabelList,
} from '../modules/labelList';
import styled from 'styled-components';
import List from '@Components/LabelList/List/index'
import Counter from '@Components/LabelList/Counter/index'

const IssueCreatecontainer = () => {
  const { data, loading, error } = useSelector(
    state => state.labelList.labelList,
  );
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(getLabelList());
  }, [dispatch]);

  if (loading && !data) return <div>로딩중...</div>;
  if (error) return <div>에러 발생!</div>;
  if (!data) return null;

  const labelList = data.response;

  return (
    <>
      <Counter count={labelList.length}/>
      <List labelList={labelList}></List>
    </>
  );
};

export default IssueCreatecontainer;
