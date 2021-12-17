package com.capstone.AninPringleOfori.dao;

import com.capstone.AninPringleOfori.model.item.Item;
import com.capstone.AninPringleOfori.model.item.TShirt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TShirtDaoImpl implements TShirtDao {
    private static final String ADD_TSHIRT_SQL = "insert into t_shirt(size, color, description, price, orderQuantity) values (?, ?, ?, ?, ?)";
    private static final String DELETE_TSHIRT_SQL = "delete from t_shirt where t_shirt_id = ?";
    private static final String FIND_TSHIRT_BY_ID_SQL = "select * from t_shirt where t_shirt_id = ?";
    private static final String UPDATE_TSHIRT_SQL = "update t_shirt set size = ?, color = ?, description = ?, price =?, orderQuantity = ? where t_shirt_id = ?";
    private static final String FIND_ALL_TSHIRTS_SQL = "select * from t_shirt";
    private static final String FIND_BY_COLOR_SQL = "select * from t_shirt where color = ?";
    private static final String FIND_BY_SIZE_SQL = "select * from t_shirt where size = ?";

    JdbcTemplate jdbcTemplate;

    @Autowired
    public TShirtDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public TShirt addtShirt(TShirt tShirt) {
        jdbcTemplate.update(ADD_TSHIRT_SQL,
                tShirt.getSize(),
                tShirt.getColor(),
                tShirt.getDescription(),
                tShirt.getPrice(),
                tShirt.getOrderQuantity());

        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        tShirt.setId(id);
        return tShirt;
    }

    @Override
    public void deletetShirt(int id) {
        jdbcTemplate.update(DELETE_TSHIRT_SQL, id);
    }

    @Override
    public void updatetShirt(TShirt tShirt) {
        jdbcTemplate.update(UPDATE_TSHIRT_SQL,
                tShirt.getSize(),
                tShirt.getColor(),
                tShirt.getDescription(),
                tShirt.getPrice(),
                tShirt.getOrderQuantity(),
                tShirt.getId());
    }

    @Override
    public List<TShirt> findAlltShirts() {
        return jdbcTemplate.query(FIND_ALL_TSHIRTS_SQL, this::mapRowTotShirt);
    }

    @Override
    public TShirt findById(int id) {
        try {
            return jdbcTemplate.queryForObject(FIND_TSHIRT_BY_ID_SQL, this::mapRowTotShirt, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<TShirt> findtShirtByColor(String color) {
        return jdbcTemplate.query(FIND_BY_COLOR_SQL, this::mapRowTotShirt, color);
    }

    @Override
    public List<TShirt> findtShirtBySize(String size) {
        return jdbcTemplate.query(FIND_BY_SIZE_SQL, this::mapRowTotShirt, size);
    }

    private TShirt mapRowTotShirt(ResultSet resultSet, int rowNum) throws SQLException {
        return new TShirt(resultSet.getInt("t_shirt_id"),
                resultSet.getString("size"),
                resultSet.getString("color"),
                resultSet.getString("description"),
                resultSet.getDouble("price"),
                resultSet.getInt("orderQuantity"));
    }

    @Override
    public void decrementQuantity(Item item, int orderQuantity) {
        if (orderQuantity > item.getOrderQuantity()) {
            throw new IllegalArgumentException(String.format("Order orderQuantity cannot be greater than %s", item.getOrderQuantity()));
        }
        item.setOrderQuantity(item.getOrderQuantity() - orderQuantity);
        updatetShirt((TShirt) item);
    }
}
