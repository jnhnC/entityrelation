package com.example.entityrelation.api;

import com.example.entityrelation.domain.Stock;
import com.example.entityrelation.service.StockService;
import com.example.entityrelation.util.SimpleExcelUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class StockApiController {

    private final StockService stockService;

    @PostMapping("/api/upload")
    public void searchUpload(@RequestParam("file") MultipartFile file){
        System.out.println("file = " + file.getOriginalFilename());
        try{
            String origFilename = file.getOriginalFilename();
            String savePath = System.getProperty("user.dir") + "\\src\\main\\resources\\uploads";

            if (!new File(savePath).exists()) {
                try{
                    new File(savePath).mkdir();
                }
                catch(Exception e){
                    e.getStackTrace();
                }
            }
            String filePath = savePath + "\\" + origFilename;
            //  file.transferTo(new File(filePath));

            File convFile = new File(filePath);
            convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();


            SimpleExcelUtil excelUtil = new SimpleExcelUtil();
            List<List<String>> excelData = excelUtil.getData(convFile).getRows();
            stockService.parseStockData(excelData);


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @GetMapping("/api/stock")
    public List<Stock> getStockList(){
        return stockService.findAll();
    }

    @Data
    @NoArgsConstructor
    static class StockRequest{
        private String col1;
        private String col2;
        private String col3;
        private String col4;
        private String col5;
        private String col6;
        private String col7;
        private String col8;
        private String col9;
        private String col10;
        private String col11;
        private String col12;
        private String col13;
        private String col14;
    }

}
