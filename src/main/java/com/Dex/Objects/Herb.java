package com.Dex.Objects;

import com.Dex.Config;
import com.Dex.Structure.Cell;

import java.util.Random;

public class Herb {
    Cell cell;
    double EnergyCurrent;
    double EnergyMax;
    static double EnergyIncreaseOnIteration;

    public void Grow()
    {
        EnergyCurrent+=EnergyIncreaseOnIteration;
        if (EnergyCurrent > EnergyMax)
            EnergyCurrent = EnergyMax;
    }

    public double Eat(double amount)
    {
        return Math.min(EnergyCurrent, amount);
    }

    public Herb(Cell cell) {
        this.cell = cell;
        EnergyMax = Config.get().getHerbEnergyMax();
        EnergyCurrent = new Random().nextInt((int) EnergyMax);
    }

    public boolean isAlive()
    {
        return EnergyCurrent != 0;
    }
    public void clearCell()
    {
        cell = null;
    }


}
