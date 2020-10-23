package com.example.entityrelation.service;

import com.example.entityrelation.domain.Stock;
import com.example.entityrelation.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    public void parseStockData(List<List<String>> excelData) {

        for (int i = 3; i < excelData.size(); i++) {
            Stock stock  = new Stock(null,
                    excelData.get(i).get(1),excelData.get(i).get(2),excelData.get(i).get(3),
                    excelData.get(i).get(4),excelData.get(i).get(5),excelData.get(i).get(6),
                    excelData.get(i).get(7),excelData.get(i).get(8),excelData.get(i).get(9),
                    excelData.get(i).get(10),excelData.get(i).get(11),excelData.get(i).get(12),
                    excelData.get(i).get(13),excelData.get(i).get(14),excelData.get(i).get(15)
            );
            stockRepository.save(stock);
        }

    }

    public List<Stock> findAll() {
        return stockRepository.findAll();
    }
}
