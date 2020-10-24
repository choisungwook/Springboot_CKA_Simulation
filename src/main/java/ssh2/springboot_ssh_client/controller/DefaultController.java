package ssh2.springboot_ssh_client.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ssh2.springboot_ssh_client.question.service.Question_rest_service;

@Controller
@RequiredArgsConstructor
public class DefaultController {
    private final Question_rest_service question_rest_service;

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("questions", question_rest_service.findAll());

        return "index";
    }
}
