package com.markklim.taxi.drive.app.component.file_converters

import com.markklim.taxi.drive.app.dao.entity.PriceCtc
import org.apache.poi.ss.usermodel.Cell
import org.springframework.stereotype.Component

@Component
class ExcelToPriceCtcConverter extends ExcelToPojoConverter<PriceCtc>{
    private PriceCtc priceCtc

    @Override
    PriceCtc createAndInitElement(List<Cell> cells) {
        priceCtc = new PriceCtc()
        String distFrom = cells.get(0).getStringCellValue()
        String distTo = cells.get(1).getStringCellValue()
        int price = cells.get(2).getNumericCellValue()
        priceCtc.setCityFrom(distFrom)
        priceCtc.setCityTo(distTo)
        priceCtc.setPrice(price)
        priceCtc.generateId()
        println priceCtc
        return priceCtc
    }
}
