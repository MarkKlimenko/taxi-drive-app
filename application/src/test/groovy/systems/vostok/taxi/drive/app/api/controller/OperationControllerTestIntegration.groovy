package systems.vostok.taxi.drive.app.api.controller

import org.apache.catalina.core.ApplicationContext
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.web.client.RestTemplate
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationResponse

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.junit.jupiter.api.Assertions.assertTrue

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DisplayName('Operation controller test')
class OperationControllerTestIntegration {
    @Autowired
    private ApplicationContext context

    private TestRestTemplate restTemplate = new TestRestTemplate()

    @Test
    @DisplayName('Execute operation with JSON operationRequest')
    void jsonPayloadOperationTest() {
        ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:"
                + context.getEmbeddedServletContainer().getPort() + "/hello", String.class);


        OperationResponse or = operationUtil.enrollOperation('CORE_TIMEOUT_OPERATION', [:])
        assertTrue(or.body instanceof BigDecimal)
        assertEquals(new BigDecimal(5), or.body)
    }

    @Test
    @DisplayName('Execute operation with byte operationRequest')
    void bytePayloadOperationTest() {
        OperationResponse or = operationUtil.enrollOperation('CORE_TIMEOUT_OPERATION', [:])
        assertTrue(or.body instanceof BigDecimal)
        assertEquals(new BigDecimal(5), or.body)
    }
}
