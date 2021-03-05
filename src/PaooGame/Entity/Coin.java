package PaooGame.Entity;

import PaooGame.Graphics.Assets;

import java.awt.*;


/*! \public class Coin extends Entity
    \brief Abstractizeaza notiunea de entitate de tip moneda.
 */
public class Coin extends Entity
{

    /// atribut folosit pentru a selecta poza de animatie
    private int frame=0;
    /// atribut folosit pentru a dicta viteza de modificare a frame-ului
    private int frameDelay=0;
    /*! \fn public Coin(int id)
        \brief Constructorul de initializare al clasei

        \param id Id-ul dalei util in desenarea hartii.
     */
    public Coin(int id)
    {
        /// Apel al constructorului clasei de baza
        super(Assets.coin[0], id);
    }
    @Override
    public void Draw(Graphics g, int x, int y)
    {
            ///Daca obiectul nu a fost atins , se deseneaza
        if(atins==0){
            EntityX=x+12;
            EntityY=y;
            g.drawImage(Assets.coin[frame], EntityX, EntityY, 24, 24, null);
        }
            ///obiectul a fost atins
        else
        {
            EntityX=1000;
            EntityY=1000;
        }

    }
    @Override
    public void Update() {
        ///imaginea obiectului se schimba rapid dand impresia de miscare
        frameDelay++;
        if (frameDelay >= 3) {
            frame++;
            if (frame>8) {
                frame=0;
            }
            frameDelay = 0;
    }


    }




}
