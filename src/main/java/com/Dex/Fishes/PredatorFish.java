package com.Dex.Fishes;

import com.Dex.Config;
import com.Dex.Servlets.AquariumServlet;
import com.Dex.Structure.Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PredatorFish extends AbstractFish {

    public PredatorFish(Cell cell)
    {
        Cell = cell;
        Random rand = new Random();
        AgeBeginMature = Config.get().getPredatorFishAgeBeginMature();
        AgeEndMature = Config.get().getPredatorFishAgeEndMature();
        AgeMax = Config.get().getPredatorFishAgeMax();
        AgeCurrent = 0.1;
        CurrentPregnancy = 0;
        MaxPregnancy = Config.get().getPredatorFishMaxPregnancy();
        isMale = rand.nextInt()<50;
        Death = DeathType.Null;
        FoodDecreaseAmount = Config.get().getPredatorFishFoodDecreaseAmount();
        FoodMaxLevel = Config.get().getPredatorFishFoodMaxLevel();
        FoodLevel = FoodMaxLevel;
    }

    public void Eat()
    {
        if (Cell.getHerbFish() != null && FoodLevel < FoodMaxLevel*0.6)
        {
            HerbFish fish = Cell.getHerbFish();
            FoodLevel += fish.Eated(FoodMaxLevel - FoodLevel);
        }

    }
    @Override
    boolean trymove(int x, int y) {
        Cell newCell = AquariumServlet.aquarium.getCell(x,y);
        if (newCell == null)
        {
            return false;
        }
        if (newCell.isAviable(this))
        {
            newCell.movePredatorFishHere(this);
            this.Cell.movePredatorFish();
            this.Cell = newCell;
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean removeCorpse() {
        if (!isAlive())
        {
            Cell.removePredatorFish(this);
            this.Cell = null;
            return true;
        }
        return false;
    }

    public void tryDoPregnant() {
        if (isMale)
            return;
        if (getisPregnant() || AgeCurrent < AgeBeginMature || AgeCurrent > AgeEndMature)
            return;
        int x = Cell.getX();
        int y = Cell.getY();
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                try {
                    PredatorFish fish = AquariumServlet.aquarium.getCell(x+i,y+j).getPredatorFish();
                    if (fish != null && fish.isMale)
                    {
                        CurrentPregnancy = 0.1;
                    }
                }
                catch (IndexOutOfBoundsException | NullPointerException e)
                {
                    continue;
                }
                //break;

            }
            if (getisPregnant())
            {
                break;
            }
        }
    }

    public void PregnantCheck()
    {
        if (isMale) {
            return;
        }
        else if (CurrentPregnancy == 0)
        {
            return;
        }
        else if (CurrentPregnancy < MaxPregnancy && CurrentPregnancy != 0) {
            CurrentPregnancy+=0.1;
            return;
        }
        int tempx=0;
        int tempy=0;
        int x = Cell.getX();
        int y = Cell.getY();
        Random rand = new Random();
        List<Integer> directions = new ArrayList<>(this.directions);
        while (directions.size() != 0 && getisPregnant())
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
            try {
                if (AquariumServlet.aquarium.getCell(tempx, tempy).isAviable(this)) {
                    CurrentPregnancy = 0;
                    AquariumServlet.aquarium.getCell(tempx, tempy).createPredatorFish(new PredatorFish(AquariumServlet.aquarium.getCell(tempx, tempy)));
                } else {
                    directions.remove(dir);
                }
            }catch (IndexOutOfBoundsException | NullPointerException e)
            {
                directions.remove(dir);
                continue;
            }
        }
        if (directions.size() == 0)
        {
            CurrentPregnancy = 0;
        }

    }
}
