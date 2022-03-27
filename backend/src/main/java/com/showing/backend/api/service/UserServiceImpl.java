package com.showing.backend.api.service;

import com.showing.backend.api.request.ModifyUserInfoReq;
import com.showing.backend.api.response.MyPageRes;
import com.showing.backend.api.response.TokenRes;
import com.showing.backend.api.response.UserInfoRes;
import com.showing.backend.common.auth.JwtTokenProvider;
import com.showing.backend.common.exception.NotFoundException;
import com.showing.backend.common.exception.handler.ErrorCode;
import com.showing.backend.common.model.KakaoProfile;
import com.showing.backend.common.model.NaverProfile;
import com.showing.backend.common.model.RoleType;
import com.showing.backend.db.entity.User;
import com.showing.backend.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final JwtTokenProvider tokenProvider;
    private final UserRepository userRepository;

    /*
    회원 정보 DB 저장
     */
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    /*
    카카오 로그인 회원 생성
     */
    @Override
    public User createKakaoUser(KakaoProfile kakaoProfile) {

        User user = new User();

        //카카오에서 받은 정보들로 회원 생성
        user.setUid(Long.toString(kakaoProfile.getId()));
        user.setNickname(kakaoProfile.getKakao_account().getProfile().getNickname());
        user.setEmail(kakaoProfile.getKakao_account().getEmail());
        user.setUserImage(kakaoProfile.getKakao_account().getProfile().getProfile_image_url());

        return createUser(user);
    }

    /*
    네이버 로그인 회원 생성
     */
    @Override
    public User createNaverUser(NaverProfile naverProfile) {
        
        User user = new User();

        //네이버에서 받은 정보들로 회원 생성
        user.setUid(naverProfile.getResponse().getId());
        user.setNickname(naverProfile.getResponse().getName());
        user.setEmail(naverProfile.getResponse().getEmail());
        user.setUserImage(naverProfile.getResponse().getProfile_image());

        return createUser(user);
    }

    /*
    id로 회원 조회
     */
    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    /*
    uid로 회원 조회
     */
    @Override
    public Optional<User> getUserByUid(String uid) {
        return userRepository.findByUid(uid);
    }

    /*
    accessToken 발급
     */
    @Override
    public String createToken(Long id, RoleType roleType){
        return tokenProvider.createToken(id.toString(),roleType);
    }

    /*
    refreshToken 발급
     */
    @Override
    public String createRefreshToken(Long id) {

        User user = getUserById(id).orElse(null);
        String refreshToken = tokenProvider.createRefreshToken();
        user.setRefreshToken(refreshToken);
        userRepository.save(user);

        return tokenProvider.createRefreshToken();
    }

    /*
    refreshToken으로 accessToken 재발급
     */
    public TokenRes refreshAccessToken(Long id, String refreshToken) {
        return new TokenRes(tokenProvider.createToken(String.valueOf(id),RoleType.USER),refreshToken);
    }

    /*
    로그아웃
     */
    @Override
    public void logout(Long id) {
        // refreshToken 초기화
        User user = getUserById(id).orElse(null);
        user.setRefreshToken(null);
        userRepository.save(user);

    }

    /*
    마이페이지 정보 조회
     */
    @Override
    public MyPageRes getMyPageInfo(Long id) {
        return null;
    }

    /*
    유저 정보 수정
     */
    @Override
    public void modifyUserInfo(ModifyUserInfoReq req) {

        User user = userRepository.findById(req.getUserId()).orElseThrow(()->new NotFoundException(ErrorCode.USER_NOT_FOUND));
        user.setNickname(req.getNickName());
        user.setIntroduce(req.getIntroduce());
        user.setUserImage(req.getUserImage());

        userRepository.save(user);
    }


}
