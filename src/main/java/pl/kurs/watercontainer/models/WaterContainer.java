package pl.kurs.watercontainer.models;

import pl.kurs.watercontainer.exceptions.InvalidCapacityException;
import pl.kurs.watercontainer.exceptions.InvalidLevelException;
import pl.kurs.watercontainer.exceptions.InvalidWaterAmountException;

import java.io.Serializable;
import java.util.Objects;

public class WaterContainer implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private double maxCapacity;
    private double waterLevel;

    private WaterContainer(String name, double maxCapacity, double waterLevel) {
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.waterLevel = waterLevel;
    }

    public static WaterContainer create(String name, double maxCapacity, double waterLevel) {
        if (maxCapacity <= 0)
            throw new InvalidCapacityException("Max capacity must be more than zero");
        if (waterLevel < 0 || waterLevel > maxCapacity)
            throw new InvalidLevelException("Invalid water level value");
        return new WaterContainer(name, maxCapacity, waterLevel);
    }

    public void addWater(double value) {
        if (value <= 0)
            throw new InvalidWaterAmountException("Value should be more than zero");
        if ((waterLevel + value) > maxCapacity)
            throw new InvalidWaterAmountException("Too much water to add");
        else
            waterLevel += value;
    }

    public void pourOutWater(double value) {
        if (value <= 0)
            throw new InvalidLevelException("Value should be more than zero");
        else if (waterLevel - value < 0)
            throw new InvalidWaterAmountException("Too much water to subtract");
        else
            waterLevel -= value;
    }

    public void pourWater(WaterContainer destination, double value){
        this.pourOutWater(value);
        destination.addWater(value);
    }

    public String getName() {
        return name;
    }

    public double getMaxCapacity() {
        return maxCapacity;
    }

    public double getWaterLevel() {
        return waterLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WaterContainer that = (WaterContainer) o;
        return Double.compare(maxCapacity, that.maxCapacity) == 0 && Double.compare(waterLevel, that.waterLevel) == 0 && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, maxCapacity, waterLevel);
    }

    @Override
    public String toString() {
        return "WaterContainer{" +
                "name='" + name + '\'' +
                ", maxCapacity=" + maxCapacity +
                ", waterLevel=" + waterLevel +
                '}';
    }
}
