package unit.com.markklim.taxi.drive.app.api.controller

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.RequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import static org.testng.Assert.*

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
@SpringBootTest
class RideControllerTest {

    @Autowired
    private WebApplicationContext context

    private MockMvc mockMvc


    @Before
    void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build()
    }

    @Test
    void ordersPathParamTest() {

        /*Mockito.when(
                userService.getAll()).thenReturn(new ArrayList<User>(Arrays.asList(
                new User()
        )))
*/

        RequestBuilder builder = MockMvcRequestBuilders
                .get("/api/user/all")
                .header("host", "localhost:8087")
                .accept(MediaType.APPLICATION_JSON)
        MvcResult result = mockMvc.perform(builder).andReturn()
        println("============================================")
        println("Result ${result.response.status}")
        println("============================================")
        assertEquals(404, result.response.status)
    }
}
