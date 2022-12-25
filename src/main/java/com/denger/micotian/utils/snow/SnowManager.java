package com.denger.micotian.utils.snow;

import net.minecraft.client.gui.GuiScreen;

import java.util.ArrayList;
import java.util.Random;

public class SnowManager {
    private static final Random random = new Random();
    private ArrayList<Snow> snows = new ArrayList<Snow>();
    private int maxsnow;
    public SnowManager(int maxsnow){
        this.maxsnow = maxsnow;
    }

    public static double getRandomInRange(double max, double min) {
        return min + (max - min) * random.nextDouble();
    }

    public void add(){
        snows.add(new Snow((float) getRandomInRange(0, GuiScreen.width), 0));
    }

    public void render(){
        for(int i = 0; i < snows.size(); i++){
            if(snows.get(i).getY() >= GuiScreen.height){
                snows.remove(i);
            }
        }

            for(Snow snow : snows){
                snow.move();
                snow.Render();
            }
        if(snows.size() < maxsnow){
            add();
        }



    }
}
