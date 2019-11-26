package com.kh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.kh.vo.PagingDto;
import com.kh.vo.SearchDto;
import com.kh.vo.Vo;

public class Dao {

	private static Dao instance;
	private Dao() {
		
	}
	public static Dao getInstance() {
		if(instance == null) {
			instance = new Dao();
		}
		return instance;
	}
	
	public Connection getConnection() {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			// javax.sql.DataSource
			DataSource ds = (DataSource)envCtx.lookup("jdbc/basicjsp"); 
			Connection conn = ds.getConnection();
			return conn;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private void closeAll(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		if (rs != null) try { rs.close(); } catch (Exception e) { }
		if (pstmt != null) try { pstmt.close(); } catch (Exception e) { }
		if (conn != null) try { conn.close(); } catch (Exception e) { }
	}
	
	public void insert(Vo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "insert into board (num, writer, email, subject, content, "
					+ "passwd, ip, file_name, ref, re_step, re_level) "
					+ "values(seq_board.nextval, ?, ?, ?, ?, "
					+ "?, ?, ?, seq_board.nextval, 0, 0)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getWriter());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getSubject());
			pstmt.setString(4, vo.getContent());
			pstmt.setString(5, vo.getPasswd());
			pstmt.setString(6, vo.getIp());
			pstmt.setString(7, vo.getFile_name());
			int count = pstmt.executeUpdate();
			if(count >= 1) {
				System.out.println("글 작성 성공");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null);
		}
	}
	
	public void update(Vo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "update board set subject = ?, content = ? where num = ? and passwd = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getSubject());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getNum());
			pstmt.setString(4, vo.getPasswd());
			int count = pstmt.executeUpdate();
			if(count >= 1) {
				System.out.println("수정 성공");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null);
		}
	}
	
	public List<Vo> getArticles(PagingDto pagingDto, SearchDto searchDto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "select * from" + 
					"        (select rownum rnum, a.* from" + 
					"            (select * from board";
			
			if (!searchDto.getSearchType().equals("") &&
					!searchDto.getKeyword().equals("")) {
				sql += "          where " + searchDto.getSearchType() +
					   "          like '%" + searchDto.getKeyword() + "%'";
			}
			
			sql +=  "             order by ref desc, re_step asc) a)" + 
					"     where rnum >= ? and rnum <= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pagingDto.getStartRow());
			pstmt.setInt(2, pagingDto.getEndRow());
			rs = pstmt.executeQuery();
			List<Vo> list = new ArrayList<>();
			while(rs.next()) {
				int num = rs.getInt("num");
				String subject = rs.getString("subject");
				String writer = rs.getString("writer");
				Timestamp reg_date = rs.getTimestamp("reg_date");
				int read_count = rs.getInt("read_count");
				String ip = rs.getString("ip");
				
				Vo vo = new Vo();
				vo.setNum(num);
				vo.setSubject(subject);
				vo.setWriter(writer);
				vo.setReg_date(reg_date);
				vo.setRead_count(read_count);
				vo.setIp(ip);
				
				list.add(vo);
			}
			return list;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return null;
	}
	
	public Vo selectArticleByNum(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "select * from board where num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String writer = rs.getString("writer");
				String subject = rs.getString("subject");
				String content = rs.getString("content");
				int read_count = rs.getInt("read_count");
				String ip = rs.getString("ip");
				int ref = rs.getInt("ref");
				int re_step = rs.getInt("re_step");
				int re_level = rs.getInt("re_level");
				
				Vo vo = new Vo();
				vo.setNum(num);
				vo.setWriter(writer);
				vo.setSubject(subject);
				vo.setContent(content);
				vo.setRead_count(read_count);
				vo.setIp(ip);
				vo.setRef(ref);
				vo.setRe_step(re_step);
				vo.setRe_level(re_level);
				return vo;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return null;
	}
	
	public void delete(int num, String passwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			String sql = "delete from board where num = ? and passwd = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, passwd);
			int count = pstmt.executeUpdate();
			if(count >= 1) {
				System.out.println("삭제 성공");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null);
		}
	}
	
	public void reply(Vo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			String sql2	= "update board set re_step = re_step + 1 where ref = ? and re_step > ?";
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setInt(1, vo.getRef());
			pstmt2.setInt(2, vo.getRe_step());
			pstmt2.executeUpdate();
			
			String sql =" insert into board (num, writer, email, subject, content, "
					+ "passwd, ip, ref, re_step, re_level) "
					+ 				 "values(seq_board.nextval, ?, ?, ?, ?, "
					+ "?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getWriter());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getSubject());
			pstmt.setString(4, vo.getContent());
			pstmt.setString(5, vo.getPasswd());
			pstmt.setString(6, vo.getIp());
			pstmt.setInt(7, vo.getRef());
			pstmt.setInt(8, vo.getRe_step() + 1);
			pstmt.setInt(9, vo.getRe_level() + 1);
			int count = pstmt.executeUpdate();
			
			if(count >= 1) {
				System.out.println("답글 달기 성공");
			}

			conn.commit();
		} catch(Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			closeAll(null, pstmt2, null);
			closeAll(conn, pstmt, null);
		}
	}
	
	public int getCount(SearchDto searchDto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			String sql = "select count(*)cnt from board ";
			if(!searchDto.getSearchType().equals("") && !searchDto.getKeyword().equals("")) {
				sql += "  where " + searchDto.getSearchType() +
						" like '%" + searchDto.getKeyword() + "%'";
			}
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int cnt = rs.getInt("CNT");
				return cnt;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return 0;
	}
}
