package com.test;

import com.junit.MathUtils;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("when running MathUtilsTest")
 class MathUtilsTest {

     @BeforeAll
     static void beforeAllInit()
     {
         System.out.println("Before All");
     }
    MathUtils utils=MathUtils.getInstance();
    @BeforeEach
    void init()
    {
        System.out.println("Before Each");
    }

    @AfterEach
    void cleanUp()
    {
        System.out.println("After each");
    }

    @Test
    @DisplayName("Testing add method")
    void test()
    {
         int expected=2;
         int actual=utils.add(1,1);
         assertEquals(expected,actual,"The method should add two numbers");
    }

    @Test
    @DisplayName("Testing circle area")
     void testComputeCircleArea()
    {
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
    @Disabled
    void testDisabled()
    {
        fail("testing failing scenario");
    }

    @Test
    void testAssume()
    {
        boolean testAssu=false;
        assumingThat(testAssu,()-> System.out.println("Assumed value"));
        assertEquals(1,1,"Expected value");
    }

    @Test
    @DisplayName("Multiply method")
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
    class AddTest{
        @Test
        @DisplayName("adding positive numbers")
        void addPositiveNums()
        {
            assertEquals(2,utils.add(1,1),"adding two positive numbers");
        }
        @Test
        @DisplayName("adding negative numbers")
        void addNegativeNums()
        {
            assertEquals(-2,utils.add(-1,-1),"adding two negative numbers");
        }
    }
}
