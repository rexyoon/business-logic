package com.whiskey.whiskey.service;

import com.whiskey.whiskey.entity.Whiskey;
import com.whiskey.whiskey.repository.WhiskeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 위스키 관리 서비스
 * - 위스키 관련 비즈니스 로직을 처리
 */
@Service
public class WhiskeyService {

    private final WhiskeyRepository whiskeyRepository;

    @Autowired
    public WhiskeyService(WhiskeyRepository whiskeyRepository) {
        this.whiskeyRepository = whiskeyRepository;
    }

    /**
     * 위스키 지역으로 찾기
     *
     * @param region 지역
     * @return List<Whiskey>
     */
    public List<Whiskey> findByRegion(String region) {
        return whiskeyRepository.findByRegion(region);
    }

    /**
     * 위스키 국가로 찾기
     *
     * @param country 국가
     * @return List<Whiskey>
     */
    public List<Whiskey> findByCountry(String country) {
        return whiskeyRepository.findByCountry(country);
    }

    /**
     * 위스키 이름으로 찾기
     *
     * @param name 위스키 이름
     * @return Optional<Whiskey>
     */
    public Optional<Whiskey> findByName(String name) {
        return whiskeyRepository.findByName(name);
    }

//    /**
//     * 위스키 저장
//     *
//     * @param whiskey 위스키 정보
//     * @return Whiskey
//     */
//    public Whiskey saveWhiskey(Whiskey whiskey) {
//        return whiskeyRepository.save(whiskey);
//    }
//
//    /**
//     * 위스키 삭제
//     *
//     * @param whiskeyId 위스키 ID
//     */
//    public void deleteWhiskey(Long whiskeyId) {
//        whiskeyRepository.deleteById(whiskeyId);
//    }
}
