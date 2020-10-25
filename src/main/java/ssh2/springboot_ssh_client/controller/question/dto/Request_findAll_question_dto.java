package ssh2.springboot_ssh_client.controller.question.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Request_findAll_question_dto {
    private Long id;
    private String question;

    @Builder
    public Request_findAll_question_dto(Long id, String question) {
        this.id = id;
        this.question = question;
    }
}
