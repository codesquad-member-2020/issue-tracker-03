import React, { useEffect, useState } from 'react';
import { useHistory } from "react-router-dom";
import { useSelector, useDispatch } from 'react-redux';
import styled from 'styled-components';
import { getMilestone } from '../modules/milestoneList';
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import MarkdownIt from "markdown-it";
import MdEditor from "react-markdown-editor-lite";
import "react-markdown-editor-lite/lib/index.css";
import TitleText from "@Components/Common/Text/TitleText";
import ContentInput from "@Components/Common/Input/ContentInput";
import SubmitButton from "@Components/Common/Button/SubmitButton";
import CancelButton from "@Components/Common/Button/CancelButton";
import { reducerUtils } from '../libs/asyncUtils';
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

  button + button {
    margin-left: 10px;
  }
`;

const mdParser = new MarkdownIt();

const MilestoneModifyContainer = ({ milestoneId }) => {
  const dispatch = useDispatch();
  //const date = new Date();
  const history = useHistory();
  const [date, setDate] = useState(new Date());
  const [dueDate, setDueDate] = useState(date);
  const [cvtDueDate, setCvtDueDate] = useState('')
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const { loginStateInfo } = useSelector(({ login }) => login);

  const { data, loading, error } = useSelector(state => state.milestoneList.milestone[milestoneId] || reducerUtils.initial());

  useEffect(() => {
    if (data) {
      setTitle(data.name);
      setCvtDueDate(data.dueDate);
      const dateArray = data.dueDate.split('-');
      setDueDate(new Date(parseInt(dateArray[0], 10), parseInt(dateArray[1], 10),parseInt(dateArray[2], 10)))
      setDescription(data.description);

      return;
    }

    dispatch(getMilestone(milestoneId));
  }, [dispatch, data, milestoneId]);

  if (loading && !data) return <div>로딩중...</div>;
  if (error) return <div>에러 발생!</div>;
  if (!data) return null;

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

  const onClickSaveChangesHandler = () => {
    if (!title) alert("타이틀을 입력해주세요");
    (async () => {
      const body = {
        name: title,
        description: description,
        dueDate: cvtDueDate,
      };

      let response = await milestoneAPI.modifyMilestone(milestoneId, body);

      if (response.success) history.push("/milestone-list/");
      else alert("요청이 처리되지 않았습니다.");
    })();
  };

  const onClickCancelButtonHandler = () => {
    history.push("/milestone-list/");
  }

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
        <CancelButton onButtonClick={onClickCancelButtonHandler}></CancelButton>
        <SubmitButton
          onButtonClick={onClickSaveChangesHandler}
          buttonText="Save changes"
          buttonEnabled={loginStateInfo}
        ></SubmitButton>
      </ButtonWrap>
    </>
  );
};

export default MilestoneModifyContainer;
