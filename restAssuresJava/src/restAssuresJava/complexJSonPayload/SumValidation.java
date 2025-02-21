package restAssuresJava.complexJSonPayload;

import org.apache.groovy.json.internal.JsonParserCharArray;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import restAssuresJava.basics.PayLoad;

public class SumValidation {

	@Test
	void SumOfCourses() {
		
		int sum = 0;
		
		JsonPath js = new JsonPath(PayLoad.CoursePrice());
		
		int totalAmount = js.get("dashboard.purchaseAmount");
		System.out.println(totalAmount);
		
		int courseSize = js.get("courses.size()");
		System.out.println(courseSize);
		
		
		for(int i=0;i<courseSize;i++) {

			int price = js.get("courses["+i+"].price");
			System.out.println(price);
			
			int copies = js.get("courses["+i+"].copies");
			System.out.println(copies);
			sum = sum + (price*copies);
		}
		
		Assert.assertEquals(sum, totalAmount);
	}
	
}
