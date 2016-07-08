package schedule;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import au.com.bytecode.opencsv.CSVReader;

public class Service{
	static final String OCFACULTY = "ocfaculty.csv";
	static final String OCCOURSE = "occourse.csv";
	static final String OCSTUDENTDEGREE = "ocstudentdegree.csv";
	static final String OCSTUDENTCOURSE = "ocstudentcourse.csv";
	static final String OCSDEGREE = "ocdegree.csv";
	static final String OCSDEGREEPLAN = "ocdegreeplan.csv";
	static final String OCSEMESTER = "ocsemester.csv";
	static final String OCGRADSCHOOL = "ocgradschool.csv";
	static final String OCUNIVERSITYNAME = "ocuniversityname.csv";
	
	public String getPath(){
		try{
			return new java.io.File( "." ).getCanonicalPath()+"\\src\\schedule\\";
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public String userAuthentication(String userText,String passwordText){
		String resultMessage="";
		try{			
			if(userText.equalsIgnoreCase("admin") && passwordText.equalsIgnoreCase("admin")){
				resultMessage="success";
			}else if(userText.equalsIgnoreCase("director") && passwordText.equalsIgnoreCase("director")){
				resultMessage="success";
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return resultMessage;
	}
	
	public ArrayList allOCMaintainData(String fileName){
		ArrayList dataList=new ArrayList();
		BufferedReader bufferedReader = null;
		String rowLine = "";
		try{
			bufferedReader = new BufferedReader(new FileReader(getPath()+fileName));
			while((rowLine = bufferedReader.readLine()) != null){
				String[] rowColumnData = rowLine.split(",");
				dataList.add(rowColumnData);
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
	
	public ArrayList allOCCoursesORDegreePlan(String fileName){
		ArrayList arrayList=new ArrayList();
		try{
			CSVReader reader = new CSVReader(new FileReader(getPath()+fileName));
		     String [] rowLine;
		     while((rowLine = reader.readNext()) != null) {
		    	 arrayList.add(rowLine);
		     }
		}catch(Exception e){
			e.printStackTrace();
		}
		return arrayList;
	 }
	
	public HashMap studentDumpMap(){
		HashMap studentDumpMap=null;
		try{
			ArrayList studentList=allOCMaintainData(OCSTUDENTDEGREE);
			if(studentList!=null && studentList.size()!=0){
				studentDumpMap=new HashMap();
				Iterator iterator=studentList.iterator();
				while(iterator.hasNext()){
					String[] tokens=(String[])iterator.next();
					studentDumpMap.put(tokens[0], tokens[1]);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return studentDumpMap;
	}
	
	public HashMap degreeForecastMap(){
		HashMap studentDumpMap=null;
		try{
			ArrayList degreeForecastList=allOCMaintainData(OCSDEGREE);
			if(degreeForecastList!=null && degreeForecastList.size()!=0){
				studentDumpMap=new HashMap();
				Iterator iterator=degreeForecastList.iterator();
				while(iterator.hasNext()){
					String[] tokens=(String[])iterator.next();
					studentDumpMap.put(tokens[0], tokens[3]);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return studentDumpMap;
	}
	
	public HashMap facultyDays(){
		HashMap facultyDaysMap=new HashMap();
		facultyDaysMap.put("M","MONDAY");
		facultyDaysMap.put("T","TUESDAY");
		facultyDaysMap.put("W","WEDNESDAY");
		facultyDaysMap.put("R","THURSDAY");
		facultyDaysMap.put("F","FRIDAY");
		return facultyDaysMap;
	}
	
	public HashMap studentCourseDumpMap(){
		HashMap studentDumpMap=null;
		try{
			ArrayList studentCourseList=allOCMaintainData(OCSTUDENTCOURSE);
			if(studentCourseList!=null && studentCourseList.size()!=0){
				studentDumpMap=new HashMap();
				Iterator iterator=studentCourseList.iterator();
				while(iterator.hasNext()){
					String[] tokens=(String[])iterator.next();
					if(studentDumpMap.containsKey(tokens[0])){
						ArrayList courses=(ArrayList)studentDumpMap.get(tokens[0]);
						courses.add(tokens[1]);
						studentDumpMap.put(tokens[0], courses);
					}else{
						ArrayList courses=new ArrayList();
						courses.add(tokens[1]);
						studentDumpMap.put(tokens[0], courses);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return studentDumpMap;
	}
	
	public HashMap studentDegreeDumpMap(){
		HashMap studentDumpMap=null;
		try{
			ArrayList studentDegreeList=allOCMaintainData(OCSTUDENTDEGREE);
			if(studentDegreeList!=null && studentDegreeList.size()!=0){
				studentDumpMap=new HashMap();
				Iterator iterator=studentDegreeList.iterator();
				while(iterator.hasNext()){
					String[] tokens=(String[])iterator.next();
					if(studentDumpMap.containsKey(tokens[1])){
						ArrayList tempStudentDegreeList=(ArrayList)studentDumpMap.get(tokens[1]);
						tempStudentDegreeList.add(tokens[0]);
						studentDumpMap.put(tokens[1], tempStudentDegreeList);
					}else{
						ArrayList tempStudentDegreeList=new ArrayList();
						tempStudentDegreeList.add(tokens[0]);
						studentDumpMap.put(tokens[1], tempStudentDegreeList);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return studentDumpMap;
	}	
	
	public int forecastStudents(){
		int totalFutureStudents=0;
		try{
			ArrayList degreeForecastList=allOCMaintainData(OCSDEGREE);
			if(degreeForecastList!=null && degreeForecastList.size()!=0){
				Iterator iterator=degreeForecastList.iterator();
				while(iterator.hasNext()){
					String[] tokens=(String[])iterator.next();
					totalFutureStudents=totalFutureStudents+Integer.parseInt(tokens[3]);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return totalFutureStudents;
	}
	
	public HashMap yearWiseStudents(String year,String notConsiderDegree1,String notConsiderDegree2,String notConsiderDegree3){
		HashMap yearWiseStudentsMap=null;
		try{
			ArrayList studentList=allOCMaintainData(OCSTUDENTDEGREE);
			HashMap studentCoursesMap=studentCourseDumpMap();
			if(studentList!=null && studentList.size()!=0 && studentCoursesMap!=null && studentCoursesMap.size()!=0){
				yearWiseStudentsMap=new HashMap();
				Iterator iterator=studentList.iterator();
				while(iterator.hasNext()){
					String[] tokens=(String[])iterator.next();
					if(tokens[2].equalsIgnoreCase(year) && !(tokens[1].equalsIgnoreCase(notConsiderDegree1)) && !(tokens[1].equalsIgnoreCase(notConsiderDegree2)) && !(tokens[1].equalsIgnoreCase(notConsiderDegree3))){
						if((ArrayList)studentCoursesMap.get(tokens[0])!=null && ((ArrayList)studentCoursesMap.get(tokens[0])).size()<10){
							yearWiseStudentsMap.put(tokens[0],(ArrayList)studentCoursesMap.get(tokens[0]));
						}else if((ArrayList)studentCoursesMap.get(tokens[0])==null){
							yearWiseStudentsMap.put(tokens[0],new ArrayList());
						}else if((ArrayList)studentCoursesMap.get(tokens[0])!=null && ((ArrayList)studentCoursesMap.get(tokens[0])).size()==0){
							yearWiseStudentsMap.put(tokens[0],new ArrayList());
						}
					}
				}
			}
			if(yearWiseStudentsMap!=null && year.equalsIgnoreCase("2016FA")){
				int futureStudentInThisYearFall=forecastStudents();
				while(futureStudentInThisYearFall>0){
					yearWiseStudentsMap.put(""+(2000000+futureStudentInThisYearFall),new ArrayList());
					futureStudentInThisYearFall--;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return yearWiseStudentsMap;
	}
	
	public HashMap degreePlanCourses(){
		HashMap degreePlanCoursesMap=null;
		try{
			ArrayList degreeList=allOCCoursesORDegreePlan(OCSDEGREEPLAN);
			if(degreeList!=null && degreeList.size()!=0){
				degreePlanCoursesMap=new HashMap();
				Iterator iterator=degreeList.iterator();
				while(iterator.hasNext()){
					String[] tokens=(String[])iterator.next();
					if(degreePlanCoursesMap.containsKey(tokens[0])){
						ArrayList courses=(ArrayList)degreePlanCoursesMap.get(tokens[0]);
						courses.add(tokens);
						degreePlanCoursesMap.put(tokens[0], courses);
					}else{
						ArrayList courses=new ArrayList();
						courses.add(tokens);
						degreePlanCoursesMap.put(tokens[0], courses);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return degreePlanCoursesMap;
	}
	
	public HashMap facultyMap(){
		HashMap facultyMap=new HashMap();
		try{
			ArrayList facultyList=allOCMaintainData(OCFACULTY);
			if(facultyList!=null && facultyList.size()!=0){
				facultyMap=new HashMap();
				Iterator iterator=facultyList.iterator();
				while(iterator.hasNext()){
					String[] tokens=(String[])iterator.next();
					facultyMap.put(tokens[0], tokens);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return facultyMap;
	}
	
	public HashMap courseFacultyMap(){
		HashMap courseFacultyMap=new HashMap();
		try{
			ArrayList courseList=allOCCoursesORDegreePlan(OCCOURSE);
			if(courseList!=null && courseList.size()!=0){
				Iterator iterator=courseList.iterator();
				while(iterator.hasNext()){
					String[] tokens=(String[])iterator.next();
					courseFacultyMap.put(tokens[0], tokens[9]);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return courseFacultyMap;
	}
	
	public HashMap courseTitleMap(){
		HashMap courseFacultyMap=null;
		try{
			ArrayList courseList=allOCMaintainData(OCCOURSE);
			if(courseList!=null && courseList.size()!=0){
				courseFacultyMap=new HashMap();
				Iterator iterator=courseList.iterator();
				while(iterator.hasNext()){
					String[] tokens=(String[])iterator.next();
					courseFacultyMap.put(tokens[0], tokens[1]);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return courseFacultyMap;
	}
	
	public HashMap studentScheduleStudyList(HashMap yearWiseStudentsMap){
		HashMap studyCourseMap=new HashMap();
		try{
			HashMap studentDumpMap=studentDumpMap();
			HashMap degreePlanCoursesMap=degreePlanCourses();			
			if(yearWiseStudentsMap!=null && yearWiseStudentsMap.size()!=0 && studentDumpMap!=null && studentDumpMap.size()!=0 
					&& degreePlanCoursesMap!=null && degreePlanCoursesMap.size()!=0){
				Iterator itr2 = yearWiseStudentsMap.keySet().iterator();
				while(itr2.hasNext()){
				    String key = (String)itr2.next();
				    String degree=(String)studentDumpMap.get(key);
				    ArrayList degreePlanCourses=(ArrayList)degreePlanCoursesMap.get(degree);
				    ArrayList studentCourses=(ArrayList)yearWiseStudentsMap.get(key);
				    if(degreePlanCourses!=null && degreePlanCourses.size()!=0 && studentCourses!=null && studentCourses.size()!=0){
				    	Iterator listIterator = degreePlanCourses.iterator();
				    	ArrayList totalDegreeCourses=new ArrayList();
				    	while(listIterator.hasNext()){
				    		String[] degreeCourses=(String[])listIterator.next();
				    		String[] listOfCourses=degreeCourses[4].split(",");
				    		for(int i=0;i<listOfCourses.length;i++){
				    			totalDegreeCourses.add(listOfCourses[i]);				    			
				    		}				    		
				    	}
				    	int count=1;
				    	for(int i=0;i<totalDegreeCourses.size();i++){
			    			String course=(String)totalDegreeCourses.get(i);
			    			if(!studentCourses.contains(course) && count<=4){
			    				if(studyCourseMap.containsKey(course)){
			    					int studentCount=Integer.parseInt((String)studyCourseMap.get(course));
			    					studentCount=studentCount+1;
			    					studyCourseMap.put(course,""+studentCount);
			    				}else{
			    					int studentCount=1;
			    					studyCourseMap.put(course,""+studentCount);
			    				}
			    				count++;
			    			}
			    		}
				    }
				}
			}
			System.out.println(studyCourseMap+" total size");
		}catch(Exception e){
			e.printStackTrace();
		}
		return studyCourseMap;
	}
	
	public ArrayList generateSchedule(String year){
		ArrayList generateScheduleList=null;
		HashMap yearWiseStudentsMap =yearWiseStudents(year,"MSE.ECE","MSE.ENGMGT","MSE.ECE, MSE.ENGMGT");
		HashMap generateCourseSchedule=studentScheduleStudyList(yearWiseStudentsMap);
		if(generateCourseSchedule!=null && generateCourseSchedule.size()!=0){
			generateScheduleList=new ArrayList();
			HashMap courseFacultyMap=courseFacultyMap();
			HashMap facultyMap=facultyMap();
			HashMap courseTitleMap=courseTitleMap();
			HashMap facultyDaysMap=facultyDays();
			Iterator itr2 = generateCourseSchedule.keySet().iterator();
			while(itr2.hasNext()){
				String key = (String)itr2.next();
			    if(Integer.parseInt((String)generateCourseSchedule.get(key))>2){
				    String[] generateSchedule=new String[5];
				    generateSchedule[0]=year;
				    generateSchedule[1]=key+" "+courseTitleMap.get(key);
				    System.out.println((String)courseFacultyMap.get(key)+"  Faculty "+key);
				    System.out.println((String[])facultyMap.get((String)courseFacultyMap.get(key))+"  Course Faculty ");
				    if((String)courseFacultyMap.get(key)!=null && ((String)courseFacultyMap.get(key)).indexOf(",")!=-1){
				    	String split[]=((String)courseFacultyMap.get(key)).split(",");
				    	//generateSchedule[2]=(String)facultyDaysMap.get(""+(((String[])facultyMap.get(split[0]))[5].charAt(0)));
					    generateSchedule[3]=split[0];
					    generateSchedule[2]=(String)facultyDaysMap.get("M");
				    }else{
				    	generateSchedule[2]=(String)facultyDaysMap.get(""+(((String[])facultyMap.get((String)courseFacultyMap.get(key)))[5].charAt(0)));
					    generateSchedule[3]=(String)courseFacultyMap.get(key);
				    }
				    
				    generateSchedule[4]="25/"+generateCourseSchedule.get(key);
				    generateScheduleList.add(generateSchedule);
			    }
			}
		}
		return generateScheduleList;
	}
}
