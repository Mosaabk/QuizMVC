package com.mosaabk.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Quiz {

    private static String[] questions= {
        "3, 1, 4, 1, 5",
        "1, 1, 2, 3, 5",
        "1, 4, 9, 16 ,25",
        "2, 3, 5, 7, 11",
        "1, 2, 4, 8, 16"
    };


    public  int score = 0;
    private static List<Integer> indexArr = new ArrayList<>();

    private static int[] answers = {9, 8, 36, 13, 32};

    public String getQuestions(int i) {
        return questions[i];
    }

    public  String[] getQuestions() {
        return questions;
    }

    public  int getScore() {
        return score;
    }

    public  List<Integer> getIndexArr() {
        return indexArr;
    }

    public  int[] getAnswers() {
        return answers;
    }

    public  int getNum(){

        if(indexArr.size()-1 == 4) {
            indexArr.clear();
            return -1;
        }

        Random rand = new Random();

        int qIndex = rand.nextInt(questions.length);

        while (indexArr.contains(qIndex)){
            qIndex = rand.nextInt(questions.length);

        }

        indexArr.add(qIndex);
        return qIndex;

    }
}
