import React from 'react';
import Article from '@Components/IssueList/List/Article';

const List = ({ list, onCheckboxClick, checkbox }) => {
  return (
    <div>
      {list.map(article => (
        <Article
          key={article.id}
          data={article}
          onCheckboxClick={onCheckboxClick}
          checked={checkbox.checkboxList.get(article.id)}
        />
      ))}
    </div>
  );
};

export default List;
