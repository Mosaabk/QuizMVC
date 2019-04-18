package com.mosaabk.controller;

import com.mosaabk.model.Quiz;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/")
public class QuizServlet extends HttpServlet {



    protected void doPost(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {

        HttpSession session=request.getSession();
        String index="";
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        for (Cookie cookie :
                request.getCookies()) {
            if (cookie.getName().equals("index")) {
                 index= cookie.getValue();
            }
        }

        Quiz quiz = (Quiz) session.getAttribute("quiz");

        if(quiz.getIndexArr().size()-1 != quiz.getQuestions().length){
            int quizNum = quiz.getNum();
            out.println(index);

            if (Integer.valueOf(request.getParameter("ans")) == quiz.getAnswers()[Integer.valueOf(index)]) {
                quiz.score++;
            }


            response.addCookie( new Cookie("index", quizNum+""));
            session.setAttribute("quiz" , quiz);

            if(quizNum != -1){


                out.println("<h1>The Number Quiz</h1>" +
                        "Your Current Score is :  " + quiz.score +
                        "</br> Guess the next number in the Sequence.</br>" + quiz.getQuestions(quizNum) +
                        "<form action='quiz' method='post'>" +
                        "<input type='text' name='ans' >" +
                        "<input type='submit' name='next' value='next' /> </br>" +
                        "</form>"+
                        "<p>" + quizNum +"</p>"
                );
            } else {

                out.println("<h1>The Number Quiz</h1>" +
                        "Your Current Score is :  " + quiz.score +
                        "<p> You have completed the number quiz with score of " + quiz.score + " out of  5"

                );

            }

        }




    }
    protected void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session=request.getSession();

        if(request.getAttribute("quiz") == null){

            Quiz q = new Quiz();
            session.setAttribute("quiz" , q);
            int quizNum = q.getNum();
            String quizQ = q.getQuestions(quizNum);



            Cookie index = new Cookie("index", quizNum+"");

            response.addCookie(index);

            out.println("<h1>The Number com.mosaabk.model.Quiz</h1>" +
                    "Your Current Score is : 0 "  +
                    "</br> Guess the next number in the Sequence.</br>"+ quizQ+
                    "<form action='quiz' method='post'>"+
                    "<input type='text' name='ans' >"+
                    "<input type='submit' name='next' value='next' /> </br>"+
                    "</form>"+
                    "<p>" + quizNum +"</p>"
            );



        }




    }
}