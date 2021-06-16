package com.Dex.Structure;

import com.Dex.Fishes.HerbFish;
import com.Dex.Fishes.PredatorFish;
import com.Dex.Main;
import com.Dex.Objects.Herb;
import com.Dex.Objects.Stone;
import com.Dex.Servlets.AquariumServlet;
import com.sun.istack.internal.Nullable;

public class Cell {
    int X;
    int Y;
    Herb herb;
    Stone stone;
    HerbFish herbFish;
    PredatorFish predatorFish;

    public Cell(int x, int y) {
        X = x;
        Y = y;
        herb = null;
        stone = null;
        herbFish = null;
        predatorFish = null;
    }

    boolean getAviable()
    {
        return stone != null && herbFish != null && predatorFish != null;
    }
    public void createStone(Stone stone)
    {
        AquariumServlet.aquarium.registerStone(stone);
        this.stone = stone;
    }

    public void createHerb(Herb herb)
    {
        AquariumServlet.aquarium.registerHerb(herb);
        this.herb = herb;
    }

    public void createHerbFish(HerbFish fish)
    {
        AquariumServlet.aquarium.registerHerbFish(fish);
        herbFish = fish;
    }

    public void createPredatorFish(PredatorFish fish)
    {
        AquariumServlet.aquarium.registerPredatorFish(fish);
        predatorFish = fish;
    }

    public void removeStone(Stone stone)
    {
        AquariumServlet.aquarium.removeStone(stone);
        this.stone = null;
    }

    public void removeHerb(Herb herb)
    {
        AquariumServlet.aquarium.removeHerb(herb);
        this.herb = null;
    }

    public void removeHerbFish(HerbFish fish)
    {
        AquariumServlet.aquarium.removeHerbFish(fish);
        herbFish = null;
    }

    public void removePredatorFish(PredatorFish fish)
    {
        AquariumServlet.aquarium.removePredatorFish(fish);
        predatorFish = null;
    }

    public Herb getHerb() {
        return herb;
    }

    public Stone getStone() {
        return stone;
    }
    @Nullable
    public HerbFish getHerbFish() {
        return herbFish;
    }

    public PredatorFish getPredatorFish() {
        return predatorFish;
    }

    public boolean isEmpty()
    {
        return stone == null && herbFish == null && predatorFish == null && herb == null;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public boolean isAviable(HerbFish fish)
    {
        return predatorFish == null && herbFish == null && stone == null;
    }

    public boolean isAviable(PredatorFish fish)
    {
        return predatorFish == null && stone == null;
    }

    public void moveHerbFish() {
        herbFish = null;
    }
    public void movePredatorFish() {
        predatorFish = null;
    }

    public void moveHerbFishHere(HerbFish fish)
    {
        this.herbFish = fish;
    }
    public void movePredatorFishHere(PredatorFish fish)
    {
        this.predatorFish = fish;
    }


}
