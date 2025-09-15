package haikang;

import java.util.*;

class Solution {

    public static int solution(Vehicle v, int distance) {
        
        return v.getTime(distance);
    }
    public int calculatingScore (String text) {
        // write code here
        String[] s = text.toLowerCase().split(" ");
        int res = 0;
        for (String string : s) {
            if (string.equals("coder")) res++;
        }
        return res;
    }
    
}

interface Vehicle {
    int getTime(int distance);
}

class Car implements Vehicle {
    private final int speed;

    public Car(int speed) {
        this.speed = speed;
    }

    @Override
    public int getTime(int distance) {
        return distance / speed;
    }
}

class Bus implements Vehicle{
    private final int speed;

    public Bus(int speed) {
        this.speed = speed;
    }

    @Override
    public int getTime(int distance) {
        return distance / speed;
    }
}