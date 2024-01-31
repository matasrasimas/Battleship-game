import React from 'react';
import BoardRow from '../BoardRow';

const GameBoard = ({ board }) => {

  const letters = ['', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'];

  return (
    <div className='grid grid-rows-11 border-2 border-black w-[98%] h-96 mb-5 w-11/12 sm:w-4/5 md:w-1/2 lg:w-1/3'>

      <div className='grid grid-cols-11'>
        {letters.map((letter, index) => {
         return <div
            key={index}
            className='flex items-center justify-center block w-full h-full border border-black bg-gray-400 font-bold'>
            {letter}
          </div>
        })}
      </div>

      {board.grid.map((row, rowIndex) => (
        <BoardRow key={rowIndex} rowIndex={rowIndex} row={row} />
      ))}
    </div>
  );
};

export default GameBoard;