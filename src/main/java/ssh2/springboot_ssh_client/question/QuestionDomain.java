package ssh2.springboot_ssh_client.question;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "question")
public class QuestionDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String question;

    private String marking_command;

    private String answer;

    @Builder
    public QuestionDomain(String question, String marking_command, String answer) {
        this.question = question;
        this.marking_command = marking_command;
        this.answer = answer;
    }
}
