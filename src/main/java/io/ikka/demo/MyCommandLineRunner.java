package io.ikka.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import java.sql.Types;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Component
public class MyCommandLineRunner implements CommandLineRunner {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
        simpleJdbcCall.withProcedureName("test_ret_val")
                .declareParameters(
                        new SqlParameter("input1", Types.INTEGER),
                        new SqlOutParameter("output1", Types.INTEGER))
                .withReturnValue();
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("input1", 1);
        Map<String, Object> execute = simpleJdbcCall.execute(params);
        log.info("{}", execute);
    }
}
