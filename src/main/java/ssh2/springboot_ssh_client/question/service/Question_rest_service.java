package ssh2.springboot_ssh_client.question.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ssh2.springboot_ssh_client.controller.question.dto.Request_create_question_dto;
import ssh2.springboot_ssh_client.controller.question.dto.Request_findAll_question_dto;
import ssh2.springboot_ssh_client.controller.question.dto.Response_findById_question_dto;
import ssh2.springboot_ssh_client.question.QuestionDomain;
import ssh2.springboot_ssh_client.question.QuestionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Question_rest_service {
    private final QuestionRepository questionRepository;

    public Response_findById_question_dto findById(Long id){
        QuestionDomain find_question = questionRepository.findById(id)
                .orElseThrow(
                        () -> new IllegalStateException("존재하지 않은 Id")
                );

        return Response_findById_question_dto.builder()
                .id(find_question.getId())
                .content(find_question.getContent())
                .build();

    }

    public Long save(Request_create_question_dto request){
        Long saveId = questionRepository.save(request.toEntity()).getId();
        return saveId;
    }

    public List<Request_findAll_question_dto> findAll(){
        List<QuestionDomain> all_questions = questionRepository.findAll();

        List<Request_findAll_question_dto> dtos = all_questions.stream()
                .map(question -> Request_findAll_question_dto.builder()
                        .id(question.getId())
                        .content(question.getContent())
                        .build())
                .collect(Collectors.toList());

        return dtos;
    }
}
