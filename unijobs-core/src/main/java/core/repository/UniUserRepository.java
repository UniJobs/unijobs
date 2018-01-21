package core.repository;

import core.model.Request;
import core.model.UniUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UniUserRepository extends BaseRepository<UniUser,Long> {

    @Query("SELECT u FROM UniUser u WHERE u.username = :username")
    UniUser getUniUserByUsername(@Param("username")String username);

    @Query("SELECT u FROM UniUser u WHERE u.id = :id")
    UniUser getUniUserById(@Param("id")Integer id);

    List<UniUser> findByEmail(String email);
    List<UniUser> findAllByToRequests(Request request);
    List<UniUser> findAllByFromRequests(Request request);
}
