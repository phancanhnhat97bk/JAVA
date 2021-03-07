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

import BEAN.Exercise;
import BEAN.Exercisequestion;
import BEAN.Member;

public class ExercisemanageDAO {
public static List<Exercise> Displaysexercisemanage(HttpServletRequest request,int start, int count,Connection conn, int typ, Member mem) {
		
		List<Exercise> list = new ArrayList<Exercise>();
		PreparedStatement ptmt = null;
		String sql ="";
		if(mem.getRoleid()==3) {
			sql = "select * from exercise where userid ="+mem.getUserid()+" AND type="+typ+" limit "+(start-1)+","+count+"";
		}
		if(mem.getRoleid()==1) {
			sql = "select * from exercise where type="+typ+" limit "+(start-1)+","+count+"";
		}
		
		try {
			ptmt = conn.prepareStatement(sql);
			ResultSet rs = ptmt.executeQuery();
			
			if(rs.isBeforeFirst()) {
				while (rs.next()) {
					Exercise exercise = new Exercise();
					int exerciseid = rs.getInt("exerciseid");
					String name = rs.getString("name");
					int type = rs.getInt("type");
					int numquestion = rs.getInt("numquestion");
					
					exercise.setExerciseid(exerciseid);
					exercise.setName(name);
					exercise.setType(type);
					exercise.setNumquestion(numquestion);
					list.add(exercise);
				}
			}else {
				request.setAttribute("msglistexercisemanage","Không có bài tập nào" );
			}
			
			
			
			
		} catch (SQLException e) {
			request.setAttribute("msglistexercisemanage",e.getMessage() );
		}
		return list;
	}

	
	public static int Countrow(Connection conn, int type, Member mem) {
		int count = 0;
		String sql ="";
		if(mem.getRoleid()==3) {
			sql = "select count(*) from exercise where userid ="+mem.getUserid()+" AND type="+type;
		}
		if(mem.getRoleid()==1) {
			
			sql = "select count(*) from exercise where type="+type;
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
	
	public static boolean Insertexercisename(HttpServletRequest request, Connection conn, Exercise exercise) {
		PreparedStatement ptmt = null;
		
		String sql = "insert into exercise (name,type,userid,numquestion) value(?,?,?,?)";
		
		try {
			ptmt = conn.prepareStatement(sql);
			String name = exercise.getName();
			int type = exercise.getType();
			int userid = exercise.getUserid();
			int numquestion = exercise.getNumquestion();
			ptmt.setString(1, name);
			ptmt.setInt(2, type);
			ptmt.setInt(3, userid);
			ptmt.setInt(4, numquestion);
			int kt = ptmt.executeUpdate();
			if(kt != 0) {
				return true;
			}
			ptmt.close();
		} catch (SQLException e) {
			request.setAttribute("msgexercisename", e.getMessage());
		}
		return false;
	}
	
	
	public static String Uploadquestionexercise(Connection conn, HttpServletRequest request,HttpServletResponse response, int exerciseid, int type)
			throws ServletException, IOException
		{
			String test = "";
			ServletContext context = request.getServletContext();
			response.setContentType("text/html; charset=UTF-8");
			
			final String Address= context.getRealPath("/Filebaitap");
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
								ExercisemanageDAO.UploadquestionfromExcel(conn, request, response, pathFile, exerciseid,type);
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
							test ="success";
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
	
	
	
	public static void UploadquestionfromExcel(Connection conn, HttpServletRequest request,HttpServletResponse response, String address, int exerciseid, int type)
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
	            Exercisequestion ex = new Exercisequestion();
	            while (cellIterator.hasNext()) {
	            	Cell cell = cellIterator.next();
	            	Object cellValue = getCellValue(cell);
	            	if (cellValue == null || cellValue.toString().isEmpty()) {
	                    continue;
	                }
	                int columnIndex = cell.getColumnIndex();
	                switch (columnIndex){
	                case 0: ex.setNum((int) cell.getNumericCellValue());  break;
	                case 1: ex.setImage(cell.getStringCellValue()); break;
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
	            
	            ex.setExerciseid(exerciseid);
	            ex.setType(type);
				ExercisemanageDAO.UploadquestiontoMysql(request, ex, conn);
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
	
	
	public static void UploadquestiontoMysql(HttpServletRequest request, Exercisequestion exam, Connection conn)
			throws ServletException, IOException{
		String sql = "insert into exercisequestion (num,image,audiogg,audiomp3,paragraph,question,"
			+"option1,option2,option3,option4,correctanswer,exerciseid,type) value (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ptmt.setInt(1, exam.getNum());
			ptmt.setString(2, exam.getImage());
			ptmt.setString(3, exam.getAudiogg());
			ptmt.setString(4, exam.getAudiomp3());
			ptmt.setString(5, exam.getParagraph());
			ptmt.setString(6, exam.getQuestion());
			ptmt.setString(7, exam.getOption1());
			ptmt.setString(8, exam.getOption2());
			ptmt.setString(9, exam.getOption3());
			ptmt.setString(10, exam.getOption4());
			ptmt.setString(11, exam.getCorrectanswer());
			ptmt.setInt(12, exam.getExerciseid());
			ptmt.setInt(13, exam.getType());
			ptmt.executeUpdate();
			ptmt.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static String UploadAudioImage(Connection conn, HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException
		{
			String test = "";
			ServletContext context = request.getServletContext();
			response.setContentType("text/html; charset=UTF-8");
			
			final String Address= context.getRealPath("/ImageAudioExercise");
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
	
	
	public static void DeleteExercisequestion(Connection conn, int exerciseid) {
			
			
			try {
				String sql = "delete from exercisequestion where exerciseid ="+exerciseid;
				PreparedStatement ptmt = conn.prepareStatement(sql);
				ptmt.executeUpdate();
				ptmt.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
	public static void DeleteExercise(Connection conn, int exerciseid) {
		
		
		try {
			String sql = "delete from exercise where exerciseid ="+exerciseid;
			PreparedStatement ptmt = conn.prepareStatement(sql);
			ptmt.executeUpdate();
			ptmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
