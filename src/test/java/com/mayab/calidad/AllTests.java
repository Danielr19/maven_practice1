package com.mayab.calidad;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AppTest.class, TestCalculadora2.class, TestParameterized.class })
public class AllTests {

}
