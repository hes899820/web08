package net.datasa.web08.controller;

import jakarta.persistence.Column;
import lombok.extern.slf4j.Slf4j;
import net.datasa.web08.dto.UserDTO;
import net.datasa.web08.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
@Service

public class UserserviceController {
    @Autowired
    Userservice service;

    @GetMapping("input")
    public String input(){

        return "input";
    }

    @PostMapping("input")
    public String input(@ModelAttribute UserDTO dto){

        service.saveUser(dto);
        return "redirect:/";
    }

    /**
     * 모든 회원 목록 보기
     * @retrun 회원목록 출력 HTML*/
    @GetMapping("userlist")
    public String userlist(Model model) {
        List<UserDTO> list = service.userlist();
        model.addAttribute("list",list);
        return "userlist";


    }
}
