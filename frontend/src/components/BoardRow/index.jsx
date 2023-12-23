import React from 'react'
import './styles.css'
import BoardCell from '../BoardCell'

const BoardRow = ({rowIndex, row}) => {
  return (
    <div className='grid grid-cols-10'>
    {row.map((cell, colIndex) => (
      <BoardCell key={colIndex} rowIndex={rowIndex} colIndex={colIndex} cell={cell}/>
    ))}
  </div>
  )
}

export default BoardRow