package testcases;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import invoice.DBServices;

import org.junit.Test;

public class TestCase3 {
	@Test  
	 public void getDeveloperHours(){  
		 DBServices dbService=new DBServices();
		 assertNotNull(dbService.getAllProjectManagerProjects("Cecily Hollack"));
		 assertNull(dbService.getAllProjectManagerProjects("Andra Scheyer"));
	 }
}
