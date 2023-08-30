package com.ninetosix.apimodule.nts.dto.member;

public record VerifyCodeReqDTO(String email, String type, int code) {}
