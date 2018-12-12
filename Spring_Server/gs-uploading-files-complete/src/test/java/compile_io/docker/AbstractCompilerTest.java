package compile_io.docker;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import compile_io.docker.JavaCompiler;
import static org.mockito.Mockito.*;
import java.io.File;

import compile_io.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
public class AbstractCompilerTest {

    @Test
    public void testExecuteCommandOutput() {
        File testFile = new File("C:\\Test\\TestyMcTestface");
        AbstractCompiler testCompiler = new JavaCompiler(testFile);
        String[] command = {"echo", "ExecuteCommandOutput"};
        testCompiler.executeAndDisplayOutput(command);
        testCompiler.executeCommand(command);
        assertTrue(true); // if no exceptions occurred, then it "passes"
    }

    @Test
    public void testGetFileName() {
        File testFile = new File("C:\\Test\\TestyMcTestface");
        AbstractCompiler testCompiler = new JavaCompiler(testFile);
        assertEquals("TestyMcTestface", testCompiler.getFileName());
    }

    @Test
    public void testGetFileDirectory() {
        File testFile = new File("C:\\Test\\TestyMcTestface");
        AbstractCompiler testCompiler = new JavaCompiler(testFile);
        assertEquals("C:\\Test", testCompiler.getFileDirectory());
    }

    @Test
    public void testSetFileName() {
        File testFile = new File("C:\\Test\\TestyMcTestface");
        AbstractCompiler testCompiler = new JavaCompiler(testFile);
        assertEquals("TestyMcTestface", testCompiler.getFileName());
        testCompiler.setFileName("NewTest");
        assertEquals("NewTest", testCompiler.getFileName());
    }

    @Test
    public void testSetFileDirectory() {
        File testFile = new File("C:\\Test\\TestyMcTestface");
        AbstractCompiler testCompiler = new JavaCompiler(testFile);
        assertEquals("C:\\Test", testCompiler.getFileDirectory());
        testCompiler.setFileDirectory("C:\\NewTest");
        assertEquals("C:\\NewTest", testCompiler.getFileDirectory());
    }
}