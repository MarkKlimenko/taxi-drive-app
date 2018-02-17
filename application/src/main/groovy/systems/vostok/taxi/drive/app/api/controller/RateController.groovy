package systems.vostok.taxi.drive.app.api.controller

import org.glassfish.jersey.media.multipart.FormDataParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import systems.vostok.taxi.drive.app.dao.entity.PriceCtc
import systems.vostok.taxi.drive.app.dao.entity.PriceDtd
import systems.vostok.taxi.drive.app.dao.repository.impl.PriceDao
import systems.vostok.taxi.drive.app.service.RateService

import javax.ws.rs.Consumes
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Controller
@Path('api')
@Produces(MediaType.APPLICATION_JSON)
class RateController {
    @Autowired
    RateService rateService

    @Autowired
    PriceDao priceDao

    @POST
    @Path('rate/dtd/config/upload')
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    uploadDtdConfig(@FormDataParam('file') InputStream file) {
        rateService.uploadDtdConfig(file)
    }

    @POST
    @Path('rate/ctc/config/upload')
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    uploadCtcConfig(@FormDataParam('file') InputStream file) {
        rateService.uploadCtcConfig(file)
    }

    @GET
    @Path('rate/dtd/all')
    getAllDtdRates() {
        priceDao.getAllDtdRates()
    }

    @GET
    @Path('rate/ctc/all')
    getAllCtcRates() {
        priceDao.getAllCtcRates()
    }

    @PUT
    @Path('rate/ctc')
    putRateCtc(PriceCtc priceCtc) {
        priceDao.addPriceCtc(priceCtc)
    }

    @PUT
    @Path('rate/dtd')
    putRateDtd(PriceDtd priceDtd) {
        priceDao.addPriceDtd(priceDtd)
    }

    @DELETE
    @Path('rate/dtd')
    deleteDtdRate(PriceDtd priceDtd) {
        priceDao.deleteDtdRate(priceDtd)
    }

    @DELETE
    @Path('rate/ctc')
    deleteCtcRate(PriceCtc priceCtc) {
        priceDao.deleteCtcRate(priceCtc)
    }
}