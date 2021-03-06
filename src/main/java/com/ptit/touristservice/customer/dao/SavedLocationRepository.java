package com.ptit.touristservice.customer.dao;

import com.ptit.touristservice.customer.model.data.SaveLocation;
import com.ptit.touristservice.customer.model.response.SaveLocationDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SavedLocationRepository extends JpaRepository<SaveLocation,String> {
//    @Query("select new com.ptit.touristservice.customer.model.response.SaveLocationDto(sl.id,sl.lat,sl.lon,sl.address) " +
//            "from SaveLocation sl join sl.user u " +
//            "where u.id = ?1")
//    List<SaveLocationDto> getSavedLocationDtos(String userID);

    List<SaveLocation> findAllByUser_Id(String userID);
    @Modifying
    @Transactional
    void deleteByIdAndUser_Id(String id,String userId);
}
