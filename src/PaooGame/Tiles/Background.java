package PaooGame.Tiles;

import PaooGame.Graphics.Assets;

import java.awt.image.BufferedImage;

/*! \public class Background extends Tile
    \brief Abstractizeaza notiunea de background.
 */
public class Background extends Tile
{
    /*! \fn public SoilTile(int id)
        \brief Constructorul de initializare al clasei

     */
    public Background(int id, BufferedImage img)
    {
        super(img, id);
    }

}
