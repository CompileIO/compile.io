package compile_io.docker;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import compile_io.docker.JavaBuilder;
import static org.mockito.Mockito.*;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import compile_io.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
public class JavaBuilderTest {

  private File mockStudentFile;
  private File mockProfessorFile;
  private List<File> studentFiles;
  private List<File> professorFiles;
  private String codePath = new String();
  private boolean isInitialized = false;

  private void initialize() {
    if (!isInitialized) {
      this.mockStudentFile = mock(File.class);
      this.mockProfessorFile = mock(File.class);
      this.studentFiles = new ArrayList<>();
      this.professorFiles = new ArrayList<>();
      this.studentFiles.add(mockStudentFile);
      this.professorFiles.add(mockProfessorFile);
      this.isInitialized = true;
    }
  }

  @Test
  public void testSuperConstructor() {
    initialize();
    when(mockStudentFile.getName()).thenReturn("TestyMcTestface");
    when(mockStudentFile.getParent()).thenReturn("C:\\Test");
    AbstractBuilder tc1 = new JavaBuilder(studentFiles, professorFiles, codePath);
    assertEquals("upload-dir", tc1.getWorkingDirectory());
  }

  @Test
  public void testSuperConstructorNullFile() {
    initialize();
    File testFileNull = mock(File.class);
    when(testFileNull.getName()).thenReturn("FileTest");
    when(testFileNull.getParent()).thenReturn(null);

    studentFiles = new ArrayList<>();
    studentFiles.add(testFileNull);

    AbstractBuilder tc2 = new JavaBuilder(studentFiles, professorFiles, codePath);
    assertEquals("/", tc2.getWorkingDirectory());
  }

  @Test
  public void testGetDockerfileData() {
    File studentFile = new File("/Test/TestyMcTestface.java");
    File professorFile = new File("/Test/TestyMcTestfaceTest.java");
    List<File> studentFiles = new ArrayList<>();
    List<File> professorFiles = new ArrayList<>();
    studentFiles.add(studentFile);
    professorFiles.add(professorFile);
    AbstractBuilder compiler = new JavaBuilder(studentFiles, professorFiles, codePath);

    StringBuilder dockerfileData = new StringBuilder();
    dockerfileData.append("FROM gradle:4.3-jdk-alpine\n");
    dockerfileData.append("WORKDIR \\Test\n");
    dockerfileData.append("EXPOSE 8000\n");
    dockerfileData.append("RUN mkdir -p src/main/java\n");
    dockerfileData.append("RUN mkdir -p src/test/java\n");
    dockerfileData.append("COPY build.gradle build.gradle\n");
    dockerfileData.append("COPY " + codePath + "TestyMcTestface.java TestyMcTestface.java\n");
    dockerfileData.append("RUN mv TestyMcTestface.java src/main/java/\n");
    dockerfileData.append("COPY " + codePath + "TestyMcTestfaceTest.java TestyMcTestfaceTest.java\n");
    dockerfileData.append("RUN mv TestyMcTestfaceTest.java src/test/java/\n");
    dockerfileData.append("CMD export GRADLE_USER_HOME=\"\\Test\" && gradle test\n");

    assertEquals(dockerfileData.toString(), compiler.getDockerfileData());
  }

}
