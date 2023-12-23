import React from 'react';
import BoardRow from '../BoardRow';

const GameBoard = ({ board }) => {
  return (
    <div className='grid grid-rows-10 border-2 border-black w-96 h-96 mb-5 w-11/12 sm:w-4/5 md:w-1/2 lg:w-1/3'>
      {board.grid.map((row, rowIndex) => (
        <BoardRow key={rowIndex} rowIndex={rowIndex} row={row}/>
      ))}
    </div>
  );
};

export default GameBoard;