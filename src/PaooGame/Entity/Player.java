package PaooGame.Entity;
import PaooGame.Database;
import PaooGame.Graphics.Assets;
import PaooGame.Graphics.CollisionObjects;
import PaooGame.KeyPress;
import PaooGame.Map;
import PaooGame.States.PlayState;

import java.awt.*;

/*! \public class Player extends Entity
    \brief Abstractizeaza notiunea de entitate de tip player.
 */
public class Player extends Entity
{
    /// atribut folosit pentru a verifica daca playerul poate sa sara
    private boolean canjump;
    /// atribut folosit pentru timerul sariturii pana cade
    private int jump_timer;
    /// atribut folosit pentru a verifica daca playerul a sarit
    private boolean jumping;
    /// atribut folosit miscarea pe axa X
    private int VelX;
    /// atribut folosit miscarea pe axa Y
    private int VelY;
    /// atribut folosit prima pozitionare a playerului
    private boolean start;
    /// atribut folosit selectia pozei
    private int frame;
    /// atribut folosit pentru timerul schimbarii pozei
    private int frameDelay;
    /// atribut folosit pentru a detecta daca trebuie realizata animatia de miscare
    private boolean animate;
    /// atribut folosit pentru viata
    private static int HP;
    /// atribut folosit pentru invulnerabilitate dupa ce ai lovit sau ai fost lovit
    private static int invul;
    /// atribut folosit pentru detectarea atingerilor
    private int atins;
    /// atribut folosit pentru schimbarea numarului de inimi
    private int i;
    /// atribut folosit pentru actiunea de atac
    private int attack;


