package com.markklim.taxi.drive.app.component.file_converters

import com.markklim.taxi.drive.app.dao.entity.PriceDtd
import com.markklim.taxi.drive.app.dao.entity.PriceCtc
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ExcelConvertersManager {
    @Autowired
    ExcelToPriceDtdConverter excelToPriceDtdConverter

    @Autowired
    ExcelToPriceCtcConverter excelToPriceCtcConverter

    List<PriceCtc> getPriceCtcList(InputStream fis){
        excelToPriceCtcConverter.getDataFromExcel(fis)
    }

    List<PriceDtd> getPriceDtdList(InputStream fis){
        excelToPriceDtdConverter.getDataFromExcel(fis)
    }
}
