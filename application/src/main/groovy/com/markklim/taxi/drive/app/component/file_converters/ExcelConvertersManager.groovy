package com.markklim.taxi.drive.app.component.file_converters

import com.markklim.taxi.drive.app.dao.entity.PriceDtd
import com.markklim.taxi.drive.app.dao.entity.PriceCtc
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * Created by viktor on 25.11.17.
 */
// TODO надо как-то менять реализацию в рантайме. Должно получиться что то типа пораждающей стратегии
//фабричный метод здесь не к месту. В идеале следует избежать List<Object> в ExcelFileToPojoConverter
@Component
class ExcelConvertersManager {
    @Autowired
    ExcelToPriceDtdConverter excelToPriceDtdConverter

    @Autowired
    ExcelToPriceCtcConverter excelToPriceCtcConverter

    public List<PriceCtc> getPriceCtcList(InputStream fis){
        return excelToPriceCtcConverter.getDataFromExcel(fis)
    }

    public List<PriceDtd> getPriceDtdList(InputStream fis){
        return excelToPriceDtdConverter.getDataFromExcel(fis)
    }
}
