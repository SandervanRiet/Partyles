package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Party;
import be.thomasmore.party.model.Venue;
import be.thomasmore.party.repositories.PartyRepository;
import be.thomasmore.party.repositories.VenueRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Controller
public class VenueController {

    private Logger logger = LoggerFactory.getLogger(VenueController.class);
    @Autowired
    private VenueRepository venueRepository;
    @Autowired
    private PartyRepository partyRepository;

    @GetMapping({"/venuedetails/{id}", "/venuedetails"})
    public String venueDetails(Model model, @PathVariable(required = false) Integer id) {
        if (id == null) return "venuedetails";
        Optional<Venue> optionalVenue = venueRepository.findById(id);
        Optional<Venue> optionalPrev = venueRepository.findFirstByIdLessThanOrderByIdDesc(id);
        Optional<Venue> optionalNext = venueRepository.findFirstByIdGreaterThanOrderById(id);
        if (optionalVenue.isPresent()) {
            model.addAttribute("venue", optionalVenue.get());
            List<Party> partys = partyRepository.findAllByVenue(optionalVenue.get());
            model.addAttribute("partys",partys);
        }
        if (optionalPrev.isPresent()) {
            model.addAttribute("prev", optionalPrev.get().getId());
        } else {
            model.addAttribute("prev", venueRepository.findFirstByOrderByIdDesc().get().getId());
        }
        if (optionalNext.isPresent()) {
            model.addAttribute("next", optionalNext.get().getId());
        } else {
            model.addAttribute("next", venueRepository.findFirstByOrderByIdAsc().get().getId());
        }
        return "venuedetails";
    }

    @GetMapping({"/venuelist", "/venuelist/{something}"})
    public String venueList(Model model) {
        Iterable<Venue> allVenues = venueRepository.findAll();
        model.addAttribute("venues", allVenues);
        model.addAttribute("nrVenues", venueRepository.count());
        return "venuelist";
    }


    @GetMapping({"/venuelist/filter"})
    public String venueListWithFilter(Model model
            ,@RequestParam(required = false) Integer minimumCapacity
            , @RequestParam(required = false) Integer maximumCapacity
            , @RequestParam(required = false) Double maximumDistance
            , @RequestParam(required = false) String isOutdoor) {
        logger.info(String.format("venueListWithFilter -- maxd=%f", maximumDistance));
        logger.info(String.format("venueListWithFilter -- min=%d", minimumCapacity));
        logger.info(String.format("venueListWithFilter -- max=%d", maximumCapacity));
        Iterable<Venue> allVenues= venueRepository.findAllVenues(minimumCapacity,maximumCapacity,maximumDistance);
        model.addAttribute("venues",allVenues);

        int nrVenues = ((Collection <Venue>) allVenues).size();
        model.addAttribute("nrVenues",nrVenues);
        model.addAttribute("showFilter", true);
        model.addAttribute("minCapacity",minimumCapacity);
        model.addAttribute("maxCapacity",maximumCapacity);
        model.addAttribute("maximumDistance",maximumDistance);
        return "venuelist";
    }
}










