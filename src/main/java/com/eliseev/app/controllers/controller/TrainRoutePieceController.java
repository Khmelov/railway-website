package com.eliseev.app.controllers.controller;

import com.eliseev.app.services.StationService;
import com.eliseev.app.services.TrainDateService;
import com.eliseev.app.services.TrainRoutePieceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/trains/list/{trainId}")
public class TrainRoutePieceController {

    private Logger logger = LoggerFactory.getLogger(TrainRoutePieceController.class);
    private TrainRoutePieceService trainRoutePieceService;
    private StationService stationService;
    private TrainDateService trainDateService;

    @Autowired
    public TrainRoutePieceController(TrainRoutePieceService service,
                                     StationService stationService,
                                     TrainDateService trainDateService) {
        this.trainRoutePieceService = service;
        this.stationService = stationService;
        this.trainDateService = trainDateService;
    }


    @GetMapping("/route-pieces")
    public String findAll(@PathVariable("trainId") long trainId, Model model) {
        logger.info("User send /trains/list/{}/route-pieces", trainId);
        /*model.addAttribute("allStations", stationService.getUnusedStations(trainId));*/
        model.addAttribute("stations", trainRoutePieceService.list(trainId));
        return "stations/trainStations";
    }

    @GetMapping("/route-details")
    public String findAllForUser(@PathVariable("trainId") long trainId, Model model) {
        logger.info("User send /trains/list/{}/stationsForUser", trainId);
        model.addAttribute("stations", trainRoutePieceService.list(trainId));
        return "stations/trainStationsForUser";
    }

    @GetMapping("/dates")
    public String getDates(@PathVariable("trainId") long trainId, Model model) {
        logger.info("User send /trains/list/{}/dates", trainId);
        model.addAttribute("dates", trainDateService.getDates(trainId));
        return "trains/startEndTrainDates";
    }

    @GetMapping("/dates/form")
    public String getTrainDateForm(@PathVariable("trainId") long trainId, Model model) {

        model.addAttribute("trainRoutePieces", trainRoutePieceService.list(trainId));

        return "stations/stationsStopTime";
    }



}
