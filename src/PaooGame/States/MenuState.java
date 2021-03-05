package PaooGame.States;

import PaooGame.Database;
import PaooGame.Game;
import PaooGame.Graphics.Assets;
import PaooGame.Graphics.CustomFont;
import PaooGame.KeyPress;
import PaooGame.MusicPlayer;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.io.FileNotFoundException;

/*! \public class MenuState extends State
    \brief Implementeaza notiunea de menu pentru joc.
 */
public class MenuState extends State
{
    /// parametru folosit pentru radul de transparenta
   private int k;
   /// butonul selectat din meniu
   private int button_selected;
   /// timerul pentru schimbarea butoanelor
   private int timer;
    /*! \fn public MenuState()
        \brief Constructorul de initializare al clasei.
     */
    public MenuState()
    {
        Database.getSettings();
        k=0;
        button_selected=1;
        timer=0;
            ///Apel al constructorului clasei de baza.
    }
    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a meniului.
     */
    @Override
    public void Update() throws FileNotFoundException {
        /// se verifica daca muzica este pornita din setari
        if(SettingsState.getMusic()==1) {
                if(soundtrack==null) {
                    soundtrack = new MusicPlayer("music/menu.wav");
                    soundtrack.play();
                }
            }
        if(SettingsState.getMusic()==0)
            {
                if(soundtrack!=null)
                {
                    soundtrack.close();
                }
        }
        if(k<255)
            k=k+3;
        if(timer>0)
            timer--;
        /// se receptioneaza butoanele pt selectarea in meniu
        if(KeyPress.s_pressed==true)
        {
            if(timer==0) {
                button_selected++;
                timer=30;
            }
        }
        if(KeyPress.w_pressed==true)
        {
            if(timer==0) {
                button_selected--;
                timer=30;
            }
        }
        if(button_selected==0)
        {
            button_selected=5;
        }
        if(button_selected==6)
        {
            button_selected=1;
        }
        /// butonul de play este selectat
        if ((KeyPress.enter_pressed == true)&&(button_selected==1)) {
            if(soundtrack!=null)
                soundtrack.close();
            KeyPress.enter_pressed=false;
            SetState(new PlayState(),this);
            k=255;
            Tile.menu.setX(10000);

        }
        /// butonul de load game este selectat
        if ((KeyPress.enter_pressed == true)&&(button_selected==2)) {
            KeyPress.enter_pressed=false;
            SetState(new SaveState(),this);
            k=255;
            Tile.menu.setX(10000);

        }
        /// butonul de settings este selectat
        if ((KeyPress.enter_pressed == true)&&(button_selected==3)) {
            KeyPress.enter_pressed=false;
            SetState(new SettingsState(),this);
            k=255;
            Tile.menu.setX(10000);

        }
        if ((KeyPress.enter_pressed == true)&&(button_selected==4)) {
            KeyPress.enter_pressed=false;
            SetState(new ControlsState(),this);
            k=255;
            Tile.menu.setX(10000);

        }
        /// butonul de exit este selectat
        if ((KeyPress.enter_pressed == true)&&(button_selected==5)) {
            Game.GetInstance("The Lumber-man", 800, 600).StopGame();
        }
    }



    @Override
        /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a meniului de start.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    public void Draw(Graphics g) {
        /// se deseneaza titlul jocului
            Tile.menu.DrawMenu(g, 0, 0);
            CustomFont.display(g, "Lumber-man", 34F, 320, 40, new Color(0,0,0,k), 0);
        CustomFont.display(g, "The Space Paradox", 36F, 260, 70, new Color(0,0,0,k), 0);
        /// se deseneaza butoanele si textul din ele si se schimba culoarea butonului selectat
        g.setColor(new Color(102, 51, 0));
            if(button_selected==1)
                g.setColor(Color.yellow);
            g.fillRoundRect(312,130,180,40,15, 15);
            CustomFont.display(g, "Start", 30F, 360, 160, new Color(0,0,0,255), 0);

            g.setColor(new Color(102, 51, 0));
            if(button_selected==2)
                g.setColor(Color.yellow);
            g.fillRoundRect(312,190,180,40,15, 15);
            CustomFont.display(g, "Load", 30F, 360, 220, new Color(0,0,0,255), 0);

            g.setColor(new Color(102, 51, 0));
            if(button_selected==3)
                g.setColor(Color.yellow);
            g.fillRoundRect(312,250,180,40,15, 15);
            CustomFont.display(g, "Settings", 30F, 330, 280, new Color(0,0,0,255), 0);

            g.setColor(new Color(102, 51, 0));
            if(button_selected==4)
                g.setColor(Color.yellow);
            g.fillRoundRect(312,310,180,40,15, 15);
            CustomFont.display(g, "Controls", 30F, 330, 340, new Color(0,0,0,255), 0);

            g.setColor(new Color(102, 51, 0));
            if(button_selected==5)
                g.setColor(Color.yellow);
            g.fillRoundRect(312,370,180,40,15, 15);
            CustomFont.display(g, "Exit", 30F, 360, 400, new Color(0,0,0,255), 0);
    }
}
