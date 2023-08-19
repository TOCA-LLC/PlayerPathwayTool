package com.toca.webtool.addPlayerToPathway.utility;

import com.toca.webtool.addPlayerToPathway.model.Pathway;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelParser {

    public static List<Pathway> parseExcel() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File("C:\\Users\\Dr. Maxwell\\dev\\addPlayerToPathway\\spreadsheet\\Pathway Data.xlsx"));

        XSSFWorkbook workbook = new XSSFWorkbook (fileInputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();

        List<Pathway> pathways = new ArrayList<>();
        for(Row row: sheet) {
            Pathway pathway = new Pathway();
            for(Cell cell: row) {
                switch(cell.getColumnIndex()){
                    case 3 -> {
                        if(cell.getCellType() == Cell.CELL_TYPE_STRING){
                            pathway.setPathwayName(cell.getStringCellValue());
                        }
                    }
                    case 5 -> {
                        if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                            pathway.setPathwayId((long) cell.getNumericCellValue());
                        }
                    }
                    case 6 -> {
                        if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                            pathway.setPlayerId((long) cell.getNumericCellValue());
                        }
                    }
                    case 7 -> {
                        if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                            pathway.setSiteId((long) cell.getNumericCellValue());
                        }
                    }
                    default -> {}
                }
            }
            pathways.add(pathway);
        }
        return pathways;
    }

}