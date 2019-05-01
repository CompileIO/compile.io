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
public class AbstractBuilderTest {

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
    public void testGetWorkingDirectory() {
        initialize();
        when(mockStudentFile.getName()).thenReturn("TestyMcTestface");
        when(mockStudentFile.getParent()).thenReturn("C:\\Test");
        AbstractBuilder testCompiler = new JavaBuilder(studentFiles, professorFiles, codePath);
        assertEquals("C:\\Test", testCompiler.getWorkingDirectory());
    }

    @Test
    public void testSetWorkingDirectory() {
        initialize();
        when(mockStudentFile.getName()).thenReturn("TestyMcTestface");
        when(mockStudentFile.getParent()).thenReturn("C:\\Test");
        AbstractBuilder testCompiler = new JavaBuilder(studentFiles, professorFiles, codePath);
        assertEquals("C:\\Test", testCompiler.getWorkingDirectory());
        testCompiler.setWorkingDirectory("C:\\NewTest");
        assertEquals("C:\\NewTest", testCompiler.getWorkingDirectory());
    }

    @Test 
    public void testGetExecuter() {
        initialize();
        when(mockStudentFile.getName()).thenReturn("TestyMcTestface");
        when(mockStudentFile.getParent()).thenReturn("C:\\Test");
        AbstractBuilder testCompiler = new JavaBuilder(studentFiles, professorFiles, codePath);
        assertTrue(testCompiler.getExecuter() instanceof CommandExecuter);
    }

    @Test
    public void testSetExecuter() {
        initialize();
        when(mockStudentFile.getName()).thenReturn("TestyMcTestface");
        when(mockStudentFile.getParent()).thenReturn("C:\\Test");
        AbstractBuilder testCompiler = new JavaBuilder(studentFiles, professorFiles, codePath);
        assertTrue(testCompiler.getExecuter() instanceof CommandExecuter);
        testCompiler.setExecuter(null);
        assertEquals(null, testCompiler.getExecuter());
    }
}