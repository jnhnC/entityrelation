package com.example.entityrelation.util;

import org.apache.poi.ooxml.util.SAXHelper;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler.SheetContentsHandler;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SimpleExcelUtil implements SheetContentsHandler {

    private List<String> row = new ArrayList<>();
    private List<List<String>> rows = new ArrayList<>();
    private List<String> header = new ArrayList<>();

    private int currentCol = -1; // 빈값 체크용
    private int currRowNum = 0; //현재 읽고 셀

    public List<List<String>> parse(File file) throws Exception {
        SimpleExcelUtil sheetHandler = new SimpleExcelUtil();

        OPCPackage opc = OPCPackage.open(file);
        XSSFReader xssfReader = new XSSFReader(opc);
        ZipSecureFile.setMinInflateRatio(0);
        StylesTable styles = xssfReader.getStylesTable();
        ReadOnlySharedStringsTable strings = new ReadOnlySharedStringsTable(opc);

//        여러의 Sheet를 읽을때 사용
//        XSSFReader.SheetIterator itr = (XSSFReader.SheetIterator)xssfReader.getSheetsData();
//        while (itr.hasNext()) {
//            InputStream sheetStreem = itr.next();
//            InputSource sheetSource=  new InputSource(sheetStreem);
//        }
        InputStream inputStream = xssfReader.getSheetsData().next();
        InputSource inputSource = new InputSource(inputStream);

        ContentHandler handle = new XSSFSheetXMLHandler(styles, strings, sheetHandler, false);
        XMLReader xmlReader = SAXHelper.newXMLReader();
        xmlReader.setContentHandler(handle);
        xmlReader.parse(inputSource);

        inputStream.close();
        opc.close();


        return rows;
    }

    @Override
    public void startRow(int rowNum) {
        System.out.println("startRow = " + rowNum);
        this.currentCol = -1;
        this.currRowNum = rowNum;
    }

    @Override
    public void endRow(int rowNum) {
        System.out.println("endRow = " + rowNum);
        if (rowNum == 0) {
            header = new ArrayList(row);
        } else {
            //헤더의 길이가 현재 로우보다 더 길다면 Cell의 뒷자리가 빈값임으로 해당값만큼 공백
            if (row.size() < header.size()) {
                for (int i = row.size(); i < header.size(); i++) {
                    row.add("");
                }
            }
            rows.add(new ArrayList(row));
        }
        row.clear();
    }

    @Override
    public void cell(String cellReference, String formattedValue, XSSFComment comment) {
        System.out.println("cell = " + formattedValue);
        int iCol = (new CellReference(cellReference)).getCol();
        int emptyCol = iCol - currentCol - 1;
        for (int i = 0; i < emptyCol; i++) {
            row.add("");
        }
        currentCol = iCol;
        row.add(formattedValue);
    }

    @Override
    public void headerFooter(String text, boolean isHeader, String tagName) {
        System.out.println("headerFooter = " + text);

    }
}
