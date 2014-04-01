package functional.com.trailblazers.freewheelers.helpers;

import org.junit.Test;
import org.openqa.selenium.WebElement;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ControlsTest {

    @Test
    public void shouldClearTheFieldAndSendKeysToTheFields() throws Exception {
        WebElement field=mock(WebElement.class);
        String value="fld_Any";
        Controls.fillField(field,value);
        verify(field).clear();
        verify(field).sendKeys(value);
    }
}
