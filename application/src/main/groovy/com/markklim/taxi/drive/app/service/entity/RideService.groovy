package com.markklim.taxi.drive.app.service.entity

import com.datastax.driver.core.querybuilder.QueryBuilder
import com.datastax.driver.core.querybuilder.Select
import com.markklim.taxi.drive.app.component.DefineDistrictComponent
import com.markklim.taxi.drive.app.component.evaluation.PriceFormer
import com.markklim.taxi.drive.app.model.Address
import com.markklim.taxi.drive.app.model.Ride
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.cassandra.core.CassandraTemplate
import org.springframework.stereotype.Service

@Service
class RideService {

    @Autowired
    private CassandraTemplate cassandraTemplate

    @Autowired
    private PriceFormer priceFormer

    @Autowired
    private DefineDistrictComponent defineDistrictComponent

    List<Ride> getAll() {
        Select select = QueryBuilder.select().from('ride')
        executeQuery(select)
    }

    void add(Ride ride) {
        cassandraTemplate.insert(ride)
    }

    protected List<Ride> executeQuery(Select select) {
        cassandraTemplate.select(select, Ride.class)
    }

    Integer getPrice(Address addressFrom, Address addressTo, String clientLogin) {
        // TODO: Используя номер клиента надо реализовать возможность скидок
        if (addressFrom.city == addressTo.city) {
            extractDtdPrice(addressFrom, addressTo)
        } else {
            extractCtcPrice(addressFrom.city, addressTo.city)
        }
    }

    private Integer extractDtdPrice(Address from, Address to) {
        String distFrom = defineDistrictComponent.defineDistrict(from.street, from.building)
        String distTo = defineDistrictComponent.defineDistrict(to.street, to.building)
        priceFormer.priceDistToDist(distFrom, distTo)
    }

    private Integer extractCtcPrice(String from, String to) {
        priceFormer.priceCityToCity(from, to)
    }
}
