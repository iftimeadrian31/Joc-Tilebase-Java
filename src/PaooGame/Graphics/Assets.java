package PaooGame.Graphics;


import java.awt.image.BufferedImage;

import static java.lang.System.exit;

/*! \public class Assets
    \brief Clasa incarca fiecare element grafic necesar jocului.

 */
public class Assets
{
        /// Referinte imagini pentru player.
    public static BufferedImage player[]=new BufferedImage[56];
    /// Referinte imagini pentru moneda.
    public static BufferedImage coin[]=new BufferedImage[9];
    /// Referinte imagini pentru lup.
    public static BufferedImage wolf[]=new BufferedImage[10];
    /// Referinte imagini pentru soparla.
    public static BufferedImage lizard[]=new BufferedImage[12];
    /// Referinte imagini pentru inima.
    public static BufferedImage heart[]=new BufferedImage[5];
    /// Referinte imagini pentru portal.
    public static BufferedImage portal[]=new BufferedImage[8];
    /// Referinte imagini pentru dummy.
    public static BufferedImage dummy[]=new BufferedImage[4];
    /// Referinte imagini pentru dragon.
    public static BufferedImage dragon[]=new BufferedImage[5];
    /// Referinte imagini pentru pamant.
    public static BufferedImage soil;
    /// Referinte imagini pentru iarba.
    public static BufferedImage grass;
    /// Referinte imagini pentru iarba2.
    public static BufferedImage grass2;
    /// Referinte imagini pentru iarba3.
    public static BufferedImage grass3;
    /// Referinte imagini pentru iarba4.
    public static BufferedImage grass4;
    /// Referinte imagini pentru iarba5.
    public static BufferedImage grass5;
    /// Referinte imagini pentru iarba6.
    public static BufferedImage grass6;
    /// Referinte imagini pentru iarba7.
    public static BufferedImage grass7;
    /// Referinte imagini pentru platforma.
    public static BufferedImage platform;
    /// Referinte imagini pentru platforma2.
    public static BufferedImage platform2;
    /// Referinte imagini pentru platforma3.
    public static BufferedImage platform3;
    /// Referinte imagini pentru piatra.
    public static BufferedImage stone;
    /// Referinte imagini pentru piatra2.
    public static BufferedImage stone2;
    /// Referinte imagini pentru piatra3.
    public static BufferedImage stone3;
    /// Referinte imagini pentru zapada.
    public static BufferedImage snow[]=new BufferedImage[5];
    /// Referinte imagini pentru pamant pentru zapada.
    public static BufferedImage dirt[]=new BufferedImage[8];
    /// Referinte imagini pentru platforma cu zapada.
    public static BufferedImage snowPlatform[]=new BufferedImage[3];
    /// Referinte imagini pentru apa.
    public static BufferedImage water[]=new BufferedImage[2];
    /// Referinte imagini pentru bloc de final.
    public static BufferedImage end;
    /// Referinte imagini pentru aer.
    public static BufferedImage air;
    /// Referinte imagini pentru background.
    public static BufferedImage background[]=new BufferedImage[4];
    /// Referinte imagini pentru butoane.
    public static BufferedImage buttons[]=new BufferedImage[6];
    /// Referinte imagini pentru background meniu.
    public static BufferedImage menu;





