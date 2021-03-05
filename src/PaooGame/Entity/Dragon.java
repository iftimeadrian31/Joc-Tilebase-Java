package PaooGame.Entity;

import PaooGame.Database;
import PaooGame.Graphics.Assets;
import PaooGame.Graphics.CollisionObjects;
import PaooGame.KeyPress;
import PaooGame.Map;
import PaooGame.States.PlayState;

import java.awt.*;

/*! \public class Dragon extends Entity
    \brief Abstractizeaza notiunea de entitate de tip dragon.
 */
public class Dragon extends Entity
{
    /// atribut folosit miscarea pe axa X
    private int VelX;
    /// atribut folosit pozitionarea initiala pe harta
    boolean start=true;
    /// atribut folosit selectarea pozei
    private int frame=4;
    /// atribut folosit pentru timerul trecerii de la o poza la alta
    private int frameDelay=0;
    /// atribut folosit pentru actiunea de atac
    private int attack=0;
    /// atribut folosit pentru actiunea de atingere
    private int hit=0;

    public Dragon(int id, int x, int y)
    {
            super(Assets.dragon[4], id);
                VelX=0;
                facing=0;
                ENTITY_HEIGHT=144;
                ENTITY_WIDTH=124;
                health=4;
    }
    @Override
    public void Draw(Graphics g, int x, int y)
    {
        /// daca entitatea inca nu este desenat se va desena la pozitia din harta, altfel este desenat in functie de valorile EntityX si EntityY
        if(EntityX==0&&EntityY==0&&start==true) {
            start = false;
            EntityX = x;
            EntityY = y-96;

        }
            g.drawImage(Assets.dragon[frame], EntityX, EntityY, ENTITY_WIDTH+64, ENTITY_HEIGHT, null);

    }
    @Override
    public void Update() {
        /// se scurge timpul de invulnerabilitate
        if(invul!=0)
        {
           invul--;
        }
        ///se verifica daca dragonul poate ataca
        atac();
        /// animatie de atac
        if(attack==1) {
            frameDelay++;
            if (frameDelay >= 20) {
                frame++;
                if (frame == 5) {
                    frame = 0;
                }
                frameDelay = 0;
            }
        }
        else
        {
            frame=4;
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
    /*! \fn private void atac()
 \brief realizeaza actiunea de atac .

 */
    private void atac()
    {
        /// timerul pt atac creste si dupa o perioada de aprox secunda incepe animatia de atac
        if(hit==50) {
            attack = 1;
        }
        /// se verifica daca playerul se afla in perimetrul dragonului pt a incepe atacul
        if(hit==75){
            if(PlayState.getPlayer().getInvul()==0&&PlayState.getPlayer().getHP()!=0) {
                PlayState.getPlayer().setInvul(50);
                PlayState.getPlayer().setAtins(1);
                PlayState.getPlayer().setFrame(0);
                if(PlayState.getPlayer().getHP()!=0)
                    PlayState.getPlayer().setHP(PlayState.getPlayer().getHP()-1);
                Entity.hearts[3-PlayState.getPlayer().getI()].change=1;
                PlayState.getPlayer().setI(PlayState.getPlayer().getI()+1);
                hit=0;
            }
        }
        if(PlayState.getPlayer().EntityX<=EntityX+170)
        {
            hit++;
        }
        else
        {
            hit=0;
            if(frame==4)
                attack=0;
        }
    }



}
