import React from 'react';
import Panel from './Panel';
import { getAssignees, getLabels, getMilestones } from '../../modules/sidePanels';

const SidePanels = ({ data }) => {
  const onClickHandler = (value, type) => {
    console.log('[click] ', value, type);
  };
  return (
    <div>
      <Panel type={'labels'} value={'name'} dispatchCallback={getLabels} onClickHandler={onClickHandler} />
      <Panel type={'milestones'} value={'title'} dispatchCallback={getMilestones} onClickHandler={onClickHandler} />
    </div>
  );
};

export default SidePanels;
