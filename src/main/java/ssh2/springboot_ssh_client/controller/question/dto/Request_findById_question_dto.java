package ssh2.springboot_ssh_client.controller.question.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Request_findById_question_dto {
    private Long id;

    @Builder
    public Request_findById_question_dto(Long id) {
        this.id = id;
    }
}
