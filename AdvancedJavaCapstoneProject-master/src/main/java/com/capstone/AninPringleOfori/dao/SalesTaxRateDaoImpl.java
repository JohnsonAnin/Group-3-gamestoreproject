package com.capstone.AninPringleOfori.dao;

import com.capstone.AninPringleOfori.model.order.SalesTaxRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class SalesTaxRateDaoImpl implements SalesTaxRateDao {
    public static final String FIND_SALES_TAX_SQL = "select * from sales_tax_rate where state = ?";

    JdbcTemplate jdbcTemplate;

    @Autowired
    public SalesTaxRateDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public SalesTaxRate findSalesTax(String state) {
        try {
            return jdbcTemplate.queryForObject(FIND_SALES_TAX_SQL, this::mapRowToSalesTax, state);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException("Invalid state specified.");
        }
    }

    public SalesTaxRate mapRowToSalesTax(ResultSet resultSet, int rowNum) throws SQLException {
        return new SalesTaxRate(resultSet.getString("state"),
                resultSet.getDouble("rate"));
    }
}
