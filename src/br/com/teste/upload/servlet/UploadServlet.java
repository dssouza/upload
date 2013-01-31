package br.com.teste.upload.servlet;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import br.com.teste.upload.bean.Notification;



/**
 * Servlet implementation class UploadServlet
 */
@WebServlet(name = "uploadfile", urlPatterns = { "/uploadfile" })
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public UploadServlet() {

    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession(true);
    	
    	List arquivos = (List) session.getAttribute("arquivos");
    	if (arquivos == null) {
    		arquivos = new ArrayList();
    	}
    	
    	List fileItemsList = null;
    	float filesize = 0;
    	String _fileLink;
    	String _fileName = null;
    	String _uploadDir = getServletContext().getRealPath("/upload/");
    	//Change upload with your directory
    	
    	try {
    	 if (ServletFileUpload.isMultipartContent(request)) {
    	 ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
    	       try {
    	        fileItemsList = servletFileUpload.parseRequest(request);
    	       } catch (FileUploadException ex) {
    	        Logger.getLogger(UploadServlet.class.getName()).log(Level.SEVERE, null, ex);
    	        //Change above line replace FileUploadExample with your file name
    	           }
    	           String optionalFileName = "";
    	           FileItem fileItem = null;

    	           Iterator it = fileItemsList.iterator();
				while (it.hasNext()) {
					FileItem fileItemTemp = (FileItem) it.next();
					if (fileItemTemp.isFormField()) {
						if (fileItemTemp.getFieldName().equals("filename")) {
							optionalFileName = fileItemTemp.getString();
						}
						/*
						 * If you want to pass some other data from JSP page.
						 * You can access then in this way. For each field you
						 * have do create if like below. if
						 * (fileItemTemp.getFieldName
						 * ().equals("Name of other field like:Firstname")) {
						 * String Firstname = fileItemTemp.getString(); }
						 */
					} else {
						fileItem = fileItemTemp;
						
						/*try {
	                           String itemName = fileItemTemp.getName();
	                           itemName = FilenameUtils.getName(itemName);
	                           File savedFile = new File(new File(_uploadDir + "/"), itemName);
	                           fileItemTemp.write(savedFile);
	                           //fileItemTemp.getOutputStream()
	                   } catch (Exception e) {
	                           e.printStackTrace();
	                   }*/
						arquivos.add(fileItem);
					}
				}
					System.out.println("quantidade de arquivos: " + arquivos.size());
					session.setAttribute("arquivos", arquivos);
					
					PrintWriter out = response.getWriter();
					out.println("<html>");
					out.println("<script language=\"Javascript\">");
					out.println("function fechaPopUp() {");
					
					out.println("window.opener.document.getElementById(\"atualizarTabela\").click();");
					out.println("self.close();");
					out.println("}");
					out.println("</script>");
					out.println("<body onload='fechaPopUp()'>");
					out.println("</body>");
					out.println("</html>");
    	            /*String referer = request.getHeader("Referer");
    	            response.sendRedirect(referer);*/
    	            }
    	        } catch (Exception e) {
    	            e.printStackTrace();
    	        }/* finally {
    	            out.close();
    	        }*/
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

}
