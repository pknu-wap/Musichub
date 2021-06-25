package com.wap.musichub.controller;

import com.wap.musichub.dto.MemberDto;
import com.wap.musichub.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class MemberController {

    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // Login page
    @GetMapping("/member/login")
    public String displayLogin() {
        return "member/login";
    }

    // Signup page
    @GetMapping("member/signup")
    public String displaySignup(MemberDto memberDto) {

        return "member/signup";
    }

    // execute signup
    @PostMapping("member/signup")
    public String executeSignup(@Valid MemberDto memberDto, Errors errors, Model model) {


        if (errors.hasErrors()) {
            // 회원가입 실패 시, 입력 데이터를 유지
            model.addAttribute("memberDto", memberDto);

            // 유효성 검사 통과 못한 필드와 메세지 핸들링
            Map<String, String> validatorResult = memberService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }

            return "member/signup";

        }

        if (checkEmailDuplicate(memberDto.getEmail()) || checkNicknameDuplicate(memberDto.getNickname())) {
            model.addAttribute("duplicate", "중복된 이메일 또는 닉네임입니다.");

            return "member/signup";
        }

        memberService.joinUser(memberDto);

        return "redirect:/member/login";
    }

    @GetMapping("member-email-exist/{email}")
    public boolean checkEmailDuplicate(@PathVariable String email) {
        return memberService.checkEmailDuplicate(email);
    }

    @GetMapping("member-nickname-exist/{nickname}")
    public boolean checkNicknameDuplicate(@PathVariable String nickname) {
        return memberService.checkNicknameDuplicate(nickname);
    }
}
