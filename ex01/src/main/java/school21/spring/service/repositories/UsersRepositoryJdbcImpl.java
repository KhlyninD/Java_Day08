package school21.spring.service.repositories;


import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository <User>{

    private static final String DROP_TABLE = "drop table if exists public.user";
    private static final String CREATE_TABLE = "create table public.user (identifier int, email varchar(30))";
    private static final String FIND_BY_ID = "select * from public.user where identifier=?";
    private static final String FIND_ALL = "select * from public.user";
    private static final String SAVE = "insert into public.user (identifier, email) values (?, ?)";
    private static final String UPDATE = "update public.user set email=? where identifier=?";
    private static final String DELETE = "delete from public.user where identifier=?";
    private static final String FIND_BY_EMAIL = "select * from public.user where email=";

    private DataSource ds;

    public UsersRepositoryJdbcImpl(DataSource ds) {
        this.ds = ds;
    }

    private void init() {
        try (Connection conn = ds.getConnection();
             PreparedStatement drop = conn.prepareStatement(DROP_TABLE);
             PreparedStatement create = conn.prepareStatement(CREATE_TABLE)) {
            drop.executeUpdate();
            create.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findById(Long id) {
        User user = null;
        try (Connection connection = ds.getConnection();
             PreparedStatement  preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User(id, resultSet.getString(2));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> usersList = new ArrayList<>();
        try (Connection connection = ds.getConnection();
             PreparedStatement  preparedStatement = connection.prepareStatement(FIND_ALL)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                usersList.add(new User(resultSet.getLong(1), resultSet.getString(2)));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }

    @Override
    public void save(User entity) {
        try (Connection connection = ds.getConnection();
             PreparedStatement  preparedStatement = connection.prepareStatement(SAVE)){
            preparedStatement.setLong(1, entity.getIdentifier());
            preparedStatement.setString(2, entity.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User entity) {
        try (Connection connection = ds.getConnection();
             PreparedStatement  preparedStatement = connection.prepareStatement(UPDATE)){
            preparedStatement.setLong(2, entity.getIdentifier());
            preparedStatement.setString(1, entity.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = ds.getConnection();
             PreparedStatement  preparedStatement = connection.prepareStatement(DELETE)){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
// = new User(resultSet.getLong(1), email);
    @Override
    public Optional<User> findByEmail(String email) {
        Optional <User> optionalUser = null;
        try (Connection connection = ds.getConnection();
             PreparedStatement  preparedStatement = connection.prepareStatement(FIND_BY_EMAIL + '\'' + email + '\'')){
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                optionalUser = Optional.of(new User(resultSet.getLong(1), email));
            }
            resultSet.close();;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return optionalUser;
    }
}
