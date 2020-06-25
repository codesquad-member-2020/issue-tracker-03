import React, { useEffect, useState } from 'react';
import { useHistory } from "react-router-dom";
import { useSelector, useDispatch } from 'react-redux';
import {
  getMilestoneList
} from '../modules/milestoneList';

import styled from 'styled-components';
import ButtonPanel from '@Components/Common/ButtonPanel'
import Counter from '@Components/MilestoneList/Counter/index'
import List from '@Components/MilestoneList/List/index'
import * as milestoneAPI from "$API/milestoneList";

const ButtonPanelWrap = styled.div`
  padding: 20px 0;
`;

const MilestoneListContainer = () => {
  const history = useHistory();
  const dispatch = useDispatch();
  const { data, loading, error } = useSelector(
    state => state.milestoneList.milestoneList,
  );
  const { loginStateInfo } = useSelector(({ login }) => login);

  useEffect(() => {
    dispatch(getMilestoneList());
  }, [dispatch]);

  if (loading && !data) return <div>로딩중...</div>;
  if (error) return <div>에러 발생!</div>;
  if (!data) return null;

  const milestoneList = data.response.milestones;
  let openCount = 0;
  let closedCount = 0;
  milestoneList.filter(milestone => milestone.open ? ++openCount : ++closedCount);

  const onNewMilestoneButtonClickHandler = () => {
    history.push('/milestone-create');
  }

  const onLabelButtonClickHandler = () => {
    history.push('/label-list');
  }

  const onDeleteButtonClickHandler = (id) => {
    (async () => {
      let response = await milestoneAPI.deleteMilestone(id);
      console.log("Delete:", response);

      if (response.success) dispatch(getMilestoneList());
      else alert("삭제 요청이 처리되지 못했습니다.")
    })();
  }

  return (
    <>
      <ButtonPanelWrap>
        <ButtonPanel
          onSubmitButtonClick={onNewMilestoneButtonClickHandler}
          submitButtonText="New milestone"
          submitButtonEnabled={loginStateInfo}
          onLabelButtonClick={onLabelButtonClickHandler}
          milestoneButtonSelected={true}
        />
      </ButtonPanelWrap>
      <Counter openCount={openCount} closeCount={closedCount} />
      <List
        milestoneList={milestoneList}
        onDeleteButtonClick={onDeleteButtonClickHandler}
        isLogin={loginStateInfo}
      ></List>
    </>
  );
};

export default MilestoneListContainer;
