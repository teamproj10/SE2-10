package testcases;

import static org.junit.Assert.assertNotNull;
import invoice.DBServices;

import javax.swing.JTextField;

import org.junit.Test;

import clients.Clients;

public class TestCase2 {
	 @Test  
	 public void getClientProjectEmployees(){  
		 DBServices dbService=new DBServices();
		 assertNotNull(dbService.getAllClients());
		 assertNotNull(dbService.getAllProjects());
		 assertNotNull(dbService.getAllEmployees());
	 }
}
