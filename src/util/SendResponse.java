package util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class SendResponse {
	private final static String CONTENT_TYPE = "text/html; charset=UTF-8";
	
	public static void writeText(HttpServletResponse response, String outText) throws IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();	// System.out.println("outText: " + outText);
		out.print(outText);
	}
}
