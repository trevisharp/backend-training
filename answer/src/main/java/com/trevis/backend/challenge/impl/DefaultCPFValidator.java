package com.trevis.backend.challenge.impl;

import com.trevis.backend.challenge.services.CPFValidator;

public class DefaultCPFValidator implements CPFValidator {

    @Override
    public boolean validate(String cpf) {
        if (cpf == null)
            return false;

        if (cpf.length() != 11)
            return false;
        
        try
        {
            int sum1 = 0, sum2 = 0;
            for (int i = 0; i < 9; i++) {
                int digit = (cpf.charAt(i) - '0');
                int value = i * digit;
                sum1 += value + digit;
                sum2 += value;
            }

            int digit1 = sum1 % 11;
            sum2 += 9 * digit1;
            int digit2 = sum2 % 11;

            return digit1 == (cpf.charAt(9) - '0') && digit2 == (cpf.charAt(10) - '0');
        }
        catch (Exception ex) {
            return false;
        }
    }
}