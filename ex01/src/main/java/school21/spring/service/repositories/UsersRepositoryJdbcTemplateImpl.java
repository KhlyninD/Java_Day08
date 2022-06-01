package school21.spring.service.repositories;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.xml.SqlXmlFeatureNotImplementedException;
import school21.spring.service.models.User;


import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository <User>{

    private static final String DROP_TABLE = "drop table if exists public.user";
    private static final String CREATE_TABLE = "create table public.user (identifier int, email varchar(30))";
    private static final String FIND_BY_ID = "select * from public.user where identifier=?";
    private static final String FIND_ALL = "select * from public.user";
    private static final String SAVE = "insert into public.user (identifier, email) values (?, ?)";
    private static final String UPDATE = "update public.user set email=? where identifier=?";
    private static final String DELETE = "delete from public.user where identifier=?";
    private static final String FIND_BY_EMAIL = "select * from public.user where email=?";

    JdbcTemplate jdbcTemplate;

    public UsersRepositoryJdbcTemplateImpl(DataSource ds) {
        this.jdbcTemplate = new JdbcTemplate(ds);
    }

    private void init() {
        jdbcTemplate.update(DROP_TABLE);
        jdbcTemplate.update(CREATE_TABLE);
    }

    @Override
    public User findById(Long id){
        try {
            return (User) jdbcTemplate.queryForObject(FIND_BY_ID,
                    new BeanPropertyRowMapper(User.class), id);
        } catch (Exception e) {}
        return null;
    }

    @Override
    public List<User> findAll() {
        try {
            return jdbcTemplate.query(FIND_ALL, new BeanPropertyRowMapper(User.class));
        } catch (Exception e) {}
        return null;
    }

    @Override
    public void save(User entity) {
        jdbcTemplate.update(SAVE, entity.getIdentifier(), entity.getEmail());
    }

    @Override
    public void update(User entity) {
        jdbcTemplate.update(UPDATE, entity.getEmail(), entity.getIdentifier());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(DELETE, id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            return Optional.of(Objects.requireNonNull(jdbcTemplate.queryForObject(FIND_BY_EMAIL,
                    new BeanPropertyRowMapper<>(User.class), email)));
        } catch (Exception e) {}
        return null;
    }
}
