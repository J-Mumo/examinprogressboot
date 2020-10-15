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
public class FinalizeScoringRequest {

    private Long questionId;
    private Long studentId;
    private Integer pointsEarned;

    public FinalizeScoringRequest(
            Long questionId,
            Long studentId,
            Integer pointsEarned ) {

        super();
        this.questionId = questionId;
        this.studentId = studentId;
        this.pointsEarned = pointsEarned;
    }


    public Long getQuestionId() {

        return questionId;
    }


    public void setQuestionId( Long questionId ) {

        this.questionId = questionId;
    }


    public Long getStudentId() {

        return studentId;
    }


    public void setStudentId( Long studentId ) {

        this.studentId = studentId;
    }


    public Integer getPointsEarned() {

        return pointsEarned;
    }


    public void setPointsEarned( Integer pointsEarned ) {

        this.pointsEarned = pointsEarned;
    }
}
