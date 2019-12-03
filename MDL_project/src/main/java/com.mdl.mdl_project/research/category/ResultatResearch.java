package com.mdl.mdl_project.research.category;

import com.mdl.mdl_project.art.StateOfArt;

import java.util.List;

/**
 * @author Andry Rajaona
 * @version 1.1
 * This class allow to do format a json for research
 */

public class ResultatResearch {

    private List<StateOfArt> soa;
    private List<String> cluster;

    public ResultatResearch(List<StateOfArt> soa, List<String> cluster){
        this.soa = soa;
        this.cluster = cluster;
    }

    public List<StateOfArt> getSoa() {
        return soa;
    }

    public void setSoa(List<StateOfArt> soa) {
        this.soa = soa;
    }

    public List<String> getCluster() {
        return cluster;
    }

    public void setCluster(List<String> cluster) {
        this.cluster = cluster;
    }
}
