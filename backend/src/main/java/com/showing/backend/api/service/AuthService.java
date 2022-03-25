package com.showing.backend.api.service;

import com.showing.backend.api.response.TokenRes;
import com.showing.backend.common.model.KakaoProfile;
import com.showing.backend.common.model.NaverProfile;
import com.showing.backend.common.model.RoleType;

public interface AuthService {
    String getKakaoAccessToken(String code);

    KakaoProfile getKakaoUserInfo(String accessToken);

    String getNaverAccessToken(String code, String state);

    NaverProfile getNaverUserInfo(String accessToken);
}
