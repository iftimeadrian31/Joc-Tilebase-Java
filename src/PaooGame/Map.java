package PaooGame;

import PaooGame.Entity.*;
import PaooGame.Graphics.CollisionObjects;
import PaooGame.Graphics.CustomFont;
import PaooGame.States.PlayState;
import PaooGame.Tiles.Tile;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.System.exit;

/*! \public class Map
    \brief clasa care se ocupa de obtinerea pozitiei dalelor , mobilor si playerului din fisierele cu harti si desenarea lor pe fereastra
 */
public class Map {
    /// atribut care se foloseste la miscarea elementelor pe axa x
    private static int l;
    /// atribut care se foloseste la miscarea elementelor pe axa y
    private static int k;
    /// matrice in care se stocheaza harta
    private int M[][];
    /// vector de referinte catre dalele intregi
    public static CollisionObjects[] Tiles;
    /// vector de referinte catre dalele tip platforma
    public static CollisionObjects[] HalfTiles;
    /// referinta catre dala de trecere la nivel urmator
    public static CollisionObjects endTile;
    /// atribut care dicteaza daca harta se mai poate misca in stanga
    private static int stop_stanga = 0;
    /// atribut care dicteaza daca harta se mai poate misca in dreapta
    private static int stop_dreapta = 0;
    /// vector de referinte catre dalele de tip apa
    public static CollisionObjects[] waterTiles;
    /// atribut care ilustreaza numarul de dale pe lungime
    private int lungime;
    /// atribut care ilustreaza numarul de dale pe inaltime
    private int inaltime;
    /// atribut care ilustreaza numarul de dale intregi
    private int count;
    /// atribut care ilustreaza numarul de dale de tip platforma
    private int halfcount;
    /// atribut care ilustreaza numarul de dale de tip apa
    private int watercount;
    /// atribut care ilustreaza numarul de monezi
    private int coinscount;
    /// atribut care ilustreaza numarul de monstrii
    private int monsterscount;

    /*! \public Map(int level)
    \brief constructor in care mai intai se calculeaza numarul de elemente de orice fel si apoi se creeaza limitele de coliziune pentru fiecare
 */
    public Map(int level) throws FileNotFoundException {
        l = 0;
        k = 0;
        count = 0;
        halfcount = 0;
        coinscount = 0;
        monsterscount = 0;
        watercount = 0;
        this.lungime = 0;
        this.inaltime = 0;
        M = new int[100][100];
        Scanner input = null;
        /// se selecteaza fisierul in care este reprezentata harta nivelului curent
        if (level == 1) {
            try {
                input = new Scanner(new File("res/levels/level_1.txt"));
            } catch (Exception e) {
                System.out.println("level 1 lipsa");
                exit(0);
            }
        } else {
            if (level == 2) {
                try {
                    input = new Scanner(new File("res/levels/level_2.txt"));
                } catch (Exception e) {
                    System.out.println("level 2 lipsa");
                    exit(0);
                }
            } else {
                if (level == 3) {
                    try {
                        input = new Scanner(new File("res/levels/level_3.txt"));
                    } catch (Exception e) {
                        System.out.println("level 3 lipsa");
                        exit(0);
                    }
                } else {
                    if (level == 4) {
                        try {
                            input = new Scanner(new File("res/levels/level_4.txt"));
                        } catch (Exception e) {
                            System.out.println("level 4 lipsa");
                            exit(0);
                        }
                    } else {
                        try {
                            input = new Scanner(new File("res/levels/level_5.txt"));
                        } catch (Exception e) {
                            System.out.println("level 5 lipsa");
                            exit(0);
                        }
                    }
                }

            }
        }
        /// se incepe citirea fisierului (a matricei) care poate avea un numar aleatoriu de linii si coloane
        while (input.hasNextLine()) {
            lungime = 0;
            String line = input.nextLine();
            Scanner lineScanner = new Scanner(line);
            while (lineScanner.hasNext()) {
                String token = lineScanner.next();
                M[inaltime][lungime] = Integer.parseInt(token);
                /// se calculeaza numarul de obiecte si entitati de fiecare fel pentru a sti marimile vectorilor
                if (M[inaltime][lungime] != 0) {
                    if (M[inaltime][lungime] == 8 || M[inaltime][lungime] == 9 || M[inaltime][lungime] == 10)
                        halfcount++;
                    else {
                        if (M[inaltime][lungime] == 50)
                            coinscount++;

                        else {
                            if (M[inaltime][lungime] == 100 || M[inaltime][lungime] == 101 || M[inaltime][lungime] == 102 || M[inaltime][lungime] == 103)
                                monsterscount++;
                            else {
                                if (M[inaltime][lungime] == 150) {
                                    PlayState.setPlayer(new Player(0));
                                    Entity.Portal = new Portal(200);

                                } else {
                                    if (M[inaltime][lungime] == 15) {
                                        endTile = new CollisionObjects();
                                    } else {
                                        if (M[inaltime][lungime] == 32 || M[inaltime][lungime] == 33)
                                            watercount++;
                                        else
                                            count++;
                                    }
                                }
                            }

                        }
                    }
                }
                /// este calculata lungimea si latimea matricei
                lungime++;
            }
            inaltime++;
            lineScanner.close();

        }
        input.close();
        /// sunt creati vectorii de obiecte si entitati de lungimi citite mai sus care sunt utili functiei collision
        Tiles = new CollisionObjects[count];
        HalfTiles = new CollisionObjects[halfcount];
        waterTiles = new CollisionObjects[watercount];
        for (int i = 0; i < count; i++) {
            Tiles[i] = new CollisionObjects();
        }
        for (int i = 0; i < halfcount; i++) {
            HalfTiles[i] = new CollisionObjects();
        }
        for (int i = 0; i < watercount; i++) {
            waterTiles[i] = new CollisionObjects();
        }
        Entity.Coins = new Coin[coinscount];
        Entity.Monsters = new Entity[monsterscount];
        Entity.hearts = new Heart[4];
    }

