package agents;

import action.ambulanceaction.AmbulanceFree;
import core.*;
import misc.Position;

import java.awt.*;
import java.util.ArrayList;

/**
 * Project: NewSimulator
 * Created by IntelliJ IDEA
 * Author: Sumin Park <smpark@se.kaist.ac.kr>
 * Github: https://github.com/sumin0407/NewSimulator.git
 */

public class Ambulance extends CS{
    public Ambulance(World world, String name) {
        super(world, name);
        router = world.router;
        addChild(new ImageObject("src/engine/resources/ambulance.png"));

        currentAction = new AmbulanceFree(this);        // Start action

        moveDelay = 0;
        frameCounter = moveDelay;
    }

    @Override
    public void onRender(Graphics2D graphics2D) {
        graphics2D.setColor(Color.red);
        graphics2D.setFont(new Font("default", Font.BOLD, 16));
        graphics2D.drawChars(name.toCharArray(), 0, name.length(), 0, 0);
    }
}
