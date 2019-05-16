package compile_io.docker;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import compile_io.docker.AbstractBuilder;
import compile_io.docker.PythonBuilder;
import static org.mockito.Mockito.*;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import compile_io.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
public class PythonBuilderTest {

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
    when(mockStudentFile.getParent()).thenReturn("/Test");
    AbstractBuilder tc1 = new PythonBuilder(studentFiles, professorFiles, codePath);
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
    isInitialized = false;
    AbstractBuilder tc2 = new PythonBuilder(studentFiles, professorFiles, codePath);
    assertEquals("upload-dir", tc2.getWorkingDirectory());

  }

  @Test
  public void testGetDockerfileData() {
    File studentFile = new File("/Test/TestyMcTestface.py");
    File professorFile = new File("/Test/TestMcTestfaceTest.py");
    List<File> studentFiles = new ArrayList<>();
    List<File> professorFiles = new ArrayList<>();
    studentFiles.add(studentFile);
    professorFiles.add(professorFile);
    AbstractBuilder compiler = new PythonBuilder(studentFiles, professorFiles, codePath);

    StringBuilder dockerfileData = new StringBuilder();
    dockerfileData.append("FROM python:latest\n");
    dockerfileData.append("WORKDIR upload-dir\n");
    dockerfileData.append("ADD TestyMcTestface.py TestyMcTestface.py\n");
    dockerfileData.append("EXPOSE 8000\n");
    dockerfileData.append("CMD python TestyMcTestface.py\n");

    assertEquals(dockerfileData.toString(), compiler.getDockerfileData());
  }

}