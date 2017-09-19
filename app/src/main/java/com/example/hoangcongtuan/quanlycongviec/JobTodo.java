package com.example.hoangcongtuan.quanlycongviec;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hoangcongtuan on 9/19/17.
 */

public class JobTodo {
    private String congViec;
    private String noiDung;
    private Date dateFinish;
    private Date timeFinsh;

    public JobTodo(String congViec, String noiDung, Date dateFinish, Date timeFinsh) {
        this.congViec = congViec;
        this.noiDung = noiDung;
        this.dateFinish = dateFinish;
        this.timeFinsh = timeFinsh;
    }

    public String toString() {
        return congViec + " - " + getDateFinishStr() + "-" + getTimeFinishStr();
    }

    public String getDateFinishStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
        return sdf.format(dateFinish);
    }

    public String getTimeFinishStr() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
        return simpleDateFormat.format(timeFinsh);
    }


    public String getCongViec() {
        return congViec;
    }

    public void setCongViec(String congViec) {
        this.congViec = congViec;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public Date getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(Date dateFinish) {
        this.dateFinish = dateFinish;
    }

    public Date getTimeFinsh() {
        return timeFinsh;
    }

    public void setTimeFinsh(Date timeFinsh) {
        this.timeFinsh = timeFinsh;
    }
}
