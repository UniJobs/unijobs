package core.service;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;


@Service
public class NotificationServiceImpl implements NotificationService {
    private SocketIOServer server ;
    private List<SocketIOClient> clients;
    private List<Integer> ids;

    public NotificationServiceImpl(){
        clients = new ArrayList<>();
        ids = new ArrayList<>();
        Configuration config = new Configuration();
        config.setHostname("192.168.0.24");
        config.setPort(8081);
        server = new SocketIOServer(config);
        server.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient socketIOClient) {
                System.out.println("some1 connected");
                clients.add(socketIOClient);
            }
        });
        server.addEventListener("userId", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient client, String data, AckRequest ackSender) throws Exception {
                System.out.println("some1 sent");
                ids.add(Integer.parseInt(data));
            }
        });
        server.start();
    }

    public void notificationJobAdded(Integer excluded){
        System.out.println("some1 notified");
        for(int i=0;i<clients.size();i++)
            clients.forEach(socket -> socket.sendEvent("message", "A job that might be of interest for you has been added"));
    }

    public void notificationStatus(List<Integer> ids){
        for(int i=0;i<clients.size();i++)
            if(ids.contains(this.ids.get(i)))
                clients.forEach(socket -> socket.sendEvent("message", "One of your Request had its STATUS updated !"));
    }

    public void notificationRequested(Integer id){
        if(ids.contains(id))
            clients.get(getClientPosition(id)).sendEvent("message", "You have a new Request for one of your jobs !");
    }

    public Integer getClientPosition(Integer id){
        for(int i=0;i<ids.size();i++)
            if(ids.get(i).equals(id))
                return i;
        return -7;
    }

    public Integer getIDPosition(SocketIOClient client){
        for(int i=0;i<clients.size();i++)
            if(clients.get(i).equals(client))
                return i;
        return -7;
    }

    @PreDestroy
    private void destroy(){
        server.stop();
    }
}
