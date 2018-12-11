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
@SpringBootTest(classes={Application.class})
public class JavaCompilerTest {

@Test
public void testTest() {
  assertEquals(true,true);
}

}
