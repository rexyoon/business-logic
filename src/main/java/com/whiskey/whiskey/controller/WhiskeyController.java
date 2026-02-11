package com.whiskey.whiskey.controller;

import com.whiskey.whiskey.entity.Whiskey;
import com.whiskey.whiskey.service.WhiskeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * 위스키 관리 컨트롤러
 * - 위스키 관련 API 엔드포인트 제공
 */
@RestController
@RequestMapping("/api/whiskeys")
public class WhiskeyController {

    private final WhiskeyService whiskeyService;

    @Autowired
    public WhiskeyController(WhiskeyService whiskeyService) {
        this.whiskeyService = whiskeyService;
    }

    /**
     * 위스키 지역으로 찾기
     *
     * @param region 지역
     * @return ResponseEntity<List<Whiskey>>
     */
    @GetMapping("/region/{region}")
    public ResponseEntity<List<Whiskey>> getWhiskeyByRegion(@PathVariable String region) {
        List<Whiskey> whiskeys = whiskeyService.findByRegion(region);
        return ResponseEntity.ok(whiskeys);
    }

    /**
     * 위스키 국가로 찾기
     *
     * @param country 국가
     * @return ResponseEntity<List<Whiskey>>
     */
    @GetMapping("/country/{country}")
    public ResponseEntity<List<Whiskey>> getWhiskeyByCountry(@PathVariable String country) {
        List<Whiskey> whiskeys = whiskeyService.findByCountry(country);
        return ResponseEntity.ok(whiskeys);
    }

    /**
     * 위스키 이름으로 찾기
     *
     * @param name 위스키 이름
     * @return ResponseEntity<Whiskey>
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<Whiskey> getWhiskeyByName(@PathVariable String name) {
        Optional<Whiskey> whiskey = whiskeyService.findByName(name);
        return whiskey.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * 위스키 등록
     *
     * @param whiskey 위스키 정보
     * @return ResponseEntity<Whiskey>
     */
    @PostMapping
    public ResponseEntity<Whiskey> createWhiskey(@RequestBody Whiskey whiskey) {
        Whiskey savedWhiskey = whiskeyService.saveWhiskey(whiskey);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedWhiskey);
    }

    /**
     * 위스키 정보 수정
     *
     * @param whiskeyId 위스키 ID
     * @param whiskey 수정할 위스키 정보
     * @return ResponseEntity<Whiskey>
     */
    @PutMapping("/{whiskeyId}")
    public ResponseEntity<Whiskey> updateWhiskey(@PathVariable Long whiskeyId, @RequestBody Whiskey whiskey) {
        whiskey.setId(whiskeyId);
        Whiskey updatedWhiskey = whiskeyService.saveWhiskey(whiskey);
        return ResponseEntity.ok(updatedWhiskey);
    }

    /**
     * 위스키 삭제
     *
     * @param whiskeyId 위스키 ID
     * @return ResponseEntity<Void>
     */
    @DeleteMapping("/{whiskeyId}")
    public ResponseEntity<Void> deleteWhiskey(@PathVariable Long whiskeyId) {
        whiskeyService.deleteWhiskey(whiskeyId);
        return ResponseEntity.noContent().build();
    }
}
