package com.bettingjaws.backend.repository;


import com.bettingjaws.backend.persistence.entity.Role;
import com.bettingjaws.backend.persistence.entity.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepositoryImpl implements UserRepository{
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final RowMapper<Optional<User>> userRowMapper = createUserRowMapper();

    public UserRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<User> getAUserByEmail(String email) {
      List<Optional<User>> user = jdbcTemplate.query("select * from user_table where email = :email", Map.of(
                "email", email), userRowMapper);
      if(user.size() == 0){
          return Optional.empty();
      }
      else{
          return user.get(0);
      }
    }

    @Override
    public User createAUser(User user) {
        jdbcTemplate.update("insert into user_table values(:id, :first_name, :last_name, :email," +
                ":phone_number, :country, :game_level, 0, 0, :role, :password)", Map.of(
                        "id", user.getId().toString(),
                "first_name", user.getFirst_name(),
                "last_name", user.getLast_name(),
                "email", user.getEmail(),
                "phone_number", user.getPhone_number(),
                "country", user.getCountry(),
                "game_level", user.getGame_level(),
                "role", user.getRole().name(),
                "password", user.getPassword()
                ));
        return user;
    }

    @Override
    public Optional<User> loadUserByUsername(String username) {
        List<Optional<User>> user = jdbcTemplate.query("select * from user_table where email = :email", Map.of(
                "email", username), userRowMapper);
        if(user.size() == 0){
            return Optional.empty();
        }
        else{
            return user.get(0);
        }
    }

    @Override
    public Boolean updateUserLevel(Integer level, String email) {
            jdbcTemplate.update("update user_table set game_level = :level, xp_progress_bar = 0 where email = :email", Map.of(
                "level", level,
                "email", email
        ));
        return true;
    }

    @Override
    public Boolean updateUserXpNumber(String email, Integer xpNumber, Integer xpNumberBar) {
        jdbcTemplate.update("update user_table set xp_number = :xp_level, xp_progress_bar = :xpNumberBar where email = :email", Map.of(
                "xp_level", xpNumber,
                "xpNumberBar", xpNumberBar,
                "email", email
        ));
        return true;
    }

    private RowMapper<Optional<User>> createUserRowMapper(){
        return ((rs, rowNum) -> {
            String id = rs.getString("id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String email = rs.getString("email");
            String phoneNumber = rs.getString("phone_number");
            String country = rs.getString("country");
            Integer game_level = Integer.parseInt(rs.getString("game_level"));
            Integer xp_number = Integer.parseInt(rs.getString("xp_number"));
            Integer xp_progress_bar = Integer.parseInt(rs.getString("xp_progress_bar"));
            Role role = Role.valueOf(rs.getString("role"));
            String password = rs.getString("password");

            return Optional.of(new User(
                    id,
                    firstName,
                    lastName,
                    email,
                    phoneNumber,
                    country,
                    game_level,
                    xp_number,
                    xp_progress_bar,
                    role,
                    password
            ));
        });
    }
}
