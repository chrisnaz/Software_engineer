package com.mdl.mdl_project.art.history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    public ArrayList<History> findBySOAID(Integer SOAID){
        ArrayList<History> histories = new ArrayList<>();
        for (History hist:historyRepository.findAll())
        {
            if(hist.getIdSOA().equals(SOAID)){
                histories.add(hist);
            }

        }
        return histories;
    }


}
