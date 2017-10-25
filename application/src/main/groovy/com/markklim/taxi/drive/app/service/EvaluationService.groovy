package com.markklim.taxi.drive.app.service

import com.markklim.taxi.drive.app.model.PriceDtd
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.cassandra.core.CassandraTemplate
import org.springframework.stereotype.Service

@Service
class EvaluationService {
    @Autowired
    private CassandraTemplate cassandraTemplate

    void addPriceDtd(PriceDtd priceDtd) {
        cassandraTemplate.insert(priceDtd)
    }
}
