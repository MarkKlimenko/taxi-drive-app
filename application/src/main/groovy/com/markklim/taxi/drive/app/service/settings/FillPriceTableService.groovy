package com.markklim.taxi.drive.app.service.settings

import com.markklim.taxi.drive.app.component.file_converters.ExcelConvertersManager

import com.markklim.taxi.drive.app.dao.entity.PriceDtd
import com.markklim.taxi.drive.app.dao.entity.PriceCtc
import com.markklim.taxi.drive.app.dao.impl.PriceDao
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service

/**
 * Created by viktor on 02.11.17.
 */
@Service
class FillPriceTableService {
    @Autowired
    ExcelConvertersManager excelConvertersManager

    @Autowired
    PriceDao priceDao

    void fillPriceDtdTableFromExcel(InputStream fis){

        List<PriceDtd> priceDtdList = excelConvertersManager.getPriceDtdList(fis)
        priceDtdList.each {
            PriceDtd dtd -> priceDao.addPriceDtd(dtd)
            println dtd.toString()
        }
    }

    void fillPriceCtcTableFromExcel(InputStream fis){
        List<PriceCtc> priceCtcList = excelConvertersManager.getPriceCtcList(fis)
        priceCtcList.each {
            PriceCtc ctc -> priceDao.addPriceCtc(ctc)
            println ctc.toString()
        }
    }
}