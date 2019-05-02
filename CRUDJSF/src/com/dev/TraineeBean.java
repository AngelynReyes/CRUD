package com.dev;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.dev.trainee.model.*;
@ManagedBean(name="traineeBean")
@RequestScoped
public class TraineeBean implements Serializable{
	
	public List<Trainee> getList(){
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		ArrayList<Trainee> trains = new ArrayList<>();
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://ducensrv005\\rstwo;databaseName=Training_SQL;user=sqltraining;password=Training@123");
			String sql = "Select id,Username,Pass FROM JAVASTREAM";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Trainee train = new Trainee(rs.getInt("id"),rs.getString("Username").toString(),rs.getString("Pass").toString());
				trains.add(train);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
		finally
		{
		try
		{
		con.close();
		ps.close();
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		}
		System.out.print(Arrays.toString(trains.toArray()));
		return trains;
	}
	public static String saveTrainee(Trainee newT) {
        int rs = 0;
        String navigationResult = "";
        PreparedStatement ps = null;
		Connection con = null;
        try {
        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://ducensrv005\\rstwo;databaseName=Training_SQL;user=sqltraining;password=Training@123");   
            ps = con.prepareStatement("insert into JAVASTREAM (id,Username, Pass) values (?,?,?)");         
            ps.setInt(1,newT.getId());
            ps.setString(2, newT.getUsername());
            ps.setString(3, newT.getPass());
            rs = ps.executeUpdate();
            con.close();
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        }
        if(rs !=0) {
            navigationResult = "traineelist.xhtml?faces-redirect=true";
        } else {
            navigationResult = "createtrainee.xhtml?faces-redirect=true";
        }
        return navigationResult;
    }
	public static String editTrainee(Integer id) {
		Trainee editRecord = null;
        System.out.println("editTraineeRecordInDB() : ID: " + id);
        PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
        /* Setting The Particular Trainee Details In Session */
        Map<String,Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
 
        try {
        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://ducensrv005\\rstwo;databaseName=Training_SQL;user=sqltraining;password=Training@123");   
            String sql = "select id,Username,Pass from JAVASTREAM where id = "+id;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs != null) {
                rs.next();
                editRecord = new Trainee(rs.getInt("id"),rs.getString("Username").toString(),rs.getString("Pass").toString()); 
             
            }
            sessionMapObj.put("editRecordObj", editRecord);
            System.out.print("EDIT" +rs.getInt("id")+rs.getString("Username").toString()+rs.getString("Pass").toString());
            con.close();
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        }
        return "/edittrainee.xhtml?faces-redirect=true";
	}
	public static String updateTrainee(Trainee up) {
		System.out.printf("UPDATE " +up.getId(),up.getUsername()+up.getPass());
		PreparedStatement ps = null;
		Connection con = null;
        try {
        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://ducensrv005\\rstwo;databaseName=Training_SQL;user=sqltraining;password=Training@123");
            String sql = "update JAVASTREAM set Username=?, Pass=? where id=?";  
            ps = con.prepareStatement(sql);
            ps.setString(1,up.getUsername());  
            ps.setString(2,up.getPass());
            ps.setInt(3,up.getId());
            ps.executeUpdate();
            con.close();            
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        }
        return "/traineelist.xhtml?faces-redirect=true";
    }
	public static String deleteTrainee(Integer id){
		PreparedStatement ps = null;
		Connection con = null;
        System.out.println("deletetraineeRecordInDB() : ID: " + id);
        try {
        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://ducensrv005\\rstwo;databaseName=Training_SQL;user=sqltraining;password=Training@123");
            ps = con.prepareStatement("delete from JAVASTREAM where id = "+id);  
            ps.executeUpdate();  
            con.close();
        } catch(Exception sqlException){
            sqlException.printStackTrace();
        }
        return "/traineelist.xhtml?faces-redirect=true";
    }
	
}
