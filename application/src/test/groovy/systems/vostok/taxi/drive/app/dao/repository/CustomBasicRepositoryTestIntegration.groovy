package systems.vostok.taxi.drive.app.dao.repository

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import systems.vostok.taxi.drive.app.dao.entity.Client
import systems.vostok.taxi.drive.app.dao.repository.impl.ClientRepository

import static org.junit.jupiter.api.Assertions.*

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CustomBasicRepositoryTestIntegration {
    @Autowired
    ClientRepository clientRepository

    @Test
    void getByEntityIdTest() {
        Client client = new Client(
                login: '81112223344',
                firstName: 'TestName',
                ridesAmount: 0
        )

        clientRepository.save(client)
        assertEquals(client, clientRepository.getByEntityId(new Client(login: '81112223344')))
    }
}

