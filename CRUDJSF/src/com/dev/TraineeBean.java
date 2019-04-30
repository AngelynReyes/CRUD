package com.dev;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.dev.trainee.model.*;
@ManagedBean(name="TraineeBean")
@SessionScoped
public class TraineeBean {
	
	public List<Trainee> getList(){
		//List<Trainee> list = new ArrayList<Trainee>();
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		ArrayList<Trainee> trains = new ArrayList<>();
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection("jdbc:sqlserver://ducensrv005\\rstwo;databaseName=Training_SQL;user=sqltraining;password=Training@123");
			String sql = "Select Username,Pass FROM JAVASTREAM";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Trainee train = new Trainee(rs.getString("Username").toString(),rs.getString("Pass").toString());
				trains.add(train);
		
				//list.add(train);
				//System.out.print("togetname "+rs.getString("Username")+" and "+rs.getString("Pass")+"\tvxcvxc\n\n");
				//System.out.print("list inside while "+list+"\n\n");
			}
			//System.out.print("Complete List "+Arrays.toString(list.toArray()));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//System.out.print("list in catch "+list.toString());
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
	
}
