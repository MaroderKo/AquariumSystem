package com.Dex;

public class Main {
    public static Aquarium aquarium;
    public static void main(String[] args) {
        aquarium = new Aquarium();
        aquarium.init();
        printinfo();
        for (int i = 0; i < 599; i++) {
            System.out.println("Итерация " + (i + 1));
            aquarium.newIteration();
        }
        printinfo();
        aquarium.newIteration();
        printinfo();
        //new MenuGUI();
    }

    public static void printinfo()
    {
        System.out.println(aquarium);
        System.out.println("Stones: "+aquarium.StoneQuantity());
        System.out.println("Herbs: "+aquarium.HerbQuantity());
        System.out.println("HerbFish: "+aquarium.HerbFishQuantity());
        System.out.println("PerdatorFish:" +aquarium.PredatorFishQuantity());
    }
}
