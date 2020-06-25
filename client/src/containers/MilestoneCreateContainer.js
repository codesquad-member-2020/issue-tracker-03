import React, { useState } from "react";
import { useHistory } from "react-router-dom";
import { useSelector } from "react-redux";
import styled from "styled-components";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import MarkdownIt from "markdown-it";
import MdEditor from "react-markdown-editor-lite";
import "react-markdown-editor-lite/lib/index.css";
import TitleText from "@Components/Common/Text/TitleText";
import ContentInput from "@Components/Common/Input/ContentInput";
import SubmitButton from "@Components/Common/Button/SubmitButton";
import * as milestoneAPI from "$API/milestoneList";

const TitleWrap = styled.div`
  width: 600px;
  padding: 7px 10px;
`;

const DueDateWrap = styled.div`
  width: 600px;
  padding: 7px 10px;

  .react-datepicker-wrapper {
    .react-datepicker__input-container {
      * {
        padding: 4px 10px;
        width: 580px;
        height: 34px;
        border: 1px solid #d1d5da;
        background-color: #fafbfc;

        ::-webkit-input-placeholder {
          color: rgb(190, 190, 190);
        }

        &:focus {
          outline: none;
          box-shadow: 0px 0px 5px #2188ff;
          background-color: #fff;
        }
      }
    }
  }
`;

const ContentsEditorWrap = styled.div`
  width: 100%;
  padding: 7px 10px;

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

const ButtonWrap = styled.div`
  padding: 7px 10px;
`;

const mdParser = new MarkdownIt();

const MilestoneCreateContainer = () => {
  const date = new Date();
  const history = useHistory();
  const [dueDate, setDueDate] = useState(date);
  const [cvtDueDate, setCvtDueDate] = useState(
    date.getFullYear() +
      "-" +
      (date.getMonth() + 1 < 10
        ? "0" + (date.getMonth() + 1)
        : date.getMonth() + 1) +
      "-" +
      (date.getDate() < 10 ? "0" + date.getDate() : date.getDate())
  );
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const { loginStateInfo } = useSelector(({ login }) => login);

  const onTitleChangeHandler = (text) => {
    setTitle(text);
  };

  const handleEditorChange = ({ text }) => {
    setDescription(text);
  };

  const onDateChangeHandler = (date) => {
    setDueDate(date);
    setCvtDueDate(
      date.getFullYear() +
        "-" +
        (date.getMonth() + 1 < 10
          ? "0" + (date.getMonth() + 1)
          : date.getMonth() + 1) +
        "-" +
        (date.getDate() < 10 ? "0" + date.getDate() : date.getDate())
    );
  };

  const onClickCreateMilestoneHandler = () => {
    if (!title) {
      alert("타이틀을 입력해주세요");
      return;
    }
    
    console.log(dueDate);
    console.log(cvtDueDate);

    (async () => {
      const body = {
        name: title,
        description: description,
        dueDate: dueDate,
      };

      let response = await milestoneAPI.createMilestone(body);

      if (response.success) history.push("/milestone-list/");
      else alert("요청이 처리되지 않았습니다.");
    })();
  };

  return (
    <>
      <TitleWrap>
        <TitleText title="Title"></TitleText>
        <ContentInput
          onTextChange={onTitleChangeHandler}
          text={title}
        ></ContentInput>
      </TitleWrap>
      <DueDateWrap>
        <TitleText title="Due date (optional)"></TitleText>
        <DatePicker
          dateFormat="yyyy/MM/dd"
          selected={dueDate}
          onChange={(date) => onDateChangeHandler(date)}
        />
      </DueDateWrap>
      <ContentsEditorWrap>
        <TitleText title="Description (optional)"></TitleText>
        <MdEditor
          value={description}
          style={{ height: "350px", width: "100%" }}
          config={{ view: { menu: false, md: true, html: false } }}
          renderHTML={(text) => mdParser.render(text)}
          onChange={handleEditorChange}
          placeholder="Leave a comment"
        />
      </ContentsEditorWrap>
      <ButtonWrap>
        <SubmitButton
          onButtonClick={onClickCreateMilestoneHandler}
          buttonText="Create milestone"
          buttonEnabled={true}
        ></SubmitButton>
      </ButtonWrap>
    </>
  );
};

export default MilestoneCreateContainer;
