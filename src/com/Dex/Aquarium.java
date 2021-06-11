package com.Dex;

import com.Dex.Fishes.HerbFish;
import com.Dex.Fishes.PredatorFish;
import com.Dex.Objects.Herb;
import com.Dex.Objects.Stone;
import com.Dex.Structure.Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Aquarium {
    private int Width;
    private int Height;
    private List<Stone> StoneList = new ArrayList<>();
    private List<Herb> HerbList = new ArrayList<>();
    private List<HerbFish> HerbFishList = new ArrayList<>();
    private List<PredatorFish> PredatorFishList = new ArrayList<>();
    private List<List<Cell>> CellList = new ArrayList<>();

    public Aquarium()
    {

        
    }
    public void makeLive()
    {
        int fishchance = (Height*Width)/Config.get().getMaxFishes();
        assert fishchance <= 100;
        if (fishchance == 0)
        {
            fishchance = 1;
        }
        Random rand = new Random();
        boolean StoneSpawned = false;
        boolean HerbSpawned = false;
        while (HerbFishList.size()+PredatorFishList.size()<Config.get().getMaxFishes()) {
            for (int i = 0; i < Width; i++) {
                for (int j = 0; j < Height; j++) {
                    Cell cell = CellList.get(i).get(j);
                    if (cell.isEmpty()) {
                        if (rand.nextInt(100) < Config.get().getStoneChance() && !StoneSpawned) {
                            Stone stone = new Stone();
                            cell.createStone(stone);
                        }
                        else if (rand.nextInt(100) < Config.get().getHerbChance() && !HerbSpawned)
                        {
                            Herb herb = new Herb(cell);
                            cell.createHerb(herb);
                        }
                        else {
                            if (HerbFishList.size() + PredatorFishList.size() < Config.get().getMaxFishes()) {
                                if (rand.nextInt(100) < fishchance) {
                                    if (rand.nextBoolean()) {
                                        HerbFish fish = new HerbFish(cell);
                                        cell.createHerbFish(fish);
                                    } else {
                                        PredatorFish fish = new PredatorFish(cell);
                                        cell.createPredatorFish(fish);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            StoneSpawned = true;
            HerbSpawned = true;
        }
    }
    
    public void registerHerb(Herb herb)
    {
        HerbList.add(herb);
    }
    
    public void registerStone(Stone stone)
    {
        StoneList.add(stone);
    }
    
    public void registerHerbFish(HerbFish fish)
    {
        HerbFishList.add(fish);
    }
    public void registerPredatorFish(PredatorFish fish)
    {
        PredatorFishList.add(fish);
    }

    public void removeHerb(Herb herb)
    {
        HerbList.remove(herb);
    }

    public void removeStone(Stone stone)
    {
        StoneList.remove(stone);
    }

    public void removeHerbFish(HerbFish fish)
    {
        HerbFishList.remove(fish);
    }
    public void removePredatorFish(PredatorFish fish)
    {
        PredatorFishList.remove(fish);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Width; i++)
        {
            for (int j = 0; j < Height; j++) {

                if (CellList.get(i).get(j).getStone() != null)
                {
                    sb.append("S");
                }
                else if (CellList.get(i).get(j).getPredatorFish() != null)
                {
                    sb.append("P");
                }
                else if (CellList.get(i).get(j).getHerbFish() != null)
                {
                    if (CellList.get(i).get(j).getHerbFish().isMale()) {
                        sb.append("F");
                    }
                    else
                    {
                        sb.append("f");
                    }
                }
                else if (CellList.get(i).get(j).getHerb() != null)
                {
                    sb.append("H");
                }
                else
                {
                    sb.append("_");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public void init() {
        Width = Config.get().getWidth();
        Height = Config.get().getHeight();
        for (int i = 0; i < Width; i++)
        {
            List<Cell> temp = new ArrayList<>();
            for (int j = 0; j < Height; j++) {
                temp.add(new Cell(i,j));
            }
            CellList.add(temp);
        }
        makeLive();
    }

    public int StoneQuantity()
    {
        return StoneList.size();
    }

    public int HerbQuantity()
    {
        return HerbList.size();
    }

    public int HerbFishQuantity()
    {
        return HerbFishList.size();
    }

    public int PredatorFishQuantity()
    {
        return PredatorFishList.size();
    }

    public void newIteration() {
            ArrayList<HerbFish> oldHFishes = new ArrayList<>(HerbFishList);
            for (HerbFish herbFish: oldHFishes)
            {
                if (!herbFish.removeCorpse())
                {
                    herbFish.move();
                    herbFish.Eat();
                    herbFish.tryDoPregnant();
                    herbFish.PregnantCheck();
                }
            }
            ArrayList<PredatorFish> oldPFishes = new ArrayList<>(PredatorFishList);
            for (PredatorFish predatorFish: oldPFishes)
            {
                if (!predatorFish.removeCorpse())
                {
                    predatorFish.move();
                    predatorFish.Eat();
                    predatorFish.tryDoPregnant();
                    predatorFish.PregnantCheck();
                }
            }
            ArrayList<Herb> oldHerbs = new ArrayList<>(HerbList);
            for (Herb herb: oldHerbs)
            {
                if (herb.isAlive())
                {
                     herb.Grow();
                }
                else
                {
                    removeHerb(herb);
                    herb.clearCell();
                }

            }


            //TODO: pregnancy


    }

    public Cell getCell(int x, int y)
    {
        try {
            return CellList.get(x).get(y);
        }
        catch (IndexOutOfBoundsException e)
        {
            return null;
        }
    }
}
