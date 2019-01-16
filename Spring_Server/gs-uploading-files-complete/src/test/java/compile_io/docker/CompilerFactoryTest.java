package compile_io.docker;


import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import compile_io.docker.BuilderFactory;
import compile_io.docker.JavaBuilder;
import compile_io.docker.PythonBuilder;
import static org.mockito.Mockito.*;
import java.io.File;
import compile_io.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
public class CompilerFactoryTest {

  BuilderFactory builderFactory = new BuilderFactory();

  @Test
  public void testGetBuilderJava() {
    File file = mock(File.class);
    assertTrue(builderFactory.getBuilder("java", file) instanceof JavaBuilder);
  }

  @Test
  public void testGetBuilderPython() {
    File file = mock(File.class);
    assertTrue(builderFactory.getBuilder("python", file) instanceof PythonBuilder);
  }

  @Test
  public void testGetCompilerNull() {
    File file = mock(File.class);
    assertEquals(builderFactory.getCompiler("unsupported/invalid language", file), null);
    assertEquals(builderFactory.getCompiler("", file), null);
  }

}
