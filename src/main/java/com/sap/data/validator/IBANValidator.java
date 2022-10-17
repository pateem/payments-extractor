package com.sap.data.validator;

import org.apache.commons.lang3.math.NumberUtils;

public class IBANValidator {

    public static boolean isValid (String IBAN) {
        IBAN = normalize(IBAN);

        if(IBAN.length() != 24 || !IBAN.substring(0, 2).equals("SK")) return false;
        IBAN = IBAN.substring(4,24) + IBAN.substring(0,4);
        String r = "";
        for(int m = 0; m < IBAN.length(); m++) {
            if(NumberUtils.isDigits(Character.toString(IBAN.charAt(m)))) {
                r += IBAN.charAt(m);
            } else {
                r += d(Character.toString(IBAN.charAt(m)));
            }
        }

        String l = (r.substring(0,9));
        String k = (r.substring(9,16));
        String j = (r.substring(16,23));
        String h = (r.substring(23,26));
        int n = Integer.parseInt(l)%97;
        n = Integer.parseInt(String.format("%d%s", n, k))%97;
        n = Integer.parseInt(String.format("%d%s", n, j))%97;
        n = Integer.parseInt(String.format("%d%s", n, h))%97;
        return n == 1;

    }


    private static String d(String l) {
        int h,g,f=0;
        String k = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for(h=0,g=l.length()-1;h<l.length();h+=1,g-=1) {
            f+=Math.pow(k.length(), g) * (k.indexOf(l.charAt(h)) + 10);
        }
        return Integer.toString(f);
    }

    public static String normalize(String IBAN) {
        return IBAN.toUpperCase().trim().replaceAll("[^A-Z0-9]", "");
    }


}