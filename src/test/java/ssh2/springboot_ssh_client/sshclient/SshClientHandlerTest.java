package ssh2.springboot_ssh_client.sshclient;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SshClientHandlerTest {
    @Autowired SshClientHandler sshClientHandler;

    @Test
    public void 연결테스트(){
        sshClientHandler.Init();
        sshClientHandler.disconnect();
    }

    @Test
    public void 명령어한번실행(){
        sshClientHandler.Init();
        String results = sshClientHandler.send_command("ls");
        System.out.println("========");
        System.out.println(results);
        System.out.println("========");
        sshClientHandler.disconnect();
    }

    @Test
    public void 명령어여러번실행(){
        sshClientHandler.Init();
        String results;
        // 1차 실행
        results = sshClientHandler.send_command("ls");
        System.out.println("========");
        System.out.println(results);
        System.out.println("========");

        // 2차 실행: 파이프라인 확인
        results = sshClientHandler.send_command("head /etc/passwd | grep -i root");
        System.out.println("========");
        System.out.println(results);
        System.out.println("========");

        sshClientHandler.disconnect();
    }
}