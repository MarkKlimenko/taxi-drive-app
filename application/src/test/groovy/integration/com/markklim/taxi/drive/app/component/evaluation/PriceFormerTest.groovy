package integration.com.markklim.taxi.drive.app.component.evaluation

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.web.WebAppConfiguration
import systems.vostok.taxi.drive.app.Application

//import org.testng.annotations.DataProvider

@TestPropertySource(locations = "classpath:test-properties.properties")
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
class PriceFormerTest {

    /* @Autowired
     PriceFormer priceFormer

     @DataProvider(name = 'dist_to_dist_price_test')
     Object[][] priceDistToDistParam() {
         [ ['grk', 'grd', 110],
           ['grd', 'grk', 110],
           ['grk', 'mtf', 110] ]
     }*/

//    @Test(dataProvider = 'dist_to_dist_price_test')
//    void priceDistToDistTest(String from, String to, Integer price) {
//        priceFormer.formDtdPrice(from, to)
//                .with { assertEquals(it, price) }
//    }
}