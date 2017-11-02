package com.markklim.taxi.drive.app.service.settings

import com.markklim.taxi.drive.app.component.FileToPojoConverter
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

    void fillPriceTableFromExcel(FileInputStream fis){
        List<PriceDtd> priceDtdList = fileToPojoConverter.getPriceDtdListFromExcel(fis)

        //TODO очищаем существующую таблицу и заполняем новыми элементами
    }
}