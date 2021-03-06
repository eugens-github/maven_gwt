package maven_gwt;

import net.ere.tmp.maven_gwt.server.AppServiceImpl;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

@Ignore
public class AppServiceImplTest {

    AppServiceImpl appService;

    @Before
    public void setUp() throws Exception {
        appService = new AppServiceImpl();
    }

    @Test
    public void testGetTime() {
        assertNotNull(appService.getTime());
    }

    @Test
    public void testGetPropertyValue() {
        assertNotNull(appService.getPropertyValue("java.version"));
        System.out.println("java.version=" + appService.getPropertyValue("java.version"));
    }

}
