import React from 'react';
import Article from './Article';

const List = ({ list, label, onCheckbox }) => {
  return (
    <div>
      {list.map(article => (
        <Article
          key={article.id}
          data={article}
          label={label}
          onCheckbox={onCheckbox}
        />
      ))}
    </div>
  );
};

export default List;
