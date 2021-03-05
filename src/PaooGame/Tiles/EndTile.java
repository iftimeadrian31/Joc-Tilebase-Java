package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

/*! \public class EndTile extends Tile
    \brief Abstractizeaza notiunea de bloc pentru a trece la nivelul urmator.
 */
public class EndTile extends Tile
{
    /*! \fn public GrassTile(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public EndTile(int id)
    {
            /// Apel al constructorului clasei de baza
        super(Assets.end, id);
    }
}
