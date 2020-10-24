package ssh2.springboot_ssh_client.controller.question.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Response_marking_question_dto {
    private String results;

    @Builder
    public Response_marking_question_dto(String results) {
        this.results = results;
    }
}
