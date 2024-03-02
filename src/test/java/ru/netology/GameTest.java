package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameTest {
    Game game = new Game();

    Player player1 = new Player(1, "Maria", 100);
    Player player2 = new Player(2, "Dmitry", 150);
    Player player3 = new Player(3, "Philipp", 50);
    Player player4 = new Player(3, "Max", 50);


    @Test
    public void shouldFindUnregisteredFirstPlayer() {

        game.register(player1);
        game.register(player2);
        game.register(player3);

        Assertions.assertThrows(NotRegisteredException.class, () -> {
            game.round("Alex", player3.getName());
        });
    }

    @Test
    public void shouldFindUnregisteredSecondPlayer() {

        game.register(player1);
        game.register(player2);
        game.register(player3);

        Assertions.assertThrows(NotRegisteredException.class, () -> {
            game.round(player2.getName(), "Anna");
        });
    }

    @Test
    public void FirstPlayerWins() {

        game.register(player2);
        game.register(player3);

        Assertions.assertEquals(1, game.round(player2.getName(), player3.getName()));
    }

    @Test
    public void SecondPlayerWin() {

        game.register(player1);
        game.register(player2);

        int actual = game.round("Maria", "Dmitry");
        int expected = 2;
        Assertions.assertEquals(expected, actual);

//        Assertions.assertEquals(2, game.round(player1.getName(), player2.getName()));
    }

    @Test
    public void whenDraw() {

        game.register(player3);
        game.register(player4);

        Assertions.assertEquals(0, game.round(player3.getName(), player4.getName()));
    }
}