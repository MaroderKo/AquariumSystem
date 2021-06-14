package com.Dex.Fishes;

import com.Dex.Config;
import com.Dex.Main;
import com.Dex.Objects.Herb;
import com.Dex.Structure.Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HerbFish extends AbstractFish {

    public HerbFish(Cell cell)
    {
        Cell = cell;
        Random rand = new Random();
        AgeBeginMature = Config.get().getHerbFishAgeBeginMature();
        AgeEndMature = Config.get().getHerbFishAgeEndMature();
        AgeMax = Config.get().getHerbFishAgeMax();
        AgeCurrent = 0.1;
        CurrentPregnancy = 0;
        MaxPregnancy = Config.get().getHerbFishMaxPregnancy();
        isMale = rand.nextInt()<50;
        Death = DeathType.Null;
        FoodDecreaseAmount = Config.get().getHerbFishFoodDecreaseAmount();
        FoodMaxLevel = Config.get().getHerbFishFoodMaxLevel();
        FoodLevel = FoodMaxLevel;
    }


    @Override
    boolean trymove(int x, int y) {
        Cell newCell = Main.aquarium.getCell(x,y);
        if (newCell == null)
        {
            return false;
        }
        if (newCell.isAviable(this))
        {
            newCell.moveHerbFishHere(this);
            this.Cell.moveHerbFish();
            this.Cell = newCell;
            this.FoodLevel-=FoodDecreaseAmount;
            this.AgeCurrent+=0.1;
            return true;
        }
        else
        {
            return false;
        }
    }

    public void Eat()
    {
        if (Cell.getHerb() != null) {
            Herb herb = Cell.getHerb();
            FoodLevel += herb.Eat(FoodMaxLevel - FoodLevel);
        }
    }

    public double Eated(double amount)
    {
        Death = DeathType.ByPredator;
        //System.out.println("Рыбу на координатах "+Cell.getX()+", "+Cell.getY()+" съели!");
        return Math.min(amount, FoodLevel);
    }

    @Override
    public boolean removeCorpse() {
        if (!isAlive())
        {
            Cell.removeHerbFish(this);
            this.Cell = null;
            return true;
        }
        return false;
    }

    public void tryDoPregnant() {
        if (isMale)
            return;
        if (isPregnant() || AgeCurrent < AgeBeginMature || AgeCurrent > AgeEndMature)
            return;
        int x = Cell.getX();
        int y = Cell.getY();
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                try {
                    HerbFish fish = Main.aquarium.getCell(x+i,y+j).getHerbFish();
                    if (fish != null && fish.isMale)
                    {
                        //System.out.println("FishPregnant1");
                        CurrentPregnancy = 0.1;
                    }
                }
                catch (IndexOutOfBoundsException | NullPointerException e)
                {
                    continue;
                }
                //break;

            }
            if (isPregnant())
            {
                //System.out.println("FishPregnant2");
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
        while (directions.size() != 0 && isPregnant())
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
                if (Main.aquarium.getCell(tempx, tempy).isAviable(this)) {
                    CurrentPregnancy = 0;
                    Main.aquarium.getCell(tempx, tempy).createHerbFish(new HerbFish(Main.aquarium.getCell(tempx, tempy)));
                    //System.out.println("Травоядная рыбка родилась на координатах "+tempx+", "+tempy);
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
            //System.out.println("Травоядная рыбка НЕ родилась на координатах "+Cell.getX()+", "+Cell.getY());
            CurrentPregnancy = 0;
        }

    }

}
