package PaooGame.Entity;

import PaooGame.Graphics.Assets;

import java.awt.*;

/*! \public class Heart extends Entity
    \brief Abstractizeaza notiunea de entitate de tip inima.
 */
public class Heart extends Entity {

    /// atribut folosit pentru selectarea pozei
    protected int frame = 0;
    /// atribut folosit pentru schimbarea vietii
    protected int change=0;

    public Heart(int id) {

        super(Assets.heart[0], id);
        EntityX = 1000;
        EntityY = 1000;
    }

    @Override
    public void Draw(Graphics g, int x, int y) {
        EntityX = x;
        EntityY = y;
        g.drawImage(Assets.heart[frame], EntityX, EntityY, 24, 24, null);
    }

    @Override
    public void Update() {
        /// daca viata scade , dispare cate o inima
        if (change == 1) {
                frame=4;
                change=0;
        }
    }
}




