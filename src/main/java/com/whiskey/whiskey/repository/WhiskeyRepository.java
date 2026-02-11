package com.whiskey.whiskey.repository;

import com.whiskey.whiskey.entity.Whiskey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WhiskeyRepository extends JpaRepository<Whiskey, Long> {

    /**
     * 특정 지역 위스키 검색
     * @param region
     * @return
     */
    List<Whiskey> findByRegion(String region);

    /**
     * 특정 국가의 위스키 목록
     * @param country
     * @return List<Whiskey>
     */
    List<Whiskey> findByCountry(String country);

    /**
     * 위스키 이름으로 검색
     * @param name 위스키 이름
     * @return Optional<Whiskey>
     */
    Optional<Whiskey> findByName(String name);
}
