package ssh2.springboot_ssh_client.question;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
@Slf4j
public class InitalizeDB {
    private final Initalize_Service initalize_service;

    @PostConstruct
    public void run(){
        initalize_service.run();
        log.info("Database initialization success");
    }

    @Component
    @RequiredArgsConstructor
    static class Initalize_Service{
        private final QuestionService questionService;


        /***
         * 문제 생성
         */
        public void run(){
            questionService.save(Create_question1());
            questionService.save(Create_question2());
        }

    }

    /***
     * 문제 1번
     * @return
     */
    private static QuestionDomain Create_question1(){
        QuestionDomain new_question = Create_question("Create deployment");

        return new_question;
    }

    /***
     * 문제 2번
     * @return
     */
    private static QuestionDomain Create_question2(){
        QuestionDomain new_question = Create_question("Create service");

        return new_question;
    }

    /***
     * 문제 초기화
     * @param content
     * @return
     */
    private static QuestionDomain Create_question(String content){
        QuestionDomain new_question = QuestionDomain.builder()
                .content(content)
                .build();

        return new_question;
    }
}
