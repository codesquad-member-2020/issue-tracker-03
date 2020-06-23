import React from 'react';
import styled from 'styled-components';

const TitleInputWrap = styled.input`
  width: 100%;
  height: 34px;
  border: 1px solid #d1d5da;
  padding: 4px 10px;
  font-size: 16px;
  background-color: #fafbfc;

  ::-webkit-input-placeholder {
    color: rgb(120, 120, 120);
  }

  &:focus {
    outline: none;
    box-shadow: 0px 0px 5px #2188ff;
    background-color: #fff;
  }
`;

const TitleInput = ({ onTextChange }) => {
  const handleChange = (e) => {
    onTextChange(e.target.value);
  }

  return (
    <TitleInputWrap onChange={handleChange} placeholder="Title" />
  );
};

export default TitleInput;
