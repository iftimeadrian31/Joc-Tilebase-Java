package PaooGame.Entity;

import PaooGame.Database;
import PaooGame.Graphics.Assets;
import PaooGame.Graphics.CollisionObjects;
import PaooGame.KeyPress;
import PaooGame.Map;

import java.awt.*;

/*! \public class Dummy extends Entity
    \brief Abstractizeaza notiunea de entitate de tip dummy.
 */
public class Dummy extends Entity
{
    /// atribut folosit miscarea pe axa X
    private int VelX;
    /// atribut folosit pozitionarea initiala pe harta
    boolean start=true;
    /// atribut folosit selectarea pozei
    private int frame=0;
    /// atribut folosit pentru timerul trecerii de la o poza la alta
    private int frameDelay=0;

    public Dummy(int id, int x, int y)
    {
        super(Assets.dummy[0], id);
                VelX=0;
                facing=0;
                ENTITY_HEIGHT=48;
                ENTITY_WIDTH=48;
                health=1;
    }
    @Override
    public void Draw(Graphics g, int x, int y)
    {
        /// daca entitatea inca nu este desenat se va desena la pozitia din harta, altfel este desenat in functie de valorile EntityX si EntityY
        if(EntityX==0&&EntityY==0&&start==true) {
            start = false;
            EntityX = x;
            EntityY = y;
        }
            g.drawImage(Assets.dummy[frame], EntityX, EntityY, ENTITY_WIDTH, ENTITY_HEIGHT, null);

    }
    @Override
    public void Update() {
        /// se scurge timpul de invulnerabilitate
        if(invul!=0)
        {
           invul--;
        }
        frameDelay++;
        if (frameDelay >= 6) {
            frame++;
            if (frame == 4) {
                frame = 0;
            }
            frameDelay = 0;
        }

        EntityX+=VelX;
        Movement();
        Collision();

    }
    /*! \fn private void Movement()
\brief realizeaza miscarea mobului .

*/
    private void Movement()
    {
        ///aceste verificari ajuta la modificarea vitezei mobului in cazul miscarii hartii care la randul ei este determinata de miscarea playerului adica de apasarea tastelor wasd
        if(KeyPress.a_pressed==true&&stop==false) {
            if(facing==0)
                VelX=2;
            else
                VelX=2;
        }
        if(KeyPress.d_pressed==true&&stop==false) {
            if(facing==0)
                VelX=-2;
            else
                VelX=-2;
        }
        if((KeyPress.a_pressed==false&&KeyPress.d_pressed==false)||(stop==true))
        {
            if(facing==0)
                VelX=0;
            else
                VelX=0;
        }
        stop=false;

    }
    /*! \fn private void Collision()
\brief realizeaza coliziunea mobului cu peretii .

*/
    private void Collision()
    {
        /// se verifica daca mobul se afla in aer si se intoarce pt a nu cadea
        int air=0;
        for(CollisionObjects t: Map.Tiles)
        {
            if(t!=null) {
                if (getBoundsLeft().intersects(t.getBounds())) {
                    VelX=2;
                    facing=1;

                }
                if (getBoundsRight().intersects(t.getBounds())) {
                    VelX=-2;
                    facing=0;
                }
                if(facing==0)
                {
                    if (getBoundsBottomLeft().intersects(t.getBounds())) {
                        air=1;
                    }
                }
                if(facing==1)
                {
                    if (getBoundsBottomRight().intersects(t.getBounds())) {
                        air=1;
                    }
                }

            }

        }
        if(air==0)
        {
            VelX=-VelX;
            if(facing==0)
            {
                facing=1;
            }
            else
            {
                facing=0;
            }
        }
    }



}
