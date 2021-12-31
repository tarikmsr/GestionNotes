package com.example.gestionnotes;

import java.util.Objects;

public class Module {
    private String moduleName ;
    private double note ;

    public  Module(){
        //
    }

    public Module(String name, double note) {
        this.moduleName = name;
        this.note = note;
    }

    public String getModuleName() {
        return moduleName;
    }
    public double getNote() {
        return note;
    }

    public String toString() {
        return "Module :"+this.moduleName +", Note: "+ this.note;
    }

}
