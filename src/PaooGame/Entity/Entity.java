package PaooGame.Entity;



import java.awt.*;
import java.awt.image.BufferedImage;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;

/*! \public abstract class Entity
    \brief Abstractizeaza notiunea de entitate.
 */
public abstract class Entity
{

    /// referinta de tip player
    public static Player player       = new Player(0);
    /// Vector de referinte de tip hearts
    public static Heart[] hearts;
    /// Vector de referinte de tip coins
    public static Entity[] Coins;
    /// Vector de referinte pentru monstrii
    public static Entity[] Monsters;
    /// referinta pentru dragon(Boss)
    public static Entity[] Dragon;
    /// referinta de tip portal
    public static Entity Portal;

    /// parametru folosit pentru sincronizarea mobilor cu harta
    public boolean stop=false;
    /// parametru pentru pozitia X
    protected int EntityX;
    /// parametru pentru pozitia Y
    protected int EntityY;
    /// parametru pentru partea in care se uita mobul
    protected int facing=1;
    /// parametru folosit pentru atingerile cu playerul
    protected int atins=0;
    /// parametru in care este trecuta viata entitatii
    protected int health=1;
    /// parametru care dicteaza timpul invulnerabilitatii dupa ce a fost atins
    protected int invul=0;
    /// parametru care dicteaza cand mobul este mort
    protected int dead=0;


    public int ENTITY_WIDTH  = 48;                       /*!< Latimea unei entitatii.*/
    public int ENTITY_HEIGHT = 48;                       /*!< Inaltimea unei entitati.*/

    public BufferedImage img;                            /*!< Imaginea aferenta tipului de entitate.*/
    protected final int id;/*!< Id-ul unic aferent tipului de entitate.*/

    /*! \fn param image Imaginea corespunzatoare entitatii.
        \param id Id-ul entitatii.
     */
    public Entity(BufferedImage image, int idd)
    {
        img = image;
        id = idd;
    }

    /*! \fn public abstract void Update()
        \brief Actualizeaza proprietatile entitatii.
     */
    public abstract void Update();


    /*! \fn public abstract void Draw(Graphics g, int x, int y)
        \brief Deseneaza in fereastra entitatea.

        \param g Contextul grafic in care sa se realizeze desenarea
        \param x Coordonata x in cadrul ferestrei unde sa fie desenata entitatea
        \param y Coordonata y in cadrul ferestrei unde sa fie desenata entitatea
     */

    public abstract void Draw(Graphics g, int x, int y);


    /*! \fn public int GetId()
        \brief Returneaza id-ul entitatii.
     */
    public int GetId()
    {
        return id;
    }
    /*! \fn public Rectangle getBounds()
        \brief Returneaza limitele entitatii.
     */
    public Rectangle getBounds(int Width,int Height){
        return new Rectangle(EntityX,EntityY,Width,Height);
    }
    /*! \fn public Rectangle getBoundsTop()
        \brief Returneaza limita de sus a entitatii.
     */
    public Rectangle getBoundsTop(){
        return new Rectangle(EntityX+5,EntityY,ENTITY_WIDTH-10,5);
    }
    /*! \fn public Rectangle getBoundsBottom()
        \brief Returneaza limita de jos a entitatii.
     */
    public Rectangle getBoundsBottom(){
        return new Rectangle(EntityX+5,EntityY+ENTITY_HEIGHT-5,ENTITY_WIDTH-10,5);
    }
    /*! \fn public Rectangle getBoundsBottomLeft()
        \brief Returneaza limita din stanga jos a entitatii(folosita pentru a se intoarce cand se gaseste limita podelei.
     */
    public Rectangle getBoundsBottomLeft(){
        return new Rectangle(EntityX-10,EntityY+ENTITY_HEIGHT,5,5);
    }
    /*! \fn public Rectangle getBoundsBottomRight()
        \brief Returneaza limita din dreapta jos a entitatii(folosita pentru a se intoarce cand se gaseste limita podelei.
     */
    public Rectangle getBoundsBottomRight(){
        return new Rectangle(EntityX+ENTITY_WIDTH+5,EntityY+ENTITY_HEIGHT,5,5);
    }
    /*! \fn public Rectangle getBoundsLeft()
        \brief Returneaza limita din stanga a entitatii.
     */
    public Rectangle getBoundsLeft(){
        return new Rectangle(EntityX,EntityY+5,5,ENTITY_HEIGHT-10);
    }
    /*! \fn public Rectangle getBoundsRight()
        \brief Returneaza limita din dreapta a entitatii.
     */
    public Rectangle getBoundsRight(){
        return new Rectangle(EntityX+ENTITY_WIDTH-5,EntityY+5,5,ENTITY_HEIGHT-10);
    }
    /*! \fn public int getDead()
        \brief Returneaza parametrul dead al entitatii(folosit pentru a disparea dupa ce moare).
     */
    public int getDead()
    {
        return dead;
    }
    /*! \fn public int setDead(int dead)
        \brief Seteaza coordonata x a entitatii.
     */
    public void setDead(int dead)
    {
        this.dead=dead;
    }
    /*! \fn public int setEntityX(int EntityX)
        \brief Seteaza coordonata x a entitatii.
     */
    public void setEntityX(int EntityX) {
        this.EntityX=EntityX;
    }
    /*! \fn public int setEntityY(int EntityY)
        \brief Seteaza coordonata y a entitatii.
     */
    public void setEntityY(int EntityY) {
        this.EntityY=EntityY;
    }
    /*! \fn public int getEntityX()
        \brief Returneaza coordonata x a entitatii.
     */
    public int getEntityX() {
        return EntityX;
    }
    /*! \fn public int getEntityY(i)
        \brief Returneaza coordonata y a entitatii.
     */
    public int getEntityY() {
        return EntityY;
    }
}


