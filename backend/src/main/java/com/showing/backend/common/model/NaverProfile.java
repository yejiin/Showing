package com.showing.backend.common.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NaverProfile {
    private String resultcode;
    private String message;
    private Profile response;

    @Getter
    @NoArgsConstructor
    public class Profile {
        public String id;
        public String name;
        public String email;
        public String profile_image;
        public String mobile;
    }
}
