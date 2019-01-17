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
    try {
      assertTrue(builderFactory.getBuilder("java", file) instanceof JavaBuilder);
    } catch (Exception e) {
      fail("Builder Factory returned the wrong Builder type");
    }
  }

  @Test
  public void testGetBuilderPython() {
    File file = mock(File.class);
    try {
      assertTrue(builderFactory.getBuilder("python", file) instanceof PythonBuilder);
    } catch (Exception e) {
      fail("Builder Factory returned the wrong Builder type");
    }
  }

  @Test
  public void testGetCompilerException() {
    File file = mock(File.class);
    try {
      builderFactory.getBuilder("unsupported/invalid language", file);
    } catch (Exception e) {
      assertTrue(e instanceof UnsupportedBuilderException);
    }

  }

}
