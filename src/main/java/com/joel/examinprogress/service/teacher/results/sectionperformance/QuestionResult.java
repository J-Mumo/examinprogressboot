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
package com.joel.examinprogress.service.teacher.results.sectionperformance;


/**
 * @author Joel Mumo
 * @date   Oct 17, 2020
 */
public class QuestionResult {

    private Long questionId;
    private String question;
    private boolean comprehensionQuestion;
    private boolean textAnswer;
    private boolean multipleAnswers;
    private boolean singleAnswer;
    private Integer pointsEarned;
    private Integer questionTotalPoints;
    private AnswerResult[] answerResults;
    private QuestionResult[] questionResults;

    public QuestionResult(
            Long questionId,
            String question,
            boolean comprehensionQuestion,
            boolean textAnswer,
            boolean multipleAnswers,
            boolean singleAnswer,
            Integer pointsEarned,
            Integer questionTotalPoints,
            AnswerResult[] answerResults,
            QuestionResult[] questionResults ) {

        super();
        this.questionId = questionId;
        this.question = question;
        this.comprehensionQuestion = comprehensionQuestion;
        this.textAnswer = textAnswer;
        this.multipleAnswers = multipleAnswers;
        this.singleAnswer = singleAnswer;
        this.pointsEarned = pointsEarned;
        this.questionTotalPoints = questionTotalPoints;
        this.answerResults = answerResults;
        this.questionResults = questionResults;
    }


    /**
     * @return the pointsEarned
     */
    public Integer getPointsEarned() {

        return pointsEarned;
    }


    /**
     * @param pointsEarned the pointsEarned to set
     */
    public void setPointsEarned( Integer pointsEarned ) {

        this.pointsEarned = pointsEarned;
    }


    /**
     * @return the questionTotalPoints
     */
    public Integer getQuestionTotalPoints() {

        return questionTotalPoints;
    }


    /**
     * @param questionTotalPoints the questionTotalPoints to set
     */
    public void setQuestionTotalPoints( Integer questionTotalPoints ) {

        this.questionTotalPoints = questionTotalPoints;
    }


    /**
     * @return the questionId
     */
    public Long getQuestionId() {

        return questionId;
    }


    /**
     * @param questionId the questionId to set
     */
    public void setQuestionId( Long questionId ) {

        this.questionId = questionId;
    }


    /**
     * @return the question
     */
    public String getQuestion() {

        return question;
    }


    /**
     * @param question the question to set
     */
    public void setQuestion( String question ) {

        this.question = question;
    }


    /**
     * @return the comprehensionQuestion
     */
    public boolean isComprehensionQuestion() {

        return comprehensionQuestion;
    }


    /**
     * @param comprehensionQuestion the comprehensionQuestion to set
     */
    public void setComprehensionQuestion( boolean comprehensionQuestion ) {

        this.comprehensionQuestion = comprehensionQuestion;
    }


    /**
     * @return the textAnswer
     */
    public boolean isTextAnswer() {

        return textAnswer;
    }


    /**
     * @param textAnswer the textAnswer to set
     */
    public void setTextAnswer( boolean textAnswer ) {

        this.textAnswer = textAnswer;
    }


    /**
     * @return the multipleAnswers
     */
    public boolean isMultipleAnswers() {

        return multipleAnswers;
    }


    /**
     * @param multipleAnswers the multipleAnswers to set
     */
    public void setMultipleAnswers( boolean multipleAnswers ) {

        this.multipleAnswers = multipleAnswers;
    }


    /**
     * @return the singleAnswer
     */
    public boolean isSingleAnswer() {

        return singleAnswer;
    }


    /**
     * @param singleAnswer the singleAnswer to set
     */
    public void setSingleAnswer( boolean singleAnswer ) {

        this.singleAnswer = singleAnswer;
    }


    /**
     * @return the answerResults
     */
    public AnswerResult[] getAnswerResults() {

        return answerResults;
    }


    /**
     * @param answerResults the answerResults to set
     */
    public void setAnswerResults( AnswerResult[] answerResults ) {

        this.answerResults = answerResults;
    }


    /**
     * @return the questionResults
     */
    public QuestionResult[] getQuestionResults() {

        return questionResults;
    }


    /**
     * @param questionResults the questionResults to set
     */
    public void setQuestionResults( QuestionResult[] questionResults ) {

        this.questionResults = questionResults;
    }
}
