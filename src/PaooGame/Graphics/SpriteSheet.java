package PaooGame.Graphics;

import java.awt.image.BufferedImage;

/*! \public class SpriteSheet
    \brief Clasa retine o referinta catre o imagine formata din dale (sprite sheet)

    Metoda crop() returneaza o dala de dimensiuni fixe (o subimagine) din sprite sheet
    de la adresa (x * latimeDala, y * inaltimeDala)
 */
public class SpriteSheet
{
    private BufferedImage       spriteSheet;              /*!< Referinta catre obiectul BufferedImage ce contine sprite sheet-ul.*/
    private static final int    tileWidth   = 48;   /*!< Latimea unei dale din sprite sheet.*/
    private static final int    tileHeight  = 48;   /*!< Inaltime unei dale din sprite sheet.*/

    /*! \fn public SpriteSheet(BufferedImage sheet)
        \brief Constructor, initializeaza spriteSheet.

        \param img Un obiect BufferedImage valid.
     */
    public SpriteSheet(BufferedImage buffImg)
    {
            /// Retine referinta catre BufferedImage object.
        spriteSheet = buffImg;
    }

    /*! \fn public BufferedImage cropBackground(int x, int y,int w ,int h)
        \brief Returneaza un obiect BufferedImage ce contine o subimage (dala).

        Subimaginea este localizata avand ca referinta punctul din stanga sus.

        \param x numarul dalei din sprite sheet pe axa x.
        \param y numarul dalei din sprite sheet pe axa y.
        \param w latimea pozei
        \param h inatlimea pozei
     */
    public BufferedImage cropBackground(int x, int y,int w ,int h)
    {
        /// Subimaginea (dala) este regasita in sprite sheet specificad coltul stanga sus
        /// al imaginii si apoi latimea si inaltimea (totul in pixeli). Coltul din stanga sus al imaginii
        /// se obtine inmultind numarul de ordine al dalei cu dimensiunea in pixeli a unei dale.
        return spriteSheet.getSubimage(x * tileWidth, y * tileHeight, w, h);

    }
    /*! \fn public BufferedImage cropMenu(int x, int y)
    \brief Returneaza un obiect BufferedImage ce contine o subimage (dala).

    Subimaginea este localizata avand ca referinta punctul din stanga sus.

    \param x numarul dalei din sprite sheet pe axa x.
    \param y numarul dalei din sprite sheet pe axa y.

 */
    public BufferedImage cropMenu(int x, int y)
    {
        /// Subimaginea (dala) este regasita in sprite sheet specificad coltul stanga sus
        /// al imaginii si apoi latimea si inaltimea (totul in pixeli). Coltul din stanga sus al imaginii
        /// se obtine inmultind numarul de ordine al dalei cu dimensiunea in pixeli a unei dale.
        return spriteSheet.getSubimage(x * tileWidth, y * tileHeight, 800, 600);

    }
    /*! \fn public BufferedImage cropBoss(int x, int y)
    \brief Returneaza un obiect BufferedImage ce contine o subimage (dala).

    Subimaginea este localizata avand ca referinta punctul din stanga sus.

    \param x numarul dalei din sprite sheet pe axa x.
    \param y numarul dalei din sprite sheet pe axa y.

 */
    public BufferedImage cropBoss(int x, int y)
    {
        /// Subimaginea (dala) este regasita in sprite sheet specificad coltul stanga sus
        /// al imaginii si apoi latimea si inaltimea (totul in pixeli). Coltul din stanga sus al imaginii
        /// se obtine inmultind numarul de ordine al dalei cu dimensiunea in pixeli a unei dale.
        return spriteSheet.getSubimage(x * 192, y * 144, 192, 144);

    }
    /*! \fn public BufferedImage cropButton(int x, int y)
    \brief Returneaza un obiect BufferedImage ce contine o subimage (dala).

    Subimaginea este localizata avand ca referinta punctul din stanga sus.

    \param x numarul dalei din sprite sheet pe axa x.
    \param y numarul dalei din sprite sheet pe axa y.

 */
    public BufferedImage cropButton(int x, int y)
    {
        /// Subimaginea (dala) este regasita in sprite sheet specificad coltul stanga sus
        /// al imaginii si apoi latimea si inaltimea (totul in pixeli). Coltul din stanga sus al imaginii
        /// se obtine inmultind numarul de ordine al dalei cu dimensiunea in pixeli a unei dale.
        return spriteSheet.getSubimage(x * 152, y * 55, 152, 55);

    }
    /*! \fn public BufferedImage crop(int x, int y)
    \brief Returneaza un obiect BufferedImage ce contine o subimage (dala).

    Subimaginea este localizata avand ca referinta punctul din stanga sus.

    \param x numarul dalei din sprite sheet pe axa x.
    \param y numarul dalei din sprite sheet pe axa y.

 */
    public BufferedImage crop(int x, int y)
    {
            /// Subimaginea (dala) este regasita in sprite sheet specificad coltul stanga sus
            /// al imaginii si apoi latimea si inaltimea (totul in pixeli). Coltul din stanga sus al imaginii
            /// se obtine inmultind numarul de ordine al dalei cu dimensiunea in pixeli a unei dale.
        return spriteSheet.getSubimage(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
    }
}
