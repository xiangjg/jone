package com.jone.util;

import java.math.BigInteger;
import java.util.List;

public class BigIntegerUtils {
    public static BigInteger sumRights(List<Integer> rights){
        BigInteger num = new BigInteger("0");
        for(int i=0; i<rights.size(); i++){
            num = num.setBit(rights.get(i));
        }
        return num;
    }

    public static boolean testRights(BigInteger sum,int targetRights){
        return sum.testBit(targetRights);
    }
}
