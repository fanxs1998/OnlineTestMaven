package dao;

import java.util.*;
import java.sql.*;
import java.util.ArrayList;

import beans.Score;
import beans.Stu;

public class ScoreDAO {
	public static final String DRIVER = "org.gjt.mm.mysql.Driver";
	public static final String DBURL = "jdbc:mysql://localhost:3306/testdb";
	public static final String DBUSER = "root";
	public static final String DBPASS = "123456";
	private Connection conn = null;
	private PreparedStatement pStat = null;
	private PreparedStatement pStat1 = null;
	private ResultSet rs = null;
	private ResultSet rs1 = null;

	public Connection getConnectionn() {
		try {
			Class.forName(DRIVER).newInstance();
			return DriverManager.getConnection(DBURL, DBUSER, DBPASS);
		} catch (Exception e) {
			return null;
		}
	}

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
			pStat = conn.prepareStatement("select * from scores where stu_id=?");
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

	public boolean addScore(Score sc) {
		conn = getConnectionn();
		try {
			pStat = conn.prepareStatement("insert into scores values(?,?,?)");
			pStat.setInt(1, sc.getStu_id());
			pStat.setString(2, sc.getStu_name());
			pStat.setDouble(3, sc.getScore());
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

	public List<Score> getAllScore() {
		List<Score> tmp_list = new ArrayList<Score>(); // 结果集存放在List集合中
		conn = getConnectionn();
		try {
			pStat = conn.prepareStatement("select * from scores,students where stu_id=id");
			rs = pStat.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				double score = rs.getDouble("score");
				tmp_list.add(new Score(id, name, score));
			}
			return tmp_list;
		} catch (Exception e) {
			return null;
		} finally {
			close();
		}
	}
	public boolean delScoreById(int id) {
		conn = getConnectionn();
		try {
			pStat = conn.prepareStatement("delete from students where stu_id=?");
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

	public List<Score> getScoreByCondition(String name1, double score1, double score2, int flag) {
		List<Score> tmp_list = new ArrayList<Score>();
		conn = getConnectionn();
		try {
			if (name1.equals("")) {
				if (flag == 0) {
					pStat = conn.prepareStatement(
							"select * from scores,students where scores.stu_id=students.id and scores.score>=? and scores.score<=? order by score asc");
				} else {
					pStat = conn.prepareStatement(
							"select * from scores,students where scores.stu_id=students.id and scores.score>=? and scores.score<=? order by score desc");
				}
				pStat.setDouble(1, score1);
				pStat.setDouble(2, score2);
			} else {
				if (flag == 0) {
					pStat = conn.prepareStatement(
							"select * from scores,students where scores.stu_id=students.id and scores.score>=? and scores.score<=? and students.name=? order by score asc");
				} else {
					pStat = conn.prepareStatement(
							"select * from scores,students where scores.stu_id=students.id and scores.score>=? and scores.score<=? and students.name=? order by score desc");
				}
				pStat.setDouble(1, score1);
				pStat.setDouble(2, score2);
				pStat.setString(3, name1);
			}

			rs = pStat.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				double score = rs.getDouble("score");
				tmp_list.add(new Score(id, name, score));
			}

			return tmp_list;
		} catch (Exception e) {
			return null;
		} finally {
			close();
		}
	}

} // end class
