package PaooGame.Entity;

import PaooGame.Database;
import PaooGame.Graphics.Assets;
import PaooGame.Map;
import PaooGame.States.PlayState;

import java.awt.*;

/*! \public class Portal extends Entity
    \brief Abstractizeaza notiunea de entitate de tip portal.
 */
public class Portal extends Entity
{

    /// atribut folosit selectarea pozei
    private int frame=0;
    /// atribut folosit pentru timerul trecerii de la o poza la alta
    private int frameDelay=0;
    /// atribut folosit pentru timerul disparitiei portalului
    private int timp=0;
    public Portal(int id)
    {

        super(Assets.portal[0], id);
        EntityX=1000;
        EntityY=1000;
    }
    @Override
    public void Draw(Graphics g, int x, int y)
    {
        /// daca numarul monezilor ramase este 0 atunci deseneaza portalul
        if(PlayState.getMap().getCoinscount()<=0&&PlayState.getLevel()!=5){
            EntityX = Map.endTile.getX() ;
            EntityY = Map.endTile.getY() - 96;
        }
        else{
                if(timp<30) {
                    EntityX = x;
                    EntityY = y;
                }
                else
                {
                    EntityX = 100000;
                    EntityY = 100000;
                }
            }
            g.drawImage(Assets.portal[frame], EntityX, EntityY, 48, 96, null);

    }
    @Override
    public void Update() {
        frameDelay++;
        if (frameDelay >= 3) {
            frame++;
            timp++;
            if (frame>7) {
                frame=0;
            }
            frameDelay = 0;
    }


    }




}
