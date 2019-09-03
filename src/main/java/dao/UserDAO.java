package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import beans.Stu;

public class UserDAO {
	public static final String DRIVER = "org.gjt.mm.mysql.Driver";// 驱动
	public static final String DBURL = "jdbc:mysql://localhost:3306/testdb";// 连接字符串
	public static final String DBUSER = "root";// 用户名
	public static final String DBPASS = "123456";// 密码
	// 三个对象
	private Connection conn = null;
	private PreparedStatement pStat = null;// PreparedStatement可实现带参数的动态查询
	private ResultSet rs = null;// 结果集

	public Connection getConnectionn() {
		// 获得数据库连接
		try {
			Class.forName(DRIVER).newInstance();// 加载mysql驱动
			return DriverManager.getConnection(DBURL, DBUSER, DBPASS);// 建立数据库连接
		} catch (Exception e) {
			return null;
		}
	}

	// 关闭数据库连接
	public void close() {
		try {
			if (rs != null)
				rs.close();
			if (pStat != null)
				pStat.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end close

	public boolean isIdExists(int id) {
		conn = getConnectionn();
		try {
			pStat = conn.prepareStatement("select * from students where id=?");
			pStat.setInt(1, id);
			rs = pStat.executeQuery();
			if (rs.next())
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		} finally {
			close();
		}
	} // end isUsernameExists

	public boolean findStu(int id, String password) {
		conn = getConnectionn();
		try {
			pStat = conn.prepareStatement("select * from students where id=? and password=?");
			pStat.setInt(1, id);
			pStat.setString(2, password);
			rs = pStat.executeQuery();
			if (rs.next())
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		} finally {
			close();
		}
	} // end findUser

	public boolean addStu(Stu stu) {
		conn = getConnectionn();
		try {
			pStat = conn.prepareStatement("insert into students values(?,?,?)");
			pStat.setInt(1, stu.getId());
			pStat.setString(2, stu.getName());
			pStat.setString(3, stu.getPassword());
			int cnt = pStat.executeUpdate();
			if (cnt > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		} finally {
			close();
		}
	} // end add

	public List<Stu> getAllStu() {
		List<Stu> tmp_list = new ArrayList<Stu>(); // 结果集存放在List集合中
		conn = getConnectionn();
		try {
			pStat = conn.prepareStatement("select * from students where 1");
			rs = pStat.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String password = rs.getString("password");
				tmp_list.add(new Stu(id, name, password));
			}
			return tmp_list;
		} catch (Exception e) {
			return null;
		} finally {
			close();
		}
	}

	public boolean delStuById(int id) {
		conn = getConnectionn();
		try {
			pStat = conn.prepareStatement("delete from students where id=?");
			pStat.setInt(1, id);
			int cnt = pStat.executeUpdate();
			if (cnt > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		} finally {
			close();
		}
	}

	public Stu getStuById(int id) {
		conn = getConnectionn();
		try {
			pStat = conn.prepareStatement("select * from students where id=?");
			pStat.setInt(1, id);
			rs = pStat.executeQuery();
			if (rs.next()) {
				Stu stu = new Stu(rs.getInt("id"), rs.getString("name"), rs.getString("password"));
				return stu;
			} else
				return null;
		} catch (Exception e) {
			return null;
		} finally {
			close();
		}
	}

	public boolean UpdateStu(Stu stu) {
		conn = getConnectionn();
		try {
			pStat = conn.prepareStatement("Update students set name=?,password=? where id=?");
			pStat.setString(1, stu.getName());
			pStat.setString(2, stu.getPassword());
			pStat.setInt(3, stu.getId());
			int cnt = pStat.executeUpdate();
			if (cnt > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		} finally {
			close();
		}
	}

	public boolean findAdmin(int id, String password) {
		conn = getConnectionn();
		try {
			pStat = conn.prepareStatement("select * from admin where id=? and password=?");
			pStat.setInt(1, id);
			pStat.setString(2, password);
			rs = pStat.executeQuery();
			if (rs.next())
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		} finally {
			close();
		}
	}

} // end class
