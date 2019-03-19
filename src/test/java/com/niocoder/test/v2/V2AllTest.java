package com.niocoder.test.v2;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ApplicationContextTestV2.class,
        BeanDefinitionTestV2.class,
        BeanDefinitionValueResolverTest2.class,
        CustomBooleanEditorTest.class,
        CustomDateEditorTest.class,
        CustomNumberEditorTest.class,
        TypeConverterTest.class})
public class V2AllTest {
}