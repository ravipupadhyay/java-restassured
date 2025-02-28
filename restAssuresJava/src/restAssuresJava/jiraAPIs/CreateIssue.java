package restAssuresJava.jiraAPIs;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

public class CreateIssue {

	String ID = null;
	
	@Test
	public void createIssue() {
		
		RestAssured.baseURI="https://rpupadhyay014.atlassian.net";
		String resp = given().header("Content-Type","application/json").header("Authorization","").
				body("{\r\n"
				+ "\"fields\": {\r\n"
				+ "\"project\": {\r\n"
				+ "\"key\": \"SCRUM\"\r\n"
				+ "},\r\n"
				+ "\"issuetype\": {\r\n"
				+ "\"name\": \"Bug\"\r\n"
				+ "},\r\n"
				+ "\"summary\": \"Links not workingt"+System.currentTimeMillis()+"\",\r\n"
				+ "\"description\": [\"value\",\"This is test desc\"]\r\n"
				+ "}\r\n"
				+ "}}").
		when().post("/rest/api/3/issue").
		then().log().all().statusCode(201).extract().response().asString();
		JsonPath js = new JsonPath(resp);
		ID = js.get("id");
		System.out.println(resp);
	}
	
	@Test(dependsOnMethods = "createIssue")
	public void sendAttachment() {
		
		
		
		RestAssured.baseURI="https://rpupadhyay014.atlassian.net";
		String resp = given().header("X-Atlassian-Token","no-check").header("Authorization","").
				multiPart("file", new File("C:\\Users\\upadhrx\\Pictures\\Screenshots\\Screenshot (1).png")).
		when().post("/rest/api/3/issue/"+ID+"/attachments").
		then().log().all().statusCode(200).extract().response().asString();
		
		System.out.println(resp);
	}
}
