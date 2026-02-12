package com.tripurahomes.residentsportal.repository;

import com.tripurahomes.residentsportal.model.Resident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResidentRepository extends JpaRepository<Resident, Long> {

    // 🔹 1. Fetch residents ordered by sNo ascending
    @Query("SELECT r FROM Resident r ORDER BY r.sNo ASC")
    List<Resident> findAllOrderedBySNo();

    // 🔹 2. Search residents by partial owner name
    @Query("SELECT r FROM Resident r WHERE LOWER(r.ownerName) LIKE LOWER(CONCAT('%', :name, '%')) ORDER BY r.sNo ASC")
    List<Resident> searchByOwnerName(@Param("name") String name);

    // 🔹 3. Search residents by owner name, flat no, or contact no
    @Query("SELECT r FROM Resident r WHERE " +
           "LOWER(r.ownerName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(r.flatNo) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(r.contactNo) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
           "ORDER BY r.sNo ASC")
    List<Resident> searchResidents(@Param("keyword") String keyword);

    // 🔹 4. Paginated list of all residents (used in merged search/pagination)
    @Query("SELECT r FROM Resident r ORDER BY r.sNo ASC")
    Page<Resident> findAllOrderedBySNo(Pageable pageable);

    // ✅ 🔹 5. Paginated search by name, flat no, or contact no
    @Query("SELECT r FROM Resident r WHERE " +
           "LOWER(r.ownerName) LIKE %:keyword% OR " +
           "LOWER(r.flatNo) LIKE %:keyword% OR " +
           "LOWER(r.contactNo) LIKE %:keyword% " +
           "ORDER BY r.sNo ASC")
    Page<Resident> searchResidentsPaginated(@Param("keyword") String keyword, Pageable pageable);

    // ✅ 🔹 6. Find resident by flat number (used for key positions)
    Resident findByFlatNo(String flatNo);

    // ✅ 🔹 7. Find all executive members by flat numbers (optional)
    @Query("SELECT r FROM Resident r WHERE r.flatNo IN :flatNos ORDER BY r.sNo ASC")
    List<Resident> findAllByFlatNos(@Param("flatNos") List<String> flatNos);
}
