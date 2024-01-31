import React, { useContext } from 'react'
import './styles.css'
import { faCircle, faX } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import GameContext from '../../GameContext'

const BoardCell = ({rowIndex, colIndex, cell}) => {

  const {setGame} = useContext(GameContext)

  const handleShoot = async()=> {
    try {
      const response = await fetch(`http://localhost:8080/api/game/shoot?row=${rowIndex}&col=${colIndex}`, {
        method: 'GET',
        credentials: 'include'
    })
      const data = await response.json()
      setGame(data)

    }catch(error) {
      console.error('An error occurred: ', error)
    }
  }

  return (
    <div
     onClick={handleShoot}
     className={`flex items-center justify-center block w-full h-full border border-black bg-gray-200 ${!cell.isHit && 'hover:bg-gray-400 cursor-pointer'}`}>
        {(cell.isHit && !cell.ship) && (
          <FontAwesomeIcon icon={faCircle} />
        )}

       {(cell.isHit && cell.ship) && (
          <FontAwesomeIcon icon={faX} className='text-red-500 font-bold' />
        )}


    </div>
  )
}

export default BoardCell