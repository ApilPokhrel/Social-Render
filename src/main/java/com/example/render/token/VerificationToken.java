package com.example.render.token;

import java.lang.*;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public class VerificationToken {

public String vcode() {
    long time = System.currentTimeMillis();
    int length = (int) (Math.log10(time) + 1);

    double divide1 = time / length;
    Random rand = new Random();

    double n = rand.nextInt(50) + 1;

    double divide2 = n / 10;

    double code1 = divide1 * divide2;
    String code = Double.toString(code1);
    return code;
}

}