    public static int getStop_Dreapta() {
        return stop_dreapta;
    }

    public static int getStop_Stanga() {
        return stop_stanga;
    }

    public static int getL() {
        return l;
    }

    public static void setL(int l) {
        Map.l = l;
    }

    /*! \public void CreateMap(Graphics g)
\brief functie care deseneaza harta si entitatile
*/
    public void CreateMap(Graphics g) {
        int x;
        int y;
        int t = 0;
        int h = 0;
        int e = 0;
        int m = 0;
        int w = 0;
        for (int i = 0; i < inaltime; ++i) {
            for (int j = 0; j < lungime; ++j) {
                if (M[i][j] != 0) {
                    /// este calculata pozitia la care se afla blocurile si obiecte
                    /// in functie de valoarea initiala din matrice si pozitia playerului
                    x = j * Tile.TILE_WIDTH - l;
                    y = i * Tile.TILE_HEIGHT - k;
                    /// in functie id-urile din matrice , sunt instantiate obiectele si desenate la pozitiile calculate mai sus
                    if (M[i][j] == 8 || M[i][j] == 9 || M[i][j] == 10) {
                        HalfTiles[h].setX(x);
                        HalfTiles[h].setY(y);
                        h++;
                        //g.fillRect(x,y,48,24);
                    } else if (M[i][j] == 50) {
                        if (Entity.Coins[e] == null)
                            Entity.Coins[e] = new Coin(0);
                        Entity.Coins[e].Draw(g, x, y);
                        e++;
                    } else if (M[i][j] == 100) {
                        if (Entity.Monsters[m] == null)
                            Entity.Monsters[m] = new Dummy(0, x, y);
                        if (Entity.Monsters[m].getDead() == 0)
                            Entity.Monsters[m].Draw(g, x, y);
                        m++;
                    } else if (M[i][j] == 101) {
                        if (Entity.Monsters[m] == null)
                            Entity.Monsters[m] = new Lizard(0, x, y);
                        if (Entity.Monsters[m].getDead() == 0)
                            Entity.Monsters[m].Draw(g, x, y);
                        m++;
                    } else if (M[i][j] == 102) {
                        if (Entity.Monsters[m] == null)
                            Entity.Monsters[m] = new Wolf(0, x, y);
                        if (Entity.Monsters[m].getDead() == 0)
                            Entity.Monsters[m].Draw(g, x, y);
                        m++;
                    } else if (M[i][j] == 103) {
                        if (Entity.Monsters[m] == null)
                            Entity.Monsters[m] = new Dragon(0, x, y);
                        if (Entity.Monsters[m].getDead() == 0)
                            Entity.Monsters[m].Draw(g, x, y);
                        m++;
                    } else if (M[i][j] == 150) {
                        if (Entity.Portal == null)
                            Entity.Portal = new Portal(0);
                        PlayState.getPlayer().Draw(g, x, y);
                        Entity.Portal.Draw(g, x - 48, y - 48);
                    } else if (M[i][j] == 15) {
                        endTile.setX(x);
                        endTile.setY(y);
                    } else if (M[i][j] == 32 || M[i][j] == 33) {
                        waterTiles[w].setX(x);
                        waterTiles[w].setY(y);
                        w++;
                    } else {
                        Tiles[t].setX(x);
                        Tiles[t].setY(y);
                        t++;
                    }

                    /// aici este realizata desenarea vectorilor de blocuri si jumatati de blocuri
                    if (M[i][j] < 34)
                        Tile.tiles[M[i][j]].Draw(g, x, y);
                    if (M[i][j] == 33 && M[i - 1][j] == 0)
                        Tile.tiles[32].Draw(g, x, y - 12);
                }
            }
        }
        /// desenarea inimilor care reprezinta viata playerului
        for(int i=0;i<4;i++)
        {
            if(Entity.hearts[i]==null)
                Entity.hearts[i]=new Heart(1);
            Entity.hearts[i].Draw(g, 24*i,0);
        }
        /// desenarea textului care spune numarul monezilor ramase
        if(coinscount>0)
            CustomFont.display(g, Integer.toString(coinscount)+" coins remaining", 15F, 650, 12, Color.yellow, 0);
        else
            CustomFont.display(g, "Portal Opened", 15F, 650, 12, Color.yellow, 0);
        CustomFont.display(g,"Score: "+Integer.toString(PlayState.getTotal_Score()), 15F, 650, 30, Color.RED, 0);
    }
    /*! \public void Update()
\brief functie care realizeaza modificarea pozitiei elementelor , blocarea iesirii de pe ecran si miscarea camerei
*/
    public void Update()
    {
        /// se calculeaza care sunt blocurile cele mai din dreapta si blocurile cele mai din stanga
        int min=100000;
        int max=0;
        for(CollisionObjects t : Map.Tiles)
        {
            if(t.getX()<min) {
                min = t.getX();
            }
            if(t.getX()>max)
            {
                max=t.getX();
            }
        }
        if(min>0)
        {
            for(Entity e:Entity.Monsters)
                if(e!=null)
                    e.stop=true;
            stop_dreapta=1;

        }
        else
        {
            stop_dreapta=0;
        }
        /// miscarea hartii se opreste cand nu mai exista blocuri mai in stanga sau mai in dreapta
        if(max<760)
        {

            for(Entity e:Entity.Monsters)
                if(e!=null)
                    e.stop=true;
            stop_stanga=1;
        }
        else
        {
            /// atunci cand playerul se apropie de coltul ecranului , se mentine pozitia lui relativa la ecran
            if(PlayState.getPlayer().getEntityX()==640||PlayState.getPlayer().getEntityX()==641) {
                for(Entity e:Entity.Monsters)
                    if(e!=null)
                        e.stop=true;
                l += 2;
                PlayState.getPlayer().setEntityX(638);
                PlayState.getPlayer().setVelX(0);
            }
            stop_stanga=0;
        }
        if (PlayState.getPlayer().getAttack() == 0 && PlayState.getPlayer().getHP() != 0 ) {
            if(stop_dreapta==0) {
                if (KeyPress.a_pressed == true)
                    l -= 2;
            }
            if(stop_stanga==0) {
                if (KeyPress.d_pressed == true)
                    l += 2;
            }
        }
        /// cand playerul ajunge la limita de sus respectiv jos se opreste miscarea lui relativa fata de ecran
        if(PlayState.getPlayer().getEntityY()>=514) {
            PlayState.getPlayer().setEntityY(512);
            k += 2;
        }
        if(PlayState.getPlayer().getEntityY()<=48) {
            PlayState.getPlayer().setEntityY ( 50);
            k -= 3;
        }
    }

    public int getCoinscount() {
        return coinscount;
    }

    public void setCoinscount(int coinscount) {
        this.coinscount=coinscount;
    }

    public int getMonsterscount() {
        return monsterscount;
    }

    public void setMonsterscount(int monsterscount) {
        this.monsterscount=monsterscount;
    }
}
