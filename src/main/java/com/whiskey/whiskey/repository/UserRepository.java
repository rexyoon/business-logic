package com.whiskey.whiskey.repository;

import com.whiskey.whiskey.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 사용자 데이터 접근 인터페이스
 * - User 엔티티에 대한 CRUD 및 사용자 관련 쿼리 제공
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * 이메일로 사용자 찾기
     * @param email 사용자 이메일
     * @return Optional<User>
     */
    Optional<User> findByEmail(String email);

    /**
     * 사용자 이름으로 사용자 찾기
     * @param username 사용자 이름
     * @return Optional<User>
     */
    Optional<User> findByUsername(String username);
}
