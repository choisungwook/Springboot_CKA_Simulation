package ssh2.springboot_ssh_client.controller.question.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssh2.springboot_ssh_client.question.QuestionDomain;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Request_create_question_dto {
    private String question;

    @Builder
    public Request_create_question_dto(String question) {
        this.question = question;
    }

    public QuestionDomain toEntity(){
        return QuestionDomain.builder()
                    .question(question)
                    .build();
    }
}
