package com.whiskey.whiskey.service;

import com.whiskey.whiskey.entity.TasteProfile;
import com.whiskey.whiskey.repository.TasteProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 맛 프로필 관리 서비스
 * - 사용자의 맛 프로필 관련 비즈니스 로직을 처리
 */
@Service
public class TasteProfileService {

    private final TasteProfileRepository tasteProfileRepository;

    @Autowired
    public TasteProfileService(TasteProfileRepository tasteProfileRepository) {
        this.tasteProfileRepository = tasteProfileRepository;
    }

    /**
     * 사용자 ID로 맛 프로필 찾기
     *
     * @param userId 사용자 ID
     * @return Optional<TasteProfile>
     */
    public Optional<TasteProfile> findByUserId(Long userId) {
        return tasteProfileRepository.findByUserId(userId);
    }

    /**
     * 맛 프로필 저장
     *
     * @param tasteProfile 맛 프로필 정보
     * @return TasteProfile
     */
    public TasteProfile saveTasteProfile(TasteProfile tasteProfile) {
        return tasteProfileRepository.save(tasteProfile);
    }

    /**
     * 맛 프로필 삭제
     *
     * @param tasteProfileId 맛 프로필 ID
     */
    public void deleteTasteProfile(Long tasteProfileId) {
        tasteProfileRepository.deleteById(tasteProfileId);
    }
}
