package com.example.mbboard.controller.cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.mbboard.dto.Member;
import com.example.mbboard.service.ILoginService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class CookieLoginController {
   @Autowired ILoginService loginService;
   
   @GetMapping("/cookieLogin")
   public String cookieLogin() {
      return "/cookie/cookieLogin";
   }
   
   @GetMapping("/cookieLogout")
   public String logout(HttpServletResponse response) {
      Cookie loginMemberId = new Cookie("loginMemberId", null);
      response.addCookie(loginMemberId);
      return "/cookie/cookieLogin";
   }
   
   @PostMapping("/cookieLogin")
   public String login(Member paramMember, HttpServletResponse response) {
      Member loginMember = loginService.login(paramMember);
      if(loginMember != null) {
         // 로그인 성공 정보도 쿠키에 저장
         Cookie loginMemberId = new Cookie("loginMemberId", paramMember.getMemberId());
         response.addCookie(loginMemberId);
         return "redirect:/cookieSuccess";   

      }
      return "redirect:/cookieLogin";
   }
   
   @GetMapping("/cookieSuccess")
   public String cookieSuccess(@CookieValue(value = "loginMemberId", required = false) String loginMemberId) {
      // 로그인 전이라면 redirect:/cookieLogin
      if(loginMemberId == null || loginMemberId.equals("")) {
         return "redirect:/cookieLogin";
      }
      // 로그인 되어 있다면
      return "/cookie/cookieSuccess";
   }
   
}
