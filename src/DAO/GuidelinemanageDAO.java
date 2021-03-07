package DAO;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import BEAN.Guideline;
import BEAN.Member;

public class GuidelinemanageDAO {
	public static List<Guideline> Displaysguidelinemanage(HttpServletRequest request,int start, int count,Connection conn, int guidelineroleid, Member mem) {
		
		List<Guideline> list = new ArrayList<Guideline>();
		PreparedStatement ptmt = null;
		String sql = "";
		
		if(mem.getRoleid()==1) {
			sql = "select * from guideline where guidelineroleid = "+guidelineroleid+" limit "+(start-1)+","+count+"";
		}
		if(mem.getRoleid()==3) {
			sql = "select * from guideline where userid="+mem.getUserid()+" AND guidelineroleid = "+guidelineroleid+" limit "+(start-1)+","+count+"";
		}
		
		
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			
			if(rs.isBeforeFirst()) {
				while (rs.next()) {
					Guideline gl = new Guideline();
					int guidelineid = rs.getInt("guidelineid");
					String guidelinename = rs.getString("guidelinename");
					String guidelineimage = rs.getString("guidelineimage");
					String content  = rs.getString("content");
					
					gl.setGuidelineid(guidelineid);
					gl.setGuidelinename(guidelinename);
					gl.setGuidelineimage(guidelineimage);
					gl.setContent(content);
					
					list.add(gl);
				}
			}else {
				request.setAttribute("msglistguidelinemanage","Không có bài hướng dẫn nào" );
			}
			
			
			
			
		} catch (SQLException e) {
			request.setAttribute("msglistguidelinemanage",e.getMessage() );
		}
		return list;
	}
	public static boolean Insertguidelinename(HttpServletRequest request, Connection conn, Guideline gl) {
		PreparedStatement ptmt = null;
		
		String sql = "insert into guideline (guidelinename,guidelineroleid, userid, level) value(?,?,?,?)";
		
		try {
			ptmt = conn.prepareStatement(sql);
			String guidelinename = gl.getGuidelinename();
			int guidelineroleid = gl.getGuidelineroleid();
			int userid = gl.getUserid();
			int level = gl.getLevel();
			ptmt.setString(1, guidelinename);
			ptmt.setInt(2, guidelineroleid);
			ptmt.setInt(3, userid);
			ptmt.setInt(4, level);
			int kt = ptmt.executeUpdate();
			if(kt != 0) {
				return true;
			}
			ptmt.close();
		} catch (SQLException e) {
			request.setAttribute("msgguidelinename", e.getMessage());
		}
		return false;
	}
	
	//ham them file anh cua cac de thi trong phan huong dan ngu phap
	public static String Uploadimageguideline(Connection conn, HttpServletRequest request,HttpServletResponse response, int guidelineid)
		throws ServletException, IOException
	{
		String test = "";
		ServletContext context = request.getServletContext();
		response.setContentType("text/html; charset=UTF-8");
		
		final String Address= context.getRealPath("/ImageUpload");
		final int MaxMemorySize = 1024*1024*3; //3MB
		final int MaxRequestSize = 1024*1024*50; //50MB
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		
		if(!isMultipart) {
			test = "Thiếu multipart/form-data trong form";
		}
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		
		// Set factory constraints
		factory.setSizeThreshold(MaxMemorySize);
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
		
		//Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		//Set overall request size constraint
		upload.setSizeMax(MaxRequestSize);
		
		try {
			//Parse the request
			List<FileItem> items = upload.parseRequest(request);
			//Process the upload items
			Iterator<FileItem> iter = items.iterator();
			while(iter.hasNext()) {
				FileItem item = iter.next();
				if(!item.isFormField()) {
					String fileName = item.getName();
					if(fileName!="") {
						String pathFile = Address + File.separator + fileName;
						
						File uploadedFile = new File(pathFile);
						boolean kt = uploadedFile.exists();
					try {	
						if(kt == true) {
							test = "File tồn tại.Yêu cầu chọn file khác";
						}
						else
						{
							item.write(uploadedFile);
							GuidelinemanageDAO.Insertguidelineimage(request, conn, fileName, guidelineid);
							test = "success";
						}
						}
					catch (Exception e) {
						test = e.getMessage();
					}
					}else {
						test = "success";
					}
					
				}
				else {
					test = "Thêm file thất bại";
				}
			}
			} catch (FileUploadException e) {
				test = e.getMessage();
			} 
		return test;
	}
	
	//Xuat id cua bai huong dan ngu phap
	public static int Retrieveidguideline(HttpServletRequest request, Connection conn, Guideline gl) {
		int guidelineid = 0;
		PreparedStatement ptmt = null;
		
		String sql = "select guidelineid from guideline "+" where guidelinename = '"+gl.getGuidelinename()+"';";
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			while(rs.next()) {
				guidelineid = rs.getInt("guidelineid");
			}
			ptmt.close();
			rs.close();
		} catch (SQLException e) {
			request.setAttribute("msgretrieve", e.getMessage());
			//e.printStackTrace();
		}
		return guidelineid;
	}
	//Xuat roleid cua bai huong dan ngu phap
	public static int Retrieveidguidelineroleid(HttpServletRequest request, Connection conn, Guideline gl) {
		int guidelineroleid = 0;
		PreparedStatement ptmt = null;
		
		String sql = "select guidelineroleid from guideline "+" where guidelineid = '"+gl.getGuidelineid()+"';";
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			while(rs.next()) {
				guidelineroleid = rs.getInt("guidelineroleid");
			}
			ptmt.close();
			rs.close();
		} catch (SQLException e) {
			request.setAttribute("msgretrieve", e.getMessage());
			//e.printStackTrace();
		}
		return guidelineroleid;
	}
	// Update ten hinh cua bai huong dan dua theo id cua bai huong dan
	public static void Insertguidelineimage(HttpServletRequest request, Connection conn, String image, int guidelineid) {
		PreparedStatement ptmt = null;
		
		String sql = "update guideline set guidelineimage=? where guidelineid="+guidelineid;
		
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, image);
			ptmt.executeUpdate();
			ptmt.close();
		} catch (SQLException e) {
			request.setAttribute("msgguidelinename", e.getMessage());
		}
	}
	
	//them noi dung cho bai hướng dẫn 
	public static boolean Insertguidelinecontent(HttpServletRequest request, Connection conn, Guideline gl,int guidelineid) {
		PreparedStatement ptmt = null;
		
		String sql ="update guideline set content=? where guidelineid="+guidelineid;
		
		try {
			ptmt = conn.prepareStatement(sql);
			String content = gl.getContent();
			ptmt.setString(1, content);
			int kt = ptmt.executeUpdate();
			if(kt != 0) {
				return true;
			}
			ptmt.close();
		} catch (SQLException e) {
			request.setAttribute("msgguidelinecontent", e.getMessage());
		}
		return false;
	}
	//Dem bang co bao nhieu row
	public static int Countrow(Connection conn, int guidelineroleid, Member mem) {
		int count = 0;
		String sql="";
		if(mem.getRoleid()==1) {
			sql = "select count(*) from guideline where guidelineroleid ="+guidelineroleid;
		}
		if(mem.getRoleid()==3) {
			sql = "select count(*) from guideline where userid="+mem.getUserid()+" AND guidelineroleid ="+guidelineroleid;
		}
		
		
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}
	
	public static void DeleteCmtguideline(Connection conn, int guidelineid) {
		
		
		try {
			String sql = "delete from cmtguideline where guidelineid ="+guidelineid;
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ptmt.executeUpdate();
			ptmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void DeleteGuideline(Connection conn, int guidelineid) {
		try {
			String sql = "delete from guideline where guidelineid ="+guidelineid;
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ptmt.executeUpdate();
			ptmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
