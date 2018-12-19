package compile_io.docker;

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
@SpringBootTest(classes = { Application.class })
public class JavaCompilerTest {

  @Test
  public void testSuperConstructor() {
    File testFile = mock(File.class);
    when(testFile.getName()).thenReturn("TestyMcTestface");
    when(testFile.getParent()).thenReturn("C:\\Test");
    AbstractCompiler tc1 = new JavaCompiler(testFile);
    assertEquals("C:\\Test", tc1.getFileDirectory());
    assertEquals("TestyMcTestface", tc1.getFileName());
    
    File testFileNull = mock(File.class);
    when(testFileNull.getName()).thenReturn("FileTest");
    when(testFileNull.getParent()).thenReturn(null);
    AbstractCompiler tc2 = new JavaCompiler(testFileNull);
    assertEquals("/", tc2.getFileDirectory());
    assertEquals("FileTest", tc2.getFileName());
  }

  @Test
  public void testGetDockerfileData() {
    File testFile = mock(File.class);
    when(testFile.getName()).thenReturn("TestyMcTestface");
    when(testFile.getParent()).thenReturn("/Test");
    AbstractCompiler compiler = new JavaCompiler(testFile);

    StringBuilder dockerfileData = new StringBuilder();
    dockerfileData.append("FROM openjdk\n");
    dockerfileData.append("WORKDIR /Test\n");
    dockerfileData.append("ADD TestyMcTestface TestyMcTestface\n");
    dockerfileData.append("EXPOSE 8000\n");
    dockerfileData.append("CMD java -jar TestyMcTestface\n");

    assertEquals(dockerfileData.toString(), compiler.getDockerfileData());
  }

}
