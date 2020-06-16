import styled from 'styled-components';

export const ArticleWrap = styled.div`
  display: flex;
  flex-wrap: nowrap;
  padding: 10px 15px;
  border: 1px solid #e0e0e0;
  & + & {
    border-top: 0;
  }
  &:hover {
    background: #f6f8fa;
  }
`;
export const ArticleCol = styled.div`
  width: 10%;
  ${({ styleProps }) => styleProps === 'textRight' && `text-align: right;`}
  ${({ styleProps }) => styleProps === 'flexAuto' && `flex: auto;`}
  ${({ styleProps }) =>
    styleProps === 'widthAuto' &&
    `width: auto; padding-right: 15px; line-height: 0;`}
`;
export const IssueIcon = styled.svg`
  path {
    ${({ bgColor }) => bgColor && `color: ${bgColor}`}
  }
`;
export const IssueTitle = styled.div`
  font-weight: 500;
  font-size: 18px;
  line-height: 1;
  * {
    display: inline-block;
  }
  a:hover {
    color: #0366d6;
  }
  span {
    padding: 3px 5px;
    margin: 0 5px;
    font-size: 14px;
    line-height: 1;
    border-radius: 3px;
    border-right: 1px solid #ccc;
    border-bottom: 1px solid #ccc;
  }
`;
export const IssueDescription = styled.div`
  * {
    display: inline-block;
    font-size: 13px;
    color: #888;
    line-height: 16px;
  }
  span + span {
    margin-left: 10px;
    font-weight: 700;
    font-size: 14px;
    svg {
      margin-right: 2px;
      vertical-align: top;
    }
  }
`;
export const IssueAssignee = styled.span`
  display: inline-block;
  width: 20px;
  height: 20px;
  border-radius: 2px;
  background: #eee;
  img {
    max-width: 100%;
  }
  & + & {
    margin-left: 2px;
  }
`;
export const IssueComment = styled.span`
  * {
    display: inline-block;
    font-weight: 700;
    font-size: 14px;
    vertical-align: middle;
  }
  span {
    margin-left: 2px;
  }
`;
