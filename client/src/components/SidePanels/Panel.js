import React, { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import styled from 'styled-components';

const Wrap = styled.div`
  position: relative;
  padding-bottom: 20px;
  margin-bottom: 20px;
  border-bottom: 1px solid #eee;
`;
const Title = styled.div`
  display: flex;
  justify-content: space-between;
  span {
    text-transform: capitalize;
  }
`;
const List = styled.div`
  overflow-y: auto;
  position: absolute;
  width: 100%;
  max-height: 300px;
  border: 1px solid #eee;
  box-shadow: 0 8px 24px rgba(149, 157, 165, 0.2);
  background: #fff;
  z-index: 2;
  div {
    padding: 10px 15px;
    & + div {
      border-top: 1px solid #eee;
    }
  }
`;
const Mask = styled.div`
  position: fixed;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  z-index: 1;
`;

const Panel = ({ type, value, dispatchCallback, onClickHandler }) => {
  const dispatch = useDispatch();
  const { data, loading, error } = useSelector(({ sidePanels }) => sidePanels[type]);
  const [open, setOpen] = useState(false);
  const onClickOpen = () => setOpen(!open);
  const close = () => setOpen(false);
  const onClickFunc = e => {
    onClickHandler(e.target.dataset.value, type);
  };

  useEffect(() => {
    if (data) return;
    open && dispatch(dispatchCallback());
  }, [open]);

  return (
    <Wrap>
      <Title onClick={onClickOpen}>
        <span>{type}</span>
        <svg viewBox="0 0 16 16" version="1.1" width="16" height="16" aria-hidden="true">
          <path d="M7.429 1.525a6.593 6.593 0 011.142 0c.036.003.108.036.137.146l.289 1.105c.147.56.55.967.997 1.189.174.086.341.183.501.29.417.278.97.423 1.53.27l1.102-.303c.11-.03.175.016.195.046.219.31.41.641.573.989.014.031.022.11-.059.19l-.815.806c-.411.406-.562.957-.53 1.456a4.588 4.588 0 010 .582c-.032.499.119 1.05.53 1.456l.815.806c.08.08.073.159.059.19a6.494 6.494 0 01-.573.99c-.02.029-.086.074-.195.045l-1.103-.303c-.559-.153-1.112-.008-1.529.27-.16.107-.327.204-.5.29-.449.222-.851.628-.998 1.189l-.289 1.105c-.029.11-.101.143-.137.146a6.613 6.613 0 01-1.142 0c-.036-.003-.108-.037-.137-.146l-.289-1.105c-.147-.56-.55-.967-.997-1.189a4.502 4.502 0 01-.501-.29c-.417-.278-.97-.423-1.53-.27l-1.102.303c-.11.03-.175-.016-.195-.046a6.492 6.492 0 01-.573-.989c-.014-.031-.022-.11.059-.19l.815-.806c.411-.406.562-.957.53-1.456a4.587 4.587 0 010-.582c.032-.499-.119-1.05-.53-1.456l-.815-.806c-.08-.08-.073-.159-.059-.19a6.44 6.44 0 01.573-.99c.02-.029.086-.075.195-.045l1.103.303c.559.153 1.112.008 1.529-.27.16-.107.327-.204.5-.29.449-.222.851-.628.998-1.189l.289-1.105c.029-.11.101-.143.137-.146zM8 0c-.236 0-.47.01-.701.03-.743.065-1.29.615-1.458 1.261l-.29 1.106c-.017.066-.078.158-.211.224a5.994 5.994 0 00-.668.386c-.123.082-.233.09-.3.071L3.27 2.776c-.644-.177-1.392.02-1.82.63a7.977 7.977 0 00-.704 1.217c-.315.675-.111 1.422.363 1.891l.815.806c.05.048.098.147.088.294a6.084 6.084 0 000 .772c.01.147-.038.246-.088.294l-.815.806c-.474.469-.678 1.216-.363 1.891.2.428.436.835.704 1.218.428.609 1.176.806 1.82.63l1.103-.303c.066-.019.176-.011.299.071.213.143.436.272.668.386.133.066.194.158.212.224l.289 1.106c.169.646.715 1.196 1.458 1.26a8.094 8.094 0 001.402 0c.743-.064 1.29-.614 1.458-1.26l.29-1.106c.017-.066.078-.158.211-.224a5.98 5.98 0 00.668-.386c.123-.082.233-.09.3-.071l1.102.302c.644.177 1.392-.02 1.82-.63.268-.382.505-.789.704-1.217.315-.675.111-1.422-.364-1.891l-.814-.806c-.05-.048-.098-.147-.088-.294a6.1 6.1 0 000-.772c-.01-.147.039-.246.088-.294l.814-.806c.475-.469.679-1.216.364-1.891a7.992 7.992 0 00-.704-1.218c-.428-.609-1.176-.806-1.82-.63l-1.103.303c-.066.019-.176.011-.299-.071a5.991 5.991 0 00-.668-.386c-.133-.066-.194-.158-.212-.224L10.16 1.29C9.99.645 9.444.095 8.701.031A8.094 8.094 0 008 0zm1.5 8a1.5 1.5 0 11-3 0 1.5 1.5 0 013 0zM11 8a3 3 0 11-6 0 3 3 0 016 0z"></path>
        </svg>
      </Title>

      {open && (
        <List open={open}>
          {loading && <div>로딩중</div>}
          {error && <div>에러 발생!</div>}
          {data && data.response.length ? (
            data.response.map((item, index) => (
              <div key={index} onClick={onClickFunc} data-value={item[value]}>
                {item[value]}
              </div>
            ))
          ) : (
            <div>None yet</div>
          )}
        </List>
      )}
      {open && <Mask onClick={close} />}
    </Wrap>
  );
};

export default Panel;
