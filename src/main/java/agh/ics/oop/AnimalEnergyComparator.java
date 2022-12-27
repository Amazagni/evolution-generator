package agh.ics.oop;

import java.util.Comparator;

public class AnimalEnergyComparator implements Comparator<Animal> {
    @Override
    public int compare(Animal o1, Animal o2) {
        return o2.getEnergy() - o1.getEnergy();
    }
}
