package com.showing.backend.api.controller;

import com.showing.backend.api.request.ModifyUserInfoReq;
import com.showing.backend.api.response.LoginRes;
import com.showing.backend.api.response.TokenRes;
import com.showing.backend.api.service.AuthService;
import com.showing.backend.api.service.UserService;
import com.showing.backend.common.auth.JwtTokenProvider;
import com.showing.backend.common.auth.JwtUtil;
import com.showing.backend.common.exception.InvalidException;
import com.showing.backend.common.exception.handler.ErrorCode;
import com.showing.backend.common.exception.handler.ErrorResponse;
import com.showing.backend.common.model.BaseResponseBody;
import com.showing.backend.common.model.KakaoProfile;
import com.showing.backend.common.model.NaverProfile;
import com.showing.backend.common.model.RoleType;
import com.showing.backend.db.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

import static com.showing.backend.common.model.ResponseMessage.*;

@Api(value = "회원 API", tags = "User")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final AuthService authService;
    private final UserService userService;
    private final JwtTokenProvider tokenProvider;

    @ApiOperation(value = "카카오 토큰 요청", notes = "카카오 인가 코드로 액세스 토큰을 요청하는 api입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = GET_KAKAO_ACCESS_TOKEN),
            @ApiResponse(code = 500, message = SERVER_ERROR, response = ErrorResponse.class)
    })
    @GetMapping(value="/kakao/token")
    public ResponseEntity<BaseResponseBody> requestKakaoToken(@RequestParam String code){
        return ResponseEntity.ok(BaseResponseBody.of(HttpStatus.OK,GET_KAKAO_ACCESS_TOKEN, authService.getKakaoAccessToken(code)));
    }

    @ApiOperation(value = "카카오 로그인", notes = "카카오 액세스 토큰으로 유저 정보를 받아 jwt 토큰을 발급하고 전송하는 api입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = LOGIN, response = LoginRes.class),
            @ApiResponse(code = 500, message = SERVER_ERROR, response = ErrorResponse.class)
    })
    @GetMapping(value = "/kakao/login")
    public ResponseEntity<BaseResponseBody> login(@RequestParam String accessToken){
        // 1. access token으로 유저 정보 받아오기
        KakaoProfile kakaoProfile = authService.getKakaoUserInfo(accessToken);
        System.out.println(kakaoProfile.toString());

        // 2. DB에서 카카오회원번호로 회원 정보 불러오기
        String uid = Long.toString(kakaoProfile.getId());

        User user = userService.getUserByUid(uid).orElse(null);

        // 3. 없으면 DB에 저장
        if(user == null)
            user = userService.createKakaoUser(kakaoProfile);

        // 4. JWT token 발급
        String token = userService.createToken(user.getId(), RoleType.USER);
        String refreshToken = userService.createRefreshToken(user.getId());

        LoginRes loginRes = new LoginRes(user.getId(), token, refreshToken, user.getNickname(), user.getUserImage());

        return ResponseEntity.ok(BaseResponseBody.of(HttpStatus.CREATED, LOGIN, loginRes));
    }

    @ApiOperation(value = "네이버 토큰 요청", notes = "네이버 인가 코드로 액세스 토큰을 요청하는 api입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = GET_NAVER_ACCESS_TOKEN),
            @ApiResponse(code = 500, message = SERVER_ERROR, response = ErrorResponse.class)
    })
    @GetMapping(value="/naver/token")
    public ResponseEntity<BaseResponseBody> requestNaverToken(@RequestParam String code, @RequestParam String state){
        return ResponseEntity.ok(BaseResponseBody.of(HttpStatus.OK,GET_NAVER_ACCESS_TOKEN, authService.getNaverAccessToken(code, state)));
    }

    @ApiOperation(value = "네이버 로그인", notes = "네이버 액세스 토큰으로 유저 정보를 받아 jwt 토큰을 발급하고 전송하는 api입니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = LOGIN, response = LoginRes.class),
            @ApiResponse(code = 500, message = SERVER_ERROR, response = ErrorResponse.class)
    })
    @GetMapping(value = "/naver/login")
    public ResponseEntity<BaseResponseBody> naverLogin(@RequestParam String accessToken){

        NaverProfile naverProfile = authService.getNaverUserInfo(accessToken);

        String uid = naverProfile.getResponse().getId();

        User user = userService.getUserByUid(uid).orElse(null);

        if(user == null)
            user = userService.createNaverUser(naverProfile);

        String token = userService.createToken(user.getId(), RoleType.USER);
        String refreshToken = userService.createRefreshToken(user.getId());

        return ResponseEntity.ok(BaseResponseBody.of(HttpStatus.CREATED, LOGIN, new LoginRes(user.getId(), token,refreshToken, user.getNickname(), user.getUserImage())));
    }

    @ApiOperation(value = "로그아웃",notes = "토큰을 만료 시킨 후 로그아웃한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = LOGOUT),
            @ApiResponse(code = 401, message = UNAUTHORIZED, response = ErrorResponse.class),
            @ApiResponse(code = 500, message = SERVER_ERROR, response = ErrorResponse.class)
    })
    @GetMapping(value = "/logout/{userId}")
    public ResponseEntity<BaseResponseBody> logout(@PathVariable Long userId) {
        userService.logout(userId);
        return ResponseEntity.ok(BaseResponseBody.of(HttpStatus.OK,LOGOUT));
    }

    @ApiOperation(value = "토큰 재발급 요청", notes = "만료된 accessToken을 refreshToken을 통해 재발급하는 api입니다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = REFRESH_TOKEN, response = TokenRes.class),
            @ApiResponse(code = 401, message = UNAUTHORIZED, response = ErrorResponse.class),
            @ApiResponse(code = 403, message = INVALID_TOKEN, response = ErrorResponse.class),
            @ApiResponse(code = 500, message = SERVER_ERROR, response = ErrorResponse.class)
    })
    @PostMapping(value = "/refresh/{userId}")
    public ResponseEntity<BaseResponseBody> refreshToken(@PathVariable Long userId, @RequestParam String refreshToken){
        if(!tokenProvider.validateToken(refreshToken))
            throw new InvalidException(ErrorCode.REFRESH_TOKEN_INVALID);
        return ResponseEntity.ok(BaseResponseBody.of(HttpStatus.CREATED,REFRESH_TOKEN,userService.refreshAccessToken(userId,refreshToken)));
    }

        return ResponseEntity.ok(BaseResponseBody.of(HttpStatus.CREATED,REFRESH_TOKEN,userService.refreshAccessToken(id,refreshToken)));
    }

    @ApiOperation(value = "유저 정보 수정", notes = "마이페이지에서 해당 유저의 정보를 수정하는 api입니다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = MODIFY_USER_INFO, response = TokenRes.class),
            @ApiResponse(code = 401, message = UNAUTHORIZED, response = ErrorResponse.class),
            @ApiResponse(code = 403, message = INVALID_TOKEN, response = ErrorResponse.class),
            @ApiResponse(code = 500, message = SERVER_ERROR, response = ErrorResponse.class)
    })
    @PutMapping(value = "")
    public ResponseEntity<BaseResponseBody> modifyUserInfo(@RequestBody ModifyUserInfoReq req) {
        // userId 유효성 체크
        Long userId = req.getUserId();
        if(!Objects.equals(req.getUserId(), JwtUtil.getCurrentId().orElse(null)))
            throw new AccessDeniedException("Access denied");
        userService.modifyUserInfo(req);
        return ResponseEntity.ok(BaseResponseBody.of(HttpStatus.OK,MODIFY_USER_INFO));
    }
}