package ssh2.springboot_ssh_client.controller.question;

import org.assertj.core.api.Assertions;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import ssh2.springboot_ssh_client.controller.question.dto.Request_create_question_dto;
import ssh2.springboot_ssh_client.question.QuestionDomain;
import ssh2.springboot_ssh_client.question.QuestionRepository;
import ssh2.springboot_ssh_client.question.service.Question_rest_service;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Question_APIControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    Question_rest_service question_rest_service;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    QuestionRepository questionRepository;

    @Test
    public void 문제조회() throws JSONException {
        //given
        Request_create_question_dto question_dto1 = Request_create_question_dto.builder()
                .content("111")
                .build();
        QuestionDomain new_question = create_question(question_dto1);

        String url = "http://localhost:" + port + "/api/v1/question/" + new_question.getId();

        //when
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        //then
        // 1. 응답코드
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        // 2. json응답
        JSONObject responseBody = new JSONObject(response.getBody());

        long respone_id = Long.parseLong(String.valueOf(responseBody.get("id")));
        Assertions.assertThat(respone_id).isEqualTo(new_question.getId());
        Assertions.assertThat(responseBody.get("content")).isEqualTo(new_question.getContent());
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