import React from 'react';
import {
  ArticleWrap,
  ArtcileLink,
  ArticleCol,
} from '@Components/IssueList/List/styled';

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
      <ArtcileLink to={`/issue-detail/${id}/`}>
        <ArticleCol>
          <input
            readOnly
            type="checkbox"
            name=""
            id=""
            onClick={onClick}
            checked={checked}
          />
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
        <ArticleCol>
          {commentCount > 1 && <span>{commentCount}</span>}
        </ArticleCol>
      </ArtcileLink>
    </ArticleWrap>
  );
};

export default Article;
