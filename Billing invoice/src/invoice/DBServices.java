package invoice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class DBServices{
	static final String CLIENTS = "client_data.csv";
	static final String USERS = "people_data.csv";
	static final String PROJECTS = "project_data.csv";
	static final String COMPANY = "company_data.csv";
	
	public String getPath(){
		try{
			return new java.io.File( "." ).getCanonicalPath()+"\\src\\invoice\\";
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public Connection mysqlconnection(){
		Connection mysqlconnection=null;
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String path = "jdbc:mysql://localhost/sowmya";
            String mysqlusername = "root";
            String mysqluserpassword= "sowmya";
            mysqlconnection =DriverManager.getConnection(path,mysqlusername,mysqluserpassword);  
		}catch (Exception e){
			e.printStackTrace();
		}
		return mysqlconnection;
	}
	
	public User userAuthentication(String userText,String passwordText){
		User resultUser=null;
		Connection mysqlconnection=null;
		Statement stmt=null;
		try{
			mysqlconnection =mysqlconnection();
			if(mysqlconnection!=null){
	            stmt = mysqlconnection.createStatement();
	            ResultSet rs = stmt.executeQuery("select * from user where username='"+userText+"'");
	    		if(rs!=null){
	    			while(rs.next()){
	    				resultUser=new User();
	    				resultUser.setUsername(rs.getString("username"));
	    				resultUser.setUserrole(rs.getString("role"));
	    				resultUser.setBillrate(""+rs.getInt("billrate"));
	    			}
	    		}
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }finally{
		     try{
		    	 if(stmt!=null){
			    	 stmt.close();
			     }
		         if(mysqlconnection!=null)
		        	 mysqlconnection.close();
		      }catch(Exception e){
		         e.printStackTrace();
		      }
		}
        return resultUser;		
	}
	
	public ArrayList getAllClients(){
		Connection mysqlconnection=null;
		Statement stmt=null;
		ArrayList clientList=null;
		try{
			mysqlconnection =mysqlconnection();
			if(mysqlconnection!=null){
	            stmt = mysqlconnection.createStatement();
	            ResultSet rs = stmt.executeQuery("select * from client where statusflag='Y'");
	    		if(rs!=null){
	    			clientList=new ArrayList();
	    			while(rs.next()){
	    				String[] tokens=new String[12];
	    				tokens[0]=""+rs.getInt("clientnumber");
	    				tokens[1]=rs.getString("clientname");
	    				tokens[2]=rs.getString("addressline1");
	    				tokens[3]=rs.getString("addressline2");
	    				tokens[4]=rs.getString("city");
	    				tokens[5]=rs.getString("state");
	    				tokens[6]=""+rs.getInt("zip");
	    				tokens[7]=rs.getString("email");
	    				tokens[8]=rs.getString("contactperson");
	    				tokens[9]=rs.getString("invoicefrequency");
	    				tokens[10]=rs.getString("billingterms");
	    				tokens[11]=rs.getString("invoicegrouping");
	    				clientList.add(tokens);
	    			}
	    		}
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }finally{
		     try{
		    	 if(stmt!=null){
			    	 stmt.close();
			     }
		         if(mysqlconnection!=null)
		        	 mysqlconnection.close();
		      }catch(Exception e){
		         e.printStackTrace();
		      }
		}
        return clientList;
    }
	
	public ArrayList getCurrentInvoices(String invoicedate){
		Connection mysqlconnection=null;
		Statement stmt=null;
		ArrayList clientList=null;
		try{
			mysqlconnection =mysqlconnection();
			if(mysqlconnection!=null){
	            stmt = mysqlconnection.createStatement();
	            ResultSet rs = stmt.executeQuery("select * from invoice where invoicedate='"+invoicedate+"'");
	    		if(rs!=null){
	    			clientList=new ArrayList();
	    			while(rs.next()){
	    				String[] tokens=new String[5];
	    				tokens[0]=""+rs.getInt("invoicenumber");
	    				tokens[1]=rs.getString("clientnumber");
	    				tokens[2]=rs.getString("projectnumber");
	    				tokens[3]=rs.getString("invoicedate");
	    				tokens[4]=rs.getString("totalamount");
	    				clientList.add(tokens);
	    			}
	    		}
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }finally{
		     try{
		    	 if(stmt!=null){
			    	 stmt.close();
			     }
		         if(mysqlconnection!=null)
		        	 mysqlconnection.close();
		      }catch(Exception e){
		         e.printStackTrace();
		      }
		}
        return clientList;
    }
	
	public String[] getClient(String clientnumber){
		Connection mysqlconnection=null;
		Statement stmt=null;
		String[] tokens=null;
		try{
			mysqlconnection =mysqlconnection();
			if(mysqlconnection!=null){
	            stmt = mysqlconnection.createStatement();
	            ResultSet rs = stmt.executeQuery("select * from client where clientnumber="+clientnumber);
	    		if(rs!=null){
	    			while(rs.next()){
	    				tokens=new String[12];
	    				tokens[0]=""+rs.getInt("clientnumber");
	    				tokens[1]=rs.getString("clientname");
	    				tokens[2]=rs.getString("addressline1");
	    				tokens[3]=rs.getString("addressline2");
	    				tokens[4]=rs.getString("city");
	    				tokens[5]=rs.getString("state");
	    				tokens[6]=""+rs.getInt("zip");
	    				tokens[7]=rs.getString("email");
	    				tokens[8]=rs.getString("contactperson");
	    				tokens[9]=rs.getString("invoicefrequency");
	    				tokens[10]=rs.getString("billingterms");
	    				tokens[11]=rs.getString("invoicegrouping");
	    			}
	    		}
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }finally{
		     try{
		    	 if(stmt!=null){
			    	 stmt.close();
			     }
		         if(mysqlconnection!=null)
		        	 mysqlconnection.close();
		      }catch(Exception e){
		         e.printStackTrace();
		      }
		}
        return tokens;
    }
	
	public String[] getInvoice(String clientnumber,String projectnumber){
		Connection mysqlconnection=null;
		Statement stmt=null;
		String[] tokens=null;
		try{
			mysqlconnection =mysqlconnection();
			if(mysqlconnection!=null){
	            stmt = mysqlconnection.createStatement();
	            ResultSet rs = stmt.executeQuery("select * from invoice where clientnumber="+clientnumber+" and projectnumber="+projectnumber);
	    		if(rs!=null){
	    			while(rs.next()){
	    				tokens=new String[12];
	    				tokens[0]=""+rs.getInt("invoicenumber");
	    				tokens[1]=rs.getString("clientnumber");
	    				tokens[2]=rs.getString("projectnumber");
	    				tokens[3]=rs.getString("invoicedate");
	    				tokens[4]=""+rs.getDouble("totalamount");
	    			}
	    		}
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }finally{
		     try{
		    	 if(stmt!=null){
			    	 stmt.close();
			     }
		         if(mysqlconnection!=null)
		        	 mysqlconnection.close();
		      }catch(Exception e){
		         e.printStackTrace();
		      }
		}
        return tokens;
    }
	
	public String getProjectName(String clientnumber,String projectnumber){
		Connection mysqlconnection=null;
		Statement stmt=null;
		String projectname=null;
		try{
			mysqlconnection =mysqlconnection();
			if(mysqlconnection!=null){
	            stmt = mysqlconnection.createStatement();
	            ResultSet rs = stmt.executeQuery("select projectname from project where projectnumber="+projectnumber+" and clientnumber="+clientnumber);
	    		if(rs!=null){
	    			while(rs.next()){
	    				projectname=""+rs.getString("projectname");
	    			}
	    		}
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }finally{
		     try{
		    	 if(stmt!=null){
			    	 stmt.close();
			     }
		         if(mysqlconnection!=null)
		        	 mysqlconnection.close();
		      }catch(Exception e){
		         e.printStackTrace();
		      }
		}
        return projectname;
    }
	
	public ArrayList getAllProjects(){
		Connection mysqlconnection=null;
		Statement stmt=null;
		ArrayList projectList=null;
		try{
			mysqlconnection =mysqlconnection();
			if(mysqlconnection!=null){
	            stmt = mysqlconnection.createStatement();
	            ResultSet rs = stmt.executeQuery("select * from project where statusflag='Y'");
	    		if(rs!=null){
	    			projectList=new ArrayList();
	    			while(rs.next()){
	    				String[] tokens=new String[9];
	    				tokens[0]=""+rs.getInt("clientnumber");
	    				tokens[1]=""+rs.getInt("projectnumber");
	    				tokens[2]=""+rs.getString("projectname");
	    				tokens[3]=rs.getString("startdate");
	    				tokens[4]=rs.getString("enddate");
	    				tokens[5]=rs.getString("status");
	    				tokens[6]=rs.getString("projectmanager");
	    				tokens[7]=rs.getString("clientcontact");	    				
	    				tokens[8]=""+rs.getDouble("budget");
	    				projectList.add(tokens);
	    			}
	    		}
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }finally{
		     try{
		    	 if(stmt!=null){
			    	 stmt.close();
			     }
		         if(mysqlconnection!=null)
		        	 mysqlconnection.close();
		      }catch(Exception e){
		         e.printStackTrace();
		      }
		}
        return projectList;
    }
	
	public ArrayList getAllEmployees(){
		Connection mysqlconnection=null;
		Statement stmt=null;
		ArrayList employeeList=null;
		try{
			mysqlconnection =mysqlconnection();
			if(mysqlconnection!=null){
	            stmt = mysqlconnection.createStatement();
	            ResultSet rs = stmt.executeQuery("select * from user where statusflag='Y'");
	    		if(rs!=null){
	    			employeeList=new ArrayList();
	    			while(rs.next()){
	    				String[] tokens=new String[4];
	    				tokens[0]=rs.getString("username");
	    				tokens[1]=rs.getString("title");
	    				tokens[2]=""+rs.getInt("billrate");
	    				tokens[3]=""+rs.getString("role");
	    				employeeList.add(tokens);
	    			}
	    		}
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }finally{
		     try{
		    	 if(stmt!=null){
			    	 stmt.close();
			     }
		         if(mysqlconnection!=null)
		        	 mysqlconnection.close();
		      }catch(Exception e){
		         e.printStackTrace();
		      }
		}
        return employeeList;
    }
	
	public ArrayList projectsReport(){
		Connection mysqlconnection=null;
		Statement stmt=null;
		ArrayList projectList=null;
		try{
			mysqlconnection =mysqlconnection();
			if(mysqlconnection!=null){
	            stmt = mysqlconnection.createStatement();
	            ResultSet rs = stmt.executeQuery("select c.clientname,p.projectname,c.contactperson,p.projectmanager,p.budget from project p,client c where c.clientnumber=p.clientnumber");
	    		if(rs!=null){
	    			projectList=new ArrayList();
	    			while(rs.next()){
	    				String[] tokens=new String[5];
	    				tokens[0]=rs.getString("clientname");
	    				tokens[1]=rs.getString("projectname");
	    				tokens[2]=rs.getString("contactperson");
	    				tokens[3]=rs.getString("projectmanager");
	    				tokens[4]=""+rs.getDouble("budget");
	    				projectList.add(tokens);
	    			}
	    		}
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }finally{
		     try{
		    	 if(stmt!=null){
			    	 stmt.close();
			     }
		         if(mysqlconnection!=null)
		        	 mysqlconnection.close();
		      }catch(Exception e){
		         e.printStackTrace();
		      }
		}
        return projectList;
    }
	
	public ArrayList getAllActiveEmployees(){
		Connection mysqlconnection=null;
		Statement stmt=null;
		ArrayList employeeList=null;
		try{
			mysqlconnection =mysqlconnection();
			if(mysqlconnection!=null){
	            stmt = mysqlconnection.createStatement();
	            ResultSet rs = stmt.executeQuery("select * from user where role='developer' and statusflag='Y'");
	    		if(rs!=null){
	    			employeeList=new ArrayList();
	    			while(rs.next()){
	    				String[] tokens=new String[4];
	    				tokens[0]=rs.getString("username");
	    				tokens[1]=rs.getString("title");
	    				tokens[2]=""+rs.getInt("billrate");
	    				tokens[3]=""+rs.getString("role");
	    				employeeList.add(tokens);
	    			}
	    		}
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }finally{
		     try{
		    	 if(stmt!=null){
			    	 stmt.close();
			     }
		         if(mysqlconnection!=null)
		        	 mysqlconnection.close();
		      }catch(Exception e){
		         e.printStackTrace();
		      }
		}
        return employeeList;
    }
	
	public ArrayList getAllActiveProjects(){
		Connection mysqlconnection=null;
		Statement stmt=null;
		ArrayList projectList=null;
		try{
			mysqlconnection =mysqlconnection();
			if(mysqlconnection!=null){
	            stmt = mysqlconnection.createStatement();
	            ResultSet rs = stmt.executeQuery("select * from project where statusflag='Y'");
	    		if(rs!=null){
	    			projectList=new ArrayList();
	    			while(rs.next()){
	    				String[] tokens=new String[9];
	    				tokens[0]=""+rs.getInt("clientnumber");
	    				tokens[1]=""+rs.getInt("projectnumber");
	    				tokens[2]=""+rs.getString("projectname");
	    				tokens[3]=rs.getString("startdate");
	    				tokens[4]=rs.getString("enddate");
	    				tokens[5]=rs.getString("status");
	    				tokens[6]=rs.getString("projectmanager");
	    				tokens[7]=rs.getString("clientcontact");	    				
	    				tokens[8]=""+rs.getDouble("budget");
	    				projectList.add(tokens);
	    			}
	    		}
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }finally{
		     try{
		    	 if(stmt!=null){
			    	 stmt.close();
			     }
		         if(mysqlconnection!=null)
		        	 mysqlconnection.close();
		      }catch(Exception e){
		         e.printStackTrace();
		      }
		}
        return projectList;
    }
	
	public String[] getProjectData(String clientnumber,String projectnumber){
		Connection mysqlconnection=null;
		Statement stmt=null;
		String[] tokens=null;
		try{
			mysqlconnection =mysqlconnection();
			if(mysqlconnection!=null){
	            stmt = mysqlconnection.createStatement();
	            ResultSet rs = stmt.executeQuery("select * from project where clientnumber="+clientnumber+" and projectnumber="+projectnumber+" and statusflag='Y'");
	    		if(rs!=null){
	    			while(rs.next()){
	    				tokens=new String[9];
	    				tokens[0]=""+rs.getInt("clientnumber");
	    				tokens[1]=""+rs.getInt("projectnumber");
	    				tokens[2]=""+rs.getString("projectname");
	    				tokens[3]=rs.getString("startdate");
	    				tokens[4]=rs.getString("enddate");
	    				tokens[5]=rs.getString("status");
	    				tokens[6]=rs.getString("projectmanager");
	    				tokens[7]=rs.getString("clientcontact");	    				
	    				tokens[8]=""+rs.getDouble("budget");
	    			}
	    		}
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }finally{
		     try{
		    	 if(stmt!=null){
			    	 stmt.close();
			     }
		         if(mysqlconnection!=null)
		        	 mysqlconnection.close();
		      }catch(Exception e){
		         e.printStackTrace();
		      }
		}
        return tokens;
    }
	
	public ArrayList getAllProjectManagerProjects(String userrname){
		Connection mysqlconnection=null;
		Statement stmt=null;
		ArrayList projectList=null;
		try{
			mysqlconnection =mysqlconnection();
			if(mysqlconnection!=null){
	            stmt = mysqlconnection.createStatement();
	            ResultSet rs = stmt.executeQuery("select clientnumber,projectnumber from project where projectmanager='"+userrname+"' and statusflag='�'");
	    		if(rs!=null){
	    			projectList=new ArrayList();
	    			while(rs.next()){
	    				String[] tokens=new String[2];
	    				tokens[0]=""+rs.getInt("clientnumber");
	    				tokens[1]=""+rs.getInt("projectnumber");	    				
	    				projectList.add(tokens);
	    			}
	    		}
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }finally{
		     try{
		    	 if(stmt!=null){
			    	 stmt.close();
			     }
		         if(mysqlconnection!=null)
		        	 mysqlconnection.close();
		      }catch(Exception e){
		         e.printStackTrace();
		      }
		}
        return projectList;
    }
	
	public ArrayList getAllDeveloperProjects(String developername){
		Connection mysqlconnection=null;
		Statement stmt=null;
		ArrayList projectList=null;
		try{
			mysqlconnection =mysqlconnection();
			if(mysqlconnection!=null){
	            stmt = mysqlconnection.createStatement();
	            ResultSet rs = stmt.executeQuery("select distinct clientnumber,projectnumber from assigndeveloper where developername='"+developername+"'");
	    		if(rs!=null){
	    			projectList=new ArrayList();
	    			while(rs.next()){
	    				String[] tokens=new String[2];
	    				tokens[0]=""+rs.getInt("clientnumber");
	    				tokens[1]=""+rs.getInt("projectnumber");	    				
	    				projectList.add(tokens);
	    			}
	    		}
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }finally{
		     try{
		    	 if(stmt!=null){
			    	 stmt.close();
			     }
		         if(mysqlconnection!=null)
		        	 mysqlconnection.close();
		      }catch(Exception e){
		         e.printStackTrace();
		      }
		}
        return projectList;
    }
	
	public ArrayList getAssignedDevelopers(){
		Connection mysqlconnection=null;
		Statement stmt=null;
		ArrayList assignDeveloperList=null;
		try{
			mysqlconnection =mysqlconnection();
			if(mysqlconnection!=null){
	            stmt = mysqlconnection.createStatement();
	            //ResultSet rs = stmt.executeQuery("select p.projectname,ad.developername,u.billrate,ad.assigndate from assigndeveloper ad,project p,user u where u.username=ad.developername and p.projectnumber=ad.projectnumber");
	            ResultSet rs = stmt.executeQuery("select ad.clientnumber,ad.projectnumber,ad.developername,u.billrate,ad.assigndate from assigndeveloper ad,user u where u.username=ad.developername");
	    		if(rs!=null){
	    			assignDeveloperList=new ArrayList();
	    			while(rs.next()){
	    				String[] tokens=new String[5];
	    				tokens[0]=""+rs.getInt("clientnumber");
	    				tokens[1]=""+rs.getInt("projectnumber");
	    				tokens[2]=rs.getString("developername");
	    				tokens[3]=""+rs.getInt("billrate");
	    				tokens[4]=""+rs.getString("assigndate");
	    				assignDeveloperList.add(tokens);
	    			}
	    		}
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }finally{
		     try{
		    	 if(stmt!=null){
			    	 stmt.close();
			     }
		         if(mysqlconnection!=null)
		        	 mysqlconnection.close();
		      }catch(Exception e){
		         e.printStackTrace();
		      }
		}
        return assignDeveloperList;
    }
	
	public ArrayList getPayrollReport(){
		Connection mysqlconnection=null;
		Statement stmt=null;
		ArrayList payrollReportList=null;
		try{
			mysqlconnection =mysqlconnection();
			if(mysqlconnection!=null){
	            stmt = mysqlconnection.createStatement();
	            ResultSet rs = stmt.executeQuery("select p.projectname,da.developername,da.billrate,da.date,da.hours,da.overhours from developerhour da,project p where p.projectnumber=ad.projectnumber and da.pmapprovestatus='Y'");
	    		if(rs!=null){
	    			payrollReportList=new ArrayList();
	    			while(rs.next()){
	    				String[] tokens=new String[7];
	    				tokens[0]=rs.getString("projectname");
	    				tokens[1]=rs.getString("developername");
	    				tokens[2]=""+rs.getInt("billrate");
	    				tokens[3]=""+rs.getString("date");
	    				tokens[4]=""+rs.getInt("hours");
	    				tokens[5]=""+rs.getInt("overhours");
	    				if(rs.getInt("hours")!=0){
	    					tokens[6]=""+(rs.getInt("hours")*rs.getInt("billrate"));
	    				}
	    				if(rs.getInt("overhours")!=0){
	    					tokens[6]=""+(rs.getInt("overhours")*rs.getInt("billrate"));
	    				}
	    				payrollReportList.add(tokens);
	    			}
	    		}
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }finally{
		     try{
		    	 if(stmt!=null){
			    	 stmt.close();
			     }
		         if(mysqlconnection!=null)
		        	 mysqlconnection.close();
		      }catch(Exception e){
		         e.printStackTrace();
		      }
		}
        return payrollReportList;
    }
	
	public ArrayList getDevelopersHours(){
		Connection mysqlconnection=null;
		Statement stmt=null;
		ArrayList developerList=null;
		try{
			mysqlconnection =mysqlconnection();
			if(mysqlconnection!=null){
	            stmt = mysqlconnection.createStatement();
	            ResultSet rs = stmt.executeQuery("select * from developerhour");
	    		if(rs!=null){
	    			developerList=new ArrayList();
	    			while(rs.next()){
	    				String[] tokens=new String[8];
	    				tokens[0]=rs.getString("clientnumber");
	    				tokens[1]=rs.getString("projectnumber");
	    				tokens[2]=rs.getString("developername");
	    				tokens[3]=rs.getString("date");
	    				tokens[4]=""+rs.getInt("hours");
	    				tokens[5]=""+rs.getInt("overhours");
	    				tokens[6]=""+rs.getInt("billrate");
	    				tokens[7]=rs.getString("pmapprovestatus");
	    				developerList.add(tokens);
	    			}
	    		}
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }finally{
		     try{
		    	 if(stmt!=null){
			    	 stmt.close();
			     }
		         if(mysqlconnection!=null)
		        	 mysqlconnection.close();
		      }catch(Exception e){
		         e.printStackTrace();
		      }
		}
        return developerList;
    }
	
	public ArrayList getClientsFromDevelopersHours(){
		Connection mysqlconnection=null;
		Statement stmt=null;
		ArrayList clientList=null;
		try{
			mysqlconnection =mysqlconnection();
			if(mysqlconnection!=null){
	            stmt = mysqlconnection.createStatement();
	            ResultSet rs = stmt.executeQuery("select * from client where clientnumber in (select distinct clientnumber from developerhour)");
	    		if(rs!=null){
	    			clientList=new ArrayList();
	    			while(rs.next()){
	    				String[] tokens=new String[12];
	    				tokens[0]=""+rs.getInt("clientnumber");
	    				tokens[1]=rs.getString("clientname");
	    				tokens[2]=rs.getString("addressline1");
	    				tokens[3]=rs.getString("addressline2");
	    				tokens[4]=rs.getString("city");
	    				tokens[5]=rs.getString("state");
	    				tokens[6]=""+rs.getInt("zip");
	    				tokens[7]=rs.getString("email");
	    				tokens[8]=rs.getString("contactperson");
	    				tokens[9]=rs.getString("invoicefrequency");
	    				tokens[10]=rs.getString("billingterms");
	    				tokens[11]=rs.getString("invoicegrouping");
	    				clientList.add(tokens);
	    			}
	    		}
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }finally{
		     try{
		    	 if(stmt!=null){
			    	 stmt.close();
			     }
		         if(mysqlconnection!=null)
		        	 mysqlconnection.close();
		      }catch(Exception e){
		         e.printStackTrace();
		      }
		}
        return clientList;
    }
	
	public ArrayList getInvoicedata(String clientnumber,String startdate,String enddate){
		Connection mysqlconnection=null;
		Statement stmt=null;
		ArrayList clientList=null;
		try{
			mysqlconnection =mysqlconnection();
			if(mysqlconnection!=null){
	            stmt = mysqlconnection.createStatement();
	            ResultSet rs = stmt.executeQuery("select projectnumber,developername,billrate,sum(hours) from developerhour where clientnumber="+clientnumber+" and STR_TO_DATE(date,'%d-%m-%Y') BETWEEN STR_TO_DATE('"+startdate+"', '%d-%m-%Y') AND STR_TO_DATE('"+enddate+"','%d-%m-%Y') group by projectnumber,developername,billrate");
	    		if(rs!=null){
	    			clientList=new ArrayList();
	    			while(rs.next()){
	    				String[] tokens=new String[4];
	    				tokens[0]=""+rs.getInt("projectnumber");
	    				tokens[1]=rs.getString("developername");
	    				tokens[2]=rs.getString("billrate");
	    				tokens[3]=rs.getString("sum(hours)");
	    				clientList.add(tokens);
	    			}
	    		}
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }finally{
		     try{
		    	 if(stmt!=null){
			    	 stmt.close();
			     }
		         if(mysqlconnection!=null)
		        	 mysqlconnection.close();
		      }catch(Exception e){
		         e.printStackTrace();
		      }
		}
        return clientList;
    }
	
	public ArrayList getInvoiceProjectdata(String clientnumber,String startdate,String enddate){
		Connection mysqlconnection=null;
		Statement stmt=null;
		ArrayList projectList=null;
		try{
			mysqlconnection =mysqlconnection();
			if(mysqlconnection!=null){
	            stmt = mysqlconnection.createStatement();
	            ResultSet rs = stmt.executeQuery("select distinct projectnumber from developerhour where clientnumber="+clientnumber+" and STR_TO_DATE(date,'%d-%m-%Y') BETWEEN STR_TO_DATE('"+startdate+"', '%d-%m-%Y') AND STR_TO_DATE('"+enddate+"','%d-%m-%Y') group by projectnumber,developername,billrate");
	    		if(rs!=null){
	    			projectList=new ArrayList();
	    			while(rs.next()){
	    				projectList.add(""+rs.getInt("projectnumber"));
	    			}
	    		}
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }finally{
		     try{
		    	 if(stmt!=null){
			    	 stmt.close();
			     }
		         if(mysqlconnection!=null)
		        	 mysqlconnection.close();
		      }catch(Exception e){
		         e.printStackTrace();
		      }
		}
        return projectList;
    }
	
	public String getProjectPaidAmount(String projectnumber,String invoicedate){
		Connection mysqlconnection=null;
		Statement stmt=null;
		String totalAmount=null;
		try{
			mysqlconnection =mysqlconnection();
			if(mysqlconnection!=null){
	            stmt = mysqlconnection.createStatement();
	            ResultSet rs = stmt.executeQuery("select sum(totalamount) from invoice where projectnumber="+projectnumber+" and STR_TO_DATE(invoicedate,'%d-%m-%Y') >= STR_TO_DATE('"+invoicedate+"', '%d-%m-%Y')");
	    		if(rs!=null){
	    			while(rs.next()){
	    				totalAmount=(""+rs.getDouble("sum(totalamount)"));
	    			}
	    		}
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }finally{
		     try{
		    	 if(stmt!=null){
			    	 stmt.close();
			     }
		         if(mysqlconnection!=null)
		        	 mysqlconnection.close();
		      }catch(Exception e){
		         e.printStackTrace();
		      }
		}
        return totalAmount;
    }
	
	public String getLastClientInvoice(String clientnumber){
		Connection mysqlconnection=null;
		Statement stmt=null;
		String lastInvoiceDate=null;
		try{
			mysqlconnection =mysqlconnection();
			if(mysqlconnection!=null){
	            stmt = mysqlconnection.createStatement();
	            ResultSet rs = stmt.executeQuery("select max(invoicedate) from invoice where clientnumber="+clientnumber);
	    		if(rs!=null){
	    			while(rs.next()){
	    				lastInvoiceDate=rs.getString("max(invoicedate)");
	    			}
	    		}
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }finally{
		     try{
		    	 if(stmt!=null){
			    	 stmt.close();
			     }
		         if(mysqlconnection!=null)
		        	 mysqlconnection.close();
		      }catch(Exception e){
		         e.printStackTrace();
		      }
		}
        return lastInvoiceDate;
    }
	
	public HashMap generateInvoices(){
		HashMap invoiceMap=new HashMap();
		try{
			ArrayList clientList=getClientsFromDevelopersHours();
			if(clientList!=null && clientList.size()!=0){
				SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
				Iterator iterator=clientList.iterator();
				while(iterator.hasNext()){
					String[] tokens=(String[])iterator.next();
					String lastInvoiceDate=getLastClientInvoice(tokens[0]);
					Calendar pastcal=Calendar.getInstance();
					if(lastInvoiceDate!=null && lastInvoiceDate.trim().length()!=0){
						pastcal.setTime(sdf.parse(lastInvoiceDate));
					}
					if(tokens[9].equalsIgnoreCase("Weekly")){							
						pastcal.add(pastcal.DATE, 7);
						if(lastInvoiceDate!=null && lastInvoiceDate.trim().length()!=0 && sdf.format(pastcal.getTime()).equalsIgnoreCase(sdf.format(new Date()))){							
							Calendar cal = Calendar.getInstance();
							cal.add(cal.DATE, -3);
							String enddate=sdf.format(cal.getTime());
							cal = Calendar.getInstance();
							cal.add(cal.DATE, -9);
							String startdate=sdf.format(cal.getTime());
							System.out.println("Weekly   			: "+startdate+"     to      "+enddate);
							ArrayList list=getInvoicedata(tokens[0],startdate,enddate);
							ArrayList projectList=getInvoiceProjectdata(tokens[0],startdate,enddate);
							if(list!=null && list.size()!=0){
								if(projectList!=null && projectList.size()!=0){
									for(int j=0;j<projectList.size();j++){
										ArrayList templist=new ArrayList();
										String projectnumber=(String)projectList.get(j);
										int totalamount=0;
										for(int i=0;i<list.size();i++){
											String[] tokensdata=(String[])list.get(i);
											if(tokensdata[0].equalsIgnoreCase(projectnumber)){
												totalamount=totalamount+(Integer.parseInt(tokensdata[2])*Integer.parseInt(tokensdata[3]));
												templist.add(tokensdata);
											}											
										}
										String invoicenumber=invoice(tokens[0], projectnumber, sdf.format(new Date()), ""+totalamount);
										if(invoicenumber!=null){
											Object[] pair=new Object[2];
											pair[0]=startdate+" - "+enddate;
											pair[1]=templist;
											invoiceMap.put(invoicenumber, pair);
										}
									}									
								}								
							}
						}else if(lastInvoiceDate==null){
							Calendar cal = Calendar.getInstance();
							cal.add(cal.DATE, -3);
							String enddate=sdf.format(cal.getTime());
							cal = Calendar.getInstance();
							cal.add(cal.DATE, -9);
							String startdate=sdf.format(cal.getTime());
							System.out.println("Else Weekly   			: "+startdate+"     to      "+enddate);
							ArrayList list=getInvoicedata(tokens[0],startdate,enddate);
							ArrayList projectList=getInvoiceProjectdata(tokens[0],startdate,enddate);
							if(list!=null && list.size()!=0){
								if(projectList!=null && projectList.size()!=0){
									for(int j=0;j<projectList.size();j++){
										ArrayList templist=new ArrayList();
										String projectnumber=(String)projectList.get(j);
										int totalamount=0;
										for(int i=0;i<list.size();i++){
											String[] tokensdata=(String[])list.get(i);
											if(tokensdata[0].equalsIgnoreCase(projectnumber)){
												totalamount=totalamount+(Integer.parseInt(tokensdata[2])*Integer.parseInt(tokensdata[3]));
												templist.add(tokensdata);
											}											
										}
										String invoicenumber=invoice(tokens[0], projectnumber, sdf.format(new Date()), ""+totalamount);
										if(invoicenumber!=null){
											Object[] pair=new Object[2];
											pair[0]=startdate+" - "+enddate;
											pair[1]=templist;
											invoiceMap.put(invoicenumber, pair);
										}
									}									
								}								
							}
						}
					}else if(tokens[9].equalsIgnoreCase("BiWeekly")){
						pastcal.add(pastcal.DATE, 14);
						if(lastInvoiceDate!=null && lastInvoiceDate.trim().length()!=0 && sdf.format(pastcal.getTime()).equalsIgnoreCase(sdf.format(new Date()))){	
							Calendar cal = Calendar.getInstance();
							cal.add(cal.DATE, -3);
							String enddate=sdf.format(cal.getTime());
							cal = Calendar.getInstance();
							cal.add(cal.DATE, -16);
							String startdate=sdf.format(cal.getTime());
							System.out.println("BiWeekly   			: "+startdate+"     to      "+enddate);
							ArrayList list=getInvoicedata(tokens[0],startdate,enddate);
							ArrayList projectList=getInvoiceProjectdata(tokens[0],startdate,enddate);
							if(list!=null && list.size()!=0){
								if(projectList!=null && projectList.size()!=0){
									for(int j=0;j<projectList.size();j++){
										ArrayList templist=new ArrayList();
										String projectnumber=(String)projectList.get(j);
										int totalamount=0;
										for(int i=0;i<list.size();i++){
											String[] tokensdata=(String[])list.get(i);
											if(tokensdata[0].equalsIgnoreCase(projectnumber)){
												totalamount=totalamount+(Integer.parseInt(tokensdata[2])*Integer.parseInt(tokensdata[3]));
												templist.add(tokensdata);
											}											
										}
										String invoicenumber=invoice(tokens[0], projectnumber, sdf.format(new Date()), ""+totalamount);
										if(invoicenumber!=null){
											Object[] pair=new Object[2];
											pair[0]=startdate+" - "+enddate;
											pair[1]=templist;
											invoiceMap.put(invoicenumber, pair);
										}
									}									
								}								
							}
						}else if(lastInvoiceDate==null){
							Calendar cal = Calendar.getInstance();
							cal.add(cal.DATE, -3);
							String enddate=sdf.format(cal.getTime());
							cal = Calendar.getInstance();
							cal.add(cal.DATE, -16);
							String startdate=sdf.format(cal.getTime());
							System.out.println("Else BiWeekly   			: "+startdate+"     to      "+enddate);cal = Calendar.getInstance();
							ArrayList list=getInvoicedata(tokens[0],startdate,enddate);
							ArrayList projectList=getInvoiceProjectdata(tokens[0],startdate,enddate);
							if(list!=null && list.size()!=0){
								if(projectList!=null && projectList.size()!=0){
									for(int j=0;j<projectList.size();j++){
										ArrayList templist=new ArrayList();
										String projectnumber=(String)projectList.get(j);
										int totalamount=0;
										for(int i=0;i<list.size();i++){
											String[] tokensdata=(String[])list.get(i);
											if(tokensdata[0].equalsIgnoreCase(projectnumber)){
												totalamount=totalamount+(Integer.parseInt(tokensdata[2])*Integer.parseInt(tokensdata[3]));
												templist.add(tokensdata);
											}											
										}
										String invoicenumber=invoice(tokens[0], projectnumber, sdf.format(new Date()), ""+totalamount);
										if(invoicenumber!=null){
											Object[] pair=new Object[2];
											pair[0]=startdate+" - "+enddate;
											pair[1]=templist;
											invoiceMap.put(invoicenumber, pair);
										}
									}									
								}								
							}
						}
					}else if(tokens[9].equalsIgnoreCase("Monthly")){
						pastcal.add(pastcal.DATE, 30);
						if(lastInvoiceDate!=null && lastInvoiceDate.trim().length()!=0 && sdf.format(pastcal.getTime()).equalsIgnoreCase(sdf.format(new Date()))){	
							Calendar cal = Calendar.getInstance();
							cal.add(cal.DATE, -3);
							String enddate=sdf.format(cal.getTime());
							cal = Calendar.getInstance();
							cal.add(cal.DATE, -32);
							String startdate=sdf.format(cal.getTime());
							System.out.println("Monthly   			: "+startdate+"     to      "+enddate);
							ArrayList list=getInvoicedata(tokens[0],startdate,enddate);
							ArrayList projectList=getInvoiceProjectdata(tokens[0],startdate,enddate);
							if(list!=null && list.size()!=0){
								if(projectList!=null && projectList.size()!=0){
									for(int j=0;j<projectList.size();j++){
										ArrayList templist=new ArrayList();
										String projectnumber=(String)projectList.get(j);
										int totalamount=0;
										for(int i=0;i<list.size();i++){
											String[] tokensdata=(String[])list.get(i);
											if(tokensdata[0].equalsIgnoreCase(projectnumber)){
												totalamount=totalamount+(Integer.parseInt(tokensdata[2])*Integer.parseInt(tokensdata[3]));
												templist.add(tokensdata);
											}											
										}
										String invoicenumber=invoice(tokens[0], projectnumber, sdf.format(new Date()), ""+totalamount);
										if(invoicenumber!=null){
											Object[] pair=new Object[2];
											pair[0]=startdate+" - "+enddate;
											pair[1]=templist;
											invoiceMap.put(invoicenumber, pair);
										}
									}									
								}								
							}
						}else if(lastInvoiceDate==null){
							Calendar cal = Calendar.getInstance();
							cal.add(cal.DATE, -3);
							String enddate=sdf.format(cal.getTime());
							cal = Calendar.getInstance();
							cal.add(cal.DATE, -32);
							String startdate=sdf.format(cal.getTime());
							System.out.println("Else Monthly   			: "+startdate+"     to      "+enddate);
							ArrayList list=getInvoicedata(tokens[0],startdate,enddate);
							ArrayList projectList=getInvoiceProjectdata(tokens[0],startdate,enddate);
							if(list!=null && list.size()!=0){
								if(projectList!=null && projectList.size()!=0){
									for(int j=0;j<projectList.size();j++){
										ArrayList templist=new ArrayList();
										String projectnumber=(String)projectList.get(j);
										int totalamount=0;
										for(int i=0;i<list.size();i++){
											String[] tokensdata=(String[])list.get(i);
											if(tokensdata[0].equalsIgnoreCase(projectnumber)){
												totalamount=totalamount+(Integer.parseInt(tokensdata[2])*Integer.parseInt(tokensdata[3]));
												templist.add(tokensdata);
											}											
										}
										String invoicenumber=invoice(tokens[0], projectnumber, sdf.format(new Date()), ""+totalamount);
										if(invoicenumber!=null){
											Object[] pair=new Object[2];
											pair[0]=startdate+" - "+enddate;
											pair[1]=templist;
											invoiceMap.put(invoicenumber, pair);
										}
									}									
								}								
							}
						}
					}else{
						SimpleDateFormat sdf1=new SimpleDateFormat("MM-yyyy");
						SimpleDateFormat sdf2=new SimpleDateFormat("MM");
						pastcal.add(pastcal.DATE, 30);
						if(lastInvoiceDate!=null && lastInvoiceDate.trim().length()!=0 && sdf.format(pastcal.getTime()).equalsIgnoreCase(sdf.format(new Date()))){
							Calendar cal = Calendar.getInstance();
							cal.add(cal.MONTH, -1);
							int month=Integer.parseInt(sdf2.format(cal.getTime()));
							String enddate="";
							if(month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12){
								enddate="31-"+sdf1.format(cal.getTime());
							}else{
								enddate="30-"+sdf1.format(cal.getTime());
							}
							String startdate="01-"+sdf1.format(cal.getTime());
							System.out.println("Monthly  cal 			: "+startdate+"     to      "+enddate);
							ArrayList list=getInvoicedata(tokens[0],startdate,enddate);
							ArrayList projectList=getInvoiceProjectdata(tokens[0],startdate,enddate);
							if(list!=null && list.size()!=0){
								if(projectList!=null && projectList.size()!=0){
									for(int j=0;j<projectList.size();j++){
										ArrayList templist=new ArrayList();
										String projectnumber=(String)projectList.get(j);
										int totalamount=0;
										for(int i=0;i<list.size();i++){
											String[] tokensdata=(String[])list.get(i);
											if(tokensdata[0].equalsIgnoreCase(projectnumber)){
												totalamount=totalamount+(Integer.parseInt(tokensdata[2])*Integer.parseInt(tokensdata[3]));
												templist.add(tokensdata);
											}											
										}
										String invoicenumber=invoice(tokens[0], projectnumber, sdf.format(new Date()), ""+totalamount);
										if(invoicenumber!=null){
											Object[] pair=new Object[2];
											pair[0]=startdate+" - "+enddate;
											pair[1]=templist;
											invoiceMap.put(invoicenumber, pair);
										}
									}									
								}								
							}
						}else if(lastInvoiceDate==null){
							Calendar cal = Calendar.getInstance();
							cal.add(cal.MONTH, -1);
							int month=Integer.parseInt(sdf2.format(cal.getTime()));
							String enddate="";
							if(month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12){
								enddate="31-"+sdf1.format(cal.getTime());
							}else{
								enddate="30-"+sdf1.format(cal.getTime());
							}
							String startdate="01-"+sdf1.format(cal.getTime());
							System.out.println("Monthly  cal 			: "+startdate+"     to      "+enddate);
							ArrayList list=getInvoicedata(tokens[0],startdate,enddate);
							ArrayList projectList=getInvoiceProjectdata(tokens[0],startdate,enddate);
							if(list!=null && list.size()!=0){
								if(projectList!=null && projectList.size()!=0){
									for(int j=0;j<projectList.size();j++){
										ArrayList templist=new ArrayList();
										String projectnumber=(String)projectList.get(j);
										int totalamount=0;
										for(int i=0;i<list.size();i++){
											String[] tokensdata=(String[])list.get(i);
											if(tokensdata[0].equalsIgnoreCase(projectnumber)){
												totalamount=totalamount+(Integer.parseInt(tokensdata[2])*Integer.parseInt(tokensdata[3]));
												templist.add(tokensdata);
											}											
										}
										String invoicenumber=invoice(tokens[0], projectnumber, sdf.format(new Date()), ""+totalamount);
										if(invoicenumber!=null){
											Object[] pair=new Object[2];
											pair[0]=startdate+" - "+enddate;
											pair[1]=templist;
											invoiceMap.put(invoicenumber, pair);
										}
									}									
								}								
							}
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return invoiceMap;
	/*	Connection mysqlconnection=null;
		Statement stmt=null;
		ArrayList developerList=null;
		try{
			mysqlconnection =mysqlconnection();
			if(mysqlconnection!=null){
	            stmt = mysqlconnection.createStatement();
	            ResultSet rs = stmt.executeQuery("select clientnumber,projectnumber,developername,hours,overhours,billrate,((hours*billrate)+(overhours*billrate)),date from developerhour");
	    		if(rs!=null){
	    			developerList=new ArrayList();
	    			while(rs.next()){
	    				String[] tokens=new String[8];
	    				tokens[0]=rs.getString("clientnumber");
	    				tokens[1]=rs.getString("projectnumber");
	    				tokens[2]=rs.getString("developername");
	    				tokens[3]=""+rs.getInt("hours");
	    				tokens[4]=""+rs.getInt("overhours");
	    				tokens[5]=""+rs.getInt("billrate");
	    				tokens[6]=""+rs.getInt("((hours*billrate)+(overhours*billrate))");
	    				tokens[7]=rs.getString("date");
	    				developerList.add(tokens);
	    			}
	    		}
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }finally{
		     try{
		    	 if(stmt!=null){
			    	 stmt.close();
			     }
		         if(mysqlconnection!=null)
		        	 mysqlconnection.close();
		      }catch(Exception e){
		         e.printStackTrace();
		      }
		}
        return developerList;	*/
    }
	
	public ArrayList invoicesReport(){
		Connection mysqlconnection=null;
		Statement stmt=null;
		ArrayList developerList=null;
		try{
			mysqlconnection =mysqlconnection();
			if(mysqlconnection!=null){
	            stmt = mysqlconnection.createStatement();
	            ResultSet rs = stmt.executeQuery("select i.invoicenumber,c.clientname,i.projectnumber,c.invoicefrequency,c.billingterms,i.invoicedate,i.totalamount,i.clientnumber from invoice i,client c where i.clientnumber=c.clientnumber");
	    		if(rs!=null){
	    			developerList=new ArrayList();
	    			while(rs.next()){
	    				String[] tokens=new String[8];
	    				tokens[0]=rs.getString("invoicenumber");
	    				tokens[1]=rs.getString("clientname");
	    				tokens[2]=rs.getString("projectnumber");
	    				tokens[3]=rs.getString("invoicefrequency");
	    				tokens[4]=rs.getString("billingterms");
	    				tokens[5]=rs.getString("invoicedate");
	    				tokens[6]=rs.getString("totalamount");
	    				tokens[7]=rs.getString("clientnumber");
	    				developerList.add(tokens);
	    			}
	    		}
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }finally{
		     try{
		    	 if(stmt!=null){
			    	 stmt.close();
			     }
		         if(mysqlconnection!=null)
		        	 mysqlconnection.close();
		      }catch(Exception e){
		         e.printStackTrace();
		      }
		}
        return developerList;
    }
	
	public String[] getCompany(){
		Connection mysqlconnection=null;
		Statement stmt=null;
		String[] companyInformation=null;
		try{
			mysqlconnection =mysqlconnection();
			if(mysqlconnection!=null){
	            stmt = mysqlconnection.createStatement();
	            ResultSet rs = stmt.executeQuery("select * from company");
	    		if(rs!=null){
	    			while(rs.next()){
	    				companyInformation=new String[7];
	    				companyInformation[0]=rs.getString("companyname");
	    				companyInformation[1]=rs.getString("addressline1");
	    				companyInformation[2]=rs.getString("addressline2");
	    				companyInformation[3]=rs.getString("city");
	    				companyInformation[4]=rs.getString("state");
	    				companyInformation[5]=""+rs.getInt("zip");
	    			}
	    		}
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }finally{
		     try{
		    	 if(stmt!=null){
			    	 stmt.close();
			     }
		         if(mysqlconnection!=null)
		        	 mysqlconnection.close();
		      }catch(Exception e){
		         e.printStackTrace();
		      }
		}
        return companyInformation;
    }
	
	public ArrayList addallClients(String fileName){
		ArrayList dataList=new ArrayList();
		BufferedReader bufferedReader = null;
		String rowLine = "";
		try{
			bufferedReader = new BufferedReader(new FileReader(getPath()+fileName));
			while((rowLine = bufferedReader.readLine()) != null){
				String[] rowColumnData = rowLine.split(",");
				dataList.add(rowColumnData);
			}
			for(int index=1;index<dataList.size();index++){
				String[] tokens=(String[])dataList.get(index);
				Connection mysqlconnection=null;
				Statement stmt=null;
				try{
					mysqlconnection =mysqlconnection();
					if(mysqlconnection!=null){
			            stmt = mysqlconnection.createStatement();
			            String sql = "insert into client (clientnumber,clientname,addressline1,addressline2,city,state,zip,email,contactperson,invoicefrequency,billingterms,invoicegrouping,statusflag) values ("+tokens[0]+",'"+tokens[1]+"','"+tokens[2]+"','"+tokens[3]+"','"+tokens[4]+"','"+tokens[5]+"',"+tokens[6]+",'"+tokens[7]+"','"+tokens[8]+"','"+tokens[9]+"','"+tokens[10]+"','"+tokens[11]+"','Y')";
			            stmt.executeUpdate(sql);
					}
		        }catch(SQLException err){
		        	err.printStackTrace();
		        }finally{
				     try{
				    	 if(stmt!=null){
					    	 stmt.close();
					     }
				         if(mysqlconnection!=null)
				        	 mysqlconnection.close();
				      }catch(Exception e){
				         e.printStackTrace();
				      }
				}
			}			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(bufferedReader != null){
				try{
					bufferedReader.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
		return dataList;	
	}
	
	public void addNewClient(String[] tokens){
		Connection mysqlconnection=null;
		Statement stmt=null;
		try{
			mysqlconnection =mysqlconnection();
			if(mysqlconnection!=null){
	            stmt = mysqlconnection.createStatement();
	            String sql = "insert into client (clientnumber,clientname,addressline1,addressline2,city,state,zip,email,contactperson,invoicefrequency,billingterms,invoicegrouping,statusflag) values ("+tokens[0]+",'"+tokens[1]+"','"+tokens[2]+"','"+tokens[3]+"','"+tokens[4]+"','"+tokens[5]+"',"+tokens[6]+",'"+tokens[7]+"','"+tokens[8]+"','"+tokens[9]+"','"+tokens[10]+"','"+tokens[11]+"','Y')";
	            stmt.executeUpdate(sql);
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }finally{
		     try{
		    	 if(stmt!=null){
			    	 stmt.close();
			     }
		         if(mysqlconnection!=null)
		        	 mysqlconnection.close();
		      }catch(Exception e){
		         e.printStackTrace();
		      }
		}	
	}
	
	public void addNewProject(String[] tokens){
		Connection mysqlconnection=null;
		Statement stmt=null;
		try{
			mysqlconnection =mysqlconnection();
			if(mysqlconnection!=null){
	            stmt = mysqlconnection.createStatement();
	            String sql = "insert into project (projectnumber,clientnumber,projectname,startdate,enddate,status,projectmanager,clientcontact,budget,statusflag) values ("+tokens[1]+","+tokens[0]+",'"+tokens[2]+"','"+tokens[3]+"','"+tokens[4]+"','"+tokens[5]+"','"+tokens[6]+"','"+tokens[7]+"',"+tokens[8]+",'Y')";
	            stmt.executeUpdate(sql);
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }finally{
		     try{
		    	 if(stmt!=null){
			    	 stmt.close();
			     }
		         if(mysqlconnection!=null)
		        	 mysqlconnection.close();
		      }catch(Exception e){
		         e.printStackTrace();
		      }
		}	
	}
	
	public void addNewEmployee(String[] tokens){
		Connection mysqlconnection=null;
		Statement stmt=null;
		try{
			mysqlconnection =mysqlconnection();
			if(mysqlconnection!=null){
	            stmt = mysqlconnection.createStatement();
	            String sql = "insert into user (username,title,billrate,role,statusflag) values ('"+tokens[0]+"','"+tokens[1]+"',"+tokens[2]+",'"+tokens[3]+"','Y')";
	            stmt.executeUpdate(sql);
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }finally{
		     try{
		    	 if(stmt!=null){
			    	 stmt.close();
			     }
		         if(mysqlconnection!=null)
		        	 mysqlconnection.close();
		      }catch(Exception e){
		         e.printStackTrace();
		      }
		}	
	}
	
	public String invoice(String clientnumber,String projectnumber,String invoicedate,String totalamount){
		Connection mysqlconnection=null;
		Statement stmt=null;
		String invoicenumber=null;
		try{
			mysqlconnection =mysqlconnection();
			if(mysqlconnection!=null){
	            stmt = mysqlconnection.createStatement();
	            Random randomno = new Random();
	            SimpleDateFormat sd=new SimpleDateFormat("ddMMyyyy");
	            invoicenumber=""+(Integer.parseInt(sd.format(new Date()))+Integer.parseInt(projectnumber));
	            String sql = "insert into invoice (invoicenumber,clientnumber,projectnumber,invoicedate,totalamount) values ("+invoicenumber+","+clientnumber+","+projectnumber+",'"+invoicedate+"',"+totalamount+")";
	            stmt.executeUpdate(sql);
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }finally{
		     try{
		    	 if(stmt!=null){
			    	 stmt.close();
			     }
		         if(mysqlconnection!=null)
		        	 mysqlconnection.close();
		      }catch(Exception e){
		         e.printStackTrace();
		      }
		}
        return invoicenumber;
	}
	
	public void updateClient(String[] tokens){
		Connection mysqlconnection=null;
		Statement stmt=null;
		try{
			mysqlconnection =mysqlconnection();
			if(mysqlconnection!=null){
	            stmt = mysqlconnection.createStatement();
	            String sql = "update client set clientname='"+tokens[1]+"',addressline1='"+tokens[2]+"',addressline2='"+tokens[3]+"',city='"+tokens[4]+"',state='"+tokens[5]+"',zip='"+tokens[6]+"',email='"+tokens[7]+"',contactperson='"+tokens[8]+"',invoicefrequency='"+tokens[9]+"',billingterms='"+tokens[10]+"',invoicegrouping='"+tokens[11]+"' where clientnumber="+tokens[0];
	            stmt.executeUpdate(sql);
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }finally{
		     try{
		    	 if(stmt!=null){
			    	 stmt.close();
			     }
		         if(mysqlconnection!=null)
		        	 mysqlconnection.close();
		      }catch(Exception e){
		         e.printStackTrace();
		      }
		}	
	}
	
	public void updateEmployeeHours(String developername,String date,String hours,String overhours){
		Connection mysqlconnection=null;
		Statement stmt=null;
		try{
			mysqlconnection =mysqlconnection();
			if(mysqlconnection!=null){
	            stmt = mysqlconnection.createStatement();
	            String sql = "update developerhour set hours="+hours+",overhours="+overhours+" where developername='"+developername+"' and date='"+date+"'";
	            stmt.executeUpdate(sql);
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }finally{
		     try{
		    	 if(stmt!=null){
			    	 stmt.close();
			     }
		         if(mysqlconnection!=null)
		        	 mysqlconnection.close();
		      }catch(Exception e){
		         e.printStackTrace();
		      }
		}	
	}
	
	public void updateApprovedEmployeeHours(String developername,String date){
		Connection mysqlconnection=null;
		Statement stmt=null;
		try{
			mysqlconnection =mysqlconnection();
			if(mysqlconnection!=null){
	            stmt = mysqlconnection.createStatement();
	            String sql = "update developerhour set pmapprovestatus='Y' where developername='"+developername+"' and date='"+date+"'";
	            stmt.executeUpdate(sql);
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }finally{
		     try{
		    	 if(stmt!=null){
			    	 stmt.close();
			     }
		         if(mysqlconnection!=null)
		        	 mysqlconnection.close();
		      }catch(Exception e){
		         e.printStackTrace();
		      }
		}	
	}
	
	public void updateEmployee(String[] tokens){
		Connection mysqlconnection=null;
		Statement stmt=null;
		try{
			mysqlconnection =mysqlconnection();
			if(mysqlconnection!=null){
	            stmt = mysqlconnection.createStatement();
	            String sql = "update user set username='"+tokens[0]+"',title='"+tokens[1]+"',billrate="+tokens[2]+",role='"+tokens[3]+"' where username='"+tokens[0]+"'";
	            stmt.executeUpdate(sql);
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }finally{
		     try{
		    	 if(stmt!=null){
			    	 stmt.close();
			     }
		         if(mysqlconnection!=null)
		        	 mysqlconnection.close();
		      }catch(Exception e){
		         e.printStackTrace();
		      }
		}	
	}
	
	public void updateProject(String[] tokens){
		Connection mysqlconnection=null;
		Statement stmt=null;
		try{
			mysqlconnection =mysqlconnection();
			if(mysqlconnection!=null){
	            stmt = mysqlconnection.createStatement();
	            String sql = "update project set clientnumber="+tokens[1]+",projectname='"+tokens[2]+"',startdate='"+tokens[3]+"',enddate='"+tokens[4]+"',status='"+tokens[5]+"',projectmanager='"+tokens[6]+"',clientcontact='"+tokens[7]+"',budget="+tokens[8]+" where projectnumber="+tokens[0];
	            stmt.executeUpdate(sql);
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }finally{
		     try{
		    	 if(stmt!=null){
			    	 stmt.close();
			     }
		         if(mysqlconnection!=null)
		        	 mysqlconnection.close();
		      }catch(Exception e){
		         e.printStackTrace();
		      }
		}	
	}
	
	public void inactiveClient(String[] tokens){
		Connection mysqlconnection=null;
		Statement stmt=null;
		try{
			mysqlconnection =mysqlconnection();
			if(mysqlconnection!=null){
	            stmt = mysqlconnection.createStatement();
	            String inactive="NO";
	            String sql = "update client set statusflag='"+inactive+"' where clientnumber="+tokens[0];
	            stmt.executeUpdate(sql);
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }finally{
		     try{
		    	 if(stmt!=null){
			    	 stmt.close();
			     }
		         if(mysqlconnection!=null)
		        	 mysqlconnection.close();
		      }catch(Exception e){
		         e.printStackTrace();
		      }
		}	
	}
	
	public void inactiveProject(String[] tokens){
		Connection mysqlconnection=null;
		Statement stmt=null;
		try{
			mysqlconnection =mysqlconnection();
			if(mysqlconnection!=null){
	            stmt = mysqlconnection.createStatement();
	            String inactive="NO";
	            String sql = "update project set statusflag='"+inactive+"' where projectnumber="+tokens[1];
	            stmt.executeUpdate(sql);
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }finally{
		     try{
		    	 if(stmt!=null){
			    	 stmt.close();
			     }
		         if(mysqlconnection!=null)
		        	 mysqlconnection.close();
		      }catch(Exception e){
		         e.printStackTrace();
		      }
		}	
	}
	
	public void inactiveEmployee(String[] tokens){
		Connection mysqlconnection=null;
		Statement stmt=null;
		try{
			mysqlconnection =mysqlconnection();
			if(mysqlconnection!=null){
	            stmt = mysqlconnection.createStatement();
	            String inactive="NO";
	            String sql = "update user set statusflag='"+inactive+"' where username='"+tokens[0]+"'";
	            stmt.executeUpdate(sql);
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }finally{
		     try{
		    	 if(stmt!=null){
			    	 stmt.close();
			     }
		         if(mysqlconnection!=null)
		        	 mysqlconnection.close();
		      }catch(Exception e){
		         e.printStackTrace();
		      }
		}	
	}
	
	public void updateCompanyInformation(String[] tokens){
		Connection mysqlconnection=null;
		Statement stmt=null;
		try{
			mysqlconnection =mysqlconnection();
			if(mysqlconnection!=null){
	            stmt = mysqlconnection.createStatement();
	            String sql = "update company set companyname='"+tokens[0]+"',addressline1='"+tokens[1]+"',addressline2='"+tokens[2]+"',city='"+tokens[3]+"',state='"+tokens[4]+"',zip="+tokens[5]+" where companyname='"+tokens[0]+"'";
	            stmt.executeUpdate(sql);
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }finally{
		     try{
		    	 if(stmt!=null){
			    	 stmt.close();
			     }
		         if(mysqlconnection!=null)
		        	 mysqlconnection.close();
		      }catch(Exception e){
		         e.printStackTrace();
		      }
		}	
	}
	
	public void insertdeveloperworktimehours(String projectnumber,String developername,String date,String hours,String overhours,String billrate,String clientnumber){
		Connection mysqlconnection=null;
		Statement stmt=null;
		try{
			mysqlconnection =mysqlconnection();
			if(mysqlconnection!=null){
	            stmt = mysqlconnection.createStatement();
	            String sql = "insert into developerhour (projectnumber,developername,date,hours,overhours,billrate,pmapprovestatus,clientnumber) values ("+projectnumber+",'"+developername+"','"+date+"',"+hours+",0,"+billrate+",'N',"+clientnumber+")";
	            stmt.executeUpdate(sql);
	            if(overhours!=null && overhours.trim().length()!=0){
	            	billrate=""+((Integer.parseInt(billrate))+(Integer.parseInt(billrate)/2));
	            	sql = "insert into developerhour (projectnumber,developername,date,hours,overhours,billrate,pmapprovestatus,clientnumber) values ("+projectnumber+",'"+developername+"','"+date+"',0,"+overhours+","+billrate+",'N',"+clientnumber+")";
		            stmt.executeUpdate(sql);
	            }
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }finally{
		     try{
		    	 if(stmt!=null){
			    	 stmt.close();
			     }
		         if(mysqlconnection!=null)
		        	 mysqlconnection.close();
		      }catch(Exception e){
		         e.printStackTrace();
		      }
		}	
	}
	
	public void insertassigneDevelopertoProject(String[] tokens){
		Connection mysqlconnection=null;
		Statement stmt=null;
		try{
			mysqlconnection =mysqlconnection();
			if(mysqlconnection!=null){
	            stmt = mysqlconnection.createStatement( );
	            String sql = "insert into assigndeveloper (projectnumber,developername,assigndate,clientnumber) values ("+tokens[0]+",'"+tokens[1]+"','"+tokens[2]+"',"+tokens[3]+")";
	            stmt.executeUpdate(sql);
			}
        }catch(SQLException err){
        	err.printStackTrace();
        }finally{
		     try{
		    	 if(stmt!=null){
			    	 stmt.close();
			     }
		         if(mysqlconnection!=null)
		        	 mysqlconnection.close();
		      }catch(Exception e){
		         e.printStackTrace();
		      }
		}	
	}
	
	public ArrayList addallProjects(String fileName){
		ArrayList dataList=new ArrayList();
		BufferedReader bufferedReader = null;
		String rowLine = "";
		try{
			bufferedReader = new BufferedReader(new FileReader(getPath()+fileName));
			while((rowLine = bufferedReader.readLine()) != null){
				String[] rowColumnData = rowLine.split(",");
				dataList.add(rowColumnData);
			}
			for(int index=1;index<dataList.size();index++){
				String[] tokens=(String[])dataList.get(index);
				Connection mysqlconnection=null;
				Statement stmt=null;
				try{
					mysqlconnection =mysqlconnection();
					if(mysqlconnection!=null){
			            stmt = mysqlconnection.createStatement( );
			            String sql = "insert into project (clientnumber,projectnumber,projectname,startdate,enddate,status,projectmanager,clientcontact,budget,statusflag) values ("+tokens[0]+","+tokens[1]+",'"+tokens[2]+"','"+tokens[3]+"','"+tokens[4]+"','"+tokens[5]+"','"+tokens[6]+"','"+tokens[7]+"',"+tokens[8]+",'Y')";
			            stmt.executeUpdate(sql);
					}
		        }catch(SQLException err){
		        	err.printStackTrace();
		        }finally{
				     try{
				    	 if(stmt!=null){
					    	 stmt.close();
					     }
				         if(mysqlconnection!=null)
				        	 mysqlconnection.close();
				      }catch(Exception e){
				         e.printStackTrace();
				      }
				}
			}			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(bufferedReader != null){
				try{
					bufferedReader.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
		return dataList;	
	}
	public ArrayList addallUsers(String fileName){
		ArrayList dataList=new ArrayList();
		BufferedReader bufferedReader = null;
		String rowLine = "";
		try{
			bufferedReader = new BufferedReader(new FileReader(getPath()+fileName));
			while((rowLine = bufferedReader.readLine()) != null){
				String[] rowColumnData = rowLine.split(",");
				dataList.add(rowColumnData);
			}
			for(int index=1;index<dataList.size();index++){
				String[] tokens=(String[])dataList.get(index);
				Connection mysqlconnection=null;
				Statement stmt=null;
				try{
					mysqlconnection =mysqlconnection();
					if(mysqlconnection!=null){
			            stmt = mysqlconnection.createStatement( );
			            String sql = "insert into user (username,title,billrate,role,statusflag) values ('"+tokens[0]+"','"+tokens[1]+"',"+tokens[2]+",'"+tokens[3]+"','Y')";
			            stmt.executeUpdate(sql);
					}
		        }catch(SQLException err){
		        	err.printStackTrace();
		        }finally{
				     try{
				    	 if(stmt!=null){
					    	 stmt.close();
					     }
				         if(mysqlconnection!=null)
				        	 mysqlconnection.close();
				      }catch(Exception e){
				         e.printStackTrace();
				      }
				}
			}			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(bufferedReader != null){
				try{
					bufferedReader.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
		return dataList;	
	}
	
	public ArrayList addCompanyInformation(String fileName){
		ArrayList dataList=new ArrayList();
		BufferedReader bufferedReader = null;
		String rowLine = "";
		try{
			bufferedReader = new BufferedReader(new FileReader(getPath()+fileName));
			while((rowLine = bufferedReader.readLine()) != null){
				String[] rowColumnData = rowLine.split(",");
				dataList.add(rowColumnData);
			}
			for(int index=1;index<dataList.size();index++){
				String[] tokens=(String[])dataList.get(index);
				Connection mysqlconnection=null;
				Statement stmt=null;
				try{
					mysqlconnection =mysqlconnection();
					if(mysqlconnection!=null){
			            stmt = mysqlconnection.createStatement( );
			            String sql="insert into company (companyname,addressline1,addressline2,city,state,zip) values ('"+tokens[0]+"','"+tokens[1]+"','"+tokens[2]+"','"+tokens[3]+"','"+tokens[4]+"',"+tokens[5]+")";
			            stmt.executeUpdate(sql);
					}
		        }catch(SQLException err){
		        	err.printStackTrace();
		        }finally{
				     try{
				    	 if(stmt!=null){
					    	 stmt.close();
					     }
				         if(mysqlconnection!=null)
				        	 mysqlconnection.close();
				      }catch(Exception e){
				         e.printStackTrace();
				      }
				}
			}			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(bufferedReader != null){
				try{
					bufferedReader.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
		return dataList;	
	}
	
	public void importDataFromFile(String fileapth,String importType){
		ArrayList dataList=new ArrayList();
		BufferedReader bufferedReader = null;
		String rowLine = "";
		try{
			bufferedReader = new BufferedReader(new FileReader(fileapth));
			while((rowLine = bufferedReader.readLine()) != null){
				String[] rowColumnData = rowLine.split(",");
				dataList.add(rowColumnData);
			}
			for(int index=1;index<dataList.size();index++){
				String[] tokens=(String[])dataList.get(index);
				Connection mysqlconnection=null;
				Statement stmt=null;
				try{
					mysqlconnection =mysqlconnection();
					if(mysqlconnection!=null){
			            stmt = mysqlconnection.createStatement();
			            String sql = "";
			            if(importType.equalsIgnoreCase("Clients")){
			            	sql="insert into client (clientnumber,clientname,addressline1,addressline2,city,state,zip,email,contactperson,invoicefrequency,billingterms,invoicegrouping,statusflag) values ("+tokens[0]+",'"+tokens[1]+"','"+tokens[2]+"','"+tokens[3]+"','"+tokens[4]+"','"+tokens[5]+"',"+tokens[6]+",'"+tokens[7]+"','"+tokens[8]+"','"+tokens[9]+"','"+tokens[10]+"','"+tokens[11]+"','Y')";
			            }else if(importType.equalsIgnoreCase("Projects")){
			            	sql = "insert into project (clientnumber,projectnumber,projectname,startdate,enddate,status,projectmanager,clientcontact,budget,statusflag) values ("+tokens[0]+","+tokens[1]+",'"+tokens[2]+"','"+tokens[3]+"','"+tokens[4]+"','"+tokens[5]+"','"+tokens[6]+"','"+tokens[7]+"',"+tokens[8]+",'Y')";
			            }else if(importType.equalsIgnoreCase("Employees")){
			            	sql = "insert into user (username,title,billrate,role,statusflag) values ('"+tokens[0]+"','"+tokens[1]+"',"+tokens[2]+",'"+tokens[3]+"','Y')";
			            }else if(importType.equalsIgnoreCase("Company")){
			            	sql="insert into company (companyname,addressline1,addressline2,city,state,zip) values ('"+tokens[0]+"','"+tokens[1]+"','"+tokens[2]+"','"+tokens[3]+"','"+tokens[4]+"',"+tokens[5]+")";
			            } 
			            stmt.executeUpdate(sql);
					}
		        }catch(SQLException err){
		        	err.printStackTrace();
		        }finally{
				     try{
				    	 if(stmt!=null){
					    	 stmt.close();
					     }
				         if(mysqlconnection!=null)
				        	 mysqlconnection.close();
				      }catch(Exception e){
				         e.printStackTrace();
				      }
				}
			}			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(bufferedReader != null){
				try{
					bufferedReader.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
	}
}
