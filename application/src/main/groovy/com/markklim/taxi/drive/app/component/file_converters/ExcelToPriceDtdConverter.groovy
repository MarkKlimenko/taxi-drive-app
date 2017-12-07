package com.markklim.taxi.drive.app.component.file_converters

import com.markklim.taxi.drive.app.dao.entity.PriceDtd
import org.apache.poi.ss.usermodel.Cell
import org.springframework.stereotype.Component

@Component
class ExcelToPriceDtdConverter extends ExcelToPojoConverter<PriceDtd> {
    @Override
    PriceDtd createAndInitElement(List<Cell> cells) {
        String distFrom = cells.get(0).getStringCellValue()
        String distTo = cells.get(1).getStringCellValue()
        int price = cells.get(2).getNumericCellValue()
        def priceDtd = new PriceDtd(distFrom, distTo, price)
        return priceDtd
    }
}
