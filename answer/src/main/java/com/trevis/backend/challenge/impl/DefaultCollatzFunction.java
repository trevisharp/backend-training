package com.trevis.backend.challenge.impl;

import com.trevis.backend.challenge.services.CollatzFunction;

public class DefaultCollatzFunction implements CollatzFunction {

    @Override
    public int apply(int input, int step) {
        
        int n = input;
        for (int i = 0; i < step; i++) {
            if (n % 2 == 0)
                n = n / 2;
            else n = 3 * n + 1;
        }
        return n;
        
    }
}