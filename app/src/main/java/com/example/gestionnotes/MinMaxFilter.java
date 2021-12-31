package com.example.gestionnotes;

import android.text.InputFilter ;
import android.text.Spanned ;

public class MinMaxFilter implements InputFilter {

    private double mIntMin, mIntMax;

    public MinMaxFilter(double minValue, double maxValue) {
        this.mIntMin = minValue;
        this.mIntMax = maxValue;
    }

    public MinMaxFilter(String minValue, String maxValue) {
        this.mIntMin = Double.parseDouble(minValue);
        this.mIntMax = Double.parseDouble(maxValue);
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try {
            Boolean isNeg = false;
            String provi = dest.toString() + source.toString();
            if("-".equals(provi.substring(0,1))){
                if(provi.length()>1) {
                    provi = provi.substring(1, provi.length());
                    isNeg = true;
                }
                else{
                    if("".equals(source)){
                        return null;
                    }
                    return "-";
                }
            }

            double input = Double.parseDouble(provi);
            if(isNeg){input = input * (-1);}

            if (isInRange(mIntMin, mIntMax, input)) {
                return null;
            }
        } catch (Exception nfe) {}
        return "";
    }

    private boolean isInRange(double a, double b, double c) {
        if((c>=a && c<=b)){
            return true;
        }
        else{
            return false;
        }
    }
}