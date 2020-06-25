import React, { useState } from "react";
import styled from "styled-components";

import TitleText from "@Components/Common/Text/TitleText";
import ContentInput from "@Components/Common/Input/ContentInput";
import CancelButton from "@Components/Common/Button/CancelButton";
import SubmitButton from "@Components/Common/Button/SubmitButton";

const ArticleExpandedWrap = styled.div`
  width: 1200px;
  align-items: center;
  justify-content: space-between;
  padding: 10px 15px;
  border: 1px solid #d1d5da;
  margin-top: -1px;
`;

const NameWrap = styled.div`
  padding-top: 10px;
  padding-bottom: 25px;
`;

const Name = styled.div`
  width: fit-content;
  height: fit-content;
  font-size: 14px;
  font-weight: bold;
  color: #fff;
  padding: 2px 5px;
  border-radius: 6px;
  background-color: ${(props) => props.color};
`;

const ContentWrap = styled.div`
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: space-between;

  div {
    padding-right: 20px;
  }
`;

const TitleWrap = styled.div`
  flex-grow: 1;
  flex-basis: 0;
  padding-right: 10px;
`;

const DescriptionWrap = styled.div`
  flex-grow: 2;
  flex-basis: 0;
`;

const ColorEditWrap = styled.div`
  flex-grow: 1;
  flex-basis: 0;
`;

const ButtonWrap = styled.div`
  flex-grow: 1;
  flex-basis: 0;

  button {
    margin-right: 8px;
  }
`;

const ArticleExpanded = ({
  id,
  index,
  labelName,
  labelNamePlaceHolder,
  labelDescription,
  labelDescriptionPlaceHolder,
  labelColor,
  onCancelButtonClick,
  onSaveButtonClick,
  changeEnabled,
  submitText,
}) => {
  const [name, setName] = useState(labelName);
  const [description, setDescription] = useState(labelDescription);
  const [color, setColor] = useState(labelColor);

  const onLabelNameChangeHandler = (text) => {
    setName(text);
  };
  const onDescriptionChangeHandler = (text) => {
    setDescription(text);
  };
  const onColorChangeHandler = (text) => {
    setColor(text);
  };
  const onCancelButtonClickHandler = (e) => {
    if (onCancelButtonClick) onCancelButtonClick(index);
  };
  const onSaveButtonClickHandler = (e) => {
    const contents = {
      name: name,
      description: description,
      color: color,
    };
    if (onSaveButtonClick) onSaveButtonClick(index, id, contents);
  };

  return (
    <ArticleExpandedWrap>
      <NameWrap>
        <Name color={color}>{labelName ? labelName : name}</Name>
      </NameWrap>
      <ContentWrap>
        <TitleWrap>
          <TitleText title="Label name"></TitleText>
          <ContentInput
            onTextChange={onLabelNameChangeHandler}
            text={name}
            placeHolder={labelNamePlaceHolder}
          />
        </TitleWrap>
        <DescriptionWrap>
          <TitleText title="Description"></TitleText>
          <ContentInput
            onTextChange={onDescriptionChangeHandler}
            text={description}
            placeHolder={labelDescriptionPlaceHolder}
          />
        </DescriptionWrap>
        <ColorEditWrap>
          <TitleText title="Color"></TitleText>
          <ContentInput onTextChange={onColorChangeHandler} text={color} />
        </ColorEditWrap>
        <ButtonWrap>
          <TitleText title="　"></TitleText>
          <CancelButton onButtonClick={onCancelButtonClickHandler} />
          <SubmitButton
            onButtonClick={onSaveButtonClickHandler}
            buttonText={submitText}
            buttonEnabled={changeEnabled}
          />
        </ButtonWrap>
      </ContentWrap>
    </ArticleExpandedWrap>
  );
};

export default ArticleExpanded;
