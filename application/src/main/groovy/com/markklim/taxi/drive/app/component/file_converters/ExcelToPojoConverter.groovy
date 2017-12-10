package com.markklim.taxi.drive.app.component.file_converters

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook

abstract class ExcelToPojoConverter<T> {
    private Workbook workbook

    abstract T createAndInitElement(List<Cell> cells)

    List<T> getDataFromExcel(InputStream fis){
        List<T> dataList
        workbook = new XSSFWorkbook(fis)
        Sheet sheet = workbook.getSheetAt(0)
        dataList = getListFromSheet(sheet)
        return dataList
    }

    private List<T> getListFromSheet(Sheet sheet){
        List<T> objectList = new ArrayList<>()

        Iterator<Row> rowIterator = sheet.iterator()
        while (rowIterator.hasNext()){
            objectList.add(getDataFromRow(rowIterator.next()))
        }
        return objectList
    }

    private T getDataFromRow(Row row){
        ArrayList<Cell> cells = new ArrayList<>(3)

        Iterator<Cell> cellIterator = row.iterator()
        while (cellIterator.hasNext()){
            cells.add(cellIterator.next())
        }

        return createAndInitElement(cells)
    }
}
