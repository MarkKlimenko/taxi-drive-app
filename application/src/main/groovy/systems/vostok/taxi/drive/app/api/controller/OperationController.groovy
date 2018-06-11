package systems.vostok.taxi.drive.app.api.controller

import org.springframework.beans.factory.annotation.Autowired
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationRequest
import systems.vostok.taxi.drive.app.executor.ExecutorService

import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces

import static systems.vostok.taxi.drive.app.util.constant.MediaType.APPLICATION_JSON

@Path('api/operation')
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
class OperationController {
    @Autowired
    ExecutorService executorService

    @POST
    execute(OperationRequest operationRequest) {
        executorService.executeOperation(operationRequest)
    }
}
