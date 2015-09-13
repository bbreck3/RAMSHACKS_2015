package edu.vcu.ramhacks.utils;

import android.content.res.Resources;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by Dani on 13/09/2015.
 */
public class ZipPopulationUtil {
    HashMap<Integer, Integer> map = new HashMap<>();
    public ZipPopulationUtil(Resources resources) {
        try {
            Scanner sc = new Scanner(resources.getAssets().open("zip-population.csv"));
            while(sc.hasNext()) {
                int a, b;
                a=sc.nextInt();
                sc.skip(Pattern.compile(","));
                b = sc.nextInt();
                map.put(a,b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getPopulation(int zipCode) {
        if(map.containsKey(zipCode))
            return map.get(zipCode);
        else
            return -1;
    }
}
