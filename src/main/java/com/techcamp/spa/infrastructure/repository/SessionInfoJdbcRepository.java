package com.techcamp.spa.infrastructure.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;

@Repository
public class SessionInfoJdbcRepository {

    private final DataSource dataSource;

    @Autowired
    public SessionInfoJdbcRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Integer isAvailable(Long clientId, Short specialistId, Long appointmentId, LocalDateTime sessionDate) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "{? = call UTILS.CHECK_AVAILABILITY(?, ?, ?, ?)}";
        return (Integer) jdbcTemplate.execute(sql, (CallableStatementCallback<Object>) cs -> {
            cs.registerOutParameter(1, Types.INTEGER);
            cs.setLong(2, clientId);
            cs.setShort(3, specialistId);
            cs.setLong(4, appointmentId);
            cs.setTimestamp(5,Timestamp.valueOf(sessionDate));
            cs.execute();
            return cs.getObject(1);
        });
    }
}