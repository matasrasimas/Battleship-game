import React, { useEffect, useState } from 'react'
import './styles.css'
import GameBoard from '../../components/GameBoard'
import GameContext from '../../GameContext'
import GameResultModal from '../../components/GameResultModal'

const Home = () => {

  const [game, setGame] = useState(null)
  const [isLoading, setIsLoading] = useState(true)

  const [showErrorMessage, setShowErrorMessage] = useState(false)


  useEffect(() => {

    const getCurrentGame = async() => {
        setIsLoading(true)
        try {
            const response = await fetch('http://localhost:8080/api/game/current', {
                method: 'GET',
                credentials: 'include',
                headers: { 'Content-Type': 'application/json' },
            })

            if(response.status == 422) {
                setShowErrorMessage(true)
            }
            const data = await response.json()
            setGame(data)
            setIsLoading(false)
            setShowErrorMessage(false)
        } catch(error) {
            console.error('An error occurred: ', error)
        }
    }

    getCurrentGame()
  }, [])

  const handleRestart = async() => {
    setIsLoading(true)
    try {
        const response = await fetch('http://localhost:8080/api/game/start', {
            method: 'GET',
            credentials: 'include',
            headers: { 'Content-Type': 'application/json' },
        })

        if(response.status == 422) {
            setShowErrorMessage(true)
        }
        const data = await response.json()
        setGame(data)
        setIsLoading(false)
        setShowErrorMessage(false)
    } catch(error) {
        console.error('An error occurred: ', error)
    }
  }


  return (

    <GameContext.Provider value={{setGame}}>
        {isLoading ? (
                <div className='flex flex-col gap-5 m-5'>
                    {showErrorMessage ? (
                        <div className='flex flex-col w-full items-center gap-5'>
                            <h1 className='game-hdr'>An error ocurred. Please try again.</h1>
                            <button
                                onClick={handleRestart}
                                className='restart-btn'>
                                    Retry
                            </button>

                        </div>
                    ) : (
                        <h1 className='game-hdr'>Loading...</h1>

                    )}
              </div>
        ) : (
            <div className='flex flex-col gap-5 m-5 w-full items-center content-center'>
                <div className='flex flex-col w-full items-center justify-center'>
                    <h1 className='game-hdr'>Battleship Game</h1>
                    <h2 className='game-subhdr'>Ships remaining: {game && game.ships.length}</h2>
                    <h2 className={`game-subhdr ${(game && game.shootsRemaining <= 5) && 'text-red-500'}`}>Shoots remaining: {game && game.shootsRemaining}</h2>
                    <h2 className='game-subhdr text-blue-800'>{game && game.shootResultMessage}</h2>
                </div>
                <div className='flex flex-col items-center w-full'>
                  <GameBoard board={game.computerBoard}/>
                  
                  

                  <button
                   onClick={handleRestart}
                   className='restart-btn'>Restart Game</button>
                </div>
                {game.status == 'WON' && (
                    <GameResultModal
                    status='WON'
                    header='You won!'
                    description='You have destroyed all ships!'
                    handleButtonClick={() => handleRestart()}/>
                    )}

            {game.status == 'LOST' && (
                <GameResultModal
                status='LOST'
                header='You lost!'
                description='You have ran out of shoots!'
                handleButtonClick={() => handleRestart()}/>
                )}   
            </div>
        )}


        

    </GameContext.Provider>

  )
}

export default Home