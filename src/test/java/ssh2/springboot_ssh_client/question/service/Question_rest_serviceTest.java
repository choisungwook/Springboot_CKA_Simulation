package ssh2.springboot_ssh_client.question.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ssh2.springboot_ssh_client.controller.question.dto.Request_create_question_dto;
import ssh2.springboot_ssh_client.controller.question.dto.Request_findById_question_dto;
import ssh2.springboot_ssh_client.controller.question.dto.Response_findById_question_dto;
import ssh2.springboot_ssh_client.question.QuestionDomain;
import ssh2.springboot_ssh_client.question.QuestionRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Question_rest_serviceTest {
    @Autowired Question_rest_service question_rest_service;
    @Autowired QuestionRepository questionRepository;

    @Test
    public void 단일문제조회(){
        //given
        Request_create_question_dto question_dto1 = Request_create_question_dto.builder()
                .content("test content")
                .build();
        QuestionDomain new_question = create_question(question_dto1);

        //when
        Request_findById_question_dto request_dto = Request_findById_question_dto.builder()
                .id(new_question.getId())
                .build();
        Response_findById_question_dto find_questionId = question_rest_service.findById(request_dto);

        //then
        Assertions.assertThat(find_questionId.getId()).isEqualTo(new_question.getId());
    }

    @Test
    public void 문제생성(){
        Request_create_question_dto question_dto1 = Request_create_question_dto.builder()
                .content("111")
                .build();
        Request_create_question_dto question_dto2 = Request_create_question_dto.builder()
                .content("222")
                .build();

        create_question(question_dto1);
        create_question(question_dto2);
    }

    /***
     * 문제 생성
     * @param request dto
     * @return 생성한 문제 엔티티
     */
    private QuestionDomain create_question(Request_create_question_dto request){
        //given
        QuestionDomain new_question = request.toEntity();

        //when
        Long saveId = question_rest_service.save(request);

        //then
        QuestionDomain find_question = questionRepository.findById(saveId)
                .orElseThrow(
                        () -> new IllegalStateException("존재하지 않은 Id")
                );
        Assertions.assertThat(find_question.getContent()).isEqualTo(new_question.getContent());

        return find_question;
    }

}