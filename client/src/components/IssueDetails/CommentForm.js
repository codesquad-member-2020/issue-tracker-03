import React from 'react';
import styled from 'styled-components';

const Wrap = styled.div`
  display: flex;
  position: relative;
  padding-top: 20px;
  margin-top: 20px;
  border-top: 1px solid #eee;
  background: #fff;
`;
const Avatar = styled.div`
  width: 40px;
  height: 40px;
`;
const FormWrap = styled.div`
  flex-grow: 1;
  margin-left: 20px;
  border: 1px solid #eee;
  border-radius: 3px;
`;
const FormHead = styled.div`
  position: relative;
  height: 40px;
  border-bottom: 1px solid #eee;
  background: #f6f8fa;
  &::before {
    content: '';
    position: absolute;
    top: 12px;
    left: -5px;
    width: 8px;
    height: 8px;
    border: 1px solid #eee;
    border-top: 0;
    border-right: 0;
    background: #f6f8fa;
    transform: rotate(45deg);
  }
  &::after {
    content: 'Write';
    position: absolute;
    left: 10px;
    bottom: 0;
    padding: 8px 15px;
    font-size: 14px;
    border: 1px solid #eee;
    border-bottom: 0;
    border-radius: 5px 5px 0 0;
    background: #fff;
  }
`;
const FormBody = styled.form`
  padding: 10px;
  textarea {
    width: 100%;
    padding: 5px;
    border: 1px solid #eee;
    border-radius: 3px;
    outline: none;
    resize: none;
  }
`;

const CommentForm = ({ author }) => {
  return (
    <Wrap>
      <Avatar>
        <img src={author.avatarURL} alt={author.userId} />
      </Avatar>
      <FormWrap>
        <FormHead />
        <FormBody>
          <textarea
            name=""
            id=""
            cols="30"
            rows="10"
            placeholder="Leave a comment"
          ></textarea>
          <div>
            <button>Close issue</button>
            <button type="submit">Comment</button>
          </div>
        </FormBody>
      </FormWrap>
    </Wrap>
  );
};

export default CommentForm;
