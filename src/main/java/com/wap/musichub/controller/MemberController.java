package com.wap.musichub.controller;

import com.wap.musichub.dto.MemberDto;
import com.wap.musichub.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
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
    @GetMapping("/member/signup")
    public String displaySignup(MemberDto memberDto) {
        return "member/signup";
    }

    // execute signup
    @PostMapping("/member/signup")
    public String executeSignup(@Valid MemberDto memberDto, Errors errors, Model model) {


        if (errors.hasErrors()) {
            // 회원가입 실패 시, 입력 데이터를 유지
            model.addAttribute("memberDto", memberDto);

            // 유효성 검사 통과 못한 필드와 메세지 핸들링
            Map<String, String> validatorResult = memberService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }

            return "/signup";

        }

        memberService.joinUser(memberDto);

        return "redirect:/member/login";
    }
}
