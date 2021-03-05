package PaooGame.States;


import PaooGame.Graphics.CustomFont;
import PaooGame.KeyPress;

import java.awt.*;

/*! \public class AboutState extends State
    \brief Implementeaza notiunea de lista de butoane
 */
public class ControlsState extends State
{
    int k;
    /*! \fn public ControlsState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

     */
    public ControlsState()
    {
        k=0;
            ///Apel al constructorului clasei de baza.
    }
    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a meniu about.
     */
    @Override
    public void Update()
    {
        if(k<255)
            k=k+3;

        /// buton de back
        if (KeyPress.enter_pressed == true) {
            KeyPress.enter_pressed = false;
            SetState(previousState,this);
        }
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a meniu about.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {
        ///desenare titlu meniu
        g.setColor(Color.blue);
        g.fillRect(0,0,1000,1000);
        CustomFont.display(g, "Controls", 34F, 315, 70, new Color(0,0,0,k), 0);

        ///desenare scris
        g.setColor(new Color(102, 51, 0));
        CustomFont.display(g, "butoanele pentru miscare sunt w a s d sau sagetile", 17F, 240, 150, new Color(0,0,0,255), 0);
        CustomFont.display(g, "butonul de selectare in meniu este enter", 17F, 280, 180, new Color(0,0,0,255), 0);
        CustomFont.display(g, "butonul pentru atac este space", 17F, 300, 210, new Color(0,0,0,255), 0);
        CustomFont.display(g, "butonul de save este p", 17F, 320, 240, new Color(0,0,0,255), 0);
            g.setColor(Color.yellow);
        g.fillRoundRect(312,310,180,50,15, 15);
        CustomFont.display(g, "Back", 34F, 330, 345, new Color(0,0,0,255), 0);
    }
}
