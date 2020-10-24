package ssh2.springboot_ssh_client.sshclient;

import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component
@Slf4j
public class SshClientHandler {
    private JSch jSch;
    private SSHConnectionInfo sshConnectionInfo;
    private Session session;
    private Channel channel;
    private ChannelExec channelExec;
    private InputStream inputStream;

    public void Init(){
        jSch = new JSch();
        sshConnectionInfo = new SSHConnectionInfo();
        create_session();
    }

    public void disconnect(){
        if(channel != null) channel.disconnect();
        if(session != null) session.disconnect();
    }

    public String send_command(String command){
        create_channel();
        StringBuilder out = new StringBuilder();

        if(channel != null){
            try {
                byte[] buffer = new byte[1024];

                channelExec = (ChannelExec) channel;
                channelExec.setCommand(command);
                channel.connect();

                // 입력 스트림 설정(명령어 실행결과 수신 스트림)
                inputStream = channel.getInputStream();
                log.info("ssh Channel configuration completed");
                while(channel.getExitStatus() == -1){
                    while(inputStream.available() > 0){
                        int readsize = inputStream.read(buffer, 0, 1024);
                        if(readsize < 0) break;
                        out.append(new String(buffer, 0, readsize));
                    }
                    if(channel.isClosed()){
                        break;
                    }
                }
                inputStream.close();
            } catch (JSchException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return out.toString();
    }

    /***
     * 세션 생성과 연결
     */
    private void create_session(){
        try {
            session = jSch.getSession(sshConnectionInfo.getUser(), sshConnectionInfo.getHost(), sshConnectionInfo.getPort());
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.setPassword(sshConnectionInfo.getPassword());
            session.connect();
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }

    /***
     * 채널 생성
     */
    private void create_channel(){
        try {
            channel =  session.openChannel("exec");

        } catch (JSchException e) {
            e.printStackTrace();
        }
    }
}
