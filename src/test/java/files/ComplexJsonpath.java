package files;

import io.restassured.path.json.JsonPath;

public class ComplexJsonpath {

    public static void main(String[] args) {

        JsonPath jsonPath = new JsonPath(Payload.coursePrice());

        //    1. Print No of courses returned by API
        int count = jsonPath.getInt("courses.size()");
        System.out.println(count);

        //2.Print Purchase Amount
        int anInt1 = jsonPath.getInt("dashboard.purchaseAmount");
        System.out.println(anInt1);

        //3. Print Title of the first course
        String title = jsonPath.get("courses[0].title");
        System.out.println(title);


        //4. Print All course titles and their respective Prices
        for (int i = 0; i < count; i++) {
            String coursesTitles = jsonPath.get("courses[" + i + "].title");
            String coursesPrice = jsonPath.get("courses[" + i + "].price").toString();
            System.out.println(coursesTitles + " " + coursesPrice);

        }
    }

//5. Print no of copies sold by RPA Course
//
//6. Verify if Sum of all Course prices matches with Purchase Amount
}
