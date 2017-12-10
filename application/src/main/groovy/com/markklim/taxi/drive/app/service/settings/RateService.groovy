package com.markklim.taxi.drive.app.service.settings

import com.markklim.taxi.drive.app.component.file_converters.ExcelConvertersManager

import com.markklim.taxi.drive.app.dao.impl.PriceDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by viktor on 02.11.17.
 */
@Service
class RateService {
    @Autowired
    ExcelConvertersManager excelConvertersManager

    @Autowired
    PriceDao priceDao

    void fillPriceDtdTableFromExcel(InputStream fis){
        excelConvertersManager.getPriceDtdList(fis).each {
            priceDao.addPriceDtd(it)
        }
    }

    void fillPriceCtcTableFromExcel(InputStream fis){
        excelConvertersManager.getPriceCtcList(fis).each {
            priceDao.addPriceCtc(it)
        }
    }
}