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
package com.joel.examinprogress.service.teacher.results.finalizescoring;


/**
 * @author Joel Mumo
 * @date   Oct 15, 2020
 */
public class FinalizeScoringRequestInitialData {

    private Long studentId;
    private Long examId;

    public FinalizeScoringRequestInitialData( Long studentId, Long examId ) {

        super();
        this.studentId = studentId;
        this.examId = examId;
    }


    public Long getStudentId() {

        return studentId;
    }


    public void setStudentId( Long studentId ) {

        this.studentId = studentId;
    }


    public Long getExamId() {

        return examId;
    }


    public void setExamId( Long examId ) {

        this.examId = examId;
    }
}
