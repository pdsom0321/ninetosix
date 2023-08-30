package com.ninetosix.apimodule.nts.service;

import com.ninetosix.apimodule.nts.dto.member.GenerateCodeReqDTO;
import com.ninetosix.apimodule.nts.dto.member.VerifyCodeReqDTO;
import com.ninetosix.coremodule.entity.AuthenticationCode;
import com.ninetosix.coremodule.repository.AuthenticationCodeRepository;
import com.ninetosix.coremodule.repository.MemberRepository;
import com.ninetosix.coremodule.vo.AuthenticationCodeType;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationCodeService {
    private final AuthenticationCodeRepository authenticationCodeRepository;
    private final MemberRepository memberRepository;
    private final JavaMailSender javaMailSender;

    public void generateCode(GenerateCodeReqDTO reqDTO) {
        String type = reqDTO.type();
        if(AuthenticationCodeType.SIGNUP.name().equals(type)) {
            if(memberRepository.existsByEmail(reqDTO.email())){
                throw new RuntimeException("이미 가입한 계정이 있습니다.");
            }
        }


        int code = generateRandomNumber();
        String title = getEmailTitle(type);
        String text = "귀하의 인증 번호는 " + code + " 입니다.";

        sendEmail(reqDTO.email(), title, text);
        authenticationCodeRepository.save(AuthenticationCode.create(code, reqDTO.email(), type));
    }

    private int generateRandomNumber() {
        Random ran = new Random();
        return ran.nextInt(888888) + 111111;
    }

    private String getEmailTitle(String type) {
        AuthenticationCodeType authenticationCodeType = AuthenticationCodeType.valueOf(type);

        return switch (authenticationCodeType) {
            case SIGNUP -> "회원가입 인증 번호 발급 안내 입니다.";
            case PASSWORD -> "비밀번호 인증 번호 발급 안내 입니다.";
        };
    }

    private void sendEmail(String email, String title, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(title);
        message.setText(text);
        javaMailSender.send(message);
    }

    public Boolean verifyCode(VerifyCodeReqDTO reqDTO) {
        AuthenticationCode authenticationCode = authenticationCodeRepository.findByCodeAndEmailAndTypeAndExpireDateGreaterThanAndExpired(reqDTO.code(), reqDTO.email(), AuthenticationCodeType.valueOf(reqDTO.type()), LocalDateTime.now(), false)
                .orElseThrow(() -> new NoSuchElementException("authentication code 정보가 없습니다."));

        authenticationCode.isEntered();
        return true;
    }
}
