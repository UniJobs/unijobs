package core.repository;

import core.model.Skill;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SkillRepository extends BaseRepository<Skill, Long>{

    @Query("SELECT s from Skill s where s.description = :description")
    Skill getSkillByDescription(@Param("description") String description);
}
