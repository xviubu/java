/* 
 * Copyright (c) 2009 Levente Farkas
 * Copyright (c) 2007, 2008 Wayne Meissner
 * 
 * This file is part of gstreamer-java.
 *
 * This code is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License version 3 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * version 3 for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * version 3 along with this work.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.gstreamer.lowlevel;

import org.gstreamer.ClockTime;
import org.gstreamer.Format;
import org.gstreamer.Query;
import org.gstreamer.QueryType;
import org.gstreamer.Structure;
import org.gstreamer.glib.GQuark;
import org.gstreamer.lowlevel.annotations.CallerOwnsReturn;
import org.gstreamer.lowlevel.annotations.Invalidate;

import com.sun.jna.Pointer;

/**
 * GstQuery functions
 */
public interface GstQueryAPI extends com.sun.jna.Library {
	GstQueryAPI GSTQUERY_API = GstNative.load(GstQueryAPI.class);

    String gst_query_type_get_name(QueryType query);
    GQuark gst_query_type_to_quark(QueryType query);
    /* register a new query */
    QueryType gst_query_type_register(String nick, String description);
    QueryType gst_query_type_get_by_nick(String nick);
    
    GType gst_query_get_type();

    /* position query */
    @CallerOwnsReturn Query gst_query_new_position(Format format);
    void gst_query_set_position(Query query, Format format, /* gint64 */ long cur);
    void gst_query_parse_position(Query query, Format[] format, /* gint64 * */ long[] cur);

    /* duration query */
    @CallerOwnsReturn Query gst_query_new_duration(Format format);
    void gst_query_set_duration(Query query, Format format, /* gint64 */ long duration);
    void gst_query_parse_duration(Query query, /* Format **/ Format[] format, /* gint64 * */ long[] duration);

    /* latency query */
    @CallerOwnsReturn Query gst_query_new_latency();
    void gst_query_set_latency(Query query, boolean live, ClockTime min_latency,
         ClockTime max_latency);
    void gst_query_parse_latency(Query query, boolean[] live, ClockTime[] min_latency, 
		                                 ClockTime[] max_latency);

    /* convert query */
    @CallerOwnsReturn Query gst_query_new_convert(Format src_format, /* gint64 */ long value, Format dest_format);
    void gst_query_set_convert(Query query, Format src_format, /* gint64 */ long src_value,
						 Format dest_format, /* gint64 */ long dest_value);
    void gst_query_parse_convert(Query query, Format[] src_format, /*gint64 **/ long[] src_value,
						 Format[] dest_format, /*gint64 **/ long[] dest_value);
    /* segment query */
    @CallerOwnsReturn Query gst_query_new_segment(Format format);
    void gst_query_set_segment(Query query, double rate, Format format,
         /* gint64 */ long start_value, /* gint64 */ long stop_value);
    void gst_query_parse_segment(Query query, double[] rate, Format[] format,
         /* gint64 * */ long[] start_value, /* gint64 * */ long[] stop_value);

    /* application specific query */
    @CallerOwnsReturn Query gst_query_new_application(QueryType type, @Invalidate Structure structure);
    Structure gst_query_get_structure(Query query);

    /* seeking query */
    @CallerOwnsReturn Query gst_query_new_seeking(Format format);
    void gst_query_set_seeking(Query query, Format format,
        boolean seekable, /* gint64 */ long segment_start, /* gint64 */ long segment_end);
    void gst_query_parse_seeking(Query query, Format[] format,
        boolean[] seekable, /* gint64 * */ long[] segment_start, /* gint64 * */ long[] segment_end);
    /* formats query */
    @CallerOwnsReturn Query gst_query_new_formats();
    void gst_query_set_formats(Query query, int n_formats, Format... formats);
    void gst_query_set_formatsv(Query query, int n_formats, Format[] formats);
    void gst_query_parse_formats_length(Query query, int[] n_formats);
    void gst_query_parse_formats_nth(Query query, int nth, Format[] format);
    
    public static class QueryStruct extends com.sun.jna.Structure {
        public volatile GstMiniObjectAPI.MiniObjectStruct mini_object;
        public volatile int type;
        public volatile Pointer structure;
        public volatile Pointer _gst_reserved;
        public QueryStruct(Pointer ptr) {
            useMemory(ptr);
        }
    }
}
