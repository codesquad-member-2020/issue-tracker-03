import React from 'react';
import Article from '@Components/IssueList/List/Article';

const List = ({ list, labelColors, onCheckboxClick, checkbox }) => {
  return (
    <div>
      {list.map(article => (
        <Article
          key={article.id}
          data={article}
          labelColors={labelColors}
          onCheckboxClick={onCheckboxClick}
          checked={checkbox.checkboxList.get(article.id)}
        />
      ))}
    </div>
  );
};

export default List;
