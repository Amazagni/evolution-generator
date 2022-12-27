package agh.ics.oop;

import java.util.Comparator;

public class AnimalEnergyComparator implements Comparator<Animal> {
    @Override
    public int compare(Animal o1, Animal o2) {
        if(o2.getEnergy() != o1.getEnergy()){
            return o2.getEnergy() - o1.getEnergy();
        }
        if(o2.getAge() != o1.getAge()){
            return o2.getAge() - o1.getAge();
        }
        return o2.getNumberOfChildren() - o1.getNumberOfChildren();
    }
}
