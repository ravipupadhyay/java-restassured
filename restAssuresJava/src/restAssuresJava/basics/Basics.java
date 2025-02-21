package restAssuresJava.basics;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Basics {
	public static void main(String[] args) {
		
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		
		String response=given().queryParam("key", "qaclick123").header("Content-Type","application/json").body(PayLoad.AddPlace()).
		when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
	
		//extract response
		JsonPath path = new JsonPath(response);
		String placeId = path.get("place_id");
		System.out.println(placeId);
		
		//Update Place
				String newAddress = "Summer Walk, Africa";
				
				given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
				.body("{\r\n" + 
						"\"place_id\":\""+placeId+"\",\r\n" + 
						"\"address\":\""+newAddress+"\",\r\n" + 
						"\"key\":\"qaclick123\"\r\n" + 
						"}").
				when().put("maps/api/place/update/json")
				.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
				
		//Get Place
		String getPlaceResponse = given().queryParam("key", "qaclick123").queryParam("place_id",placeId).
				when().get("maps/api/place/get/json").
				then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		JsonPath js = new JsonPath(getPlaceResponse);
		
		String place = js.get("address");
		
		Assert.assertEquals(newAddress, place);
		
		
		
	}
}
