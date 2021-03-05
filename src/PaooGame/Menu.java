package PaooGame;

import PaooGame.Graphics.Assets;
import PaooGame.Tiles.Tile;

/*! \public class Menu extends Tile
    \brief clasa care se ocupa de desenarea backround-ului meniului
 */
public class Menu extends Tile
{
    /*! \fn public SoilTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public Menu(int id)
    {
        super(Assets.menu, id);
    }

}
