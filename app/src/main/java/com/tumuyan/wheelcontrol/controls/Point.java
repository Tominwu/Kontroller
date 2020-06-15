package com.tumuyan.wheelcontrol.controls;

public class Point {
    int x;
    int y;
    int area;

    public Point(){

    }

    public int distance(Point b){
        return (x-b.x)*(x-b.x)+(y-b.y)*(y-b.y);
    }

    public Point(int x,int y){
        this.x=x;
        this.y=y;
    }

    public void set(Point p){
        this.x=p.x;
        this.y=p.y;
    }

    public void set(int x,int y){
        this.x=x;
        this.y=y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
