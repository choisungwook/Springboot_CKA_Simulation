package ssh2.springboot_ssh_client.sshclient;

import lombok.Getter;

@Getter
public class SSHConnectionInfo {
    private String user = "test";
    private String host = "127.0.0.1";
    private String password = "toor";
    private int port = 3333;
}
