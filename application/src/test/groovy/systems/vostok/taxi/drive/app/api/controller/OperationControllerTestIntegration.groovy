package systems.vostok.taxi.drive.app.api.controller

import groovy.json.JsonOutput
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit.jupiter.SpringExtension
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationResponse
import systems.vostok.taxi.drive.app.operation.OperationDirection
import systems.vostok.taxi.drive.app.operation.OperationRequest

import static org.junit.jupiter.api.Assertions.assertEquals

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName('Operation controller test')
class OperationControllerTestIntegration {
    @Autowired
    TestRestTemplate restTemplate

    @Test
    @DisplayName('Execute operation with JSON operationRequest')
    void jsonPayloadOperationTest() {
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8)

        Map operationRequest = [
                operationName: 'CORE_SIMPLE_OPERATION',
                direction    : OperationDirection.enroll,
                body         : JsonOutput.toJson([sum: 25])
        ]

        ResponseEntity<OperationResponse> response = restTemplate.postForEntity(
                '/api/operation',
                new HttpEntity<>(operationRequest, headers),
                OperationResponse.class
        )

        assertEquals(125, response.body.body)
    }

    @Test
    @DisplayName('Execute operation with byte operationRequest')
    void bytePayloadOperationTest() {
        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM)

        OperationRequest or = new OperationRequest(
                id: UUID.randomUUID().toString(),
                operationName: 'CORE_SIMPLE_OPERATION',
                direction: OperationDirection.valueOf('enroll' as String),
                async: false,
                body: JsonOutput.toJson([sum: 25])
        )

        ResponseEntity<OperationResponse> response = restTemplate.postForEntity(
                '/api/v2/operation',
                new HttpEntity<>(or.toByteBuffer().array(), headers),
                OperationResponse.class
        )

        assertEquals(125, response.body.body)
    }
}
