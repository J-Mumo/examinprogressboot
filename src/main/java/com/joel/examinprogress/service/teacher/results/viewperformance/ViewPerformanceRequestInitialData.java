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


/**
 * @author Joel Mumo
 * @date   Oct 17, 2020
 */
public class ViewPerformanceRequestInitialData {

    private Long studentId;
    private Long examId;

    public ViewPerformanceRequestInitialData( Long studentId, Long examId ) {

        super();
        this.studentId = studentId;
        this.examId = examId;
    }


    /**
     * @return the studentId
     */
    public Long getStudentId() {

        return studentId;
    }


    /**
     * @param studentId the studentId to set
     */
    public void setStudentId( Long studentId ) {

        this.studentId = studentId;
    }


    /**
     * @return the examId
     */
    public Long getExamId() {

        return examId;
    }


    /**
     * @param examId the examId to set
     */
    public void setExamId( Long examId ) {

        this.examId = examId;
    }
}
