package core.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 10/22/2017.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Client extends UniUser {

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<Request>  requests = new ArrayList<>();

    @OneToMany(mappedBy = "fromClient", fetch = FetchType.LAZY)
    private List<Recommandation> sent = new ArrayList<>();

    @OneToMany(mappedBy = "toClient", fetch = FetchType.LAZY)
    private List<Recommandation> received = new ArrayList<>();
}
