package pl.kurs.watercontainer.services;

import pl.kurs.watercontainer.models.WaterContainer;

public class WaterContainerService {

    public WaterContainer findContainerWithMostWater(WaterContainer[] containers){
        int index = findFirstNonNullContainerIndex(containers);
        WaterContainer containerWithMostWater = containers[index];


        for (int k = index; k < containers.length - 1; k++) {
            if (containers[k + 1] != null && containers[k + 1].getWaterLevel() > containerWithMostWater.getWaterLevel()){
                containerWithMostWater = containers[k + 1];
            }
        }
        return containerWithMostWater;
    }

    public double getPercentageFulfillment(WaterContainer container){
        return container.getWaterLevel() / container.getMaxCapacity();
    }

    public WaterContainer findMostFiledContainer(WaterContainer[] containers){
        int index = findFirstNonNullContainerIndex(containers);
        WaterContainer mostFiledContainer = containers[index];

        for (int i = index; i < containers.length - 1; i++) {
            if (containers[i + 1] != null){
                if (getPercentageFulfillment(containers[i + 1]) > getPercentageFulfillment(mostFiledContainer))
                    mostFiledContainer = containers[i + 1];
            }
        }
        return mostFiledContainer;
    }

    public WaterContainer[] findEmptyContainers(WaterContainer[] containers){
        int emptyContainersCounter = 0;
        for (WaterContainer container : containers) {
            if (container != null && container.getWaterLevel() == 0){
                emptyContainersCounter++;
            }
        }
        WaterContainer[] emptyContainersArray = new WaterContainer[emptyContainersCounter];
        int index = 0;

        for (int i = 0; i < containers.length; i++) {
            if (containers[i] != null && containers[i].getWaterLevel() == 0){
                emptyContainersArray[index++] = containers[i];
            }
        }
        return emptyContainersArray;
    }

    private int findFirstNonNullContainerIndex(WaterContainer[] containers){
        if (containers.length == 0){
            throw new RuntimeException("Array is empty");
        }

        int index = 0;
        for (int i = 0; i < containers.length; i++) {
            if (containers[i] != null){
                index = i;
                break;
            }
        }
        return index;
    }
}
