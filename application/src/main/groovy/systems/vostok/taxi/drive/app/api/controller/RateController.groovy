package systems.vostok.taxi.drive.app.api.controller

import org.springframework.stereotype.Controller

import javax.ws.rs.Path
import javax.ws.rs.Produces

import static systems.vostok.taxi.drive.app.util.constant.MediaType.APPLICATION_JSON

@Controller
@Path('api')
@Produces(APPLICATION_JSON)
class RateController {
    /*@Autowired
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
    }*/
}