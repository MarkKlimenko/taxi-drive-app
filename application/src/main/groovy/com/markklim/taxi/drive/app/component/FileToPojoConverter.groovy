package com.markklim.taxi.drive.app.component

import com.markklim.taxi.drive.app.model.PriceDtd
import com.markklim.taxi.drive.app.util.UtilHash
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.stereotype.Component

import java.util.stream.Stream

/**
 * Created by viktor on 02.11.17.
 */
//TODO в дальнейшем нужно сделать этот класс абстрактным. PriceDtd заменить на generic (по необходимости)
//при добавлении новых методов конвертирования, класс будет разрастаться.
@Component
class FileToPojoConverter {
    //на случай, если в будущем придется работать не только с excel файлами
    private Workbook workbook

    List<PriceDtd> getPriceDtdListFromExcel(FileInputStream fis){
        List<PriceDtd> priceDtdList = new ArrayList<>()
        workbook = new XSSFWorkbook(fis)
        //Предполагается, что необходимые данные будут находиться на одной странице файла.
        //При усложнении структуры передаваемого документа,
        // придется пробегаться по всем страницам файла
        Sheet sheet = workbook.getSheetAt(0)
        priceDtdList = getListFromSheet(fis, sheet)
        return priceDtdList
    }

    private List<PriceDtd> getListFromSheet(Sheet sheet){
        List<PriceDtd> objectList = new ArrayList<>()

        Iterator<Row> rowIterator = sheet.iterator()
        while (rowIterator.hasNext()){
            objectList.add(getPriceDtdFromRow(rowIterator.next()))
        }
    }

    private PriceDtd getPriceDtdFromRow(Row row){
        PriceDtd priceDtd = PriceDtd
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
        priceDtd.setId(UtilHash.getPriceDtdHashCode(distFrom, distTo))
    }
}
