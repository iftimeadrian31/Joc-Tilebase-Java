package PaooGame.States;

import PaooGame.Database;
import PaooGame.Entity.Entity;
import PaooGame.Entity.Player;
import PaooGame.Graphics.CustomFont;
import PaooGame.KeyPress;
import PaooGame.Map;
import PaooGame.MusicPlayer;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.io.FileNotFoundException;

import static java.lang.System.exit;

/*! \public class PlayState extends State
    \brief Implementeaza/controleaza jocul.
 */
public class PlayState extends State {
    /// atribut care spune levelul curent
    private static int level;
    private static Player player;  /*!< Referinta catre obiectul animat erou (controlat de utilizator).*/
    /// referinta care harta
    private static Map map;
    /// atribut care dicteaza obtinerea hartii
    private boolean MapStart;
    /// atribut pentru transparenta scrisului
    private int k;
    /// atribut folosit pentru trecerea de la un level la altul
    private static Boolean next = true;
    /// atribut care stocheaza scorul din prezent
    private static int total_score;
    /// atribut care stocheaza scorul de la inceputul levelului
    private static int last_score;

    /*! \fn public PlayState()
        \brief Constructorul de initializare al clasei
     */
    public PlayState() throws FileNotFoundException {
        level = 1;
        k = 255;
        player = Entity.player;
        MapStart = false;
        total_score = 0;
        last_score = 0;
    }


    /*! \fn public void Load(int level, int score)
    \brief functie care seteaza levelul si scorul incarcat
 */
    public void Load(int level, int score)
    {
        this.level=level;
        this.total_score=score;
    }
    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a jocului.
     */
    @Override
    public void Update()
    {
        /// se apeleaza functia update din obiectele create
        player.Update();
        map.Update();
        for (Entity e : Entity.Coins) {
            if (e != null)
                e.Update();
        }
        for (Entity e : Entity.Monsters) {
            if (e != null && e.getDead()==0) {
                e.Update();
            }
        }
        for (Entity e : Entity.hearts) {
            if (e != null) {
                e.Update();
            }
        }
        Entity.Portal.Update();
        /// butonul p este apasat , starea de joc este citita si salvata in baza de date
        if (KeyPress.p_pressed == true) {
            SaveState.setLevel(level);
            SaveState.setScore(last_score);
            Database data;
            data=new Database();
            data.saveGame(level,last_score);
        }
    }

