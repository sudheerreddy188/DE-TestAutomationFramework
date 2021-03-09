package web_api_automation_fw;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;



public class RestAssuredTest {

	
	@Test
	public void RestAssuredTest() {
		System.out.println("I ran RestAssured(Json) demo tc .!!! :)");
		//BaseURL or Host
		RestAssured.baseURI="https://jsonplaceholder.typicode.com";
		
		Response res = given().
		       when().
		       get("/todos/1").
		       then().assertThat().statusCode(200).and().contentType(ContentType.JSON).extract().response();
		System.out.println(res.asPrettyString());

	}
}
