package invoice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class DBServices{
	static final String CLIENTS = "client_data.csv";
	static final String USERS = "people_data.csv";
	static final String PROJECTS = "project_data.csv";
	
	public String getPath(){
		try{
			return new java.io.File( "." ).getCanonicalPath()+"\\src\\";
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public Connection mysqlconnection(){
		Connection mysqlconnection=null;
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String path = "jdbc:mysql://localhost:3306/sowmya";
            String mysqlusername = "root";
            String mysqluserpassword= "bill";
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
	            stmt = mysqlconnection.createStatement( );
	            ResultSet rs = stmt.executeQuery("select * from user where username='"+userText+"'");
	    		if(rs!=null){
	    			while(rs.next()){
	    				resultUser=new User();
	    				resultUser.setUsername(rs.getString("username"));
	    				resultUser.setUserrole(rs.getString("role"));
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
	            stmt = mysqlconnection.createStatement( );
	            ResultSet rs = stmt.executeQuery("select * from client");
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
	
	public ArrayList getAllProjects(){
		Connection mysqlconnection=null;
		Statement stmt=null;
		ArrayList projectList=null;
		try{
			mysqlconnection =mysqlconnection();
			if(mysqlconnection!=null){
	            stmt = mysqlconnection.createStatement( );
	            ResultSet rs = stmt.executeQuery("select * from project");
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
	            stmt = mysqlconnection.createStatement( );
	            ResultSet rs = stmt.executeQuery("select * from user");
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
			            stmt = mysqlconnection.createStatement( );
			            String sql = "insert into client (clientnumber,clientname,addressline1,addressline2,city,state,zip,email,contactperson,invoicefrequency,billingterms,invoicegrouping,statusflag) values ("+tokens[0]+",'"+tokens[1]+"','"+tokens[2]+"','"+tokens[3]+"','"+tokens[4]+"','"+tokens[5]+"',"+tokens[6]+",'"+tokens[7]+"','"+tokens[8]+"','"+tokens[9]+"','"+tokens[10]+"','"+tokens[11]+"','N')";
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
			            String sql = "insert into project (projectnumber,clientnumber,projectname,startdate,enddate,status,projectmanager,clientcontact,budget,statusflag) values ("+tokens[0]+","+tokens[1]+",'"+tokens[2]+"','"+tokens[3]+"','"+tokens[4]+"','"+tokens[5]+"','"+tokens[6]+"','"+tokens[7]+"',"+tokens[8]+",'N')";
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
			            String sql = "insert into user (username,title,billrate,role) values ('"+tokens[0]+"','"+tokens[1]+"',"+tokens[2]+",'"+tokens[3]+"')";
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
}
