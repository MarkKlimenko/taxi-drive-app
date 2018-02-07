package unit.com.markklim.taxi.drive.app.component

import systems.vostok.taxi.drive.app.dao.entity.Address
import org.testng.annotations.DataProvider

class DefineDistrictComponentTest {

    @DataProvider(name = 'define_district_test')
    Object[][] addressParams() {
        [[new Address(null, null, "Spassk", "Nekrasova", "58/2"), "District"],
         [new Address(null, null, "Spassk", "Pushkina", "22"), "District"]]
    }

//    @Test(dataProvider = "define_district_test")
//    void defineDistrict(Address address, String expDistrict) throws Exception {
//        new DistrictMatcher().defineDistrict(address)
//                .with { assertEquals(it, expDistrict) }
//    }
}
