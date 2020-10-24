package ssh2.springboot_ssh_client.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public Long save(QuestionDomain requestDomain){
        return questionRepository.save(requestDomain).getId();
    }

    public List<QuestionDomain> findAll(){
        return questionRepository.findAll();
    }

    public QuestionDomain findById(Long id){
        QuestionDomain find_question = questionRepository.findById(id)
                .orElseThrow(
                        () -> new IllegalStateException("존재하지 않은 ID")
                );

        return find_question;
    }

}
