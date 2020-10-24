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

    private String content;

    @Builder
    public QuestionDomain(String content) {
        this.content = content;
    }
}
