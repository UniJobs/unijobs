package core.repository;

import core.model.Voter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VoterRepository extends BaseRepository<Voter, Long> {

    @Query("SELECT u FROM Voter u WHERE u.username = :username")
    Voter getVoterByUsername(@Param("username") String username);

}
