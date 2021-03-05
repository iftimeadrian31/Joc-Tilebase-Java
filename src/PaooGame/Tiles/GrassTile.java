package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

/*! \public class GrassTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip iarba.
 */
public class GrassTile extends Tile
{
    /*! \fn public GrassTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public GrassTile(int id)
    {
        super(Assets.grass, id);
        switch(id)
        {
            case 1: img=Assets.grass;
                    break;
            case 2: img=Assets.grass2;
                break;
            case 3: img=Assets.grass3;
                break;
            case 4: img=Assets.grass4;
                break;
            case 5: img=Assets.grass5;
                break;
            case 6: img=Assets.grass6;
                break;
            case 7: img=Assets.grass7;
                break;
        }
    }
}
