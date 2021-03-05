package PaooGame.Tiles;

import PaooGame.Menu;
import PaooGame.Graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \public class Tile
    \brief Retine toate dalele intr-un vector si ofera posibilitatea regasirii dupa un id.
 */
public class Tile
{
    protected Graphics g;
    protected int x;
    protected int y;
    private boolean solid=true;
    public static Tile tiles[]          = new Tile[39];       /*!< Vector de referinte de tipuri de dale.*/

        /// De remarcat ca urmatoarele dale sunt statice si publice. Acest lucru imi permite sa le am incarcate
        /// o singura data in memorie

    public static Tile menu        = new Menu(0);


    public static Tile background1         = new Background(0, Assets.background[0]);
    public static Tile background3         = new Background(0, Assets.background[1]);
    public static Tile background4         = new Background(0, Assets.background[2]);
    public static Tile background5         = new Background(0, Assets.background[3]);
    public static Tile grassTile        = new GrassTile(1);     /*!< Dala de tip iarba*/
    public static Tile grassTile2        = new GrassTile(2);     /*!< Dala de tip iarba*/
    public static Tile grassTile3        = new GrassTile(3);     /*!< Dala de tip iarba*/
    public static Tile grassTile4        = new GrassTile(4);     /*!< Dala de tip iarba*/
    public static Tile grassTile5        = new GrassTile(5);     /*!< Dala de tip iarba*/
    public static Tile grassTile6        = new GrassTile(6);     /*!< Dala de tip iarba*/
    public static Tile grassTile7        = new GrassTile(7);     /*!< Dala de tip iarba*/
    public static Tile platformTile         = new Platform(8);      /*!< Dala de tip platforma*/
    public static Tile platformTile2         = new Platform(9);      /*!< Dala de tip platforma*/
    public static Tile platformTile3         = new Platform(10);      /*!< Dala de tip platforma*/
    public static Tile soilTile         = new SoilTile(11);      /*!< Dala de tip sol/pamant*/
    public static Tile stoneTile         = new StoneTile(12);      /*!< Dala de tip piatra*/
    public static Tile stoneTile2         = new StoneTile(13);      /*!< Dala de tip piatra*/
    public static Tile stoneTile3         = new StoneTile(14);      /*!< Dala de tip piatra*/
    public static Tile endTile         = new EndTile(15);      /*!< Dala de tip bloc de final*/
    public static Tile snowTile         = new SnowTile(16);      /*!< Dala de tip sol/pamant*/
    public static Tile snowTile2         = new SnowTile(17);      /*!< Dala de tip sol/pamant*/
    public static Tile snowTile3         = new SnowTile(18);      /*!< Dala de tip sol/pamant*/
    public static Tile snowTile4         = new SnowTile(19);      /*!< Dala de tip sol/pamant*/
    public static Tile snowTile5         = new SnowTile(20);      /*!< Dala de tip sol/pamant*/
    public static Tile soilTile2         = new SoilTile(21);      /*!< Dala de tip sol/pamant*/
    public static Tile soilTile3         = new SoilTile(22);      /*!< Dala de tip sol/pamant*/
    public static Tile soilTile4         = new SoilTile(23);      /*!< Dala de tip sol/pamant*/
    public static Tile soilTile5         = new SoilTile(24);      /*!< Dala de tip sol/pamant*/
    public static Tile soilTile6         = new SoilTile(25);      /*!< Dala de tip sol/pamant*/
    public static Tile soilTile7         = new SoilTile(26);      /*!< Dala de tip sol/pamant*/
    public static Tile soilTile8         = new SoilTile(27);      /*!< Dala de tip sol/pamant*/
    public static Tile soilTile9         = new SoilTile(28);      /*!< Dala de tip sol/pamant*/
    public static Tile snowPlatform         = new SnowPlatform(29);      /*!< Dala de tip sol/pamant*/
    public static Tile snowPlatform2         = new SnowPlatform(30);      /*!< Dala de tip sol/pamant*/
    public static Tile snowPlatform3         = new SnowPlatform(31);      /*!< Dala de tip sol/pamant*/
    public static Tile waterTile         = new WaterTile(32);      /*!< Dala de tip sol/pamant*/
    public static Tile waterTile2         = new WaterTile(33);      /*!< Dala de tip sol/pamant*/



    public static final int TILE_WIDTH  = 48;                       /*!< Latimea unei dale.*/
    public static final int TILE_HEIGHT = 48;                       /*!< Inaltimea unei dale.*/

    public BufferedImage img;                                    /*!< Imaginea aferenta tipului de dala.*/
    protected final int id;                                         /*!< Id-ul unic aferent tipului de dala.*/

    /*! \fn public Tile(BufferedImage texture, int id)
        \brief Constructorul aferent clasei.

        \param image Imaginea corespunzatoare dalei.
        \param id Id-ul dalei.
     */
    public Tile(BufferedImage image, int idd)
    {
        img = image;
        id = idd;
        tiles[id] = this;
    }
    public Tile()
    {
        id = 0;
    }

    /*! \fn public void Update()
        \brief Actualizeaza proprietatile dalei.
     */
    public void Update()
    {

    }

    /*! \fn public void Draw(Graphics g, int x, int y)
        \brief Deseneaza in fereastra dala.

        \param g Contextul grafic in care sa se realizeze desenarea
        \param x Coordonata x in cadrul ferestrei unde sa fie desenata dala
        \param y Coordonata y in cadrul ferestrei unde sa fie desenata dala
     */
    public void DrawBackground(Graphics g, int x, int y)
{
    /// Desenare dala
    g.drawImage(img, x, y, 800, 600, null);
}
    public void DrawMenu(Graphics g, int x, int y)
    {
        /// Desenare dala
        g.drawImage(img, x, y, 800, 600, null);
    }
    public void DrawButton(Graphics g, int x, int y)
    {
        /// Desenare dala
        g.drawImage(img, x, y, 185, 68, null);
    }
    public void Draw(Graphics g, int x, int y)
    {
        this.g=g;
        this.x=x;
        this.y=y;
        g.drawImage(img, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }

    /*! \fn public boolean IsSolid()
        \brief Returneaza proprietatea de dala solida (supusa coliziunilor) sau nu.
     */
    public boolean IsSolid()
    {
        return solid;
    }

    /*! \fn public int GetId()
        \brief Returneaza id-ul dalei.
     */
    public int GetId()
    {
        return id;
    }
    public Rectangle getBounds(){
        return new Rectangle(x,y,TILE_WIDTH,TILE_HEIGHT);
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }
}
