package com.markklim.taxi.drive.app.service.settings

import com.markklim.taxi.drive.app.component.file_converters.ExcelConvertersManager
import com.markklim.taxi.drive.app.component.file_converters.ExcelToPojoConverter
import com.markklim.taxi.drive.app.component.file_converters.ExcelToPriceDtdConverter
import com.markklim.taxi.drive.app.dao.impl.PriceDao
import com.markklim.taxi.drive.app.model.PriceCtc
import com.markklim.taxi.drive.app.model.PriceDtd
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
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

    void fillPriceDtdTableFromExcel(InputStream fis, FormDataContentDisposition fileMetaData){
        println "начали, " + fileMetaData.fileName
        List<PriceDtd> priceDtdList = excelConvertersManager.getPriceDtdList(fis)
        priceDtdList.each {
            PriceDtd dtd -> priceDao.addPriceDtd(dtd)
            println dtd.toString()
        }
        println "Все ок"
    }

    void fillPriceCtcTableFromExcel(InputStream fis, FormDataContentDisposition fileMetaData){
        println "начали, " + fileMetaData.fileName
        List<PriceCtc> priceCtcList = excelConvertersManager.getPriceCtcList(fis)
        priceCtcList.each {
            PriceCtc ctc -> priceDao.addPriceCtc(ctc)
            println ctc.toString()
        }
        println "Все ок"
    }
}