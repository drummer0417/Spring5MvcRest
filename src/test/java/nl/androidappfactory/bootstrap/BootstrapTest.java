package nl.androidappfactory.bootstrap;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class BootstrapTest {

	@Mock
	Bootstrap bootstrap;

	@Before
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void sillyTest() {

		when(bootstrap.test(anyString())).thenReturn("Testje");
		assertEquals("Testje", bootstrap.test("test"));
	}

}
