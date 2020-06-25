import React from "react";

import Article from "@Components/MilestoneList/List/Article";

const List = ({
  milestoneList,
  onDeleteButtonClick,
}) => {
  return (
    <>
      {milestoneList &&
        milestoneList.map((article, index) => (
          <Article
            key={article.id}
            id={article.id}
            index={index}
            description={article.description}
            title={article.name}
            dueDate={article.dueDate}
            onDeleteButtonClick={onDeleteButtonClick}
          />
        ))}
    </>
  );
};

export default List;
