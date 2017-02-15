package mobi.openddr.example.flow;

import org.testng.annotations.Test;

import mobi.openddr.example.flow.HelloWorld;

public class HelloWorldTest
{
   @Test
   public void testGetText() {
      HelloWorld fixture = new HelloWorld();
      assert "Hello World!".equals(fixture.getText());
   }
}
