package com.example.test;

public class Player {
    static private int num = 1;
    private int curNum ,score;
    private String name;

    public Player(String name){
        curNum = num++;
        score = 0;
        this.name = name;
    }

    public Player(){
        curNum = num++;
        score = 0;
        name = curNum + " player";
    }

    public void upScore(){
        score++;
    }


    @Override
    public String toString(){
        return name;
    }

    public void setNum(int num){
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setCurNum(int num){
        this.curNum = num;
    }

    public int getCurNum() {
        return curNum;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
