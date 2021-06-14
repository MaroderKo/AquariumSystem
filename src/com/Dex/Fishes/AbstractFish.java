package com.Dex.Fishes;

import com.Dex.Structure.Cell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

abstract class AbstractFish {
    protected Cell Cell = null;
    protected double AgeCurrent = 0;//
    protected double AgeMax = 0;//
    protected boolean isMale = false;//
    protected double AgeBeginMature = 0;//
    protected double CurrentPregnancy = 0;
    protected double MaxPregnancy = 0;//
    protected double FoodLevel = 0;//
    protected double FoodMaxLevel = 0;//
    protected double AgeEndMature = 0;//
    protected DeathType Death = DeathType.Null;//
    protected double FoodDecreaseAmount = 0;//

    protected final List<Integer> directions = Arrays.asList(1,2,3,4,6,7,8,9);

    void doPregnant()
    {
        CurrentPregnancy = 1;
    }
    boolean isDead()
    {
        return Death != DeathType.Null;
    }
    DeathType GetDeathReason()
    {
        return Death;
    }

    public AbstractFish()
    {}

    public AbstractFish(Cell cell) {
        Cell = cell;
    }

    public void move() {
        int x = Cell.getX();
        int y = Cell.getY();
        Random rand = new Random();
        List<Integer> directions = new ArrayList<>(this.directions);
        boolean isMoved = false;
        int tempx = 0;
        int tempy = 0;
        while (directions.size() != 0 && !isMoved)
        {
            int dir = rand.nextInt(directions.size());
            switch (directions.get(dir))
            {
                case 1:
                    tempx = x - 1;
                    tempy = y - 1;
                    break;
                case 2:
                    tempx = x;
                    tempy = y - 1;
                    break;
                case 3:
                    tempx = x + 1;
                    tempy = y - 1;
                    break;
                case 4:
                    tempx = x - 1;
                    tempy = y;
                    break;
                case 6:
                    tempx = x + 1;
                    tempy = y;
                    break;
                case 7:
                    tempx = x - 1;
                    tempy = y + 1;
                    break;
                case 8:
                    tempx = x;
                    tempy = y + 1;
                    break;
                case 9:
                    tempx = x + 1;
                    tempy = y + 1;
                    break;

            }
            if (trymove(tempx,tempy))
            {
                isMoved = true;
            }
            else
            {
                directions.remove(dir);
            }
        }
    }
    abstract boolean trymove(int x, int y);

    public abstract boolean removeCorpse();
    public boolean isMale()
    {
        return isMale;
    }

    public boolean isAlive() {
        if (FoodLevel <= 0) {
            Death = DeathType.ByHunger;
            //System.out.println("Fish at "+Cell.getX()+", "+Cell.getY()+" Dead by Hunger!");
        }
        if (AgeCurrent > AgeMax) {
            Death = DeathType.ByAge;
            //System.out.println("Fish at "+Cell.getX()+", "+Cell.getY()+" Dead by Age!");
        }
        if (Death != DeathType.Null) {
            return false;
        } else
        {return true;}
    }

    public boolean isPregnant()
    {
        return CurrentPregnancy != 0;
    }
}
