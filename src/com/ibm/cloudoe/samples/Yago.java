package com.ibm.cloudoe.samples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Servlet implementation class Yago
 */
@WebServlet("/Yago")
public class Yago extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Yago() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String input= request.getParameter("Enteredtext");
		System.out.println("Entered text is : " +input);
		
	try{
		JSONParser parser = new JSONParser();
		String data;
		data = "text=" +input;

        HttpURLConnection con = (HttpURLConnection) new URL("https://gate.d5.mpi-inf.mpg.de/aida/service/disambiguate").openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.getOutputStream().write(data.getBytes("UTF-8"));

        // Get the inputstream
        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

        // .. and print it
        String tmp;
        
        while((tmp = reader.readLine()) != null) {
            System.out.println("temp="+tmp);
            
            Object obj = parser.parse(tmp);
            JSONObject jsonObject = (JSONObject) obj;
            String originalFileName = (String) jsonObject.get("originalFileName");
            String originalText = (String) jsonObject.get("originalText");
            String overallTime = (String) jsonObject.get("overallTime");
            JSONArray mentions = (JSONArray) jsonObject.get("mentions");
          
            
            for(int i=0;i<mentions.size();i++)
            {
            //	System.out.println(mentions.get(i));
            	Object obj2 = parser.parse(mentions.get(i).toString());
                JSONObject jsonObject2 = (JSONObject) obj2;
                JSONArray allEntities = (JSONArray) jsonObject2.get("allEntities");
                
                for(int j=0;j<allEntities.size();j++)
                {
                	
                	System.out.println("in for last");
                	Object obj3 = parser.parse(allEntities.get(j).toString());
                	JSONObject jsonObject3 = (JSONObject) obj3;
                	String kdId = (String) jsonObject3.get("kbIdentifier");
                	String disambiguationScore = (String) jsonObject3.get("disambiguationScore");
                	System.out.println("KbID: "+kdId + "===="+"score: "+disambiguationScore);
                	request.setAttribute("kdId",kdId);
                	
                }
                
            }
            
            
            }
        
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    } catch (MalformedURLException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	RequestDispatcher dispatcher =(RequestDispatcher) request.getRequestDispatcher("/Home.jsp");
	dispatcher.forward(request, response);
}

		
		
		
}


