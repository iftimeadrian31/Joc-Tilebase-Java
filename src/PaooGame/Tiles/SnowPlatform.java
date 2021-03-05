package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

/*! \public class SnowPlatform extends Tile
    \brief Abstractizeaza notiunea de dala de tip platforma.
 */
public class SnowPlatform extends Tile
{
    /*! \fn public SoilTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public SnowPlatform(int id)
    {
        super(Assets.snowPlatform[0], id);
        switch(id) {
            case 29:
                img = Assets.snowPlatform[0];
                break;
            case 30:
                img = Assets.snowPlatform[1];
                break;
            case 31:
                img = Assets.snowPlatform[2];
                break;
        }
    }

}
