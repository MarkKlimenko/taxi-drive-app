package com.markklim.taxi.drive.app.component.file_converters

import com.markklim.taxi.drive.app.dao.entity.PriceCtc
import org.apache.poi.ss.usermodel.Cell
import org.springframework.stereotype.Component

@Component
class ExcelToPriceCtcConverter extends ExcelToPojoConverter<PriceCtc>{
    private PriceCtc priceCtc

    @Override
    PriceCtc createAndInitElement(List<Cell> cells) {
        String cityFrom = cells.get(0).getStringCellValue()
        String cityTo = cells.get(1).getStringCellValue()
        int price = cells.get(2).getNumericCellValue()
        priceCtc = new PriceCtc(cityFrom, cityTo, price)
        priceCtc.generateId()
        return priceCtc
    }
}
