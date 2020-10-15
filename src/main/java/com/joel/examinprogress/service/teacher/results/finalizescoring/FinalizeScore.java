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
public class FinalizeScore {

    private Long questionId;
    private String question;
    private Integer questionMaxPoints;
    private String studentAnswer;
    
    public FinalizeScore(
            Long questionId,
            String question,
            Integer questionMaxPoints,
            String studentAnswer) {
        super();
        this.questionId = questionId;
        this.question = question;
        this.questionMaxPoints = questionMaxPoints;
        this.studentAnswer = studentAnswer;
    }

    
    public Long getQuestionId() {
    
        return questionId;
    }

    
    public void setQuestionId( Long questionId ) {
    
        this.questionId = questionId;
    }

    
    public String getQuestion() {
    
        return question;
    }

    
    public void setQuestion( String question ) {
    
        this.question = question;
    }

    
    public Integer getQuestionMaxPoints() {
    
        return questionMaxPoints;
    }

    
    public void setQuestionMaxPoints( Integer questionMaxPoints ) {
    
        this.questionMaxPoints = questionMaxPoints;
    }

    
    public String getStudentAnswer() {
    
        return studentAnswer;
    }

    
    public void setStudentAnswer( String studentAnswer ) {
    
        this.studentAnswer = studentAnswer;
    }
}
