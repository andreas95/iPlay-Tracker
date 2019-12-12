package main.java.com.andreas.tracker.iplay.model;

import java.util.List;

/**
 * Created by Andreas on 7/5/16.
 */
public class Poll {

    private String question;
    private List<String> answers;
    private int no_votes;
    private List<Integer> answers_votes;

    public Poll() {}
    public Poll(String question, List answers) {
        this.question=question;
        this.answers=answers;
    }

    public String getQuestion() {return question;}
    public List<String> getAnswers() {return answers;}
    public int getNo_votes() {return no_votes;}
    public List<Integer> getAnswers_votes() {return answers_votes;}
    public void setQuestion(String question) {
        this.question=question;
    }
    public void setAnswers(List<String> answers) {
        this.answers=answers;
    }
    public void setNo_votes(int no_votes) {this.no_votes=no_votes;}
    public void setAnswers_votes(List<Integer> answers_votes) {this.answers_votes=answers_votes;}
}
