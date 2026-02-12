package com.tripurahomes.residentsportal.service;

import com.tripurahomes.residentsportal.model.Resident;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ResidentService {

    List<Resident> getAllResidents();

    List<Resident> searchResidentsByOwnerName(String name);

    List<Resident> searchResidents(String keyword);

    Page<Resident> getResidentsPaginated(int page, int size);

    Page<Resident> searchResidentsPaginated(String keyword, int page, int size);
}
