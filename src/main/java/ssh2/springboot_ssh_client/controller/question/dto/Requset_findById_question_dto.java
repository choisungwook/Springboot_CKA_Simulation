package ssh2.springboot_ssh_client.controller.question.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Requset_findById_question_dto {
    private Long id;

    @Builder
    public Requset_findById_question_dto(Long id) {
        this.id = id;
    }
}
