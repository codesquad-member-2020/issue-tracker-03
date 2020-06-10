import React from 'react';
import Header from '../components/Common/Header';
import Footer from '../components/Common/Footer';
import IssueListContainer from '../containers/IssueListContainer';

const IssueListPage = () => {
  return (
    <>
      <Header />
      <IssueListContainer />
      <Footer />
    </>
  );
};

export default IssueListPage;
