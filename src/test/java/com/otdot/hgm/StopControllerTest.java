package com.otdot.hgm;

import com.otdot.hgm.controllers.StopController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.graphql.test.tester.GraphQlTester;

@GraphQlTest(StopController.class)
public class StopControllerTest {

    @Autowired
    private GraphQlTester graphQlTest;

    @Test
    void shouldGetFirstStop() {
     this.graphQlTest.documentName("stopQueryTest")
             .variable("id", "a1")
             .execute()
             .path("stopById")
             .matchesJson("""
                     {
                           "name": "Lokkalankatu",
                           "id": "a1",
                           "lat": 60.202282,
                           "lon": 24.358088
                     }
                     """);
    }

}
