package agh.ics.oop;

import java.util.Comparator;

public class ToxicCorpsesComparator implements Comparator<ToxicCorpsesField> {
    @Override
    public int compare(ToxicCorpsesField o1, ToxicCorpsesField o2) {
        if(o1.getCorspes() - o2.getCorspes() != 0){
            return o1.getCorspes() - o2.getCorspes();
        }
        return o1.getRandomNumber() - o2.getRandomNumber();

    }
}
