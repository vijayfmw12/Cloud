package com.tripurahomes.residentsportal.controller;

import com.tripurahomes.residentsportal.model.Resident;
import com.tripurahomes.residentsportal.repository.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class ContactController {

    @Autowired
    private ResidentRepository residentRepository;

    @GetMapping("/contact")
    public String showContactPage(Model model) {
        // Association members based on flatNo (configured manually)
        Resident president = residentRepository.findByFlatNo("A101");
        Resident vicePresident = residentRepository.findByFlatNo("A102");
        Resident generalSecretary = residentRepository.findByFlatNo("A103");
        Resident jointSecretary = residentRepository.findByFlatNo("A104");
        Resident treasurer = residentRepository.findByFlatNo("A105");

        // Executive Members
        List<String> executiveFlats = Arrays.asList("A106", "A107", "A108");
        List<Resident> executives = residentRepository.findAllByFlatNos(executiveFlats);

        // Watchman Contact (optional static for now)
        String watchmanPhone1 = "1234567890";
        String watchmanPhone2 = "9876543210";

        // Send to view
        model.addAttribute("president", president);
        model.addAttribute("vicePresident", vicePresident);
        model.addAttribute("generalSecretary", generalSecretary);
        model.addAttribute("jointSecretary", jointSecretary);
        model.addAttribute("treasurer", treasurer);
        model.addAttribute("executives", executives);
        model.addAttribute("watchmanPhone1", watchmanPhone1);
        model.addAttribute("watchmanPhone2", watchmanPhone2);

        return "contact";
    }
}
