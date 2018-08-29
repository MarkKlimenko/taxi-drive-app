package systems.vostok.taxi.drive.app.operation.client

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.context.SecurityContextImpl
import org.springframework.test.context.junit.jupiter.SpringExtension
import systems.vostok.taxi.drive.app.dao.domain.operation.OperationResponse
import systems.vostok.taxi.drive.app.operation.OperationFlowTestUtil
import systems.vostok.taxi.drive.app.operation.PreconditionTestUtil
import systems.vostok.taxi.drive.app.util.exception.OperationExecutionException

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.junit.jupiter.api.Assertions.assertThrows
import static systems.vostok.taxi.drive.app.dao.domain.operation.CoreOperationNames.*

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DisplayName('Client CRUD flow tests')
class ClientFlowTestIntegration {
    @Autowired
    ClientFlowTestUtil clientUtil

    @Autowired
    OperationFlowTestUtil operationUtil

    @Autowired
    PreconditionTestUtil preconditionUtil

    @BeforeAll
    static void initTest() {
        SecurityContext context = new SecurityContextImpl()
        context.setAuthentication(new UsernamePasswordAuthenticationToken('tester', '123456'))
        SecurityContextHolder.setContext(context)
    }

    @Test
    @DisplayName('Create client')
    void createTest() {
        preconditionUtil.clientPreconditions()
        clientUtil.createClient('simple_client')
                .with { clientUtil.checkClient('simple_client', it) }
    }

    @Test
    @DisplayName('Edit client')
    void editTest() {
        preconditionUtil.clientPreconditions()

        clientUtil.createClient('simple_client')
        clientUtil.editClient('simple_client_edited')
                .with { clientUtil.checkClient('simple_client_edited', it) }
    }

    @Test
    @DisplayName('Delete client')
    void deleteTest() {
        preconditionUtil.clientPreconditions()

        clientUtil.createClient('simple_client')
        clientUtil.deleteClient('simple_client')
        clientUtil.checkClientNonexistence('simple_client')
    }

    @Test
    @DisplayName('Rollback create client operation')
    void rollbackCreationTest() {
        preconditionUtil.clientPreconditions()

        OperationResponse addResponse = clientUtil.createClient('simple_client')
        clientUtil.checkClient('simple_client', addResponse)

        operationUtil.rollbackOperation(ADD_CLIENT_OPERATION, addResponse)
        clientUtil.checkClientNonexistence('simple_client')
    }

    @Test
    @DisplayName('Rollback edit client operation')
    void rollbackEditTest() {
        preconditionUtil.clientPreconditions()

        OperationResponse addResponse = clientUtil.createClient('simple_client')
        OperationResponse editResponse = clientUtil.editClient('simple_client_edited')

        operationUtil.rollbackOperation(EDIT_CLIENT_OPERATION, editResponse)
        clientUtil.checkClient('simple_client', addResponse)
    }

    @Test
    @DisplayName('Rollback delete client operation')
    void rollbackDeleteTest() {
        preconditionUtil.clientPreconditions()

        OperationResponse addResponse = clientUtil.createClient('simple_client')
        OperationResponse deleteResponse = clientUtil.deleteClient('simple_client')
        clientUtil.checkClientNonexistence('simple_client')

        operationUtil.rollbackOperation(DELETE_CLIENT_OPERATION, deleteResponse)
        clientUtil.checkClient('simple_client', addResponse)
    }

    @Test
    @DisplayName('Create already existing client')
    void editAlreadyExistingTest() {
        preconditionUtil.clientPreconditions()
        clientUtil.createClient('simple_client')

        OperationExecutionException e = assertThrows(
                OperationExecutionException.class,
                { clientUtil.createClient('simple_client') }
        )
        assertEquals('Entity with target ID already exists', e.cause.message)
    }

    @Test
    @DisplayName('Delete nonexistent client')
    void deleteNonexistentTest() {
        preconditionUtil.clientPreconditions()

        OperationExecutionException e = assertThrows(
                OperationExecutionException.class,
                { clientUtil.deleteClient('simple_client') }
        )
        assertEquals('Entity with target ID does not exist', e.cause.message)
    }

    @Test
    @DisplayName('Rollback creation of edited client')
    void rollbackCreationOfEditedTest() {
        preconditionUtil.clientPreconditions()

        OperationResponse addResponse = clientUtil.createClient('simple_client')
        OperationResponse editResponse = clientUtil.editClient('simple_client_edited')

        OperationExecutionException e = assertThrows(
                OperationExecutionException.class,
                { operationUtil.rollbackOperation(ADD_CLIENT_OPERATION, addResponse) }
        )
        assertEquals('Rollback rejected: entity was modified or removed', e.cause.message)

        clientUtil.checkClient('simple_client_edited', editResponse)
    }

    @Test
    @DisplayName('Rollback of double edited client')
    void rollbackDoubleEditedTest() {
        preconditionUtil.clientPreconditions()

        clientUtil.createClient('simple_client')
        OperationResponse editFirstResponse = clientUtil.editClient('simple_client_edited')
        OperationResponse editSecondResponse = clientUtil.editClient('simple_client_edited_2')

        OperationExecutionException e = assertThrows(
                OperationExecutionException.class,
                { operationUtil.rollbackOperation(EDIT_CLIENT_OPERATION, editFirstResponse) }
        )
        assertEquals('Rollback rejected: entity was modified or removed', e.cause.message)

        clientUtil.checkClient('simple_client_edited_2', editSecondResponse)
    }

    @Test
    @DisplayName('Rollback creation of already deleted client')
    void rollbackAlreadyDeletedTest() {
        preconditionUtil.clientPreconditions()

        OperationResponse addResponse = clientUtil.createClient('simple_client')
        clientUtil.deleteClient('simple_client')

        OperationExecutionException e = assertThrows(
                OperationExecutionException.class,
                { operationUtil.rollbackOperation(ADD_CLIENT_OPERATION, addResponse) }
        )
        assertEquals('Rollback rejected: entity was modified or removed', e.cause.message)

        clientUtil.checkClientNonexistence('simple_client')
    }
}
