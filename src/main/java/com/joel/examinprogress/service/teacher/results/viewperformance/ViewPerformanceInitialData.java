/*
    =======================================================================================
    This code is part of Exam In Progress.

    Exam In Progress is an online exam software owned by Joel Mumo.

    The Exam In Progress software has a proprietary license. Please look at or request
    exam_in_progress_license.txt for further details.

    Copyright (C) 2020 JMumo

    Email:  jaymumo6@gmail.com

    ========================================================================================
    Author : Joel Mumo
    ========================================================================================
*/
package com.joel.examinprogress.service.teacher.results.viewperformance;

import com.joel.examinprogress.service.student.exam.examinprogress.ExamResult;

/**
 * @author Joel Mumo
 * @date   Oct 17, 2020
 */
public class ViewPerformanceInitialData {

    private String studentName;
    private ExamResult examResult;

    public ViewPerformanceInitialData( String studentName, ExamResult examResult ) {

        super();
        this.studentName = studentName;
        this.examResult = examResult;
    }


    /**
     * @return the studentName
     */
    public String getStudentName() {

        return studentName;
    }


    /**
     * @param studentName the studentName to set
     */
    public void setStudentName( String studentName ) {

        this.studentName = studentName;
    }


    /**
     * @return the examResult
     */
    public ExamResult getExamResult() {

        return examResult;
    }


    /**
     * @param examResult the examResult to set
     */
    public void setExamResult( ExamResult examResult ) {

        this.examResult = examResult;
    }
}
