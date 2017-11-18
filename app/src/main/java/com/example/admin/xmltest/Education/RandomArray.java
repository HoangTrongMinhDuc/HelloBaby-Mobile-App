package com.example.admin.xmltest.Education;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by USER on 11/15/2017.
 */

public class RandomArray {
    public static <T> ArrayList<T> random(ArrayList<T> list){
        ArrayList<T> randomlist = new ArrayList<>();
        randomlist.addAll(list);
        Random random= new Random();
        for (int i = 0; i<randomlist.size();i++)
        {
            int iRand = random.nextInt(randomlist.size());
            T temp = randomlist.get(i);
            randomlist.set(i,randomlist.get(iRand));
            randomlist.set(iRand,temp);
        }
        return randomlist;
}
}
