package com.showing.backend.common.model;

public class ResponseMessage {

    private ResponseMessage() {
    }

    // Common
    public static final String SERVER_ERROR =  "서버 에러";
    public static final String UNAUTHORIZED = "인증 되지 않은 유저";
    public static final String FORBIDDEN = "허용하지 않는 접근";
    public static final String NOT_FOUND = "Not Found 오류";

    // Auth
    public static final String GET_KAKAO_ACCESS_TOKEN = "카카오 토큰 생성 성공";
    public static final String GET_NAVER_ACCESS_TOKEN = "네이버 토큰 생성 성공";

    // Login
    public static final String REFRESH_TOKEN = "토큰 재발급 성공";
    public static final String INVALID_TOKEN = "유효하지 않은 리프레쉬 토큰";
    public static final String LOGIN = "로그인 성공";
    public static final String LOGOUT = "로그아웃 성공";
    public static final String GET_USER_INFO = "유저 정보 조회 성공";
    public static final String MODIFY_USER_INFO = "유저 정보 수정 성공";

    // Review
    public static final String ADD_REVIEW = "리뷰 추가 성공";
    public static final String GET_REVIEW = "리뷰 조회 성공";
    public static final String MODIFY_REVIEW = "리뷰 수정 성공";
    public static final String DELETE_REVIEW = "리뷰 삭제 성공";

    // Rating
    public static final String ADD_RATING = "별점 등록 성공";
    public static final String MODIFY_RATING = "별점 수정 성공";
    public static final String DELETE_RATING = "별점 삭제 성공";

    // Performance
    public static final String GET_SEASON = "시즌 정보 조회 성공";

    // Recommend
    public static final String GET_RECOMMEND = "추천목록 조회 성공";

}