    @Override
           /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a jocului cand ruleaza.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    public void Draw(Graphics g) throws FileNotFoundException {
        /// se deseneaza nivelul 1

        if(level==1)
        {
            if(next==true)
            {
                last_score=total_score;
                if(SettingsState.getMusic()==1) {
                    soundtrack = new MusicPlayer("music/level1.wav");
                    soundtrack.play();
                }
                k=255;
                map = new Map(1);
                next=false;
                MapStart=false;
            }
            Tile.background1.DrawBackground(g, 0, 0);

            map.CreateMap(g);
            if(MapStart==false)
            {
                /// atunci cand se trece la acest nivel , va aparea acest mesaj
                if (k > 1)
                {
                    k = k - 2;
                    CustomFont.display(g, "Introduction", 34F, 315, 70, new Color(239, 255, 0, k), 0);
                }
                else
                {
                    k=0;
                    MapStart = true;
                }
            }
        }
        /// se deseneaza nivelul 2
        else if(level==2)
            {
                if(next==true)
                {
                    last_score=total_score;
                    if(soundtrack!=null)
                    soundtrack.close();
                    if(SettingsState.getMusic()==1) {
                        soundtrack = new MusicPlayer("music/level1.wav");
                        soundtrack.play();
                    }
                    k=255;
                    map = new Map(2);
                    next=false;
                    MapStart=false;
                }
                Tile.background1.DrawBackground(g, 0, 0);
                map.CreateMap(g);
                if(MapStart==false)
                {
                    if (k > 1)
                    {
                        /// atunci cand se trece la acest nivel , va aparea acest mesaj
                        k = k - 2;
                        CustomFont.display(g, "A new dawn", 34F, 315, 70, new Color(239, 255, 0, k), 0);
                    }
                    else
                    {
                        k=0;
                        MapStart = true;
                    }
                }
            }
        /// se deseneaza nivelul 3
            else if(level==3)
                {

                    if(next==true)
                    {
                        last_score=total_score;
                        if(soundtrack!=null)
                        soundtrack.close();
                        if(SettingsState.getMusic()==1) {
                            soundtrack = new MusicPlayer("music/level3.wav");
                            soundtrack.play();
                        }
                        k=255;
                        map = new Map(3);
                        next=false;
                        MapStart=false;
                    }
                    Tile.background3.DrawBackground(g, 0, 0);
                    map.CreateMap(g);
                    if(MapStart==false)
                    {
                        if (k > 1)
                        {
                            /// atunci cand se trece la acest nivel , va aparea acest mesaj
                            k = k - 2;
                            CustomFont.display(g, "We have to go deeper", 34F, 315, 70, new Color(239, 255, 0, k), 0);
                        }
                        else
                        {
                            k=0;
                            MapStart = true;
                        }
                    }
                }
        /// se deseneaza nivelul 4
                else if(level==4)
                    {
                        if(next==true)
                        {
                            last_score=total_score;
                            if(soundtrack!=null)
                            soundtrack.close();
                            if(SettingsState.getMusic()==1) {
                                soundtrack = new MusicPlayer("music/level4.wav");
                                soundtrack.play();
                            }
                            k=255;
                            map = new Map(4);
                            next=false;
                            MapStart=false;
                        }
                        Tile.background4.DrawBackground(g, 0, 0);
                        map.CreateMap(g);
                        if(MapStart==false)
                        {
                            if (k > 1)
                            {
                                /// atunci cand se trece la acest nivel , va aparea acest mesaj
                                k = k - 2;
                                CustomFont.display(g, "Winter is here", 34F, 315, 70, new Color(239, 255, 0, k), 0);
                            }
                            else
                            {
                                k=0;
                                MapStart = true;
                            }
                        }
                    }
        /// se deseneaza nivelul 5
                    else if(level==5)
                        {

                            if(next==true)
                            {
                                last_score=total_score;
                                if(soundtrack!=null)
                                soundtrack.close();
                                if(SettingsState.getMusic()==1) {
                                    soundtrack = new MusicPlayer("music/level5.wav");
                                    soundtrack.play();
                                }
                                k=255;
                                map = new Map(5);
                                next=false;
                                MapStart=false;
                            }
                            Tile.background5.DrawBackground(g, 0, 0);
                            map.CreateMap(g);
                            if(MapStart==false)
                            {
                                if (k > 1)
                                {
                                    /// atunci cand se trece la acest nivel , va aparea acest mesaj
                                    k = k - 2;
                                    CustomFont.display(g, "Finally at peace", 34F, 315, 70, new Color(239, 255, 0, k), 0);
                                }
                                else
                                {
                                    k=0;
                                    MapStart = true;
                                }
                            }
                        }
        /// se deseneaza ecranul de finish
                        else if(level==6) {
                                player.setEntityX(10000);
                                if (k < 255)
                                    k = k + 3;
                                else
                                    k=255;
                                CustomFont.display(g, "Congratulation", 34F, 315, 320, new Color(0, 0, 0, k), 0);
                                if (k == 255) {
                                    next = true;
                                    if(soundtrack!=null)
                                    soundtrack.close();
                                    SetState(new MenuState(),this);
                                }
                            }
        /// daca playerul a murit , va aparea pe ecran mesajul si se face trecerea din playstate in menustate
        if(Entity.player.getHP()==0)
        {
            if(k<255)
                k=k+3;
            CustomFont.display(g, "You Died", 34F, 315, 70, new Color(255,0,0,k), 0);
            if(k==255)
            {
                next = true;
                if(soundtrack!=null)
                soundtrack.close();
                SetState(new MenuState(),this);
            }
        }
        if (KeyPress.p_pressed == true) {
            k=255;
            while(k>2) {
                    k = k - 1;
                    CustomFont.display(g, "Saved succesfully", 20F, 615, 45, new Color(239, 255, 0, k), 0);
                }
            KeyPress.p_pressed=false;
        }
    }
    /*! \fn public static Map getMap()
\brief functie care obtine harta curenta

*/
    public static Map getMap()
    {
        return map;
    }
    /*! \fn public void setMap(Map map)
\brief functie care modifica harta curenta

*/
    public void setMap(Map map)
    {
        PlayState.map=map;
    }
    /*! \fn public static int getTotal_Score()
\brief functie care obtine scorul curent

*/
    public static int getTotal_Score()
    {
        return total_score;
    }
    /*! \fn public static void setTotal_Score(int total_score)
\brief functie care modifica scorul total

*/
    public static void setTotal_Score(int total_score)
    {
        PlayState.total_score=total_score;
    }
    /*! \fn public static int getLevel()
\brief functie care obtine level-ul

*/
    public static int getLevel()
    {
        return level;
    }
    /*! \fn public static void setLevel(int level)
\brief functie care modifica level-ul

*/
    public static void setLevel(int level)
    {
        PlayState.level=level;
    }
    /*! \fn public static void setNext(Boolean next)
\brief functie care modifica atributul next

*/
    public static void setNext(Boolean next)
    {
        PlayState.next=next;
    }
    /*! \fn public static Player getPlayer()
\brief functie care obtine referinta spre player

*/
    public static Player getPlayer()
    {
        return player;
    }
    /*! \fn public static void setPlayer(Player player)
\brief functie care aduce referinta la playyer

*/
    public static void setPlayer(Player player)
    {
        PlayState.player=player;
    }

}
