package requests;

import io.restassured.RestAssured;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import javax.xml.stream.Location;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.get;


public class APITests {

    //1) Execute a GET request that lists all posts resources
    @Test
    public void GetList(){

        given()
                .get("https://jsonplaceholder.typicode.com/posts")
                .then()
                .statusCode(200)
                .log().all();
    }
    //2) Execute a GET request that returns a single posts resource with id = 11
    @Test
    public void GetSingleList(){

        given()
                .get("https://jsonplaceholder.typicode.com/posts/11")
                .then()
                .statusCode(200)
                .log().all();
    }
    //3) Execute a POST request to create a new posts resource
    @Test
    public void PostRequest(){

        JSONObject request = new JSONObject();
        request.put("title", "foo" );
        request.put("body" , "bar" );
        request.put("userId", 1  );

        System.out.println(request);
        System.out.println(request.toJSONString());

        given().
                body(request.toJSONString()).
                when().
                post("https://jsonplaceholder.typicode.com/posts").
                then().
                statusCode(201).
                log().all();

    }
    //4) Execute a DELETE request that removes the posts resource with id = 1
    @Test
    public void DeleteRequest(){
        given().
                when().
                delete("https://jsonplaceholder.typicode.com/posts/1").
                then().
                statusCode(200).
                log().all();

    }
    //5) Bonus Question: Add assertions for the expected status codes i.e. 200 OK
    @Test
    public void assertions(){

        given()
                .get("https://jsonplaceholder.typicode.com/posts")
                .then()
                .assertThat()
                .statusCode(200)
                .log().all();

    }
    //Challenge:
   // @Test
    //public void GetDogList()
    //{
        //Location location =
               // given().
                        //when().
                       // post("https://dog.ceo/dog-api/").as(Location.class);

        //Assert.assertEquals()
        //System.out.println(location);
        {
            //"message": "https://images.dog.ceo/breeds/pinscher-miniature/n02107312_5567.jpg",
                //"status": "success"
        }
   // }
//    @Test
//    public void UserRegistrationSuccessful() {
//        RestAssured.baseURI ="https://dog.ceo/dog-api/";
//        RequestSpecification request = RestAssured.given();
//        JSONObject requestParams = new JSONObject();
//        //requestParams.put("UserName", "test_rest");
//        //requestParams.put("Password", "rest@123");
//        request.body(requestParams.toJSONString());
//        Response response = request.post("/Account/v1/User");
//        ResponseBody body = response.getBody();
//// Deserialize the Response body into JSONSuccessResponse
//        JSONSuccessResponse responseBody = body.as(JSONSuccessResponse.class);
//// Use the JSONSuccessResponseclass instance to Assert the values of Response.
//        Assert.assertEquals("OPERATION_SUCCESS", responseBody.SuccessCode);
//        Assert.assertEquals("Operation completed successfully", responseBody.Message); }
}
