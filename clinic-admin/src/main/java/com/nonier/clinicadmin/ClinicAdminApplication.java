package com.nonier.clinicadmin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer
@SpringBootApplication
public class ClinicAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClinicAdminApplication.class, args);
    }

}
