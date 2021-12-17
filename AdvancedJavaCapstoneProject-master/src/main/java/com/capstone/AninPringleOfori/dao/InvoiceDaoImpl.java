package com.capstone.AninPringleOfori.dao;

import com.capstone.AninPringleOfori.model.order.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InvoiceDaoImpl implements InvoiceDao {

    public static final String ADD_INVOICE_SQL = "insert into invoice(name, street, city, state, zipcode, item_type, item_id, unit_price, orderQuantity, subtotal, tax, processing_fee, total) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    JdbcTemplate jdbcTemplate;

    @Autowired
    public InvoiceDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Invoice addInvoice(Invoice invoice) {
        jdbcTemplate.update(ADD_INVOICE_SQL,
                invoice.getName(),
                invoice.getStreet(),
                invoice.getCity(),
                invoice.getState(),
                invoice.getZipCode(),
                invoice.getItemType(),
                invoice.getItemId(),
                invoice.getUnitPrice(),
                invoice.getOrderQuantity(),
                invoice.getSubTotal(),
                invoice.getTax(),
                invoice.getProcessingFee(),
                invoice.getTotal());

        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        invoice.setInvoiceId(id);

        return invoice;
    }
}
