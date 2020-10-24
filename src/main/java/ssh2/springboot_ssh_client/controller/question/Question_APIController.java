package ssh2.springboot_ssh_client.controller.question;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ssh2.springboot_ssh_client.controller.question.dto.Response_findById_question_dto;
import ssh2.springboot_ssh_client.question.service.Question_rest_service;

@RestController
@RequiredArgsConstructor
public class Question_APIController {
    private final Question_rest_service question_rest_service;

    @GetMapping("/api/v1/question/{id}")
    public Response_findById_question_dto get_question(@PathVariable Long id){
        Response_findById_question_dto response = question_rest_service.findById(id);
        return response;
    }


}
