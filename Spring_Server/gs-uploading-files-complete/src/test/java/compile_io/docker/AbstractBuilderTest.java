package compile_io.docker;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import compile_io.docker.JavaBuilder;
import static org.mockito.Mockito.*;
import java.io.File;

import compile_io.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
public class AbstractBuilderTest {

    @Test
    public void testGetFileName() {
        File testFile = mock(File.class);
        when(testFile.getName()).thenReturn("TestyMcTestface");
        when(testFile.getParent()).thenReturn("C:\\Test");
        AbstractBuilder testCompiler = new JavaBuilder(testFile);
        assertEquals("TestyMcTestface", testCompiler.getFileName());
    }

    @Test
    public void testGetFileDirectory() {
        File testFile = mock(File.class);
        when(testFile.getName()).thenReturn("TestyMcTestface");
        when(testFile.getParent()).thenReturn("C:\\Test");
        AbstractBuilder testCompiler = new JavaBuilder(testFile);
        assertEquals("C:\\Test", testCompiler.getFileDirectory());
    }

    @Test
    public void testSetFileName() {
        File testFile = mock(File.class);
        when(testFile.getName()).thenReturn("TestyMcTestface");
        when(testFile.getParent()).thenReturn("C:\\Test");
        AbstractBuilder testCompiler = new JavaBuilder(testFile);
        assertEquals("TestyMcTestface", testCompiler.getFileName());
        testCompiler.setFileName("NewTest");
        assertEquals("NewTest", testCompiler.getFileName());
    }

    @Test
    public void testSetFileDirectory() {
        File testFile = mock(File.class);
        when(testFile.getName()).thenReturn("TestyMcTestface");
        when(testFile.getParent()).thenReturn("C:\\Test");
        AbstractBuilder testCompiler = new JavaBuilder(testFile);
        assertEquals("C:\\Test", testCompiler.getFileDirectory());
        testCompiler.setFileDirectory("C:\\NewTest");
        assertEquals("C:\\NewTest", testCompiler.getFileDirectory());
    }

    @Test 
    public void testGetExecuter() {
        File testFile = mock(File.class);
        when(testFile.getName()).thenReturn("TestyMcTestface");
        when(testFile.getParent()).thenReturn("C:\\Test");
        AbstractBuilder testCompiler = new JavaBuilder(testFile);
        assertTrue(testCompiler.getExecuter() instanceof CommandExecuter);
    }

    @Test
    public void testSetExecuter() {
        File testFile = mock(File.class);
        when(testFile.getName()).thenReturn("TestyMcTestface");
        when(testFile.getParent()).thenReturn("C:\\Test");
        AbstractBuilder testCompiler = new JavaBuilder(testFile);
        assertTrue(testCompiler.getExecuter() instanceof CommandExecuter);
        testCompiler.setExecuter(null);
        assertEquals(null, testCompiler.getExecuter());
    }
}