    public Player(int id) {
        super(Assets.player[0], id);
        canjump=true;
        facing=1;
        jump_timer=0;
        jumping=false;
        attack=0;
        frame=0;
        atins=0;
        invul=0;
        frameDelay=0;
        HP=4;
        animate=false;
        start=true;
    }
    public void Draw(Graphics g, int x, int y) {

        /// daca entitatea inca nu este desenat se va desena la pozitia din harta, altfel este desenat in functie de valorile EntityX si EntityY
            if (EntityX == 0 && EntityY == 0 && start == true) {
                start = false;
                EntityX = x;
                EntityY = y;
            }/// desen animatie deces in functie de in ce parte se uita playerul
            if (HP == 0) {
                if (facing == 0) {
                    g.drawImage(Assets.player[frame + 44], EntityX, EntityY, ENTITY_WIDTH, ENTITY_HEIGHT, null);
                } else if (facing == 1) {
                    g.drawImage(Assets.player[frame + 38], EntityX, EntityY, ENTITY_WIDTH, ENTITY_HEIGHT, null);

                }
            } else {
                /// desen animatie atac in functie de in ce parte se uita playerul
                if (attack == 1) {
                    if (facing == 0) {
                        g.drawImage(Assets.player[frame + 32], EntityX, EntityY, ENTITY_WIDTH, ENTITY_HEIGHT, null);
                    } else if (facing == 1) {
                        g.drawImage(Assets.player[frame + 26], EntityX, EntityY, ENTITY_WIDTH, ENTITY_HEIGHT, null);
                    }
                } else { /// desen atingere in functie de in ce parte se uita playerul
                    if (atins == 1) {
                        if (facing == 0) {
                            g.drawImage(Assets.player[frame + 53], EntityX, EntityY, ENTITY_WIDTH, ENTITY_HEIGHT, null);
                        } else if (facing == 1) {
                            g.drawImage(Assets.player[frame + 50], EntityX, EntityY, ENTITY_WIDTH, ENTITY_HEIGHT, null);
                        }
                    } else { /// desen animatie saritura in functie de in ce parte se uita playerul
                        if (jumping) {
                            if (facing == 0) {
                                g.drawImage(Assets.player[frame + 19], EntityX, EntityY, ENTITY_WIDTH, ENTITY_HEIGHT, null);
                            } else if (facing == 1) {
                                g.drawImage(Assets.player[frame + 13], EntityX, EntityY, ENTITY_WIDTH, ENTITY_HEIGHT, null);
                            }
                        } else { /// desen animatie miscare in functie de in ce parte se uita playerul
                            if (facing == 0) {
                                g.drawImage(Assets.player[frame + 7], EntityX, EntityY, ENTITY_WIDTH, ENTITY_HEIGHT, null);
                            } else if (facing == 1) {
                                g.drawImage(Assets.player[frame], EntityX, EntityY, ENTITY_WIDTH, ENTITY_HEIGHT, null);
                            }
                        }
                    }
                }
            }

        }
    @Override
    public void Update() {
        /// animatie player mort
        if(HP==0)
        {
            for(Entity m:Entity.Monsters)
            {
                m.stop=true;
            }
            frameDelay++;
            if (frameDelay >= 5) {

                if (frame < 5) {
                    frame++;
                }
                frameDelay = 0;
            }
            Gravity();
            Collision();
        }
        else {
            Attack();
            Jumping();
            Gravity();
            Movement();
            Collision();
            /// animatie atac
            if (attack == 1) {
                for (Entity m : Entity.Monsters) {
                    m.stop = true;
                }
                frameDelay++;
                if (frameDelay >= 5) {

                    if (frame < 6) {
                        frame++;
                    }
                    if (frame >= 6) {
                        frame = 0;
                        attack = 0;
                    }
                    frameDelay = 0;
                }
                /// animatie atingere
            } else {
                if (atins == 1) {
                    frameDelay++;
                    if (frameDelay >= 8) {

                        if (frame < 3) {
                            frame++;
                        }
                        if (frame >= 3) {
                            frame = 0;
                            atins = 0;
                        }
                        frameDelay = 0;
                    }
                    /// animatie saritura
                } else {
                    if (VelX != 0 || VelY != 0) animate = true;
                    else animate = false;
                    if (animate) {
                        if (jumping) {
                            frameDelay++;
                            if (frameDelay >= 12) {

                                if (frame < 5) {
                                    frame++;
                                }
                                frameDelay = 0;
                            }
                            /// animatie miscare
                        } else {
                            frameDelay++;
                            if (frameDelay >= 6) {
                                frame++;
                                if (frame >= 5) {
                                    frame = 1;
                                }
                                frameDelay = 0;
                            }
                        }
                    } else {
                        frame = 0;
                    }
                }
            }
            EntityX += VelX;
            EntityY += VelY;
            ScreenWall();
            VelX = 0;
            if (invul != 0) {
                invul--;
            }
        }
    }
    /*! \private void ScreenWall()
\brief Se ocupa de restrictionarea iesirii de pe ecran a playerului
*/
    private void ScreenWall()
    {
        if(EntityX<=-15)
        {
            EntityX=-15;
            for(Entity e:Entity.Monsters)
            e.stop=true;
        }

        if(EntityY<=0)
            EntityY=0;
        if(EntityX>=774)
        {
            EntityX=774;
            for(Entity e:Entity.Monsters)
                e.stop=true;
        }
        if(EntityY>=552)
            EntityY=552;
    }
    /*! \private void Jumping()
    \brief Se ocupa de saritura playerului
 */
    private void Jumping()
    {
        /// playerul sare o anumita perioada de timp , din momentul inceperii sariturii si pana la atingerea unui block , playerul nu mai poate sari inca odata
        if(attack==0&&(HP!=0)) {
            if (canjump == true) {
                if (KeyPress.w_pressed == true) {
                    if (jumping == false) {
                        VelY = -3;
                        jumping = true;
                    }
                    if (jump_timer == 35) {
                        canjump = false;
                        VelY = 3;
                        jump_timer = 0;
                    }
                    jump_timer++;
                }
            }
        }
    }
    /*! \private void Movement()
        \brief Se ocupa de miscarea playerului
     */
    private void Movement()
    {
        ///aceste verificari ajuta la miscarea playerului
        /// de asemenea este verificat daca playerul este in mijlocul actiunii de atac sau daca e mort
        if(attack==0&&HP!=0) {
            if (KeyPress.a_pressed == true) {
                if(Map.getStop_Dreapta()==1)
                {
                    VelX = -2;
                }
                else {
                    VelX = -1;
                }
                facing = 0;
            }
            if (KeyPress.d_pressed == true) {
                if(Map.getStop_Stanga()==1)
                {
                    VelX = 2;
                }
                else {
                    VelX = 1;
                }
                facing = 1;
            }
        }
    }
    /*! \fn     private void Attack()
\brief se ocupa de atacare .

*/
    private void Attack()
    {
        if(HP!=0)
            if(KeyPress.space_pressed==true) {
                attack=1;
            }

    }
    /*! \fn     private void Gravity()
\brief se ocupa de gravitatie.

*/
    private void Gravity()
    {
        /// atata timp cat playerul nu sare in sus , este verificat daca exista bloc sub el si daca nu exista ,playerul incepe sa cada
        if(KeyPress.w_pressed==false) {
            for (CollisionObjects t : Map.Tiles) {
                if (t != null) {

                    if (!(getBoundsBottom().intersects(t.getBounds()))) {
                        VelY = 2;
                        jumping=true;
                    }
                    else
                    {
                        VelY = 0;
                    }

                }

            }
            for (CollisionObjects t : Map.HalfTiles) {
                if (t != null) {
                    if (!(getBoundsBottom().intersects(t.getBounds2()))) {
                            VelY = 2;
                            jumping=true;


                    }
                }

            }
        }
    }
    /*! \fn     private void Collision()
\brief se ocupa de coliziunea cu celelalte blocuri .

*/
    private void Collision()
    {
        /// daca conditia terminarii nivelului este indeplinita , se trece la urmatorul nivel
        if ((getBoundsBottom().intersects(Map.endTile.getBounds()))&&(start==false)&&(PlayState.getMap().getCoinscount()<=0))
        {
            PlayState.setTotal_Score(PlayState.getTotal_Score()+10*HP);
            PlayState.setTotal_Score(PlayState.getTotal_Score()+50);
            PlayState.setLevel(PlayState.getLevel()+1);
            PlayState.setNext(true);
        }
        /// interzice playerlui sa se miste spre pozitii in care se afla blocuri
        if (getBoundsTop().intersects(Map.endTile.getBounds())) {
            if(VelY<0) {
                VelY = 0;
            }
        }
        if (getBoundsBottom().intersects(Map.endTile.getBounds())) {
            if(VelY>0)
                VelY=0;
            canjump=true;
            jump_timer=0;
            jumping=false;


        }
        if (getBoundsLeft().intersects(Map.endTile.getBounds())) {
            if(VelX<0) {
                VelX = 0;
                Map.setL(Map.getL()+2);
                for(Entity e:Entity.Monsters)
                    if(e!=null)
                        e.stop=true;
            }

        }
        if (getBoundsRight().intersects(Map.endTile.getBounds())) {
            if(VelX>0) {
                VelX = 0;
                Map.setL(Map.getL()-2);
                for(Entity e:Entity.Monsters)
                    if(e!=null)
                        e.stop=true;

            }
        }
        for(CollisionObjects t: Map.waterTiles)
        {
            if(t!=null) {
                if (getBoundsBottom().intersects(t.getBounds())) {
                    HP=0;

                }
            }

        }
        for(CollisionObjects t: Map.Tiles)
        {
            if(t!=null) {
                if (getBoundsTop().intersects(t.getBounds())) {
                    if(VelY<0) {
                        VelY = 0;
                    }
                }
                if (getBoundsBottom().intersects(t.getBounds())) {
                    if(VelY>0)
                        VelY=0;
                        canjump=true;
                        jump_timer=0;
                        jumping=false;


                }
                if (getBoundsLeft().intersects(t.getBounds())) {
                    if(VelX<0) {
                        VelX = 0;
                        Map.setL(Map.getL()+2);
                        for(Entity e:Entity.Monsters)
                            if(e!=null)
                            e.stop=true;
                    }

                }
                if (getBoundsRight().intersects(t.getBounds())) {
                    if(VelX>0) {
                        VelX = 0;
                        Map.setL(Map.getL()-2);
                        for(Entity e:Entity.Monsters)
                            if(e!=null)
                            e.stop=true;

                    }
                }
            }

        }
        for(CollisionObjects t: Map.HalfTiles)
        {
            if(t!=null) {
                if (getBoundsTop().intersects(t.getBounds2())) {
                    if(VelY<0)
                        VelY = 0;


                }
                if (getBoundsBottom().intersects(t.getBounds2())) {
                    if(VelY>0)
                        VelY=0;
                    canjump=true;
                    jump_timer=0;
                    jumping=false;


                }
                if (getBoundsLeft().intersects(t.getBounds2())) {
                    if(VelX<0) {
                        VelX = 0;
                        Map.setL(Map.getL()+2);
                        for(Entity e:Entity.Monsters)
                            e.stop=true;
                    }

                }
                else if (getBoundsRight().intersects(t.getBounds2())) {
                    if(VelX>0) {
                        VelX = 0;
                        Map.setL(Map.getL()-2);
                        for(Entity e:Entity.Monsters)
                            e.stop=true;
                    }
                }
            }

        }
        /// este verificata coliziunea cu monezile
        for(Entity e: Entity.Coins)
        {
            if(e!=null) {
                if (getBoundsTop().intersects(e.getBounds(24,24))) {
                    e.atins++;
                    PlayState.setTotal_Score(PlayState.getTotal_Score()+20);
                    PlayState.getMap().setCoinscount(PlayState.getMap().getCoinscount()-1);



                }
                if (getBoundsBottom().intersects(e.getBounds(24,24))) {
                    e.atins++;
                    PlayState.setTotal_Score(PlayState.getTotal_Score()+20);
                    PlayState.getMap().setCoinscount(PlayState.getMap().getCoinscount()-1);

                }
                if (getBoundsLeft().intersects(e.getBounds(24,24))) {
                    e.atins++;
                    PlayState.setTotal_Score(PlayState.getTotal_Score()+20);
                    PlayState.getMap().setCoinscount(PlayState.getMap().getCoinscount()-1);

                }
                if (getBoundsRight().intersects(e.getBounds(24,24))) {
                    e.atins++;
                    PlayState.setTotal_Score(PlayState.getTotal_Score()+20);
                    PlayState.getMap().setCoinscount(PlayState.getMap().getCoinscount()-1);

                }
            }
        }
        ///  este verificata coliziunea cu mobii si se scade viata playerului
        for(Entity m: Entity.Monsters)
        {
            if(m!=null) {
                if (getBoundsTop().intersects(m.getBounds(m.ENTITY_WIDTH,m.ENTITY_HEIGHT))) {
                    if(m.invul==0){
                        if(invul==0&&HP!=0) {
                            invul=50;
                            atins=1;
                            frame=0;
                            if(HP!=0)
                                HP--;
                            Entity.hearts[3-i].change=1;
                            i++;
                        }
                    }


                }
                if (getBoundsBottom().intersects(m.getBounds(m.ENTITY_WIDTH,m.ENTITY_HEIGHT))) {
                    if(m.invul==0){
                        if(invul==0&&HP!=0) {
                            invul=50;
                            atins=1;
                            frame=0;
                            if(HP!=0)
                                HP--;
                            Entity.hearts[3-i].change=1;
                            i++;
                        }
                    }

                }
                ///  este verificata coliziunea cu mobii in timpul in care playerul ataca si in caz pozitiv , mobul moare
                    if ((facing==0)&&(attack==1)&&(getHitBoundsLeft().intersects(m.getBounds(m.ENTITY_WIDTH+24, m.ENTITY_HEIGHT)))){
                        if(m.invul==0) {
                            m.health--;
                            m.invul=50;
                            if (m.health == 0) {
                                PlayState.setTotal_Score(PlayState.getTotal_Score()+20);
                                m.dead=1;
                                PlayState.getMap().setMonsterscount(PlayState.getMap().getMonsterscount()-1);
                            }
                        }

                    }
                    if ((facing==1)&&(attack==1)&&(getHitBoundsRight().intersects(m.getBounds(m.ENTITY_WIDTH+24, m.ENTITY_HEIGHT)))){
                        if(m.invul==0) {
                            m.health--;
                            m.invul=50;
                            if (m.health == 0) {
                                PlayState.setTotal_Score(PlayState.getTotal_Score()+20);
                                m.dead=1;
                                PlayState.getMap().setMonsterscount(PlayState.getMap().getMonsterscount()-1);
                            }
                        }
                    }
                else {
                    if (getBoundsLeft().intersects(m.getBounds(m.ENTITY_WIDTH,m.ENTITY_HEIGHT))) {
                        if(m.invul==0){
                            if(invul==0&&HP!=0) {
                                invul=50;
                                atins=1;
                                frame=0;
                                if(HP!=0)
                                    HP--;
                                Entity.hearts[3-i].change=1;
                                i++;
                            }
                        }

                    }
                    if (getBoundsRight().intersects(m.getBounds(m.ENTITY_WIDTH,m.ENTITY_HEIGHT))) {
                        if(m.invul==0){
                            if(invul==0&&HP!=0) {
                                invul=50;
                                atins=1;
                                frame=0;
                                if(HP!=0)
                                    HP--;
                                Entity.hearts[3-i].change=1;
                                i++;
                            }
                        }

                    }
                }
            }
        }
    }
    @Override public Rectangle getBoundsTop(){
        if(facing==1) {
            return new Rectangle(EntityX+5, EntityY, ENTITY_WIDTH-25, 5);
        }
        else
        {
            return new Rectangle(EntityX+20,EntityY,ENTITY_WIDTH-25,5);
        }
    }
    @Override public Rectangle getBoundsBottom(){
        if(facing==1) {
            return new Rectangle(EntityX+5, EntityY + ENTITY_HEIGHT - 5, ENTITY_WIDTH-25, 5);
        }
        else
        {
            return new Rectangle(EntityX+20,EntityY+ENTITY_HEIGHT-5,ENTITY_WIDTH-25,5);
        }
    }
    @Override public Rectangle getBoundsLeft(){
        if(facing==1) {
            return new Rectangle(EntityX, EntityY + 5, 5, ENTITY_HEIGHT - 10);
        }
        else
        {
            return new Rectangle(EntityX+15, EntityY + 5, 5, ENTITY_HEIGHT - 10);
        }
    }
    @Override public Rectangle getBoundsRight(){
        if(facing==1) {
            return new Rectangle(EntityX + ENTITY_WIDTH - 20, EntityY + 5, 5, ENTITY_HEIGHT - 10);
        }
        else
        {
            return new Rectangle(EntityX + ENTITY_WIDTH - 5, EntityY + 5, 5, ENTITY_HEIGHT - 10);
        }

    }
    /*! \fn     public Rectangle getHitBoundsLeft()
\brief functie care returneaza limitele din stanga pentru atac .

*/
    public Rectangle getHitBoundsLeft() {
        return new Rectangle(EntityX - 5, EntityY, 5, ENTITY_HEIGHT);
    }
    /*! \fn     public Rectangle getHitBoundsRight()
\brief functie care returneaza limitele din dreapta pentru atac .

*/
    public Rectangle getHitBoundsRight() {
        return new Rectangle(EntityX + ENTITY_WIDTH +5, EntityY, 5, ENTITY_HEIGHT);
    }
    /*! \fn     public int getHP()
\brief functie care returneaza viata .

*/
    public int getHP()
    {
        return HP;
    }
    /*! \fn     public void setHP(int HP)
\brief functie care seteaza viata .

*/
    public void setHP(int HP)
    {
        Player.HP=HP;
    }
    /*! \fn     public int getInvul()
\brief functie care returneaza timerul de invulnerabilitate .

*/
    public int getInvul() {
        return invul;
    }
    /*! \fn     public void setInvul(int invul)
\brief functie care seteaza timerul de invulnerabilitate .

*/
    public void setInvul(int invul)
    {
        Player.invul=invul;
    }
    /*! \fn     public int getVelX()
\brief functie care returneaza viteza pe axa x .

*/
    public int getVelX() {
        return VelX;
    }
    /*! \fn     public void setVelX(int VelX)
\brief functie care seteaza viteza pe axa x .

*/
    public void setVelX(int VelX)
    {
        this.VelX=VelX;
    }
    /*! \fn         public int getAttack()
\brief functie care returneaza daca actiunea de attack e in desfasurare .

*/
    public int getAttack() {
        return attack;
    }
    /*! \fn     public void setAttack(int attack)
\brief functie care seteaza timerul pentru actiunea de atac .

*/
    public void setAttack(int attack)
    {
        this.attack=attack;
    }
    /*! \fn     public int getAtins()
\brief functie care returneaza daca ai fost atins .

*/
    public int getAtins() {
        return atins;
    }
    /*! \fn     public void setAtins(int atins)
\brief functie care seteaza indicele pentru atineri .

*/
    public void setAtins(int atins)
    {
        this.atins=atins;
    }
    /*! \fn     public int getFrame()
\brief functie care returneaza indicele pozei animatiei .

*/
    public int getFrame() {
        return frame;
    }
    /*! \fn     public void setFrame(int frame)
\brief functie care seteaza indicele pozei animatiei .

*/
    public void setFrame(int frame)
    {
        this.frame=frame;
    }
    /*! \fn     public int getI()
\brief functie care returneaza indicele pentru afisarea inimilor .

*/
    public int getI()
    {
        return i;
    }
    /*! \fn     public void setI(int i)
\brief functie care seteaza indicele pentru afisarea inimilor .

*/
    public void setI(int i)
    {
        this.i=i;
    }


}
