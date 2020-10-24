package ssh2.springboot_ssh_client.question.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ssh2.springboot_ssh_client.question.QuestionDomain;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class QuestionGeneralServiceTest {
    @Autowired
    Question_general_Service questionGeneralService;

    @Test
    public void 문제생성(){

        QuestionDomain question1 = create_question("1111");
        QuestionDomain question2 = create_question("333");
        QuestionDomain question3 = create_question("4444");
        QuestionDomain question4 = create_question("555");
        QuestionDomain question5 = create_question("555");
    }

    @Test
    public void 모든문제조회(){
        List<QuestionDomain> All_question = questionGeneralService.findAll();
        All_question.stream()
                .forEach(question -> System.out.println(question.getContent()));

    }
    
    /***
     * 문제 생성
     * @param content
     * @return 생성한 문제 엔티티
     */
    private QuestionDomain create_question(String content){
        //given
        QuestionDomain new_question = QuestionDomain.builder()
                .content(content)
                .build();
        //when
        Long saveId = questionGeneralService.save(new_question);

        //then
        QuestionDomain find_question = questionGeneralService.findById(saveId);
        Assertions.assertThat(find_question.getId()).isEqualTo(new_question.getId());
        Assertions.assertThat(find_question.getContent()).isEqualTo(new_question.getContent());

        return new_question;
    }
    
}