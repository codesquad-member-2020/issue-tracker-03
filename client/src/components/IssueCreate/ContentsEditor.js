import React from 'react';
import styled from 'styled-components';
import MarkdownIt from 'markdown-it'
import MdEditor from 'react-markdown-editor-lite'
import 'react-markdown-editor-lite/lib/index.css';

const ContentsEditorWrap = styled.div`
  width: 100%;
  padding-top: 20px;
  padding-bottom: 20px;

  .tool-bar {
    display: none;
  }

  .editor-container {
    .section {
      .section-container {
        background-color: #fafbfc;

        &:focus {
          outline: none;
          box-shadow: 0px 0px 5px #2188ff;
          background-color: #fff;
        }
      }
    }
  }
`;

const mdParser = new MarkdownIt();

const ContentsEditor = ({ onTextChange }) => {
  const handleEditorChange = ({ text }) => {
    onTextChange(text);
  }

  return (
    <ContentsEditorWrap>
      <MdEditor
        value=""
        style={{ height: "350px", width: "100%" }}
        config={{ view: { menu: false, md: true, html: false } }}
        renderHTML={(text) => mdParser.render(text)}
        onChange={handleEditorChange}
        placeholder="Leave a comment"
        />
      </ContentsEditorWrap>
  );
};

export default ContentsEditor;
