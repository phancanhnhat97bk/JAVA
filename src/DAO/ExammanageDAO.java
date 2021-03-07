package DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import BEAN.Examination;
import BEAN.Examinationquestion;
import BEAN.Member;

public class ExammanageDAO {
	
	
	public static List<Examination> Displaysexammanage(HttpServletRequest request,int start, int count,Connection conn, Member mem) {
		
		List<Examination> list = new ArrayList<Examination>();
		PreparedStatement ptmt = null;
		String sql ="";
		if(mem.getRoleid()==3) {
			sql = "select * from examination where userid ="+mem.getUserid()+" limit "+(start-1)+","+count+"";
		}
		if(mem.getRoleid()==1) {
		sql = "select * from examination limit "+(start-1)+","+count+"";
		}
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			
			if(rs.isBeforeFirst()) {
				while (rs.next()) {
					Examination ex = new Examination();
					int examinationid = rs.getInt("examinationid");
					String examinationname = rs.getString("examinationname");
					String examinationimage = rs.getString("examinationimage");
					int checkquestion = rs.getInt("checkquestion");
					int time = rs.getInt("time");
					
					ex.setExaminationid(examinationid);
					ex.setExaminationname(examinationname);
					ex.setExaminationimage(examinationimage);
					ex.setCheckquestion(checkquestion);
					ex.setTime(time);
					
					list.add(ex);
				}
			}else {
				request.setAttribute("msglistexammanage","Không có bài thi nào" );
			}
			
			
			
			
		} catch (SQLException e) {
			request.setAttribute("msglistexammanage",e.getMessage() );
		}
		return list;
	}
	
	

	
	//Dem bang co bao nhieu row
	public static int Countrow(Connection conn, Member mem) {
		int count = 0;
		String sql ="";
		if(mem.getRoleid()==3) {
		sql = "select count(*) from examination where userid = "+mem.getUserid()+"";
		}
		
		if(mem.getRoleid()==1) {
			sql = "select count(*) from examination";
			}
		
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
	public static boolean Insertexamname(HttpServletRequest request, Connection conn, Examination ex) {
		PreparedStatement ptmt = null;
		
		String sql = "insert into examination (examinationname,time,userid) value(?,?,?)";
		
		try {
			ptmt = conn.prepareStatement(sql);
			String examinationname = ex.getExaminationname();
			int time = ex.getTime();
			int userid = ex.getUserid();
			ptmt.setString(1, examinationname);
			ptmt.setInt(2, time);
			ptmt.setInt(3, userid);
			int kt = ptmt.executeUpdate();
			if(kt != 0) {
				return true;
			}
			ptmt.close();
		} catch (SQLException e) {
			request.setAttribute("msgexamname", e.getMessage());
		}
		return false;
	}
	
	
	
	
	public static int Retrieveidexam(HttpServletRequest request, Connection conn, Examination ex) {
		int examinationid = 0;
		PreparedStatement ptmt = null;
		
		String sql = "select examinationid from examination "+" where userid="+ex.getUserid()+" AND examinationname = '"+ex.getExaminationname()+"';";
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			while(rs.next()) {
				examinationid = rs.getInt("examinationid");
			}
			ptmt.close();
			rs.close();
		} catch (SQLException e) {
			request.setAttribute("msgretrieveexam", e.getMessage());
			//e.printStackTrace();
		}
		return examinationid;
	}
	
	
	
	public static void Updatenameimage(HttpServletRequest request, Connection conn, String image, int examinationid) {
		PreparedStatement ptmt = null;
		
		String sql = "update examination set examinationimage = ? where examinationid="+examinationid;
		
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, image);
			ptmt.executeUpdate();
			ptmt.close();
		} catch (SQLException e) {
			request.setAttribute("msgexaminationname", e.getMessage());
		}
	}
	
	public static String Uploadimageexam(Connection conn, HttpServletRequest request,HttpServletResponse response, int examinationid)
			throws ServletException, IOException
		{
			String test = "";
			ServletContext context = request.getServletContext();
			response.setContentType("text/html; charset=UTF-8");
			
			final String Address= context.getRealPath("/ImageAudioExam");
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
								ExammanageDAO.Updatenameimage(request, conn, fileName, examinationid);
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
	
	
	public static void UploadquestionfromExcel(Connection conn, HttpServletRequest request,HttpServletResponse response, String address, int examinationid)
			throws ServletException, IOException{
		
		try {
			FileInputStream inp;
			inp = new FileInputStream(address);
			HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
			
			Sheet sheet = wb.getSheetAt(0);
			
			// Get all rows
	        Iterator<Row> iterator = sheet.iterator();
	        while (iterator.hasNext()) {
	            Row nextRow = iterator.next();
	            if (nextRow.getRowNum() == 0) {
	                // Ignore header
	                continue;
	            }
	         // Get all cells
	            Iterator<Cell> cellIterator = nextRow.cellIterator();
	            Examinationquestion ex = new Examinationquestion();
	            while (cellIterator.hasNext()) {
	            	Cell cell = cellIterator.next();
	            	Object cellValue = getCellValue(cell);
	            	if (cellValue == null || cellValue.toString().isEmpty()) {
	                    continue;
	                }
	                int columnIndex = cell.getColumnIndex();
	                switch (columnIndex){
	                case 0: ex.setNum((int) cell.getNumericCellValue());  break;
	                case 1: ex.setImagequestion(cell.getStringCellValue()); break;
	                case 2: ex.setAudiogg(cell.getStringCellValue()); break;
	                case 3: ex.setAudiomp3(cell.getStringCellValue()); break;
	                case 4: ex.setParagraph(cell.getStringCellValue()); break;
	                case 5: ex.setQuestion(cell.getStringCellValue()); break;
	                case 6: ex.setOption1(cell.getStringCellValue()); break;
	                case 7: ex.setOption2(cell.getStringCellValue()); break;
	                case 8: ex.setOption3(cell.getStringCellValue()); break;
	                case 9: ex.setOption4(cell.getStringCellValue()); break;
	                case 10: ex.setCorrectanswer(cell.getStringCellValue()); break;
	                default:
	                    break;
	                }
	            }
	            
	            ex.setExaminationid(examinationid);
				ExammanageDAO.UploadquestiontoMysql(request, ex, conn);
	        }
			wb.close();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}catch (NullPointerException e) {
			// TODO: handle exception
		}
		
	}
	
	@SuppressWarnings({ "deprecation", "unused" })
	private static Object getCellValue(Cell cell) {
        CellType cellType = cell.getCellTypeEnum();
        Object cellValue = null;
        switch (cellType) {
        case BOOLEAN:
            cellValue = cell.getBooleanCellValue();
            break;
        case FORMULA:
            Workbook workbook = cell.getSheet().getWorkbook();
            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
            cellValue = evaluator.evaluate(cell).getNumberValue();
            break;
        case NUMERIC:
            cellValue = cell.getNumericCellValue();
            break;
        case STRING:
            cellValue = cell.getStringCellValue();
            break;
        case _NONE:
        case BLANK:
        case ERROR:
            break;
        default:
            break;
        }
 
        return cellValue;
    }
	
	public static void UploadquestiontoMysql(HttpServletRequest request, Examinationquestion exam, Connection conn)
			throws ServletException, IOException{
		String sql = "insert into examinationquestion (num,imagequestion,audiogg,audiomp3,paragraph,question,"
			+"option1,option2,option3,option4,correctanswer,examinationid) value (?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ptmt.setInt(1, exam.getNum());
			ptmt.setString(2, exam.getImagequestion());
			ptmt.setString(3, exam.getAudiogg());
			ptmt.setString(4, exam.getAudiomp3());
			ptmt.setString(5, exam.getParagraph());
			ptmt.setString(6, exam.getQuestion());
			ptmt.setString(7, exam.getOption1());
			ptmt.setString(8, exam.getOption2());
			ptmt.setString(9, exam.getOption3());
			ptmt.setString(10, exam.getOption4());
			ptmt.setString(11, exam.getCorrectanswer());
			ptmt.setInt(12, exam.getExaminationid());
			
			ptmt.executeUpdate();
			ptmt.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static String Uploadquestionexam(Connection conn, HttpServletRequest request,HttpServletResponse response, int examinationid)
			throws ServletException, IOException
		{
			String test = "";
			ServletContext context = request.getServletContext();
			response.setContentType("text/html; charset=UTF-8");
			
			final String Address= context.getRealPath("/Filedethi");
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
								try {
								ExammanageDAO.UploadquestionfromExcel(conn, request, response, pathFile, examinationid);
								}catch(NullPointerException e) {
									test =e.getMessage();
								}
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
	
	public static void Checkquestionexam(HttpServletRequest request, Connection conn, int checkquestion, int examinationid) {
		PreparedStatement ptmt = null;
		
		String sql = "update examination set checkquestion=? where examinationid="+examinationid;
		
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setInt(1, checkquestion);
			ptmt.executeUpdate();
			ptmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static String UploadAudioImage(Connection conn, HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException
		{
			String test = "";
			ServletContext context = request.getServletContext();
			response.setContentType("text/html; charset=UTF-8");
			
			final String Address= context.getRealPath("/ImageAudioExam");
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
								test = "success";
							}
							}
						catch (Exception e) {
							test = e.getMessage();
						}
						}else
						{
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
	
public static void DeleteExaminationquestion(Connection conn, int examinationid) {
		
		
		try {
			String sql = "delete from examinationquestion where examinationid ="+examinationid;
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ptmt.executeUpdate();
			ptmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

public static void DeleteExamination(Connection conn, int examinationid) {
	
	
	try {
		String sql = "delete from examination where examinationid ="+examinationid;
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.executeUpdate();
		ptmt.close();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

public static void DeleteResult(Connection conn, int examinationid) {
	
	
	try {
		String sql = "delete from result where examinationid ="+examinationid;
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.executeUpdate();
		ptmt.close();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}


public static List<Examinationquestion> Displayquestions(HttpServletRequest request,Connection conn,int examinationid) {
	List<Examinationquestion> list = new ArrayList<Examinationquestion>();
	PreparedStatement ptmt = null;
	String sql = "select * from examinationquestion where examinationid ="+examinationid;
	
	try {
		ptmt = conn.prepareStatement(sql);
		ResultSet rs = ptmt.executeQuery();
		
		if(rs.isBeforeFirst()) {
			while (rs.next()) {
				Examinationquestion exq = new Examinationquestion();
				int examinationquestionid = rs.getInt("examinationquestionid"); 
				int num = rs.getInt("num");
				String imagequestion = rs.getString("imagequestion");
				String audiogg  = rs.getString("audiogg");
				String audiomp3 = rs.getString("audiomp3");
				String paragraph = rs.getString("paragraph");
				String question = rs.getString("question");
				String option1 = rs.getString("option1");
				String option2 = rs.getString("option2");
				String option3 = rs.getString("option3");
				String option4 = rs.getString("option4");
				String correctanswer = rs.getString("correctanswer");
				
				exq.setExaminationquestionid(examinationquestionid);
				exq.setNum(num);
				exq.setImagequestion(imagequestion);
				exq.setAudiogg(audiogg);
				exq.setAudiomp3(audiomp3);
				exq.setParagraph(paragraph);
				exq.setQuestion(question);
				exq.setOption1(option1);
				exq.setOption2(option2);
				exq.setOption3(option3);
				exq.setOption4(option4);
				exq.setCorrectanswer(correctanswer);
				
		
				
				list.add(exq);
			}
		}else {
			request.setAttribute("msgquiz","Không có câu hỏi nào" );
		}
		
		
	} catch (SQLException e) {
		request.setAttribute("msglistexammanage",e.getMessage() );
	}
	return list;
}


	
}
