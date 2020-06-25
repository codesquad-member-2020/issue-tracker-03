import React from 'react';
import Filter from './Filter';
import Counter from './Counter'

const Filters = ({ isOpen, openButtonClick, closedButtonClick }) => {
  return (
    <div>
      <Counter
        isOpen={isOpen}
        openButtonClick={openButtonClick}
        closedButtonClick={closedButtonClick}
      />
    </div>
  );
};

export default Filters;
