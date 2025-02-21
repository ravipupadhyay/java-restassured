package restAssuresJava.complexJSonPayload;

import io.restassured.path.json.JsonPath;
import restAssuresJava.basics.PayLoad;

public class ComplexJsonParse {

	
	public static void main(String[] args) {
		
		JsonPath js = new JsonPath(PayLoad.CoursePrice());
		
		int courseSize = js.get("courses.size()");
		System.out.println(courseSize);
		
		int amount = js.get("dashboard.purchaseAmount");
		System.out.println(amount);
		
		for(int i=0;i<courseSize;i++) {
			
			String title = js.get("courses["+i+"].title");
			System.out.println(title);

			int price = js.get("courses["+i+"].price");
			System.out.println(price);
			

			int copies = js.get("courses["+i+"].copies");
			System.out.println(copies);
		}
	}
}
