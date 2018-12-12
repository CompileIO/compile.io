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
    File testFile = new File("C:\\Test\\TestyMcTestfile");
    AbstractCompiler tc1 = new JavaCompiler(testFile);
    assertEquals("C:\\Test", tc1.getFileDirectory());
    assertEquals("TestyMcTestfile", tc1.getFileName());
    
    testFile = new File("FileTest");
    AbstractCompiler tc2 = new JavaCompiler(testFile);
    assertEquals("/", tc2.getFileDirectory());
    assertEquals("FileTest", tc2.getFileName());
  }

}
