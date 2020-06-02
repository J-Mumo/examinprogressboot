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
