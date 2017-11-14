package com.markklim.taxi.drive.app.service.settings

import com.markklim.taxi.drive.app.component.FileToPojoConverter
import com.markklim.taxi.drive.app.component.database.QueryHelper
import com.markklim.taxi.drive.app.model.PriceDtd;
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
    QueryHelper queryHelper

    void fillPriceTableFromExcel(FileInputStream fis){
        println "начали"
        List<PriceDtd> priceDtdList = fileToPojoConverter.getPriceDtdListFromExcel(fis)
        queryHelper.setPriceDtd(priceDtdList)
        println "Все ок"
    }
}