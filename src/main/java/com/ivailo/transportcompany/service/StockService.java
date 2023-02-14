package com.ivailo.transportcompany.service;

import com.ivailo.transportcompany.entity.Company;
import com.ivailo.transportcompany.entity.Stock;
import com.ivailo.transportcompany.entity.Transportation;
import com.ivailo.transportcompany.repository.StockRepository;
import com.ivailo.transportcompany.repository.TransportationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {
    private final StockRepository stockRepository;

    private final TransportationRepository transportationRepository;

    @Autowired
    public StockService(StockRepository stockRepository, TransportationRepository transportationRepository) {
        this.stockRepository = stockRepository;
        this.transportationRepository = transportationRepository;
    }

    public Stock findStock(Long id) {
        return stockRepository.findById(id).orElseThrow();
    }

    public List<Stock> findAllStocks() {
        return stockRepository.findAll();
    }

    public Stock saveStock(Stock stock) {
        return stockRepository.save(stock);
    }

    public Stock updateStock(Stock stock) {
        Stock stockToBeUpdated = stockRepository.findById(stock.getId()).orElseThrow();
        stockToBeUpdated.setStockName(stock.getStockName());
        stockToBeUpdated.setWeight(stock.getWeight());
        return stockRepository.save(stockToBeUpdated);
    }

    public String delete(Long id) {
        stockRepository.deleteById(id);
        return "Stock with id [" + id + "] has been successfully deleted!";
    }

    public Stock addTransportationToStock(Long stockId, Long transportationId) {
        Stock stock = stockRepository.findById(stockId).orElseThrow();
        Transportation transportation = transportationRepository.findById(transportationId).orElseThrow();
        stock.setTransportation(transportation);
        return stockRepository.save(stock);
    }
}