    /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

        Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
        sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */
    public static void Init()
    {
        SpriteSheet sheet=null;
        SpriteSheet bosssheet=null;
        SpriteSheet Background1=null;
        SpriteSheet Background2=null;
        SpriteSheet Background3=null;
        SpriteSheet Background4=null;
        SpriteSheet Menu=null;
        SpriteSheet Button=null;
            /// Se creaza temporar obiecte SpriteSheet initializate prin intermediul clasei ImageLoader
        try {
            sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/PaooGameSpriteSheet.png"));
            bosssheet = new SpriteSheet(ImageLoader.LoadImage("/textures/Boss.png"));
            Background1 = new SpriteSheet(ImageLoader.LoadImage("/textures/Background1.png"));
            Background2 = new SpriteSheet(ImageLoader.LoadImage("/textures/Background3.png"));
            Background3 = new SpriteSheet(ImageLoader.LoadImage("/textures/Background4.png"));
            Background4 = new SpriteSheet(ImageLoader.LoadImage("/textures/Background5.png"));
            Menu = new SpriteSheet(ImageLoader.LoadImage("/textures/Menu.png"));
            Button = new SpriteSheet(ImageLoader.LoadImage("/textures/buttons.png"));
        }
        catch (Exception e)
        {
            System.out.println("fisier assets lipsa");
            exit(0);
        }
        try{
            /// Se obtin subimaginile corespunzatoare elementelor necesare.
            grass = sheet.crop(0, 0);
            grass2 = sheet.crop(1, 0);
            grass3 = sheet.crop(2, 0);
            grass4 = sheet.crop(0, 1);
            grass5 = sheet.crop(2, 1);
            grass6 = sheet.crop(3, 2);
            grass7 = sheet.crop(5, 2);
            soil = sheet.crop(1, 1);
            platform = sheet.crop(6, 0);
            platform2 = sheet.crop(7, 0);
            platform3 = sheet.crop(8, 0);
            stone = sheet.crop(1, 3);
            stone2 = sheet.crop(2, 3);
            stone3 = sheet.crop(3, 3);
            end = sheet.crop(9, 2);
            snow[0] = sheet.crop(0, 25);
            snow[1] = sheet.crop(1, 25);
            snow[2] = sheet.crop(2, 25);
            snow[3] = sheet.crop(6, 25);
            snow[4] = sheet.crop(0, 26);
            dirt[0] = sheet.crop(3, 25);
            dirt[1] = sheet.crop(4, 25);
            dirt[2] = sheet.crop(5, 25);
            dirt[3] = sheet.crop(7, 25);
            dirt[4] = sheet.crop(8, 25);
            dirt[5] = sheet.crop(9, 25);
            dirt[6] = sheet.crop(1, 26);
            dirt[7] = sheet.crop(2, 26);
            snowPlatform[0] = sheet.crop(3, 26);
            snowPlatform[1] = sheet.crop(4, 26);
            snowPlatform[2] = sheet.crop(5, 26);
            water[0] = sheet.crop(6, 26);
            water[1] = sheet.crop(7, 26);
            for (int i = 0; i < 26; i++) {
                if (i == 0)
                    player[0] = sheet.crop(0, 8);
                if (i > 0 && i < 7)
                    player[i] = sheet.crop(i - 1, 13);
                if (i == 7)
                    player[i] = sheet.crop(5, 14);
                if (i > 7 && i < 14)
                    player[i] = sheet.crop(13 - i, 19);
                if (i > 13 && i < 20)
                    player[i] = sheet.crop(i - 14, 12);
                if (i > 19)
                    player[i] = sheet.crop(25 - i, 18);
            }
            for (int i = 0; i < 12; i++) {
                if (i < 6)
                    player[26 + i] = sheet.crop(i, 8);
                else
                    player[26 + i] = sheet.crop(11 - i, 14);
            }

            for (int i = 0; i < 12; i++) {
                if (i < 6)
                    player[38 + i] = sheet.crop(i, 9);
                else
                    player[38 + i] = sheet.crop(11 - i, 15);
            }
            for (int i = 0; i < 6; i++) {
                if (i < 3)
                    player[50 + i] = sheet.crop(i, 10);
                else
                    player[50 + i] = sheet.crop(8 - i, 16);
            }
            for (int i = 0; i < 8; i++) {
                coin[i] = sheet.crop(i, 6);
            }
            coin[8] = sheet.crop(0, 7);
            for (int i = 0; i < 10; i++) {
                if (i < 5)
                    wolf[i] = sheet.crop(i, 22);
                else
                    wolf[i] = sheet.crop(10 - i, 23);
            }
            for (int i = 0; i < 12; i++) {
                if (i < 6)
                    lizard[i] = sheet.crop(i, 20);
                else
                    lizard[i] = sheet.crop(11 - i, 21);
            }
            for (int i = 0; i < 5; i++) {
                heart[i] = sheet.crop(i, 24);
            }
            background[0] = Background1.cropBackground(0, 0, 1900, 1000);
            background[1] = Background2.cropBackground(0, 0, 500, 290);
            background[2] = Background3.cropBackground(0, 0, 1800, 700);
            background[3] = Background4.cropBackground(0, 0, 450, 250);

            menu = Menu.cropMenu(0, 0);
            for (int i = 0; i < 6; i++) {
                if (i < 4)
                    buttons[i] = Button.cropButton(0, i);
                else
                    buttons[i] = Button.cropButton(1, i - 4);
            }
            for (int i = 0; i < 8; i++)
                portal[i] = sheet.crop(i, 27);
            air = sheet.crop(1, 7);
            for (int i = 0; i < 4; i++)
                dummy[i] = sheet.crop(i, 30);
            for (int i = 0; i < 5; i++) {
                dragon[i] = bosssheet.cropBoss(i, 0);
            }
        }
        catch (Exception e)
        {
            System.out.println("coordonatele unei poze se afla in afara sprite-sheet-ului");
            exit(0);
        }
    }
}
