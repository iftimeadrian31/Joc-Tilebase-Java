package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

/*! \public class SnowTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip zapada.
 */
public class SnowTile extends Tile
{
    /*! \fn public GrassTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public SnowTile(int id)
    {
            /// Apel al constructorului clasei de baza
        super(Assets.snow[0], id);
        switch(id)
        {
            case 16: img=Assets.snow[0];
                break;
            case 17: img=Assets.snow[1];
                break;
            case 18: img=Assets.snow[2];
                break;
            case 19: img=Assets.snow[3];
                break;
            case 20: img=Assets.snow[4];
                break;


        }
    }
}
