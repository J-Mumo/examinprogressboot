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
package com.joel.examinprogress.domain.token;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.joel.examinprogress.domain.AbstractPersistentEntity;
import com.joel.examinprogress.domain.teacher.Teacher;

/**
 * @author Joel Mumo
 * @date   Nov 27, 2020
 */
@Entity
@Table( name = "payment_history" )
public class PaymentHistory extends AbstractPersistentEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 4580481369483777996L;

    @Column( name = "amount_paid", nullable = false )
    private BigDecimal amountPaid;

    @Column( name = "tokens_bought", nullable = false )
    private Integer tokensBought = 0;

    @Column( name = "currency", nullable = false, unique = false, length = 100 )
    private String curreny;

    @Column( name = "flw_ref", nullable = false, unique = false )
    private String flw_ref;

    @Column( name = "transaction_id", nullable = false )
    private Integer transactionId = 0;

    @Column( name = "tx_ref", nullable = false, unique = false )
    private String tx_ref;

    @ManyToOne( )
    @JoinColumn( name = "fk_teacher",
            foreignKey = @ForeignKey( name = "payment_history_fk_teacher" ),
            nullable = false )
    private Teacher teacher;

    
    /**
     * @return the amountPaid
     */
    public BigDecimal getAmountPaid() {
    
        return amountPaid;
    }

    
    /**
     * @param amountPaid the amountPaid to set
     */
    public void setAmountPaid( BigDecimal amountPaid ) {
    
        this.amountPaid = amountPaid;
    }

    
    /**
     * @return the tokensBought
     */
    public Integer getTokensBought() {
    
        return tokensBought;
    }

    
    /**
     * @param tokensBought the tokensBought to set
     */
    public void setTokensBought( Integer tokensBought ) {
    
        this.tokensBought = tokensBought;
    }

    
    /**
     * @return the curreny
     */
    public String getCurreny() {
    
        return curreny;
    }

    
    /**
     * @param curreny the curreny to set
     */
    public void setCurreny( String curreny ) {
    
        this.curreny = curreny;
    }

    
    /**
     * @return the flw_ref
     */
    public String getFlw_ref() {
    
        return flw_ref;
    }

    
    /**
     * @param flw_ref the flw_ref to set
     */
    public void setFlw_ref( String flw_ref ) {
    
        this.flw_ref = flw_ref;
    }

    
    /**
     * @return the transactionId
     */
    public Integer getTransactionId() {
    
        return transactionId;
    }

    
    /**
     * @param transactionId the transactionId to set
     */
    public void setTransactionId( Integer transactionId ) {
    
        this.transactionId = transactionId;
    }

    
    /**
     * @return the tx_ref
     */
    public String getTx_ref() {
    
        return tx_ref;
    }

    
    /**
     * @param tx_ref the tx_ref to set
     */
    public void setTx_ref( String tx_ref ) {
    
        this.tx_ref = tx_ref;
    }

    
    /**
     * @return the teacher
     */
    public Teacher getTeacher() {
    
        return teacher;
    }

    
    /**
     * @param teacher the teacher to set
     */
    public void setTeacher( Teacher teacher ) {
    
        this.teacher = teacher;
    }

}
