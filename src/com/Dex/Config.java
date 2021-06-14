package com.Dex;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
//SingleTone
public class Config {
    private static final File PROPS = new File("config/resumes.properties");
    private static final Config INSTANCE = new Config();


    private final int width;
    private final int height;
    private final int maxFishes;
    private final int StoneChance;
    private final int HerbChance;
    private final double HerbEnergyMax;
    private final double HerbFishAgeBeginMature;
    private final double HerbFishAgeEndMature;
    private final double HerbFishAgeMax;
    private final double HerbFishMaxPregnancy;
    private final double HerbFishFoodDecreaseAmount;
    private final double HerbFishFoodMaxLevel;
    private final double PredatorFishAgeBeginMature;
    private final double PredatorFishAgeEndMature;
    private final double PredatorFishAgeMax;
    private final double PredatorFishMaxPregnancy;
    private final double PredatorFishFoodDecreaseAmount;
    private final double PredatorFishFoodMaxLevel;

    public static Config get() {
        return INSTANCE;
    }

    private Config() {
        try (InputStream is = new FileInputStream(PROPS)) {
            Properties props = new Properties();
            props.load(is);
            width = Integer.parseInt(props.getProperty("width"));
            height = Integer.parseInt(props.getProperty("height"));
            maxFishes = Integer.parseInt(props.getProperty("maxFishes"));
            HerbChance = Integer.parseInt(props.getProperty("HerbChance"));
            StoneChance = Integer.parseInt(props.getProperty("StoneChance"));
            HerbEnergyMax = Double.parseDouble(props.getProperty("HerbEnergyMax"));

            HerbFishAgeBeginMature=Double.parseDouble(props.getProperty("HerbFishAgeBeginMature"));
            HerbFishAgeEndMature=Double.parseDouble(props.getProperty("HerbFishAgeEndMature"));
            HerbFishAgeMax=Double.parseDouble(props.getProperty("HerbFishAgeMax"));
            HerbFishMaxPregnancy=Double.parseDouble(props.getProperty("HerbFishMaxPregnancy"));
            HerbFishFoodDecreaseAmount=Double.parseDouble(props.getProperty("HerbFishFoodDecreaseAmount"));
            HerbFishFoodMaxLevel=Double.parseDouble(props.getProperty("HerbFishFoodMaxLevel"));

            PredatorFishAgeBeginMature=Double.parseDouble(props.getProperty("PredatorFishAgeBeginMature"));
            PredatorFishAgeEndMature=Double.parseDouble(props.getProperty("PredatorFishAgeEndMature"));
            PredatorFishAgeMax=Double.parseDouble(props.getProperty("PredatorFishAgeMax"));
            PredatorFishMaxPregnancy=Double.parseDouble(props.getProperty("PredatorFishMaxPregnancy"));
            PredatorFishFoodDecreaseAmount=Double.parseDouble(props.getProperty("PredatorFishFoodDecreaseAmount"));
            PredatorFishFoodMaxLevel=Double.parseDouble(props.getProperty("PredatorFishFoodMaxLevel"));
            if (height > 100 || width > 100) throw new AssertionError("Размеры аквариума не могут превышать размеры 100х100");
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file " + PROPS.getAbsolutePath());
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getMaxFishes() {
        return maxFishes;
    }

    public int getStoneChance() {
        return StoneChance;
    }

    public int getHerbChance() {
        return HerbChance;
    }

    public double getHerbEnergyMax() {
        return HerbEnergyMax;
    }

    public double getHerbFishAgeBeginMature() {
        return HerbFishAgeBeginMature;
    }

    public double getHerbFishAgeEndMature() {
        return HerbFishAgeEndMature;
    }

    public double getHerbFishAgeMax() {
        return HerbFishAgeMax;
    }

    public double getHerbFishMaxPregnancy() {
        return HerbFishMaxPregnancy;
    }

    public double getHerbFishFoodDecreaseAmount() {
        return HerbFishFoodDecreaseAmount;
    }

    public double getHerbFishFoodMaxLevel() {
        return HerbFishFoodMaxLevel;
    }

    public double getPredatorFishAgeBeginMature() {
        return PredatorFishAgeBeginMature;
    }

    public double getPredatorFishAgeEndMature() {
        return PredatorFishAgeEndMature;
    }

    public double getPredatorFishAgeMax() {
        return PredatorFishAgeMax;
    }

    public double getPredatorFishMaxPregnancy() {
        return PredatorFishMaxPregnancy;
    }

    public double getPredatorFishFoodDecreaseAmount() {
        return PredatorFishFoodDecreaseAmount;
    }

    public double getPredatorFishFoodMaxLevel() {
        return PredatorFishFoodMaxLevel;
    }
}