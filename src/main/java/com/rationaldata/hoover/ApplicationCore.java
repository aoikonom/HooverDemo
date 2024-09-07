package com.rationaldata.hoover;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@QuarkusMain
@ApplicationPath("/api/v1/")
public class ApplicationCore extends Application {

    public static void main(String... args) {
        Quarkus.run(args);
    }
}