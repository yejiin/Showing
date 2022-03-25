package com.showing.backend.api.service;

import com.showing.backend.api.response.TokenRes;
import com.showing.backend.common.model.KakaoProfile;
import com.showing.backend.common.model.NaverProfile;
import com.showing.backend.common.model.RoleType;
import com.showing.backend.db.entity.User;

import java.util.Optional;


public interface UserService {

    User createUser(User user);

    Optional<User> getUserById(Long id);

    Optional<User> getUserByUid(String uid);

    User createKakaoUser(KakaoProfile kakaoProfile);

    User createNaverUser(NaverProfile naverProfile);

    String createToken(Long id, RoleType roleType);

    String createRefreshToken(Long id);

    TokenRes refreshAccessToken(Long id, String refreshToken);

    void logout(Long id);

}
