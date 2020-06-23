import React, { useEffect, useState } from 'react';
import { useHistory } from "react-router-dom";
import { useSelector, useDispatch } from 'react-redux';
import {
  getLabelList,
} from '../modules/labelList';
import styled from 'styled-components';
import List from '@Components/LabelList/List/index'
import Counter from '@Components/LabelList/Counter/index'
import ButtonPanel from '@Components/Common/ButtonPanel'
import ArticleExpanded from "@Components/LabelList/List/ArticleExpanded";

const ButtonPanelWrap = styled.div`
  padding: 20px 0;
`;

const IssueCreatecontainer = () => {
  const history = useHistory();
  const [expandedList, setExpandedList] = useState(new Map());
  const [newLabelTabVisible, setNewLabelTabVisible] = useState(false);
  const { loginStateInfo } = useSelector(({ login }) => login);
  const { data, loading, error } = useSelector(
    state => state.labelList.labelList,
  );
  const dispatch = useDispatch();

  useEffect(() => {
    if (data) {
      const labelList = data.response;
      labelList.expanded = new Map();
      labelList.forEach((element, index) => {
        expandedList.set(index, false);
      });
      return
    };
    dispatch(getLabelList());
  }, [dispatch, data]);

  const onNewLabelButtonClickHandler = () => {
    setNewLabelTabVisible(true);
  }

  const onMilestoneButtonClickHandler = () => {
    history.push('/milestone-list');
  }

  const onEditButtonClickHandler = (index) => {
    setExpandedList(new Map(expandedList.set(index, true)))
  }

  const onDeleteButtonClickHandler = (index) => {
    console.log("onDeleteButtonClickHandler", index);
  }

  const onCancelButtonClickHandler = (index) => {
    setExpandedList(new Map(expandedList.set(index, false)))
  }

  const onSaveButtonClickHandler = (index, contents) => {
    console.log("onSaveButtonClickHandler", index, contents);
  }

  const onCancelCreateNewLabelHandler = () => {
    setNewLabelTabVisible(false);
  }

  const onCreateNewLabelHandler = () => {
  }

  if (loading && !data) return <div>로딩중...</div>;
  if (error) return <div>에러 발생!</div>;
  if (!data) return null;

  const labelList = data.response;

  return (
    <>
      <ButtonPanelWrap>
        <ButtonPanel
          onSubmitButtonClick={onNewLabelButtonClickHandler}
          submitButtonText="New label"
          submitButtonEnabled={true}
          onMilestoneButtonClick={onMilestoneButtonClickHandler}
          labelButtonSelected={true}
        />
      </ButtonPanelWrap>
      {newLabelTabVisible && (
        <ArticleExpanded
          onCancelButtonClick={onCancelCreateNewLabelHandler}
          onSaveButtonClick={onCreateNewLabelHandler}
          submitText="Create label"
        />
      )}
      <Counter count={labelList.length} />
      <List
        labelList={labelList}
        expandedList={expandedList}
        onEditButtonClick={onEditButtonClickHandler}
        onDeleteButtonClick={onDeleteButtonClickHandler}
        onSaveButtonClick={onSaveButtonClickHandler}
        onCancelButtonClick={onCancelButtonClickHandler}
        changeEnabled={loginStateInfo}
        submitText="Save changes"
      ></List>
    </>
  );
};

export default IssueCreatecontainer;
