package ua.challenge.java.compiler.api;

import org.junit.Test;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/**
 * Created by d.bakal on 01.11.2016.
 */
public class CompilerApiTest {

    /**
    * This small code snippet gets the Java compiler instances and
    * prints out on the console a list of supported Java source versions
    */
    @Test
    public void testToolProvider() {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.getSourceVersions().forEach(System.out::println);
    }
}
