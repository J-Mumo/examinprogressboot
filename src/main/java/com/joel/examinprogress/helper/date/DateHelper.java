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
package com.joel.examinprogress.helper.date;

import java.text.SimpleDateFormat;

/**
 * @author Joel Mumo
 * @date   26 May, 2020
 */
public interface DateHelper {

    SimpleDateFormat FORMAT_MMM_yyyy = new SimpleDateFormat( "MMM yyyy" );
    SimpleDateFormat FORMAT_dd_MM_yyyy = new SimpleDateFormat( "dd MMM yyyy" );

    SimpleDateFormat FORMAT_yyyy_MM_dd_HH_mm_ss_SSS =
            new SimpleDateFormat( "yyyy_MM_dd_HH_mm_ss_SSS" );

    SimpleDateFormat FORMAT_EEEEE_dd_MMMM_yyyy_HH_mm =
            new SimpleDateFormat( "EEEEE, dd MMMM yyyy, HH:mm" );
}
