package com.markklim.taxi.drive.app.service

import com.markklim.taxi.drive.app.dao.entity.City
import com.markklim.taxi.drive.app.dao.entity.District
import com.markklim.taxi.drive.app.dao.entity.PriceCtc
import com.markklim.taxi.drive.app.dao.entity.PriceDtd
import com.markklim.taxi.drive.app.dao.impl.GeoDao
import com.markklim.taxi.drive.app.dao.impl.PriceDao
import com.markklim.taxi.drive.app.util.WordUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import system.vostok.tda.service.ExcelParserService

@Service
class RateService {

    @Autowired
    GeoDao geoDao

    @Autowired
    @Delegate
    WordUtil wordUtil

    @Autowired
    PriceDao priceDao

    void uploadDtdConfig(InputStream file) {
        List<District> districts = geoDao.getAllDistricts()
                .each { it.name = modifyGeoName(it.name) }

        new ExcelParserService().parseDocument(file, 0, 'MIRROR_DIAGONAL')
                .collect { new PriceDtd(getEntityId(it[0], districts), getEntityId(it[1], districts), it[2] as Integer) }
                .toSet()
                .each { priceDao.addPriceDtd(it) }
    }

    void uploadCtcConfig(InputStream file) {
        List<City> cities = geoDao.getAllCities()
                .each { it.name = modifyGeoName(it.name) }

        new ExcelParserService().parseDocument(file, 0, 'PLAIN_HEADER')
                .with { removeHeader(it) }
                .collect { new PriceCtc(getEntityId(it[0], cities), getEntityId(it[1], cities), it[3] as Integer) }
                .each { priceDao.addPriceCtc(it) }
    }

    protected String getEntityId(String name, List entities) {
        name = modifyGeoName(name)
        Object entity = entities.find { it.name == name }
        if (!entity) {
            throw new NullPointerException("No such entity ${name} in geo table")
        }
        entity.id
    }

    protected List<String> removeHeader(List data) {
        data.remove(0)
        data
    }
}
