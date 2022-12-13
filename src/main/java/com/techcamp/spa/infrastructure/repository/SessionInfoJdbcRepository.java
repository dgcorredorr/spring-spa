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
    @Autowired
    private DataSource dataSource;

    public Integer isAvailable(Long clientId, Short specialistId, Long appointmentId, LocalDateTime sessionDate) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "{? = call SESSION_UTILS.CHECK_AVAILABILITY(?, ?, ?, ?)}";
        Object result = jdbcTemplate.execute(sql, (CallableStatementCallback<Object>) cs -> {
            cs.registerOutParameter(1, Types.INTEGER);
            cs.setLong(2, clientId);
            cs.setShort(3, specialistId);
            cs.setLong(4, appointmentId);
            cs.setTimestamp(5,Timestamp.valueOf(sessionDate));
            cs.execute();
            return cs.getObject(1);
        });
//        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
//                .withCatalogName("SESSION_UTILS")
//                .withFunctionName("CHECK_AVAILABILITY");
//
//        SqlParameterSource params = new MapSqlParameterSource()
//                .addValue("CLIENT_ID_FA", clientId)
//                .addValue("SPECIALIST_ID_FA", specialistId)
//                .addValue("APPOINTMENT_ID_FA", appointmentId)
//                .addValue("SESSION_DATE_FA",Timestamp.valueOf(sessionDate));
//
//        System.out.println(Arrays.toString(params.getParameterNames()));
//
//        return jdbcCall.executeFunction(Integer.class, params);
        return (Integer) result;
    }
}
