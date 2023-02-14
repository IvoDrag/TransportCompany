package com.ivailo.transportcompany.controller;

import com.ivailo.transportcompany.entity.Company;
import com.ivailo.transportcompany.entity.Stock;
import com.ivailo.transportcompany.service.StockService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stock")
public class StockController {
    public final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/{id}")
    public Stock findStockById(@PathVariable Long id) {
        return stockService.findStock(id);
    }

    @GetMapping("/all")
    public List<Stock> getAllCompanies() {
        return stockService.findAllStocks();
    }

    @PostMapping("/add")
    public Stock addStock(@RequestBody Stock stock) {
        return stockService.saveStock(stock);
    }

    @PutMapping("/edit")
    public Stock editStock(@RequestBody Stock stock) {
        return stockService.updateStock(stock);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStock(@PathVariable Long id) {
        return stockService.delete(id);
    }

    @PutMapping("/{stockId}/transportation/{transportationId}")
    public Stock addTransportation(@PathVariable Long stockId, @PathVariable Long transportationId) {
        return stockService.addTransportationToStock(stockId, transportationId);
    }
}
