package com.mdl.mdl_project.derive_tag;

import javax.xml.crypto.Data;
import java.util.ArrayList;

public class Derive {
    private ArrayList<String> derive;

    public Derive (){
        derive = new ArrayList<String>();
    }

    public ArrayList<String> getDerive() {
        return derive;
    }

    public void setDerive(ArrayList<String> derive) {
        this.derive = derive;
    }

    public int size() {
        return derive.size();
    }

    public String get(int i) {
        return derive.get(i);
    }
}