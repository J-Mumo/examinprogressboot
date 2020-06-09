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
package com.joel.examinprogress.context.threads;

/**
 * @author Joel Mumo
 * @date   9 June 2020
 */
public class ThreadLocals {

    public static final ThreadLocal<String> protocolThreadLocal =
            new ThreadLocal<>();

    public static final ThreadLocal<String> domainThreadLocal =
            new ThreadLocal<>();

    public static final ThreadLocal<Integer> portThreadLocal =
            new ThreadLocal<>();
}
