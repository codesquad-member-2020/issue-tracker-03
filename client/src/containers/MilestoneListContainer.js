import React, { useEffect, useState } from 'react';
import { useHistory } from "react-router-dom";
import { useSelector, useDispatch } from 'react-redux';
import styled from 'styled-components';

import ButtonPanel from '@Components/Common/ButtonPanel'
import Counter from '@Components/MilestoneList/Counter/index'
import List from '@Components/MilestoneList/List/index'

const ButtonPanelWrap = styled.div`
  padding: 20px 0;
`;

const MilestoneListContainer = () => {
  return (
    <>
      <ButtonPanelWrap>
        <ButtonPanel
          // onSubmitButtonClick={onNewLabelButtonClickHandler}
          submitButtonText="New milestone"
          submitButtonEnabled={true}
          // onMilestoneButtonClick={onMilestoneButtonClickHandler}
          milestoneButtonSelected={true}
        />
      </ButtonPanelWrap>
      <Counter openCount={2} closeCount={1}/>
      <List></List>
    </>
  );
};

export default MilestoneListContainer;
