package com.example.firstspringbootapp;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
//@SpringBootTest
@ActiveProfiles("test")
class FirstSpringBootAppApplicationTests {

    static String hello="";
    String world="";

    @BeforeAll
    public static void beforeAll(){
        System.out.println("Before all ");
        hello = "Hello";
    }
    @BeforeEach
    public void beforeEach(){
        System.out.println("Before each ");
        world = "World";
    }
    @Test
    void contextLoads() {
        assertThat(true).isTrue();
        hello = "Hello1";
        System.out.println("Test #1");
    }
    @Test
    void contextLoads2() {
        assertThat(true).isTrue();
        assertThat(hello).isEqualTo("Hello");
        System.out.println("Test #2");
    }
    @AfterEach
    public void afterEach(){
        System.out.println("After each ");
    }
    @AfterAll
    public static void afterAll(){
        System.out.println("After all ");
    }
}
