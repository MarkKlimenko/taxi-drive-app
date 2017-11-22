package com.markklim.taxi.drive.app.service.settings

import com.markklim.taxi.drive.app.component.file_converters.FileToPojoConverter
import com.markklim.taxi.drive.app.dao.impl.PriceDao
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
    FileToPojoConverter fileToPojoConverter

    @Autowired
    PriceDao priceDao

    void fillPriceTableFromExcel(InputStream fis, FormDataContentDisposition fileMetaData){
        println "начали, " + fileMetaData.fileName
        List<PriceDtd> priceDtdList = fileToPojoConverter.getPriceDtdListFromExcel(fis)
        priceDtdList.each {
            PriceDtd dtd -> priceDao.addPriceDtd(dtd)
                dtd.toString()
        }
        println "Все ок"
    }
}