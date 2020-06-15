import { Link } from 'react-router-dom';
import styled, { css } from 'styled-components';

export const ArticleWrap = styled.div`
  padding: 8px 16px;
  border: 1px solid #e0e0e0;
  & + & {
    border-top: 0;
  }
`;
export const ArtcileLink = styled(Link)`
  display: flex;
  flex-wrap: nowrap;
  align-items: center;
`;
export const ArticleCol = styled.div`
  width: 5%;
  ${props =>
    props.type === 'title' &&
    css`
      flex: auto;
    `}
`;
