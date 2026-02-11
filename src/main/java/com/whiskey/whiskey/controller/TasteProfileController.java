package com.whiskey.whiskey.controller;

import com.whiskey.whiskey.entity.TasteProfile;
import com.whiskey.whiskey.service.TasteProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * 맛 프로필 관리 컨트롤러
 * - 맛 프로필 관련 API 엔드포인트 제공
 */
@RestController
@RequestMapping("/api/taste-profiles")
public class TasteProfileController {
    private final TasteProfileService tasteProfileService;
    @Autowired
    public TasteProfileController(TasteProfileService tasteProfileService) {
        this.tasteProfileService = tasteProfileService;
    }
    /**
     * 사용자의 맛 프로필 찾기
     * @param userId 사용자 ID
     * @return ResponseEntity<TasteProfile>
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<TasteProfile> getTasteProfileByUserId(@PathVariable Long userId) {
        Optional<TasteProfile> tasteProfile = tasteProfileService.findByUserId(userId);
        return tasteProfile.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    /**
     * 맛 프로필 등록
     * @param tasteProfile 맛 프로필 정보
     * @return ResponseEntity<TasteProfile>
     */
    @PostMapping
    public ResponseEntity<TasteProfile> createTasteProfile(@RequestBody TasteProfile tasteProfile) {
        TasteProfile savedTasteProfile = tasteProfileService.saveTasteProfile(tasteProfile);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTasteProfile);
    }
    /**
     * 맛 프로필 수정
     * @param tasteProfileId 맛 프로필 ID
     * @param tasteProfile 수정할 맛 프로필 정보
     * @return ResponseEntity<TasteProfile>
     */
    @PutMapping("/{tasteProfileId}")
    public ResponseEntity<TasteProfile> updateTasteProfile(@PathVariable Long tasteProfileId, @RequestBody TasteProfile tasteProfile) {
        tasteProfile.setId(tasteProfileId);
        TasteProfile updatedTasteProfile = tasteProfileService.saveTasteProfile(tasteProfile);
        return ResponseEntity.ok(updatedTasteProfile);
    }
    /**
     * 맛 프로필 삭제
     * @param tasteProfileId 맛 프로필 ID
     * @return ResponseEntity<Void>
     */
    @DeleteMapping("/{tasteProfileId}")
    public ResponseEntity<Void> deleteTasteProfile(@PathVariable Long tasteProfileId) {
        tasteProfileService.deleteTasteProfile(tasteProfileId);
        return ResponseEntity.noContent().build();
    }
}
