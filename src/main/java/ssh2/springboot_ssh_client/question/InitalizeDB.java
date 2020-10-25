package ssh2.springboot_ssh_client.question;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ssh2.springboot_ssh_client.question.service.Question_general_Service;

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
        private final Question_general_Service questionGeneralService;


        /***
         * 문제 생성
         */
        public void run(){
            questionGeneralService.save(Create_question1());
            questionGeneralService.save(Create_question2());
        }

    }

    /***
     * 문제 1번
     * @return
     */
    private static QuestionDomain Create_question1(){
        String question = "pod를 생성하세요<br>pod 이름: test<br>image:nginx";
        QuestionDomain new_question = Create_question(question);

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
     * @param
     * @return
     */
    private static QuestionDomain Create_question(String question){
        QuestionDomain new_question = QuestionDomain.builder()
                .question(question)
                .build();

        return new_question;
    }
}
