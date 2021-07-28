package com.jojo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.Comparator;
import java.util.Map;

//@RunWith(SpringRunner.class)
//@SpringBootTest
//@TestPropertySource(properties={
//        "spring.autoconfigure.exclude=" +
//                "org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration"
//})
public class testPathMatcher {
    // @Test
    public static void main(String args[]) {
        AntPathMatcher antPathMatcher = new AntPathMatcher();

        System.out.println(antPathMatcher.isPattern("?D:/workspace/jojo_adventure/"));
    }
}
