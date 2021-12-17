package com.capstone.AninPringleOfori.dao;

import com.capstone.AninPringleOfori.model.item.Console;
import com.capstone.AninPringleOfori.model.item.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class ConsoleDaoImpl implements ConsoleDao {
    private static final String ADD_CONSOLE_SQL = "insert into console(model, manufacturer, memory_amount, processor, price, orderQuantity) values (?, ?, ?, ?, ?, ?)";
    private static final String DELETE_CONSOLE_SQL = "delete from console where console_id = ?";
    private static final String FIND_CONSOLE_BY_ID_SQL = "select * from console where console_id = ?";
    private static final String UPDATE_CONSOLE_SQL = "update console set model = ?, manufacturer = ?, memory_amount = ?, processor =?, price =?, orderQuantity = ? where console_id = ?";
    private static final String FIND_ALL_CONSOLES_SQL = "select * from console";
    private static final String FIND_BY_MANUFACTURER_SQL = "select * from console where manufacturer = ?";

    JdbcTemplate jdbcTemplate;

    @Autowired
    public ConsoleDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Console addConsole(Console console) {
        jdbcTemplate.update(ADD_CONSOLE_SQL,
                console.getModel(),
                console.getManufacturer(),
                console.getMemoryAmount(),
                console.getProcessor(),
                console.getPrice(),
                console.getOrderQuantity());

        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        console.setId(id);
        return console;
    }

    @Override
    public void deleteConsole(int id) {
        jdbcTemplate.update(DELETE_CONSOLE_SQL, id);

    }

    @Override
    public Console findById(int id) {
        try {
            return jdbcTemplate.queryForObject(FIND_CONSOLE_BY_ID_SQL, this::mapRowToConsole, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void updateConsole(Console console) {
        jdbcTemplate.update(UPDATE_CONSOLE_SQL,
                console.getModel(),
                console.getManufacturer(),
                console.getMemoryAmount(),
                console.getProcessor(),
                console.getPrice(),
                console.getOrderQuantity(),
                console.getId());
    }

    @Override
    public List<Console> findAllConsoles() {
        return jdbcTemplate.query(FIND_ALL_CONSOLES_SQL, this::mapRowToConsole);
    }

    @Override
    public List<Console> findConsolesByManufacturer(String manufacturer) {
        return jdbcTemplate.query(FIND_BY_MANUFACTURER_SQL, this::mapRowToConsole, manufacturer);
    }

    @Override
    public void decrementQuantity(Item item, int orderQuantity) {
        if (orderQuantity > item.getOrderQuantity()) {
            throw new IllegalArgumentException(String.format("Order orderQuantity cannot be greater than %s", item.getOrderQuantity()));
        }
        item.setOrderQuantity(item.getOrderQuantity() - orderQuantity);
        updateConsole((Console) item);
    }

    public Console mapRowToConsole(ResultSet resultSet, int rowNum) throws SQLException {
        return new Console(resultSet.getInt("console_id"),
                resultSet.getString("model"),
                resultSet.getString("manufacturer"),
                resultSet.getString("memory_amount"),
                resultSet.getString("processor"),
                resultSet.getDouble("price"),
                resultSet.getInt("orderQuantity"));
    }
}
