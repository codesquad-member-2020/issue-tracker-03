import React from 'react';
import styled, { css } from 'styled-components';

const ArticleWrap = styled.div`
  display: flex;
  flex-wrap: nowrap;
  align-items: center;
  padding: 8px 16px;
  border: 1px solid #e0e0e0;
  & + & {
    border-top: 0;
  }
`;
const ArticleCol = styled.div`
  width: 5%;
  ${props =>
    props.type === 'title' &&
    css`
      flex: auto;
    `}
`;

const Article = ({ data, labelColors, onCheckboxClick, checked }) => {
  const {
    id,
    title,
    createdBy,
    labels,
    mileStone,
    createdTimeAt,
    assignee,
    commentCount,
  } = data;

  const onClick = e => {
    const checked = e.target.checked;
    onCheckboxClick(id, checked);
  };

  return (
    <ArticleWrap>
      <ArticleCol>
        <input readOnly type="checkbox" name="" id="" onClick={onClick} checked={checked} />
      </ArticleCol>
      <ArticleCol>아이콘</ArticleCol>
      <ArticleCol type="title">
        <div>
          {title}
          {labels.length > 0 &&
            labels.map((item, index) => {
              const color = labelColors.get(item);
              return (
                <span key={index} style={color && { color: color.color }}>
                  {item}
                </span>
              );
            })}
        </div>
        <div>
          <span>
            {`#${id}`} / 이슈상태 / {createdTimeAt} / {createdBy}
          </span>
          {mileStone && <span>{mileStone}</span>}
        </div>
      </ArticleCol>
      <ArticleCol>
        {assignee.length > 0 &&
          assignee.map((name, index) => <span key={index}>{name}</span>)}
      </ArticleCol>
      <ArticleCol>{commentCount > 1 && <span>{commentCount}</span>}</ArticleCol>
    </ArticleWrap>
  );
};

export default Article;