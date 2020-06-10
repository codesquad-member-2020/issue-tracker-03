import React from 'react';
import Article from './Article';

const List = ({ list }) => {
  return (
    <div>
      {list.map(article => (
        <Article key={article.id} data={article} />
      ))}
    </div>
  );
};

export default List;
