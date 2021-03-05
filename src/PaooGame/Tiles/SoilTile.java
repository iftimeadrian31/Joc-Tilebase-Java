package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

/*! \public class SoilTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip sol/pamant.
 */
public class SoilTile extends Tile
{
    /*! \fn public SoilTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public SoilTile(int id)
    {
        super(Assets.soil, id);
                switch(id) {
                    case 11:
                        img = Assets.soil;
                        break;
                    case 21:
                        img = Assets.dirt[0];
                        break;
                    case 22:
                        img = Assets.dirt[1];
                        break;
                    case 23:
                        img = Assets.dirt[2];
                        break;
                    case 24:
                        img = Assets.dirt[3];
                        break;
                    case 25:
                        img = Assets.dirt[4];
                        break;
                    case 26:
                        img = Assets.dirt[5];
                        break;
                    case 27:
                        img = Assets.dirt[6];
                        break;
                    case 28:
                        img = Assets.dirt[7];
                        break;
                }
    }

}
