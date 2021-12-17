package com.capstone.AninPringleOfori.service;

import com.capstone.AninPringleOfori.dao.*;
import com.capstone.AninPringleOfori.factory.ItemDaoFactory;
import com.capstone.AninPringleOfori.model.order.Invoice;
import com.capstone.AninPringleOfori.model.order.ProcessingFee;
import com.capstone.AninPringleOfori.view.OrderViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServiceLayer {
    private static final double SURGE_PROCESSING_FEE = 15.49;
    final ProcessingFeeDao processingFeeDao;
    final SalesTaxRateDao salesTaxRateDao;
    final InvoiceDao invoiceDao;
    final ItemDaoFactory daoFactory;


    @Autowired
    public ServiceLayer(GameDao gameDao, TShirtDao tShirtDao, ConsoleDao consoleDao, ProcessingFeeDao processingFeeDao,
                        SalesTaxRateDao salesTaxRateDao,
                        InvoiceDao invoiceDao) {
        this.processingFeeDao = processingFeeDao;
        this.salesTaxRateDao = salesTaxRateDao;
        this.invoiceDao = invoiceDao;
        this.daoFactory = new ItemDaoFactory(gameDao, tShirtDao, consoleDao);
    }

    @Transactional
    public Invoice generateInvoice(OrderViewModel viewModel) {
        // Find the subtotal fee
        double subtotal = viewModel.getOrderQuantity() * viewModel.getItem().getPrice();

        // Calculate the tax
        double taxRate = salesTaxRateDao.findSalesTax(viewModel.getState()).getRate();
        double totalTax = calculateTax(subtotal, taxRate);

        // Calculate the total price
        double processingFee = findProcessingFee(viewModel.getOrderQuantity(),
                processingFeeDao.getFee(viewModel.getItemType()));

        // Update the orderQuantity
        getDao(viewModel.getItemType()).decrementQuantity(viewModel.getItem(), viewModel.getOrderQuantity());

        // Generate the invoice
        Invoice invoice = new Invoice();
        invoice.setName(viewModel.getName());
        invoice.setStreet(viewModel.getStreet());
        invoice.setCity(viewModel.getCity());
        invoice.setState(viewModel.getState());
        invoice.setZipCode(viewModel.getZipCode());
        invoice.setItemType(viewModel.getItemType());
        invoice.setItemId(viewModel.getItem().getId());
        invoice.setUnitPrice(viewModel.getItem().getPrice());
        invoice.setOrderQuantity(viewModel.getOrderQuantity());
        invoice.setSubTotal(subtotal);
        invoice.setTax(totalTax);
        invoice.setProcessingFee(processingFee);
        invoice.setTotal(subtotal + totalTax + processingFee);

        return invoiceDao.addInvoice(invoice);
    }

    private double calculateTax(double subtotal, double rate) {
        return subtotal * rate;
    }

    private double findProcessingFee(int orderQuantity, ProcessingFee processingFee) {
        return processingFee.getFee() + (orderQuantity > 10 ? SURGE_PROCESSING_FEE : 0);
    }

    public ItemDao getDao(String itemType) {
        return daoFactory.getDaoInstance(itemType);
    }
}
