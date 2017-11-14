package unit.com.markklim.taxi.drive.app.component

import com.markklim.taxi.drive.app.component.DistrictMatcher
import com.markklim.taxi.drive.app.model.Address
import org.testng.annotations.Test
import org.testng.annotations.DataProvider

import static org.junit.Assert.*

class DefineDistrictComponentTest {

    @DataProvider(name = 'define_district_test')
    Object[][] addressParams() {
        [[new Address(null, null, "Spassk", "Nekrasova", "58/2"), "District"],
         [new Address(null, null, "Spassk", "Pushkina", "22"), "District"]]
    }

    @Test(dataProvider = "define_district_test")
    void defineDistrict(Address address, String expDistrict) throws Exception {
        new DistrictMatcher().defineDistrict(address)
                .with { assertEquals(it, expDistrict) }
    }
}
