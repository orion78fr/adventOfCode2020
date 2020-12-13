package fr.orion78.adventOfCode.year2020.day13;

import java.math.BigInteger;

public class Test {
    public static void main(String[] args) {
        BigInteger a = Part2.compute("17,x,13,19");
        if(a.longValue() != 3417){
            throw new RuntimeException();
        }
        a = Part2.compute("67,7,59,61");
        if(a.longValue() != 754018){
            throw new RuntimeException();
        }
        a = Part2.compute("67,x,7,59,61");
        if(a.longValue() != 779210){
            throw new RuntimeException();
        }
        a = Part2.compute("67,7,x,59,61");
        if(a.longValue() != 1261476){
            throw new RuntimeException();
        }
        a = Part2.compute("1789,37,47,1889");
        if(a.longValue() != 1202161486){
            throw new RuntimeException();
        }
        a = Part2.compute("7,13,x,x,59,x,31,19");
        if(a.longValue() != 1068781){
            throw new RuntimeException();
        }
    }
}
