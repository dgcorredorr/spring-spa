package com.techcamp.spa.infrastructure.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class ClientJdbcRepository {
    private final DataSource dataSource;

    @Autowired
    public ClientJdbcRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void deleteInactiveClients() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "{call UTILS.DELETE_INACTIVE_CLIENTS}";
        jdbcTemplate.execute(sql);
        System.out.println("Procedimiento masivo ejecutado on-demand");
    }
}
