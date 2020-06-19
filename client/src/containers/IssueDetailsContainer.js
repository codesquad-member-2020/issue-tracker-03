import React, { useEffect, useState } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import styled from 'styled-components';
import { getIssue } from '../modules/issueList';
import { reducerUtils } from '../libs/asyncUtils';
import IssueDetails from '../components/IssueDetails';
import IssueDetailesTitle from '../components/IssueDetails/IssueDetailesTitle';

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
`;

const IssueDetailsContainer = ({ issueId }) => {
  const dispatch = useDispatch();
  const [isEdit, setEdit] = useState(false);
  const { data, loading, error } = useSelector(
    state => state.issueList.issue[issueId] || reducerUtils.initial(),
  );

  useEffect(() => {
    if (data) return;
    dispatch(getIssue(issueId));
  }, [data, issueId, dispatch]);

  if (loading && !data) return <div>로딩중...</div>;
  if (error) return <div>에러 발생!</div>;
  if (!data) return null;

  const onClickEdit = () => setEdit(true);
  const onClickClose = () => setEdit(false);
  const onClickSave = value => console.log('[Save] : ', value);

  return (
    <Wrap>
      <TitleSection>
        <IssueDetailesTitle
          issue={data}
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
        <SideSection></SideSection>
      </ContentsWrap>
    </Wrap>
  );
};

export default IssueDetailsContainer;
