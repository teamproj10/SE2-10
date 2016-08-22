package testcases;

import static org.junit.Assert.*;
import invoice.DBServices;

import org.junit.Test;

public class TestCase1{
    @Test  
    public void testUserLogin(){  
    	DBServices dbService=new DBServices();
    	assertNull(dbService.userAuthentication("Amber Monarrez","project manager"));
    	assertNotNull(dbService.userAuthentication("Amber Monarrez","manager"));
    }  
}  
