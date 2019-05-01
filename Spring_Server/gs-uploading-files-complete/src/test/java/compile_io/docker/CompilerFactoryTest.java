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
import java.util.List;
import java.util.ArrayList;
import compile_io.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
public class CompilerFactoryTest {

  private BuilderFactory builderFactory = new BuilderFactory();
  private File mockStudentFile = mock(File.class);
  private File mockProfessorFile = mock(File.class);
  private List<File> studentFiles = new ArrayList<>();
  private List<File> professorFiles = new ArrayList<>();
  private String codePath = new String();
  private boolean isInitialized = false;


  private void initialize() {
      if (!isInitialized) {
          this.studentFiles.add(mockStudentFile);
          this.professorFiles.add(mockProfessorFile);
          this.isInitialized = true;
      }
  }

  @Test
  public void testGetBuilderJava() {
    initialize();
    try {
      assertTrue(builderFactory.getBuilder("java", studentFiles, professorFiles, codePath) instanceof JavaBuilder);
    } catch (Exception e) {
      fail("Builder Factory returned the wrong Builder type");
    }
  }

  @Test
  public void testGetBuilderPython() {
    initialize();
    try {
      assertTrue(builderFactory.getBuilder("python", studentFiles, professorFiles, codePath) instanceof PythonBuilder);
    } catch (Exception e) {
      fail("Builder Factory returned the wrong Builder type");
    }
  }

  @Test
  public void testGetCompilerException() {
    initialize();
    try {
      builderFactory.getBuilder("unsupported/invalid language", studentFiles, professorFiles, codePath);
    } catch (Exception e) {
      assertTrue(e instanceof UnsupportedBuilderException);
    }

  }

}
