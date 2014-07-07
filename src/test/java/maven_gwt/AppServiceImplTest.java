package maven_gwt;

import net.ere.tmp.maven_gwt.server.AppServiceImpl;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class AppServiceImplTest {

    AppServiceImpl appService;

    @Before
    public void setUp() throws Exception {
        appService = new AppServiceImpl();
    }

    @Ignore
    @Test
    public void testGetTime() {
        assertNotNull(appService.getTime());
    }

    @Ignore
    @Test
    public void testGetPropertyValue() {
        assertNotNull(appService.getPropertyValue("java.version"));
        System.out.println("java.version=" + appService.getPropertyValue("java.version"));
    }

}
