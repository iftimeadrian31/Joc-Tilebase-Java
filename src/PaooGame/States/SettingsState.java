package PaooGame.States;


import PaooGame.Database;
import PaooGame.Graphics.CustomFont;
import PaooGame.KeyPress;
import PaooGame.MusicPlayer;
import PaooGame.Tiles.Tile;

import java.awt.*;

/*! \public class SettingsState extends State
    \brief Implementeaza notiunea de settings pentru joc.

    Aici setarile vor trebui salvate/incarcate intr-un/dintr-un fisier/baza de date sqlite.
 */
public class SettingsState extends State
{
    /// atribut folosit pentru transparenta scrisului
    int k;
    /// atribut folosit pentru alegerea butoanelor 1-> w a s d  iar 0-> sageti
    private static int controls=0;
    /// atribut folosit pentru blocarea muzicii
    private static int music=0;
    /// atribut folosit pentru identificarea butonului selectat
    private int button_selected;
    /// atribut folosit pentru timerul modificarii butonului selectat
    private int timer;
    /*! \fn public SettingsState()
    \brief Constructorul de initializare al clasei.

 */
    public SettingsState()
    {
        button_selected=1;
        timer=0;
        k=0;
    }

    /*! \fn public void Update()
        \brief Actualizeaza starea setarilor.
     */
    @Override
    public void Update()
    {
        if(k<255)
            k=k+3;
        if(timer>0)
            timer--;
        /// se realizeaza animatie de trecere prin butoane
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
            button_selected=3;
        }
        if(button_selected==4)
        {
            button_selected=1;
        }
        /// s-a selectat butonul de schimbat tastele de miscare
        if ((KeyPress.enter_pressed == true)&&(button_selected==1)) {
            KeyPress.enter_pressed = false;
            if(controls==1)
                controls=0;
            else
            {
                controls=1;
            }

        }/// s-a selectat butonul de inchis si pornit muzica
        if ((KeyPress.enter_pressed == true)&&(button_selected==2)) {
            KeyPress.enter_pressed = false;
            if(music==1) {
                music = 0;
                if(this.previousState.soundtrack!=null)
                    this.previousState.soundtrack.close();
            }
            else
            {
                music=1;
                this.previousState.soundtrack = new MusicPlayer("music/menu.wav");
                this.previousState.soundtrack.play();
            }

        }
        /// s-a selectat butonul de back si s-au salvat setarile in baza de date
        if ((KeyPress.enter_pressed == true)&&(button_selected==3)) {
            KeyPress.enter_pressed = false;
            SetState(previousState,this);
            Database data;
            data=new Database();
            data.saveSettings(controls,music);

        }
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran setarile.

        \param g Contextul grafic in care trebuie sa deseneze starea setarilor pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {
        Tile.menu.DrawMenu(g, 0, 0);
        CustomFont.display(g, "Settings", 34F, 315, 70, new Color(0,0,0,k), 0);

        g.setColor(new Color(102, 51, 0));
        if(button_selected==1)
            g.setColor(Color.yellow);
        g.fillRoundRect(312,150,180,50,15, 15);
        if(controls==1)
            CustomFont.display(g, "Controls: w a s d", 17F, 315, 185, new Color(0,0,0,255), 0);
        else
            CustomFont.display(g, "Controls: arrows", 17F, 315, 185, new Color(0,0,0,255), 0);

        g.setColor(new Color(102, 51, 0));
        if(button_selected==2)
            g.setColor(Color.yellow);
        g.fillRoundRect(312,230,180,50,15, 15);
        if(music==1)
            CustomFont.display(g, "Music:ON", 17F, 325, 265, new Color(0,0,0,255), 0);
        else
            CustomFont.display(g, "Music:OFF", 17F, 325, 265, new Color(0,0,0,255), 0);
        g.setColor(new Color(102, 51, 0));
        if(button_selected==3)
            g.setColor(Color.yellow);
        g.fillRoundRect(312,310,180,50,15, 15);
        CustomFont.display(g, "Back", 34F, 340, 345, new Color(0,0,0,255), 0);


    }
    public static int getControls()
    {
        return controls;
    }
    public static int getMusic()
    {
        return music;
    }
    public static void setControls(int controls)
    {
        SettingsState.controls=controls;
    }
    public static void setMusic(int music)
    {
        SettingsState.music=music;
    }
}
