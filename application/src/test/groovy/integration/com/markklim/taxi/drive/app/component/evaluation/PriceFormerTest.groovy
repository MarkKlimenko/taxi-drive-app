package integration.com.markklim.taxi.drive.app.component.evaluation

import com.markklim.taxi.drive.app.Application
import com.markklim.taxi.drive.app.component.PriceFormer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests
import org.springframework.test.context.web.WebAppConfiguration
import org.testng.annotations.DataProvider
import org.testng.annotations.Test

import static org.testng.Assert.*

@TestPropertySource(locations = "classpath:test-properties.properties")
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
class PriceFormerTest extends AbstractTestNGSpringContextTests {

    @Autowired
    PriceFormer priceFormer

    @DataProvider(name = 'dist_to_dist_price_test')
    Object[][] priceDistToDistParam() {
        [ ['grk', 'grd', 110],
          ['grd', 'grk', 110],
          ['grk', 'mtf', 110] ]
    }

//    @Test(dataProvider = 'dist_to_dist_price_test')
//    void priceDistToDistTest(String from, String to, Integer price) {
//        priceFormer.formDtdPrice(from, to)
//                .with { assertEquals(it, price) }
//    }
}