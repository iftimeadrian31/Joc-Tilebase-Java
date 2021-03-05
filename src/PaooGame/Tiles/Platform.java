package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

/*! \public class Platform extends Tile
    \brief Abstractizeaza notiunea de dala de tip platforma.
 */
public class Platform extends Tile
{
    /*! \fn public SoilTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */

    public Platform(int id)
    {
        super(Assets.platform, id);
        switch(id)
        {
            case 8: img=Assets.platform;
                break;
            case 9: img=Assets.platform2;
                break;
            case 10: img=Assets.platform3;
                break;

        }
    }

}
