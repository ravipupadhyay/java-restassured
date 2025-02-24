package restAssuresJava.dynamicJsonPayload;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import restAssuresJava.basics.PayLoad;

import static io.restassured.RestAssured.*;

public class DynamicJson {

	
	@Test(dataProvider = "bookData")
	public void addBook(String isbn, String aisle) {
		RestAssured.baseURI = "http://216.10.245.166";
		
		String resp= given().header("Content-Type","application/json").body(PayLoad.addBook(isbn,aisle)).
		when().post("/Library/Addbook.php").
		then().log().all().statusCode(200).extract().response().asString();
		
		JsonPath js = new JsonPath(resp);
		String id = js.get("ID");
		System.out.println(id);
		
	}
	
	
	
	@DataProvider(name = "bookData")
	public Object[][] getData() {
		
		return new Object[][] {{"physics","3445"},{"science","5667"},{"bio","6778"}};
	}
	
}
