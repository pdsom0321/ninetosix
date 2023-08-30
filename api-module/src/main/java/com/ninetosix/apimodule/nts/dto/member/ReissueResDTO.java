package com.ninetosix.apimodule.nts.dto.member;

public record ReissueResDTO(String accessToken, String refreshToken) {
    public static ReissueResDTO of(String accessToken, String refreshToken) {
        return new ReissueResDTO(accessToken, refreshToken);
    }
}
