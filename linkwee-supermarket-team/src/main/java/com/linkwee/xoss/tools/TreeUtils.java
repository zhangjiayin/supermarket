package com.linkwee.xoss.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TreeUtils {

	public static void main(String[] args) throws SQLException {
		int n = 20;
		String user_id = "27e42f5e214f47a082f46e92c98d2ca2";
        StringBuilder sql = new StringBuilder();
        sql.append("select p1.mobile,p1.user_id ");
        for( int i =1;i<n; i++){
        	sql.append(",p"+i+".parent_id as parent_id"+i);
        }
        //------ depth -----
        sql.append(" ,case ");
        for( int i =1;i<n; i++){
        	sql.append("when p"+i+".parent_id = '"+user_id+"'  then "+i+" ");
        }
        sql.append(" end as depth  ");
        //------ depth end -----
        sql.append(" from   tcrm_cfplanner p1");
        for( int i =1+1;i<n; i++){
        	sql.append(" left join   tcrm_cfplanner p"+i+" on p"+i+".user_id = p"+(i-1)+".parent_id ");
        }
        sql.append(" where       '"+user_id+"' in (p1.parent_id ");
        for( int i =1+1;i<n; i++){
        	sql.append(" ,p"+i+".parent_id");
        }
        sql.append(" ) order  by "+(n+1));
        for( int i =n;i>2; i--){
        	sql.append(" ,"+i+"");
        }
        System.out.println(sql.toString());
        
    	/*Connection conn = null;
        String url = "jdbc:mysql://120.76.97.142:3306/supermarketbak?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull";
        String username = "root";
        String password = "linghui66123";
		try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url,username,password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql.toString());
            while (rs.next()) {
                    System.out.println(rs.getString(1) + "|" + rs.getString(2)+"|" + rs.getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	if(conn!=null){
        		conn.close();
        	}
        }*/
	}
}
