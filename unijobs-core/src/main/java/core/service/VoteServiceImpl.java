package core.service;

import core.model.Vote;
import core.repository.VoteRepository;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Service
public class VoteServiceImpl implements VoteService {

    private static final Logger log = LoggerFactory.getLogger(VoteService.class);

    @Autowired
    VoteRepository voteRepository;

    @Override
    @Transactional
    public void addVote(Vote vote) {
        log.trace("createVote: vote={}", vote);

        vote = voteRepository.save(vote);

        log.trace("createVote: vote={}", vote);
    }

    @Override
    public void shuffle() throws SQLException {
        String sql = String.format("CREATE TABLE votes2 AS SELECT * FROM votes ORDER BY RANDOM();\n" +
                "DROP TABLE votes;\n" +
                "ALTER TABLE votes2\n" +
                "RENAME TO votes;" );
        Properties connectionProps = new Properties();
        connectionProps.put("user", "postgres");
        connectionProps.put("password", "postgres");
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/crypto-master",connectionProps);
        conn.createStatement().execute(sql);
    }
}