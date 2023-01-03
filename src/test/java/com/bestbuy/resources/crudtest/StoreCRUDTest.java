package com.bestbuy.resources.crudtest;

import com.bestbuy.model.StorePojo;
import com.bestbuy.resources.testbase.TestBase;
import com.bestbuy.studentinfo.StoresSteps;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.HashMap;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;


@RunWith(SerenityRunner.class)
public class StoreCRUDTest extends TestBase {

    static String name = "Next";
    static String type = "Express";
    static String address = "London Road";
    static String address2 = "next street";
    static String city = "London";
    static String state = "Greater London";
    static String zip = "NN15 7EW";
    static int lat = 35;
    static int lng = 23;
    static String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8";

    static int id;
    @Steps
    StoresSteps storesSteps;


    @Title("This will get all stores details")
    @Test
    public void test001() {
        storesSteps.getAllStores();
    }


    @Title("This method will create new store details")
    @Test
    public void test002() {
        ValidatableResponse response = storesSteps.createNewStore(name, type, address, address2, city, state, zip, lat, lng, hours);
        response.log().all().body("name", equalTo(name));
        id = response.extract().path("id");
    }


    @Title("This will get single store details")
    @Test
    public void test003() {
        storesSteps.getAStoreByitId(id).statusCode(200);

    }


    @Title("This will update single store details")
    @Test
    public void test004() {
        name = name + "O2 Arena";
        ValidatableResponse response = storesSteps.updateStoreDetail(id, name, type, address, address2, city, state, zip, lat, lng, hours);
        response.log().all().body("name", equalTo(name));
    }


    @Title("This will delete single store details")
    @Test
    public void test005() {
        storesSteps.deleteStoreById(id).statusCode(200);
        storesSteps.getAStoreByitId(id).statusCode(404);
    }
}
