import React from "react";

import ArticleNormal from "@Components/LabelList/List/ArticleNormal";
import ArticleExpanded from "@Components/LabelList/List/ArticleExpanded";

const List = ({
  labelList,
  expandedList,
  onEditButtonClick,
  onDeleteButtonClick,
  onCancelButtonClick,
  onSaveButtonClick,
  changeEnabled,
  submitText
}) => {
  return (
    <div>
      {labelList.map((article, index) => {
        if (!expandedList.get(index))
          return (
            <ArticleNormal
              key={index}
              index={index}
              id={article.id}
              labelName={article.name}
              labelColor={article.color}
              labelDescription={article.description}
              onEditButtonClick={onEditButtonClick}
              onDeleteButtonClick={onDeleteButtonClick}
              changeEnabled={changeEnabled}
            />
          );
        else
          return (
            <ArticleExpanded
              key={index}
              index={index}
              id={article.id}
              labelName={article.name}
              labelNamePlaceHolder="Label name"
              labelColor={article.color}
              labelDescription={article.description}
              labelDescriptionPlaceHolder="Description (optional)"
              onCancelButtonClick={onCancelButtonClick}
              onSaveButtonClick={onSaveButtonClick}
              changeEnabled={changeEnabled}
              submitText={submitText}
            />
          );
      })}
    </div>
  );
};

export default List;
