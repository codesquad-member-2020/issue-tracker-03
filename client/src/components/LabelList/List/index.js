import React from 'react';
import styled from 'styled-components'

import Article from '@Components/LabelList/List/Article';

const List = ({ labelList }) => {
  return (
    <div>
      {labelList.map(article => (
        <Article
          name={article.name}
          color={article.color}
          description={article.description}
        />
      ))}
    </div>
  );
};

export default List;
