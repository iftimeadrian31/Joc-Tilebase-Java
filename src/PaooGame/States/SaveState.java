package PaooGame.States;


import PaooGame.Database;
import PaooGame.Graphics.CustomFont;
import PaooGame.KeyPress;
import PaooGame.MusicPlayer;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.io.FileNotFoundException;

/*! \public class SaveState extends State
    \brief Implementeaza notiunea de load pentru joc.

    Aici setarile vor trebui salvate/incarcate intr-un/dintr-un fisier/baza de date sqlite.
 */
public class SaveState extends State
{

    /// atribut care arata butonul selectat
    private int button_selected;
    /// atribut care e folosit pentru timerul modificarii butonului selectat
    private int timer;
    /// atribut folosit pentru transparenta scrisului
    private int k;
    /// atribut folosit pentru level
    private static int level=1;
    /// atribut folosit pentru a stoca scorul
    private static int score=0;
    /// referinta care playstate
    private PlayState play;
    /*! \fn public SaveState()
    \brief Constructorul de initializare al clasei.

 */
    public SaveState() throws FileNotFoundException {
        play=new PlayState();
        Database.LoadGame();
        level=play.getLevel();
        score=play.getTotal_Score();
        button_selected=1;
        timer=0;
        k=0;
    }

    /*! \fn public void Update()
        \brief Actualizeaza starea setarilor.
     */
    @Override
    public void Update() throws FileNotFoundException {
        if(k<255)
            k=k+3;
        if(timer>0)
            timer--;
        // secventa care ilustreaza selectarea butoanelor din meniu
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
            button_selected=2;
        }
        if(button_selected==3)
        {
            button_selected=1;
        }
        /// s-a selectat nivelul de incarcat , scorul este modificat si se face trecerea state-ului in starea play
        if ((KeyPress.enter_pressed == true)&&(button_selected==1)) {
            KeyPress.enter_pressed = false;
            if(previousState.soundtrack!=null)
                previousState.soundtrack.close();
            SetState(play,this);
            k=255;
            Tile.menu.setX(10000);

        }
        /// buton de back
        if ((KeyPress.enter_pressed == true)&&(button_selected==2)) {
            KeyPress.enter_pressed = false;
            SetState(previousState,this);
        }
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran meniul de load.

        \param g Contextul grafic in care trebuie sa deseneze starea setarilor pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {
        ///desenare titlu meniu
        Tile.menu.DrawMenu(g, 0, 0);
        CustomFont.display(g, "Save", 34F, 315, 70, new Color(0,0,0,k), 0);

        ///desenare butoane si scrisul de pe ele
        g.setColor(new Color(102, 51, 0));
        if(button_selected==1)
            g.setColor(Color.yellow);
        g.fillRoundRect(312,150,180,150,15, 15);
        CustomFont.display(g, "Load", 34F, 330, 185, new Color(0,0,0,255), 0);
        CustomFont.display(g, "level: "+level, 34F, 330, 215, new Color(0,0,0,255), 0);
        CustomFont.display(g, "score: "+score, 34F, 330, 245, new Color(0,0,0,255), 0);

        g.setColor(new Color(102, 51, 0));
        if(button_selected==2)
            g.setColor(Color.yellow);
        g.fillRoundRect(312,310,180,50,15, 15);
        CustomFont.display(g, "Back", 34F, 330, 345, new Color(0,0,0,255), 0);
    }
    public int getLevel()
    {
        return level;
    }
    public static void setLevel(int level)
    {
        SaveState.level=level;
    }
    public int getScore()
    {
        return score;
    }
    public static void setScore(int score)
    {
        SaveState.score=score;
    }
}
