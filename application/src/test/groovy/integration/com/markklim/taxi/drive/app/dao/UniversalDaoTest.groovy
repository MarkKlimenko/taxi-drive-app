package integration.com.markklim.taxi.drive.app.dao

import com.datastax.driver.core.querybuilder.QueryBuilder
import com.datastax.driver.core.querybuilder.Select
import systems.vostok.taxi.drive.app.Application
import systems.vostok.taxi.drive.app.dao.repository.UniversalDao
import systems.vostok.taxi.drive.app.dao.entity.Client
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests
import org.springframework.test.context.web.WebAppConfiguration
import org.testng.annotations.Test

import static org.testng.Assert.*

@TestPropertySource(locations = 'classpath:test-properties.properties')
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
class UniversalDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    @Delegate
    UniversalDao universalDao

    Client singleClient = [clientLogin: '+79147654321',
                           firstName  : 'Mark',
                           lastName   : 'Klimenko',
                           ridesAmount: 9,
                           clientType : null] as Client

    @Test
    void insertSingleTest() {
        insertSingle(singleClient)
                .with { assertEquals(it, singleClient) }
    }

    @Test
    void selectSingleTest() {
        Select select = QueryBuilder.select().from('client')
        select.where(QueryBuilder.eq('clientLogin', '+79147654321'))

        selectSingle(select, Client.class)
                .with { assertEquals(it, singleClient) }
    }

    @Test
    void selectUnexistedBySingleTest() {
        Select select = QueryBuilder.select().from('client')
        select.where(QueryBuilder.eq('clientLogin', 'notInBase'))

        selectSingle(select, Client.class)
                .with { assertEquals(it, null) }
    }

    @Test
    void selectUnexistedByAllTest() {
        Select select = QueryBuilder.select().from('client')
        select.where(QueryBuilder.eq('clientLogin', 'notInBase'))

        selectAll(select, Client.class)
                .with { assertEquals(it, []) }
    }
}
