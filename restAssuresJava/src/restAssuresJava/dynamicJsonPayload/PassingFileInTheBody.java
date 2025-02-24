package restAssuresJava.dynamicJsonPayload;

import org.testng.annotations.DataProvider;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import java.nio.file.Files;

import java.nio.file.Paths;

import io.restassured.RestAssured;

import io.restassured.path.json.JsonPath;


public class PassingFileInTheBody {

	//@Test
	public void addBook() throws IOException{
	RestAssured.baseURI="http://216.10.245.166";
	String resp=given().
	header("Content-Type","application/json").
	body(GenerateStringFromResource("C:\\Users\\ravi\\Documents\\Addbookdetails.json")).
	when().
	post("/Library/Addbook.php").
	then().assertThat().statusCode(200).
	extract().response().asString();
	JsonPath js= new JsonPath(resp);
	   String id=js.get("ID");
	   System.out.println(id);
	   //deleteBOok
	}


	public static String GenerateStringFromResource(String path) throws IOException {

	    return new String(Files.readAllBytes(Paths.get(path)));



	}
	
	public class MissingNumber {
	    public static int missingNumber(int[] nums) {
	        int n = nums.length + 1; // Array has one missing number
	        int expectedSum = (n * (n - 1)) / 2; // Sum of first N natural numbers
	        int actualSum = 0;

	        for (int num : nums) {
	            actualSum += num;
	        }

	        return expectedSum - actualSum; // Missing number
	    }

	    public static void main(String[] args) {
	        int[] arr = {1, 2, 4, 5, 6}; // Missing 3
	        System.out.println(missingNumber(arr)); // Output: 3
	    }
	}

	}

