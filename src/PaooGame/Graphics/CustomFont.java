package PaooGame.Graphics;


import java.awt.*;

import java.io.File;
import java.io.IOException;

/*! \public class CustomFont
    \brief Clasa folosita pentru a afisa text in casuta ecranului
 */
public class CustomFont {
    /// atribut care reprezinta fontul pentru scris
    private static Font font = null;


    /*! \public static void setFont(String file)
    \brief functie care modifica fontul
 */
    public static void setFont(String file) throws IOException, FontFormatException {

        font=Font.createFont(Font.TRUETYPE_FONT, new File(file));


    }


    /*! \public static void display(Graphics g, String text, float size, int posx, int posy, Color color, int style)
\brief functie care modifica fontul


        \param g interpretorul grafic.
        \param text textul care sa fie afisat.
        \param size marimea textului.
        \param posx coordonata pe axa x a textului.
        \param posy coordonata pe axa y a textului.
        \param color culoarea textului.
        \param style stilul textului.

*/
    public static void display(Graphics g, String text, float size, int posx, int posy, Color color, int style){

        font = font.deriveFont(style, size);

        g.setFont(font);

        g.setColor(color);

        g.drawString(text, posx, posy);

    }


    /*! \public static Dimension center(Graphics g, String text, float size, int style)
\brief functie care modifica fontul


        \param g interpretorul grafic.
        \param text textul care sa fie afisat.
        \param size marimea textului.
        \param style stilul textului.

*/
    public static Dimension center(Graphics g, String text, float size, int style){

        font = font.deriveFont(style, size);

        FontMetrics metrics = g.getFontMetrics(font);

        Dimension screen = new Dimension(800,600);

        int x = (screen.width - metrics.stringWidth(text)) / 2;

        int y = (screen.height - metrics.getHeight()) / 2 + metrics.getAscent();

        return new Dimension(x, y);

    }

}