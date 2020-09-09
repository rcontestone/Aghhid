package com.rcons.fcallbacks.QST_Helper;

import java.util.ArrayList;

public class QuestionModel {

    private String QuestionNumber;
    private String QuestionName;
    private String QuestionType;
    private String answerGiven = "";
    private int Answer;
    ArrayList<String> OptionName;

    public String getQuestionNumber() {
        return QuestionNumber;
    }

    public void setQuestionNumber(String questionNumber) {
        QuestionNumber = questionNumber;
    }

    public String getQuestionName() {
        return QuestionName;
    }

    public void setQuestionName(String questionName) {
        QuestionName = questionName;
    }

    public String getQuestionType() {
        return QuestionType;
    }

    public void setQuestionType(String questionType) {
        QuestionType = questionType;
    }

    public int getAnswer() {
        return Answer;
    }

    public void setAnswer(int answer) {
        Answer = answer;
    }

    public ArrayList<String> getOptionName() {
        return OptionName;
    }

    public void setOptionName(ArrayList<String> optionName) {
        OptionName = optionName;
    }

    public String getAnswerGiven() {
        return answerGiven;
    }

    public void setAnswerGiven(String answerGiven) {
        this.answerGiven = answerGiven;
    }
}
