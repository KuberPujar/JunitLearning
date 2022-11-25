package com.test;

import com.junit.MathUtils;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import jdk.jfr.Name;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("when running MathUtilsTest")
 class MathUtilsTest {
TestInfo testInfo;
TestReporter testReporter;
     @BeforeAll
     static void beforeAllInit()
     {
         System.out.println("Before All");
     }
    MathUtils utils=MathUtils.getInstance();
    @BeforeEach
    void init(TestInfo testInfo,TestReporter testReporter)
    {
        this.testReporter=testReporter;
        this.testInfo=testInfo;
        System.out.println("Before Each");
        testReporter.publishEntry("Running test "+testInfo.getDisplayName()+" with tags "+testInfo.getTags());
    }

    @AfterEach
    void cleanUp()
    {
        System.out.println("After each");
    }

    @Test
    @DisplayName("Testing add method")
    @Tag("Math")
    void test()
    {
         int expected=2;
         int actual=utils.add(1,1);
         assertEquals(expected,actual,"The method should add two numbers");
    }

    //@Test
    @DisplayName("Testing circle area")
    @RepeatedTest(3)
     void testComputeCircleArea(RepetitionInfo repetitionInfo)
    {
        System.out.println(repetitionInfo.getTotalRepetitions());
        assertEquals(314.1592653589793,utils.computeCircleArea(10),"Method should find area of circle");
    }

    @Test
    @EnabledOnOs(OS.LINUX)
     void testDivide()
    {
        assertEquals(3,utils.divide(6,2),"Divide by zero should throw");
    }

    @Test
    @DisplayName("Test should not run as its disabled")
    @Disabled("Test disable due to nt in scope")
    void testDisabled()
    {
        fail("testing failing scenario");
    }

    @Test
    void testAssume()
    {
        boolean testAssume=false;
        assumingThat(testAssume,()-> System.out.println("Assumed value"));
        assertEquals(3,utils.add(2,1),"Expected value");
    }

    @Test
    @DisplayName("Multiply method")
    @Tag("Math")
    void testMultiply()
    {
        assertAll(
                ()-> assertEquals(4,utils.multiply(2,2),"Should return product of two numbers"),
                ()-> assertEquals(0,utils.multiply(2,0),"Should return product of two numbers"),
                ()-> assertEquals(-2,utils.multiply(-1,2),"Should return product of two numbers")
        );
    }
    @Nested
    @DisplayName("Add Method")
    @Tag("Math")
    class AddTest{
        @Test
        @DisplayName("adding positive numbers")
        void addPositiveNumbers()
        {
            assertEquals(2,utils.add(1,1),"adding two positive numbers");
        }
        @Test
        @DisplayName("adding negative numbers")
        void addNegativeNumbers()
        {
            assertEquals(-2,utils.add(-1,-1),"adding two negative numbers");
        }
    }


}
