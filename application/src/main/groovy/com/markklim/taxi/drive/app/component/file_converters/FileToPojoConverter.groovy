package com.markklim.taxi.drive.app.component.file_converters

import com.markklim.taxi.drive.app.model.PriceDtd

import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.springframework.stereotype.Component

/**
 * Created by viktor on 02.11.17.
 */
//TODO в дальнейшем нужно сделать этот класс абстрактным. PriceDtd заменить на generic (по необходимости)
//при добавлении новых методов конвертирования, класс будет разрастаться.
@Component
abstract class FileToPojoConverter {
    //на случай, если в будущем придется работать не только с excel файлами
    private Workbook workbook
    abstract Workbook createWorkbook(InputStream fis)

    List<PriceDtd> getPriceDtdListFromExcel(InputStream fis){
        List<PriceDtd> priceDtdList
        workbook = createWorkbook(fis)
        //Предполагается, что необходимые данные будут находиться на одной странице файла.
        //При усложнении структуры передаваемого документа,
        // придется пробегаться по всем страницам файла
        Sheet sheet = workbook.getSheetAt(0)
        priceDtdList = getListFromSheet(sheet)
        return priceDtdList
    }

    private List<PriceDtd> getListFromSheet(Sheet sheet){
        List<PriceDtd> objectList = new ArrayList<>()

        Iterator<Row> rowIterator = sheet.iterator()
        while (rowIterator.hasNext()){
            objectList.add(getPriceDtdFromRow(rowIterator.next()))
        }
        return objectList
    }

    private PriceDtd getPriceDtdFromRow(Row row){
        PriceDtd priceDtd = new PriceDtd()
        ArrayList<Cell> cells = new ArrayList<>(3)

        Iterator<Cell> cellIterator = row.iterator()
        while (cellIterator.hasNext()){
            cells.add(cellIterator.next())
        }

        initPriceDtd(priceDtd, cells)
        return priceDtd
    }

    private void initPriceDtd(PriceDtd priceDtd, List<Cell> cells){
        String distFrom = cells.get(0).getStringCellValue()
        String distTo = cells.get(1).getStringCellValue()
        int price = cells.get(2).getNumericCellValue()
        priceDtd.setDistFrom(distFrom)
        priceDtd.setDistTo(distTo)
        priceDtd.setPrice(price)
        priceDtd.generateId()
    }
}
