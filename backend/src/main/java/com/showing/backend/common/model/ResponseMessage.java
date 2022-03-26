package com.showing.backend.common.model;

public class ResponseMessage {

    private ResponseMessage() {
    }

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
    public static final String GET_REVIEW = "리뷰 조회 성공";
    public static final String MODIFY_REVIEW = "리뷰 수정 성공";
    public static final String DELETE_REVIEW = "리뷰 삭제 성공";

}
