import React from 'react';
import styled from 'styled-components'

import SubmitButton from '@Components/Common/Button/SubmitButton'
import NavigationButton from '@Components/Common/Button/NavigationButton'

const ButtonPanelWrap = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const ButtonPanel = ({
  onSubmitButtonClick,
  submitButtonText,
  submitButtonEnabled,
  onMilestoneButtonClick,
  milestoneButtonSelected,
  milestoneCount,
  onLabelButtonClick,
  labelButtonSelected,
  labelCount,
}) => {
  return (
    <ButtonPanelWrap>
      <div>
        <NavigationButton
          onButtonClick={onLabelButtonClick}
          buttonText="Labels"
          selected={labelButtonSelected}
          count={labelCount}
        ></NavigationButton>
        <NavigationButton
          onButtonClick={onMilestoneButtonClick}
          buttonText="Milestones"
          selected={milestoneButtonSelected}
          count={milestoneCount}
        ></NavigationButton>
      </div>
      <SubmitButton
        onButtonClick={onSubmitButtonClick}
        buttonText={submitButtonText}
        buttonEnabled={submitButtonEnabled}
      />
    </ButtonPanelWrap>
  );
};

export default ButtonPanel;
