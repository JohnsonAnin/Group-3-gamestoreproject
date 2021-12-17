package com.capstone.AninPringleOfori.dao;

import com.capstone.AninPringleOfori.model.item.Game;
import com.capstone.AninPringleOfori.model.item.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GameDaoImpl implements GameDao {
    private static final String ADD_GAME_SQL = "insert into game(title, esrb_rating, description, price, studio, orderQuantity) values (?, ?, ?, ?, ?, ?)";
    private static final String DELETE_GAME_SQL = "delete from game where game_id = ?";
    private static final String FIND_GAME_BY_ID_SQL = "select * from game where game_id = ?";
    private static final String UPDATE_GAME_SQL = "update game set title = ?, esrb_rating = ?, description = ?, price =?, studio =?, orderQuantity = ? where game_id = ?";
    private static final String FIND_ALL_GAMES_SQL = "select * from game";
    private static final String FIND_BY_STUDIO_SQL = "select * from game where studio = ?";
    private static final String FIND_BY_RATING_SQL = "select * from game where esrb_rating = ?";
    private static final String FIND_BY_TITLE_SQL = "select * from game where title = ?";

    JdbcTemplate jdbcTemplate;

    @Autowired
    public GameDaoImpl(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    @Override
    public Game addGame(Game game) {
        jdbcTemplate.update(ADD_GAME_SQL,
                game.getTitle(),
                game.getEsrbRating(),
                game.getDescription(),
                game.getPrice(),
                game.getStudio(),
                game.getOrderQuantity());

        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        game.setId(id);
        return game;
    }

    @Override
    public void deleteGame(int id) {
        jdbcTemplate.update(DELETE_GAME_SQL, id);
    }

    @Override
    public Game findById(int id) {
        try {
            return jdbcTemplate.queryForObject(FIND_GAME_BY_ID_SQL, this::mapRowToGame, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void updateGame(Game game) {
        jdbcTemplate.update(UPDATE_GAME_SQL,
                game.getTitle(),
                game.getEsrbRating(),
                game.getDescription(),
                game.getPrice(),
                game.getStudio(),
                game.getOrderQuantity(),
                game.getId());
    }

    @Override
    public List<Game> findAllGames() {
        return jdbcTemplate.query(FIND_ALL_GAMES_SQL, this::mapRowToGame);
    }

    @Override
    public List<Game> findGamesByStudio(String studio) {
        return jdbcTemplate.query(FIND_BY_STUDIO_SQL, this::mapRowToGame, studio);
    }

    @Override
    public List<Game> findGamesByEsrbRating(String esrbRating) {
        return jdbcTemplate.query(FIND_BY_RATING_SQL, this::mapRowToGame, esrbRating);
    }

    @Override
    public List<Game> findGamesByTitle(String title) {
        return jdbcTemplate.query(FIND_BY_TITLE_SQL, this::mapRowToGame, title);
    }

    private Game mapRowToGame(ResultSet resultSet, int rowNum) throws SQLException {
        return new Game(
                resultSet.getInt("game_id"),
                resultSet.getString("title"),
                resultSet.getString("esrb_rating"),
                resultSet.getString("description"),
                resultSet.getDouble("price"),
                resultSet.getString("studio"),
                resultSet.getInt("orderQuantity"));
    }

    @Override
    public void decrementQuantity(Item item, int orderQuantity) {
        if (orderQuantity > item.getOrderQuantity()) {
            throw new IllegalArgumentException(String.format("Order orderQuantity cannot be greater than %s", item.getOrderQuantity()));
        }
        item.setOrderQuantity(item.getOrderQuantity() - orderQuantity);
        updateGame((Game) item);
    }
}
