package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

/*! \public class StoneTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip piatra.
 */
public class StoneTile extends Tile
{
    /*! \fn public GrassTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public StoneTile(int id)
    {
            /// Apel al constructorului clasei de baza
        super(Assets.stone, id);
        switch(id)
        {
            case 12: img=Assets.stone;
                break;
            case 13: img=Assets.stone2;
                break;
            case 14: img=Assets.stone3;
                break;

        }
    }
}
