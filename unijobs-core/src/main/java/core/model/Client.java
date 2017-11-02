package core.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Alex on 10/22/2017.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Client extends UniUser {

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<Request>  requests = new ArrayList<>();

    @OneToMany(mappedBy = "fromClient", fetch = FetchType.LAZY)
    private List<Recommendation> sent = new ArrayList<>();

    @OneToMany(mappedBy = "toClient", fetch = FetchType.LAZY)
    private List<Recommendation> received = new ArrayList<>();

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<Job> myJobs = new ArrayList<>();

    public Client(String username, String password, String email, String firstname, String lastname, Date dob, String phone){
        super(username, password, email, firstname, lastname, dob, phone);
    }

    public void addRequest(Request request){
        this.requests.add(request);
    }

    public void receiveRecommandation(Recommendation recommendation){
        this.received.add(recommendation);
    }

    public void sendRecommandation(Recommendation recommendation) {
        this.sent.add(recommendation);
    }

    public void addJob(Job job){
        this.myJobs.add(job);
    }
}
