package com.backend.backend.controllers;

import com.backend.backend.models.Game;
import com.backend.backend.services.IBattleshipGameService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/game")
@CrossOrigin(origins = {"http://localhost:5173", "http://127.0.0.1:5173/"}, maxAge = 3600, allowCredentials = "true")
public class GameController {
    private final IBattleshipGameService<Game> gameService;


    @Autowired
    public GameController(IBattleshipGameService<Game> gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/start")
    public ResponseEntity<Game> startNewGame(HttpSession session) {
        Game currentGame = gameService.startNewGame();

        if(currentGame == null) {
            return ResponseEntity.status(422).body(null);
        }

        session.setAttribute("currentGame", currentGame);
        return ResponseEntity.ok(currentGame);
    }

    @GetMapping("/current")
    public ResponseEntity<Game> getCurrentGame(HttpSession session) {
        Game currentGame = (Game) session.getAttribute("currentGame");
        if (currentGame == null) {
            currentGame = gameService.startNewGame();
            if(currentGame == null) {
                return ResponseEntity.status(422).body(null);
            }
            session.setAttribute("currentGame", currentGame);
        }
        return ResponseEntity.ok(currentGame);
    }

    @GetMapping("/shoot")
    public ResponseEntity<Game> handleShoot(HttpSession session, @RequestParam int row, @RequestParam int col) {
        Game currentGame = (Game) session.getAttribute("currentGame");
        if (currentGame == null) {
            currentGame = gameService.startNewGame();

        } else {
            gameService.handleShoot(currentGame, row, col);
        }

        if(currentGame == null) {
            return ResponseEntity.status(422).body(null);
        }
        session.setAttribute("currentGame", currentGame);
        return ResponseEntity.ok(currentGame);
    }
}