package com.whiskey.whiskey.controller;

import com.whiskey.whiskey.dto.request.AuthRequest;
import com.whiskey.whiskey.dto.response.AuthResponse;
import com.whiskey.whiskey.entity.User;
import com.whiskey.whiskey.security.JwtTokenProvider;
import com.whiskey.whiskey.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * 인증 컨트롤러
 * - 로그인, 회원가입 관련 API 제공
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(UserService userService, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 사용자 회원가입
     *
     * @param authRequest 이메일, 사용자명, 비밀번호
     * @return ResponseEntity<AuthResponse>
     */
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthRequest authRequest) {
        // 이미 존재하는 사용자 확인
        Optional<User> existingUser = userService.findByEmail(authRequest.getEmail());
        if (existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new AuthResponse(null, "이미 가입된 이메일입니다.", false));
        }

        // 새 사용자 생성
        User user = new User();
        user.setEmail(authRequest.getEmail());
        user.setUsername(authRequest.getUsername());
        user.setPassword(passwordEncoder.encode(authRequest.getPassword()));

        User savedUser = userService.saveUser(user);

        String token = jwtTokenProvider.generateToken(savedUser.getId(), savedUser.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new AuthResponse(token, "회원가입 성공", true));
    }

    /**
     * 사용자 로그인
     *
     * @param authRequest 이메일(또는 사용자명), 비밀번호
     * @return ResponseEntity<AuthResponse>
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        // 이메일로 사용자 찾기
        Optional<User> userOptional = userService.findByEmail(authRequest.getEmail());

        // 사용자가 없으면 사용자명으로 찾기
        if (userOptional.isEmpty()) {
            userOptional = userService.findByUsername(authRequest.getEmail());
        }

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthResponse(null, "사용자를 찾을 수 없습니다.", false));
        }

        User user = userOptional.get();

        // 비밀번호 검증
        if (!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthResponse(null, "비밀번호가 일치하지 않습니다.", false));
        }

        // JWT 토큰 생성
        String token = jwtTokenProvider.generateToken(user.getId(), user.getUsername());
        return ResponseEntity.ok(new AuthResponse(token, "로그인 성공", true));
    }
}
