import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class GreetingControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void noParamGreetingShouldReturnDefaultMessage() throws Exception {

		this.mockMvc.perform(get("/greeting")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.content").value("Hello, World!"));
	}

	@Test
	public void paramGreetingShouldReturnTailoredMessage() throws Exception {

		this.mockMvc.perform(get("/greeting").param("name", "Spring Community"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.content").value("Hello, Spring Community!"));
	}
	
	  @When("the client requests the greeting without parameters")
	    public void whenClientRequestsGreetingWithoutParameters() throws Exception {
	        resultActions = mockMvc.perform(get("/greeting")).andDo(print());
	    }

	    @When("the client requests the greeting with parameter {string}")
	    public void whenClientRequestsGreetingWithParameter(String name) throws Exception {
	        resultActions = mockMvc.perform(get("/greeting").param("name", name)).andDo(print());
	    }

	    @Then("the response should have status code {int}")
	    public void thenResponseShouldHaveStatusCode(int statusCode) throws Exception {
	        resultActions.andExpect(status().is(statusCode));
	    }

	    @Then("the response content should be {string}")
	    public void thenResponseContentShouldBe(String expectedContent) throws Exception {
	        resultActions.andExpect(jsonPath("$.content").value(expectedContent));
	    }

}
