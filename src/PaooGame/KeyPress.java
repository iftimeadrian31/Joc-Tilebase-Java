package PaooGame;


import PaooGame.States.PlayState;
import PaooGame.States.SettingsState;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*! \public class KeyPress  implements KeyListener
    \brief clasa care se ocupa de inputul la tastatura
 */
public class KeyPress  implements KeyListener {

    public static boolean w_pressed;
    public static boolean a_pressed;
    public static boolean d_pressed;
    public static boolean s_pressed;
    public static boolean space_pressed;
    public static boolean enter_pressed;
    public static boolean p_pressed;
    /*! \public KeyPress()
    \brief constructorul
 */
    public KeyPress()
    {
        /// initial nici un buton nu este apasat
        p_pressed=false;
        w_pressed=false;
        a_pressed=false;
        d_pressed=false;
        s_pressed=false;
        space_pressed=false;
        enter_pressed=false;



    }

    public void keyTyped(KeyEvent e) {

    }
    /*! \public void keyPressed(KeyEvent e)
\brief functie care seteaza butonul atunci cand tasta este apasata
*/
    public void keyPressed(KeyEvent e) {
        /// se seteaza variabila corespondenta literei apasate
        if(SettingsState.getControls()==1) {
            /// miscarea se realizeaza pe w a s d
            if (e.getKeyCode() == KeyEvent.VK_D) {
                d_pressed = true;
                a_pressed=false;
            }
            if (e.getKeyCode() == KeyEvent.VK_P) {
                p_pressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_W) {
                w_pressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_A) {
                a_pressed = true;
                d_pressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                s_pressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                space_pressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                enter_pressed = true;
            }
        }
        else
        {
            /// miscarea se realizeaza pe sageti
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                d_pressed = true;
                a_pressed=false;
            }
            if (e.getKeyCode() == KeyEvent.VK_P) {
                p_pressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                w_pressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                a_pressed = true;
                d_pressed=false;
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                s_pressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                space_pressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                enter_pressed = true;
            }
        }
    }
    /*! \public void keyReleased(KeyEvent e)
\brief functie care deselecteaza butonul atunci cand tasta este eliberata
*/
    public void keyReleased(KeyEvent e) {
        if(SettingsState.getControls()==1) {
            /// miscarea se realizeaza cu w a s d
            if (e.getKeyCode() == KeyEvent.VK_W) {
                w_pressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_P) {
                p_pressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_A) {
                a_pressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                s_pressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                d_pressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                space_pressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                enter_pressed = false;
            }
        }
        else
        {
            /// miscarea se realizeaza cu sagetile
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                d_pressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                w_pressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                a_pressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                s_pressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                space_pressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                enter_pressed = false;
            }
        }

    }
}