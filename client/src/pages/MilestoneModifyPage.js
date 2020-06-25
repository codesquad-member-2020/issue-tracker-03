import React from 'react';
import MilestoneModifyContainer from '../containers/MilestoneModifyContainer';

const MilestoneModifyPage = ({match}) => {
  return <MilestoneModifyContainer milestoneId={match.params.milestoneId}></MilestoneModifyContainer>;
};

export default MilestoneModifyPage;
