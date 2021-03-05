package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

/*! \public class WaterTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip apa.
 */
public class WaterTile extends Tile
{
    /*! \fn public GrassTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public WaterTile(int id)
    {
            /// Apel al constructorului clasei de baza
        super(Assets.water[0], id);
        switch(id) {
            case 32:
                img = Assets.water[0];
                break;
            case 33:
                img = Assets.water[1];
                break;
        }
    }
}
