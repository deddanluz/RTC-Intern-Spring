/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ded_technologies.rtc.intern.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *
 * @author Даниил
 */
@SpringBootApplication(scanBasePackages = {"ded_technologies.rtc.intern.spring"})
@EnableJpaRepositories(basePackages = {"ded_technologies.rtc.intern.spring.repositories"})
public class RTCInternSpring {

    public static void main(String[] args) {
        SpringApplication.run(RTCInternSpring.class, args);
    }
}
