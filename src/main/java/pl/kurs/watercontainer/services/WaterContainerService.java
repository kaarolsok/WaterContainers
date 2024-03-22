package pl.kurs.watercontainer.services;

import pl.kurs.watercontainer.models.WaterContainer;

public class WaterContainerService {

    public WaterContainer findContainerWithMostWater(WaterContainer[] containers){
        if (containers.length == 0){
            return null;
        }
        WaterContainer containerWithMostWater = null;
        int index = 0;
        for (int i = 0; i < containers.length; i++) {
            if (containers[i] != null){
                containerWithMostWater = containers[i];
                index = i;
                break;
            }
        }


        for (int k = index; k < containers.length; k++) {
            if (containers[k] != null && containers[k].getWaterLevel() > containerWithMostWater.getWaterLevel()){
                containerWithMostWater = containers[k];
            }
        }
        return containerWithMostWater;
    }
}
