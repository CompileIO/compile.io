package test;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import compile_io.docker.CompilerFactory;
import compile_io.docker.JavaCompiler;
import static org.mockito.Mockito.*;
import java.io.File;
import compile_io.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={Application.class})
public class CompilerFactoryTest {

  CompilerFactory compilerFactory = new CompilerFactory();

//  @mock
 // JavaCompiler javaCompiler

  @Test
  public void testGetCompilerJava() {
    File file = mock(File.class);
    assertEquals(compilerFactory.getCompiler("java", file) instanceof JavaCompiler, true);
  }

  @Test
  public void testGetCompilerNull() {
    File file = mock(File.class);
    assertEquals(compilerFactory.getCompiler("unsupported/invalid language", file), null);
    assertEquals(compilerFactory.getCompiler("", file), null);
  }


}
