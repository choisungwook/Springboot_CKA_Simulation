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
            int question_length = 6;

            questionGeneralService.save(Create_question1());
            questionGeneralService.save(Create_question2());
            questionGeneralService.save(Create_question3());
            questionGeneralService.save(Create_question4());
            questionGeneralService.save(Create_question5());
            questionGeneralService.save(Create_question6());
            questionGeneralService.save(Create_question7());
        }

    }

    /***
     * 문제 1번
     * @return
     */
    private static QuestionDomain Create_question1(){
        String question = "pod를 생성하세요<br>pod 이름: test<br>image:nginx";
        QuestionDomain new_question = Create_question(question, "kubectl get po -o json | jq -r '.items[]  | [.metadata.name, .status.phase] | @tsv' | grep test | grep Running");
        return new_question;
    }

    /***
     * 문제 2번
     * @return
     */
    private static QuestionDomain Create_question2(){
        QuestionDomain new_question = Create_question("Deployment를 생성하세요. 그리고 생성한 deploy를 서비스를 생성하세요<br>deployment 이름: redis-deploy<br>image=redis<br><br>서비스 이름:redis-service<br>port type: NodePort<br>port: 6379<br>target-port=6379", "미정");
        // deploy 생성 커맨드: kubectl create deploy redis-deploy --image=redis
        // service 생성 커맨드:  kubectl expose deploy redis-deploy --name=redis-service --type=NodePort --port=6379 --target-port=6379
        return new_question;
    }

    /***
     * 문제3번
     * @return
     */
    private static QuestionDomain Create_question3(){
        QuestionDomain new_question = Create_question("configmap을 생성하세요<br>configmap 이름: configmap-a<br>설정 키/값: hello=world", "미정");
        //커맨드: kubectl create configmap configmap-a --from-literal="hello=world"
        return new_question;
    }

    private static QuestionDomain Create_question4(){
        QuestionDomain new_question = Create_question("namespace hello를 생성하세요", "미정");
        //커맨드: kubectl create ns hello
        return new_question;
    }

    private static QuestionDomain Create_question5(){
        QuestionDomain new_question = Create_question("role을 생성하세요", "미정");
        return new_question;
    }

    private static QuestionDomain Create_question6(){
        QuestionDomain new_question = Create_question("node01에 있는 모든 파드를 다른 곳으로 전부 이동시키세요", "미정");
        // kubectl drain node01 --ignore-daemonsets
        return new_question;
    }

    private static QuestionDomain Create_question7(){
        QuestionDomain new_question = Create_question("NodeSelector을 이용해서 특정 node로 pod를 스케쥴하세요<br>node label: hello=world", "미정");
        // kubectl drain node01 --ignore-daemonsets
        return new_question;
    }

    private static QuestionDomain Create_question8(){
        QuestionDomain new_question = Create_question("deployment hello-deploy의 replica를 6개로 수정하세요", "미정");
        // kubectl drain node01 --ignore-daemonsets
        return new_question;
    }

    private static QuestionDomain Create_question9(){
        QuestionDomain new_question = Create_question("컨테이너 3개를 실행하는 pod를 생성하세요<br>컨테이너1 image: nginx<br>컨테이너2 image: redis<br>컨테이너3 image: memcahced", "미정");
        // kubectl drain node01 --ignore-daemonsets
        return new_question;
    }

    private static QuestionDomain Create_question10(){
        QuestionDomain new_question = Create_question("secret를 이용하여 pod를 생성하세요.", "미정");
        // kubectl drain node01 --ignore-daemonsets
        return new_question;
    }


    /***
     * 문제 초기화
     * @param
     * @return
     */
    private static QuestionDomain Create_question(String question, String marking_command){
        QuestionDomain new_question = QuestionDomain.builder()
                .question(question)
                .marking_command(marking_command)
                .build();

        return new_question;
    }
}
