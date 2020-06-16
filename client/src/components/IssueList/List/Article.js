import React from 'react';
import { Link } from 'react-router-dom';
import {
  ArticleWrap,
  ArticleCol,
  IssueIcon,
  IssueTitle,
  IssueDescription,
  IssueAssignee,
  IssueComment,
} from '@Components/IssueList/List/styled';

const OpenIcon = () => {
  return (
    <IssueIcon
      viewBox="0 0 16 16"
      version="1.1"
      width="16"
      height="16"
      aria-hidden="true"
      bgColor="#28a745"
    >
      <path d="M8 1.5a6.5 6.5 0 100 13 6.5 6.5 0 000-13zM0 8a8 8 0 1116 0A8 8 0 010 8zm9 3a1 1 0 11-2 0 1 1 0 012 0zm-.25-6.25a.75.75 0 00-1.5 0v3.5a.75.75 0 001.5 0v-3.5z"></path>
    </IssueIcon>
  );
};

const CloseIcon = () => {
  return (
    <IssueIcon
      viewBox="0 0 16 16"
      version="1.1"
      width="16"
      height="16"
      aria-hidden="true"
      bgColor="#cb2431"
    >
      <path d="M1.5 8a6.5 6.5 0 0110.65-5.003.75.75 0 00.959-1.153 8 8 0 102.592 8.33.75.75 0 10-1.444-.407A6.5 6.5 0 011.5 8zM8 12a1 1 0 100-2 1 1 0 000 2zm0-8a.75.75 0 01.75.75v3.5a.75.75 0 11-1.5 0v-3.5A.75.75 0 018 4zm4.78 4.28l3-3a.75.75 0 00-1.06-1.06l-2.47 2.47-.97-.97a.749.749 0 10-1.06 1.06l1.5 1.5a.75.75 0 001.06 0z"></path>
    </IssueIcon>
  );
};

const Article = ({ data, onCheckboxClick, checked }) => {
  const {
    id,
    title,
    author,
    labels,
    mileStone,
    createdTimeAt,
    assignee,
    commentCount,
    open,
  } = data;

  const onClick = e => {
    const checked = e.target.checked;
    onCheckboxClick(id, checked);
  };

  return (
    <ArticleWrap>
      <ArticleCol styleProps="widthAuto">
        <input
          readOnly
          type="checkbox"
          name=""
          id=""
          onClick={onClick}
          checked={checked}
        />
      </ArticleCol>
      <ArticleCol styleProps="widthAuto">
        {open ? <OpenIcon /> : <CloseIcon />}
      </ArticleCol>
      <ArticleCol styleProps="flexAuto">
        <IssueTitle>
          <Link to={`/issue-detail/${id}/`}>{title}</Link>
          {labels.length > 0 &&
            labels.map((item, index) => {
              return (
                <span key={index} style={{ background: item.color }}>
                  {item.name}
                </span>
              );
            })}
        </IssueTitle>
        <IssueDescription>
          <span>
            {`#${id} 
            ${open ? 'opend' : 'closed'} 
            ${createdTimeAt} by ${author.userId}`}
          </span>
          {mileStone && (
            <span>
              <svg
                viewBox="0 0 16 16"
                version="1.1"
                width="16"
                height="16"
                role="img"
              >
                <path d="M7.75 0a.75.75 0 01.75.75V3h3.634c.414 0 .814.147 1.13.414l2.07 1.75a1.75 1.75 0 010 2.672l-2.07 1.75a1.75 1.75 0 01-1.13.414H8.5v5.25a.75.75 0 11-1.5 0V10H2.75A1.75 1.75 0 011 8.25v-3.5C1 3.784 1.784 3 2.75 3H7V.75A.75.75 0 017.75 0zm0 8.5h4.384a.25.25 0 00.161-.06l2.07-1.75a.25.25 0 000-.38l-2.07-1.75a.25.25 0 00-.161-.06H2.75a.25.25 0 00-.25.25v3.5c0 .138.112.25.25.25h5z"></path>
              </svg>
              {mileStone}
            </span>
          )}
        </IssueDescription>
      </ArticleCol>
      <ArticleCol>
        {assignee.length > 0 &&
          assignee.map((name, index) => (
            <IssueAssignee key={index}>
              <img src={name.avatarURL} alt={name.userId} />
            </IssueAssignee>
          ))}
      </ArticleCol>
      <ArticleCol styleProps="textRight">
        {commentCount > 1 && (
          <IssueComment>
            <svg
              viewBox="0 0 16 16"
              version="1.1"
              width="16"
              height="16"
              aria-hidden="true"
            >
              <path d="M2.75 2.5a.25.25 0 00-.25.25v7.5c0 .138.112.25.25.25h2a.75.75 0 01.75.75v2.19l2.72-2.72a.75.75 0 01.53-.22h4.5a.25.25 0 00.25-.25v-7.5a.25.25 0 00-.25-.25H2.75zM1 2.75C1 1.784 1.784 1 2.75 1h10.5c.966 0 1.75.784 1.75 1.75v7.5A1.75 1.75 0 0113.25 12H9.06l-2.573 2.573A1.457 1.457 0 014 13.543V12H2.75A1.75 1.75 0 011 10.25v-7.5z"></path>
            </svg>
            <span>{commentCount}</span>
          </IssueComment>
        )}
      </ArticleCol>
    </ArticleWrap>
  );
};

export default Article;
