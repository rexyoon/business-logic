package com.whiskey.whiskey.service;

import com.whiskey.whiskey.entity.User;
import com.whiskey.whiskey.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 사용자 관리 서비스
 * - 사용자 관련 비즈니스 로직을 처리
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 사용자 이메일로 찾기
     *
     * @param email 사용자 이메일
     * @return Optional<User>
     */
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * 사용자 이름으로 찾기
     *
     * @param username 사용자 이름
     * @return Optional<User>
     */
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * 사용자 저장
     *
     * @param user 사용자 정보
     * @return User
     */
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /**
     * 사용자 삭제
     *
     * @param userId 사용자 ID
     */
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
