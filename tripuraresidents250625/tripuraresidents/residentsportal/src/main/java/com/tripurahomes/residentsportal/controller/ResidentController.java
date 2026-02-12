package com.tripurahomes.residentsportal.controller;

import com.tripurahomes.residentsportal.model.Resident;
import com.tripurahomes.residentsportal.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/residents")  // ✅ All routes under "/residents"
public class ResidentController {

    private final ResidentService residentService;

    @Autowired
    public ResidentController(ResidentService residentService) {
        this.residentService = residentService;
    }

    // ✅ Combined Search + Pagination View (Main Entry)
    @GetMapping("")
    public String viewResidents(
            @RequestParam(name = "keyword", required = false) String keyword,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            Model model) {

        Page<Resident> residentsPage;

        if (keyword != null && !keyword.trim().isEmpty()) {
            residentsPage = residentService.searchResidentsPaginated(keyword.trim(), page, size);
        } else {
            residentsPage = residentService.getResidentsPaginated(page, size);
        }

        model.addAttribute("residentsPage", residentsPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", residentsPage.getTotalPages());
        model.addAttribute("size", size);
        model.addAttribute("keyword", keyword);

        return "residents"; // ✅ All handled via residents.html now
    }
}
