package PaooGame.Graphics;

import java.awt.*;

/*! \public class CollisionObjects
    \brief Clasa in care este abstractizata ideea de coliziune pentru tiles.
 */
public class CollisionObjects {
    protected int x;                                                /*!< pozitia pe axa x a unei dale.*/
    protected int y;                                                /*!< pozitia pe axa y a unei dale.*/
    public static final int TILE_WIDTH  = 48;                       /*!< Latimea unei dale.*/
    public static final int TILE_HEIGHT = 48;                       /*!< Inaltimea unei dale.*/


    public CollisionObjects()
    {

    }
    /*! \fn public int getX()
\brief realizeaza obtinerea coordonatei pe axa x .

*/
    public int getX()
    {
        return x;
    }
    /*! \fn public int getY()
\brief realizeaza obtinerea coordonatei pe axa y .

*/
    public int getY()
    {
        return y;
    }
    /*! \fn public void setY(int y)
\brief realizeaza modificarea coordonatei pe axa y .

*/
    public void setY(int y) {
        this.y = y;
    }
    /*! \fn public void setX(int x)
\brief realizeaza modificarea coordonatei pe axa x .

*/

    public void setX(int x) {
        this.x = x;
    }
    /*! \fn public Rectangle getBounds()
\brief realizeaza obtinerea limitelor blocurilor intregi .

*/
    public Rectangle getBounds(){
        return new Rectangle(x,y,TILE_WIDTH,TILE_HEIGHT);
    }
    /*! \fn public Rectangle getBounds2()
\brief realizeaza obtinerea limitelor blocurilor de tip platforma .

*/
    public Rectangle getBounds2(){
        return new Rectangle(x,y,TILE_WIDTH,TILE_HEIGHT/2);
    }

}
