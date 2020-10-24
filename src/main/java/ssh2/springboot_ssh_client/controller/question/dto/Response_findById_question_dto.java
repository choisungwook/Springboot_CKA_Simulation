package ssh2.springboot_ssh_client.controller.question.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Response_findById_question_dto {
    private Long id;
    private String content;

    @Builder
    public Response_findById_question_dto(Long id, String content) {
        this.id = id;
        this.content = content;
    }
}
