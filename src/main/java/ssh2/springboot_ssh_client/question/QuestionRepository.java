package ssh2.springboot_ssh_client.question;


import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<QuestionDomain, Long> {
}
