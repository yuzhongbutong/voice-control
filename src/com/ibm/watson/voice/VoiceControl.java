package com.ibm.watson.voice;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

/**
 * Servlet implementation class Talk
 */
@WebServlet("/Car")
public class VoiceControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final HttpApplicationDeviceEventPublish publish = new HttpApplicationDeviceEventPublish();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VoiceControl() {
        super();
    }

	/**
	 * @throws IOException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String command = request.getParameter("command");
		JsonObject data = new JsonObject();
		data.addProperty("id", "100");
		data.addProperty("data", command);
		publish.publishEvent(data);
	}
}
