package net.datasa.web08.controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import net.datasa.web08.dto.UserDTO;
import net.datasa.web08.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Slf4j
@Controller
@Service
public class SessionController {

    @Autowired
    Userservice service;


    //로그인 처리
    @GetMapping("login")
    public String login(){
        return "login";
    }
    @PostMapping("login")
    public String loginProcess(
            @RequestParam("id") String id,
            @RequestParam("password") String password,
            HttpSession session, Model model
    ){
        //디비에 있는 거 맵으로 만든다 아이디 키값, 비밀번호 벨류값 each문으로 돌리며 키와 벨류값이 맞으면 로그인
        //아니면.. 디비에서 findId해서 값을 찾는다 일치하면 비밀번호를 가져와서 비교해본다? 둘다 맞으면 로그인
        UserDTO loginUser = service.login(id, password);

        if (loginUser != null) {
            session.setAttribute("loginID", loginUser.getId());
            return "redirect:/"; // 로그인 성공
        } else {
            model.addAttribute("error", "아이디 또는 비밀번호가 일치하지 않습니다.");
            return "login"; // 로그인 실패
        }


    }

    @GetMapping("/logout")
    public String logout(HttpSession session){ //로그아웃

        session.removeAttribute("loginID");

        return "redirect:/";

    }
}
