package utils;

public class AutoIncremental {
	 private  Long autoIncremental;
	 private static AutoIncremental instance = null;
	    
	    private AutoIncremental(){
	          autoIncremental = new Long(0);
	    }
	    public  synchronized static AutoIncremental getInstance(){
	    	 if(instance == null){
	    		  instance = new AutoIncremental();	  
	    	 }
	         return instance;
	    }
	    private void setAutoIncremental(){
	    	autoIncremental = new Long(0);
	    }
	   
	    public  static synchronized Long getAutoIncremental(){
	    	getInstance().autoIncremental ++;
	    	 return instance.autoIncremental;
	    	 
	    }
	    public  static void  restartAutoIncremental(){
	         getInstance().setAutoIncremental();   
	    	 
	    }   
	    
}
