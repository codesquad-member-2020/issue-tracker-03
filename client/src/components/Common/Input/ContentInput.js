import React from 'react';
import styled from 'styled-components';

const StyledContentInput = styled.input`
  width: 100%;
  height: 34px;
  border: 1px solid #d1d5da;
  padding: 4px 10px;
  font-size: 16px;
  background-color: #fafbfc;

  ::-webkit-input-placeholder {
    color: rgb(190, 190, 190);
  }

  &:focus {
    outline: none;
    box-shadow: 0px 0px 5px #2188ff;
    background-color: #fff;
  }
`;

const ContentInput = ({ placeHolder, onTextChange, text }) => {
  const handleChange = (e) => {
    onTextChange(e.target.value);
  }

  return (
    <StyledContentInput onChange={handleChange} placeholder={placeHolder} value={text ? text : ''}/>
  );
};

export default ContentInput;