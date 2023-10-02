package com.ci.pojo.vo;


/**
 * @author Cikian
 * @version 1.0
 * @description TODO
 * @date 2023/10/2 17:49
 */


public class CateView {

    private String value;
    private String text;

    public CateView() {
    }

    @Override
    public String toString() {
        return "CateView{" +
                "value='" + value + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    public CateView(String value, String text) {
        this.value = value;
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
