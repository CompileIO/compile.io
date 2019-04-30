package compile_io.controllers;


import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import compile_io.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class })
public class CodeControllerTest {

  @Test
  public void testTest() {
    assertEquals(true, true);
  }

}
