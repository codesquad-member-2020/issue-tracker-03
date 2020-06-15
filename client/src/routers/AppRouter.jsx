import React from 'react';
import {
  BrowserRouter as Router,
  Route
} from "react-router-dom";

import IssueListPage from '@Pages/IssueListPage';
import IssueDetailPage from '@Pages/IssueDetailPage';
import IssueCreatePage from '@Pages/IssueCreatePage';
import LabelListPage  from '@Pages/LabelListPage';
import MilestoneCreatePage from '@Pages/MilestoneCreatePage';
import MilestoneListPage from '@Pages/MilestoneListPage';
import MilestoneModifyPage from '@Pages/MilestoneModifyPage';
import LoginPage from '@Pages/LoginPage';
import Cookies from 'universal-cookie';

import { setLoginInfo, resetLoginInfo } from '../modules/login';
import { useDispatch } from 'react-redux';

import HeaderContainer from '@Containers/HeaderContainer'
import FooterContainer from '@Containers/FooterContainer'

function AppRouter() {
  const dispatch = useDispatch();

  const cookies = new Cookies();
  const loginInfo = cookies.get('jwt');
  
  if (loginInfo !== undefined) {
    dispatch(setLoginInfo(loginInfo));
  }
  else {
    dispatch(resetLoginInfo());
  }

  return (
    <>
    <HeaderContainer />
    <Router>
      <Route exact={true} path="/" component={IssueListPage} />
      <Route path="/issue-detail/:issueId/" component={IssueDetailPage} />
      <Route path="/issue-create/" component={IssueCreatePage} />
      <Route path="/label-list/" component={LabelListPage} />
      <Route path="/milestone-create/" component={MilestoneCreatePage} />
      <Route path="/milestone-list/" component={MilestoneListPage} />
      <Route path="/milestone-modify/:milestoneId/" component={MilestoneModifyPage} />
      <Route path="/login/" component={LoginPage} />
    </Router>
    <FooterContainer />
    </>
  );
}

export default AppRouter;