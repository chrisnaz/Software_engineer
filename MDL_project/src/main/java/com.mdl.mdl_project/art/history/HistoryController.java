package com.mdl.mdl_project.art.history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class HistoryController {

    @Autowired
    private HistoryService historyService;


    @RequestMapping("/StateOfArts/{StateOfArtsid}/history")
    public ArrayList<History> getHistory(@PathVariable Integer StateOfArtsid){

        return historyService.findBySOAID(StateOfArtsid);
    }

}
