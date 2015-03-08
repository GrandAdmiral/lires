package com.example.jimmy.test;
import android.provider.Settings.Secure;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by jimmy on 21/01/2015.
 */
public class Session {
    public int score;
    public int currquest,num,diff;
    public int flag;
    public String playerid;
    public ArrayList<Question> questions;
    public String questt,answ1,answ2,answ3,answ4,ca,sa;
    public int[] prize = {100,200,300,500,1000,2000,3000,5000,10000,20000,30000,50000,100000,200000,300000,500000,586509,1000000};
    public ArrayList<String> help;
    public ArrayList<Integer> depository;
    public Session(){};



    Question getQuestion(int dif) {
        System.out.println("Ran for dif: "+dif);
        int i = 0;
        Question q =null;
        while (i == 0) {
            Random randomGenerator = new Random();
            int index = randomGenerator.nextInt(questions.size());
            q = questions.get(index);
            System.out.println("Question Difficulty: " + q.getDifficulty());
            if (dif < 3) {
                if ((q.getDifficulty() <= dif)&&(depository.indexOf(q.getNumber())<0 ))  {
                    i = 1;
                }
            }
                if (dif > 2) {
                    if ((q.getDifficulty() == dif)&&(depository.indexOf(q.getNumber())<0))  {
                        i = 1;
                    }
                }
            }
        depository.add(q.getNumber());
        return q;
    }

    ArrayList<Integer> getPercentages() {
        int a, b, c, d;
        Random f = new Random();
        ArrayList<Integer> ans = new ArrayList<Integer>();
        if (diff < 3) {

            a = getRandom(60, 80);
            b = (100 - a) / 2 + f.nextInt(3);
            c = (100 - a - b) / 2 + f.nextInt(2);
            d = (100 - a - b - c);
            Integer[] wow = new Integer[]{a, b, c, d};
            ans.addAll(Arrays.asList(wow));
            return ans;
        } else if (diff < 4) {
            a = getRandom(50, 60);
            b = (100 - a) / 2 + f.nextInt(3);
            c = (100 - a - b) / 2 + f.nextInt(2);
            d = (100 - a - b - c);
            Integer[] wow = new Integer[]{a, b, c, d};
            ans.addAll(Arrays.asList(wow));
            return ans;
        } else if (diff < 5) {
            a = getRandom(30,50);
            b = getRandom(30,50)-f.nextInt(5);
            c = (100 - a - b) / 2 + f.nextInt(2);
            d = (100 - a - b - c);
            Integer[] wow = new Integer[]{a, b, c, d};
            ans.addAll(Arrays.asList(wow));
            return ans;
        }
        else {
            a = getRandom(25, 40);
            b = getRandom(25, 40);
            c = (100 - a - b) % 2 + f.nextInt(2);
            d = (100 - a - b - c);
            Integer[] wow = new Integer[]{a, b, c, d};
            ans.addAll(Arrays.asList(wow));
            return ans;
        }
    }



    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCurrquest() {
        return currquest;
    }

    public void setCurrquest(int currquest) {
        this.currquest = currquest;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getPlayerid() {
        return playerid;
    }

    public void setPlayerid(String playerid) {
        this.playerid = playerid;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public String getQuestt() {
        return questt;
    }

    public void setQuestt(String questt) {
        this.questt = questt;
    }

    public String getAnsw1() {
        return answ1;
    }

    public void setAnsw1(String answ1) {
        this.answ1 = answ1;
    }

    public String getAnsw2() {
        return answ2;
    }

    public void setAnsw2(String answ2) {
        this.answ2 = answ2;
    }

    public String getAnsw3() {
        return answ3;
    }

    public void setAnsw3(String answ3) {
        this.answ3 = answ3;
    }

    public String getAnsw4() {
        return answ4;
    }

    public void setAnsw4(String answ4) {
        this.answ4 = answ4;
    }

    public String getCa() {
        return ca;
    }

    public void setCa(String ca) {
        this.ca = ca;
    }

    public int[] getPrize() {
        return prize;
    }

    public void setPrize(int[] prize) {
        this.prize = prize;
    }

    public String getSa() {
        return sa;
    }

    public String getAnswer(int i){
        if (i==1) return answ1;
        if (i==2) return answ2;
        if (i==3) return answ3;
        if (i==4)  {
            return answ4;
        }
        else {
            return answ1;
        }
    }

    public void setSa(String sa) {
        this.sa = sa;
    }

    void setParams(Question q){
        questt=q.getText();
        answ1=q.getAnswer1();

        answ2=q.getAnswer2();
        answ3=q.getAnswer3();
        answ4=q.getAnswer4();
        ca=q.getCorrect();
        sa=q.getSecond();
        diff=q.getDifficulty();
        num=q.getNumber();

    }

    public ArrayList<String> getHelp() {
        return help;
    }

    public void setHelp(ArrayList<String> help) {
        this.help = help;
    }

   public void start(){
    depository=new ArrayList<Integer>();
    score=0;
    currquest=1;
    Question q= getQuestion(2);
    setParams(q);
    flag=0;


   }
void advance(String response){
    if (response.equals(ca)) {
        currquest++;
        score = prize[currquest - 2];
        Question q=null;
        if (currquest < 8) {q=getQuestion(2); System.out.println("Get question(2)");} else
        if (currquest <12) {q=getQuestion(3); System.out.println("Get question(3)");} else
        if (currquest<15 ) {q=getQuestion(4); System.out.println("Get question(4)");} else
        if (currquest>14) {q=getQuestion(5); System.out.println("Get question(5)");}
        setParams(q);
    }
    else {
        flag=1;
    }

}

public int getRandom(int a, int b) {
    Random r = new Random();
    int Low = a;
    int High = b;
    int R = r.nextInt(High - Low) + Low;
    return R;
}
}
