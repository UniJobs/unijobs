package core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * Created by Ionut on 10/22/2018.
 */

@NoRepositoryBean
@Transactional
public interface BaseRepository<T, ID extends Serializable>
        extends JpaRepository<T, ID> {
}
