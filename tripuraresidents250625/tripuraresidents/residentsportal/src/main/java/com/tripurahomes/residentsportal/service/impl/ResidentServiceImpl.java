package com.tripurahomes.residentsportal.service.impl;

import com.tripurahomes.residentsportal.model.Resident;
import com.tripurahomes.residentsportal.repository.ResidentRepository;
import com.tripurahomes.residentsportal.service.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResidentServiceImpl implements ResidentService {

    private final ResidentRepository residentRepository;

    @Autowired
    public ResidentServiceImpl(ResidentRepository residentRepository) {
        this.residentRepository = residentRepository;
    }

    @Override
    public List<Resident> getAllResidents() {
        return residentRepository.findAllOrderedBySNo();
    }

    @Override
    public List<Resident> searchResidentsByOwnerName(String name) {
        return residentRepository.searchByOwnerName(name);
    }

    @Override
    public List<Resident> searchResidents(String keyword) {
        return residentRepository.searchResidents(keyword);
    }

    @Override
    public Page<Resident> getResidentsPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return residentRepository.findAllOrderedBySNo(pageable);
    }

    @Override
    public Page<Resident> searchResidentsPaginated(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (keyword == null || keyword.trim().isEmpty()) {
            return residentRepository.findAllOrderedBySNo(pageable);
        }
        return residentRepository.searchResidentsPaginated(keyword.trim().toLowerCase(), pageable);
    }
}
