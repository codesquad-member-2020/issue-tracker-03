import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import styled from 'styled-components';
import { getIssue } from '../modules/issueList';
import { reducerUtils } from '../libs/asyncUtils';
import IssueDetails from '../components/IssueDetails';
import IssueDetailesTitle from '../components/IssueDetails/IssueDetailesTitle';
import SidePanels from '../components/SidePanels';

const Wrap = styled.div``;
const ContentsWrap = styled.div`
  display: flex;
`;
const TitleSection = styled.section``;
const ContentsSection = styled.section`
  flex-grow: 7;
`;
const SideSection = styled.section`
  flex-grow: 3;
  padding-left: 15px;
`;

const IssueDetailsContainer = ({ issueId }) => {
  const dispatch = useDispatch();
  const [isEdit, setEdit] = useState(false);
  const [title, setTitle] = useState(null);

  const { data, loading, error } = useSelector(state => state.issueList.issue[issueId] || reducerUtils.initial());

  useEffect(() => {
    if (data) return setTitle(data.title);
    dispatch(getIssue(issueId));
  }, [data, issueId, title, dispatch]);

  if (loading && !data) return <div>로딩중...</div>;
  if (error) return <div>에러 발생!</div>;
  if (!data) return null;

  const onClickEdit = () => setEdit(true);
  const onClickClose = () => setEdit(false);
  const onClickSave = value =>
    axios
      .put(`http://15.164.138.255/api/issues/${data.id}`, {
        title: value,
      })
      .then(() => dispatch(getIssue(issueId)))
      .catch(e => console.log(`[error] : ${e}`));

  return (
    <Wrap>
      <TitleSection>
        <IssueDetailesTitle
          issue={data}
          title={title}
          onClickEdit={onClickEdit}
          onClickSave={onClickSave}
          onClickClose={onClickClose}
          isEdit={isEdit}
        />
      </TitleSection>
      <ContentsWrap>
        <ContentsSection>
          <IssueDetails issue={data} />
        </ContentsSection>
        <SideSection>
          <SidePanels data={data} />
        </SideSection>
      </ContentsWrap>
    </Wrap>
  );
};

export default IssueDetailsContainer;
