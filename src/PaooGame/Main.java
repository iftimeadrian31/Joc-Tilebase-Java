package PaooGame;

import PaooGame.GameWindow.GameWindow;

import java.io.FileNotFoundException;

/*! \public class Main
    \brief clasa main
 */
public class Main
{
    public static void main(String[] args) throws FileNotFoundException {
        /// se obtine o instanta a obiectului Game care utilizeaza threaduri
        Game paooGame = Game.GetInstance("The Lumber-man", 800, 600);
        paooGame.StartGame();
    }
}
