package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.BMI;

/**
 * Servlet implementation class GetData
 */
public class GetData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String heightSt = request.getParameter("height");
		String weightSt = request.getParameter("weight");
		heightSt = heightSt == null ? "1": heightSt;
		weightSt = weightSt == null ? "0": weightSt;
		double heightCm = Double.parseDouble(heightSt);
		double weightKg = Double.parseDouble(weightSt);
		double bmi = weightKg/Math.pow((heightCm/100),2);
		String fileName;
		if(bmi<18.5) {
			fileName = "img1.jpg";
		}else if(bmi< 25) {
			fileName = "img2.jpg";
		}else if(bmi<30) {
			fileName = "img3.jpg";
		}else if(bmi<35) {
			fileName = "img4.jpg";
		}else if(bmi<40){
			fileName = "img5.jpg";
		}else {
			fileName = "img6.jpg";
		}
		String imgPath = "http://localhost:8080/bmiapi/images/"+fileName;
		BMI b = new BMI();
		b.setHeight(heightCm);
		b.setWeight(weightKg);
		b.setBmi(Math.round(bmi*100)/100d);
		b.setImgPath(imgPath);
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		out.print(gson.toJson(b));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
