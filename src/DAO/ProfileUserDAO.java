package DAO;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import BEAN.Member;

public class ProfileUserDAO {
	public static boolean UpdateMember(HttpServletRequest request, Connection conn, Member mb) {
		PreparedStatement ptmt = null;
		
		String sql ="update user set password=?   where userid="+mb.getUserid()+"";
		
		try {
			ptmt = conn.prepareStatement(sql);
			String password = ProfileUserDAO.md5(mb.getPassword());
			ptmt.setString(1, password);
			int kt = ptmt.executeUpdate();
			if(kt != 0) {
				return true;
			}
			ptmt.close();
		} catch (SQLException e) {
			request.setAttribute("msgchangePass", e.getMessage());
		}
		return false;
	}
	public static String md5(String msg) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(msg.getBytes());
            byte byteData[] = md.digest();
            //convert the byte to hex format method 1
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            return  sb.toString();
        } catch (Exception ex) {
            return "";
        }
    }
	
	public static String Uploadimageuser(Connection conn, HttpServletRequest request,HttpServletResponse response, Member mem)
			throws ServletException, IOException
		{
			String test = "";
			ServletContext context = request.getServletContext();
			response.setContentType("text/html; charset=UTF-8");
			
			final String Address= context.getRealPath("/Avatar");
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
								ProfileUserDAO.Updatenameimage(request, conn, fileName, mem);
								test = "success";
								HttpSession session = request.getSession(true);
								session.setAttribute("sessionimage", fileName);
							}
							}
						catch (Exception e) {
							test = e.getMessage();
						}
						}else {
							test="success";
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
	
	
	public static void Updatenameimage(HttpServletRequest request, Connection conn, String image, Member mem) {
		PreparedStatement ptmt = null;
		
		String sql = "update user set userimage=? where userid="+mem.getUserid();
		
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, image);
			ptmt.executeUpdate();
			ptmt.close();
		} catch (SQLException e) {
			request.setAttribute("msgimagename", e.getMessage());
		}
	}
}
