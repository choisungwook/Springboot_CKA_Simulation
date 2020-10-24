package ssh2.springboot_ssh_client.controller.question;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ssh2.springboot_ssh_client.controller.question.dto.Request_findById_question_dto;
import ssh2.springboot_ssh_client.controller.question.dto.Response_findById_question_dto;
import ssh2.springboot_ssh_client.question.service.Question_rest_service;

@RestController
@RequiredArgsConstructor
public class QuestionController {
    private final Question_rest_service question_rest_service;

    @GetMapping("/api/v1/question")
    public Response_findById_question_dto get_question(@RequestBody Request_findById_question_dto request){
        Response_findById_question_dto response = question_rest_service.findById(request);
        return response;
    }


}
