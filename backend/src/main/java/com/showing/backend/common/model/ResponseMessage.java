package com.showing.backend.common.model;

public class ResponseMessage {

    private ResponseMessage() {
    }

    // Common
    public static final String SERVER_ERROR =  "서버 에러입니다.";
    public static final String UNAUTHORIZED = "인증 되지 않은 유저입니다.";
    public static final String INVALID_INPUT = "Invalid Input 에러입니다.";

    // Auth
    public static final String GET_KAKAO_ACCESS_TOKEN = "카카오 토큰 생성 성공입니다.";
    public static final String GET_NAVER_ACCESS_TOKEN = "네이버 토큰 생성 성공입니다.";

    // Login
    public static final String REFRESH_TOKEN = "토큰 재발급 성공입니다";
    public static final String INVALID_TOKEN = "유효하지 않은 리프레쉬 토큰입니다.";
    public static final String LOGIN = "로그인 성공입니다.";
    public static final String LOGOUT = "로그아웃 성공입니다.";

    // Review
    public static final String ADD_REVIEW = "리뷰 추가 성공";

    //Rating
    public static final String ADD_RATING = "별점 등록 성공입니다.";
    
}
