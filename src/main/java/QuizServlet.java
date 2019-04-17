import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


@WebServlet("/")
public class QuizServlet extends HttpServlet {



    protected void doPost(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        int scoreC = 0;
        int index = 0;
        String[] strArr;
        List<String> indexArr = new ArrayList<>();

        for (Cookie cookie :
                request.getCookies()) {
            if (cookie.getName().equals("score")) {
                 scoreC = Integer.valueOf(cookie.getValue());
            }

            if(cookie.getName().equals("index")) {
                index = Integer.valueOf(cookie.getValue());
            }

            if(cookie.getName().equals("indexArr")){
                strArr = (cookie.getValue().split(","));

                indexArr = new ArrayList<>(Arrays.asList(strArr));

            }

        }
        int quizNum = Quiz.getNum();
        String getString = indexArr.toString();
        if(indexArr.size()-1 <= Quiz.getQuestions().length) {
            while (indexArr.contains(String.valueOf(quizNum))) {
                quizNum = Quiz.getNum();
            }

            indexArr.add(String.valueOf(quizNum));

            if (Integer.valueOf(request.getParameter("ans")) == Quiz.getAnswers()[index]) {
                scoreC++;
            }

            response.addCookie(new Cookie("score",scoreC+""));
            response.addCookie(new Cookie("index", quizNum+""));
            response.addCookie(new Cookie("indexArr", indexArr.toString() +""));

            out.println("<h1>The Number Quiz</h1>" +
                    "Your Current Score is :  " + scoreC +
                    "</br> Guess the next number in the Sequence.</br>" + Quiz.getQuestions(quizNum) +
                    "<form action='quiz' method='post'>" +
                    "<input type='text' name='ans' >" +
                    "<input type='submit' name='next' value='next' /> </br>" +
                    "</form>"+
                    "<p>" + indexArr.size() +"</p>" +
                    "<p>" + quizNum +"</p>" +
                    "<p>"+getString+"</p>"+
                    "<p>"+indexArr.toString()+"</p>"

            );
        }else {
            out.println("You're done");


        }




    }
    protected void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();


        if(request.getAttribute("quiz") == null){
            request.setAttribute("quiz" , new Quiz());
            String quizQ = Quiz.getQuestions(Quiz.getNum());
            int quizNum = Quiz.getNum();


            Cookie scoreC = new Cookie("score",Quiz.getScore()+"");
            Cookie index = new Cookie("index", quizNum+"");
            Cookie indexArr = new Cookie("indexArr",  quizNum+"");


            response.addCookie(scoreC);
            response.addCookie(index);
            response.addCookie(indexArr);

            out.println("<h1>The Number Quiz</h1>" +
                    "Your Current Score is : 0 "  +
                    "</br> Guess the next number in the Sequence.</br>"+ quizQ+
                    "<form action='quiz' method='post'>"+
                    "<input type='text' name='ans' >"+
                    "<input type='submit' name='next' value='next' /> </br>"+
                    "</form>"
            );



        }




    }
}