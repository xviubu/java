/* 
 * Copyright (c) 2009 Levente Farkas
 * Copyright (c) 2007 Wayne Meissner
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

import org.gstreamer.ActivateMode;
import org.gstreamer.Buffer;
import org.gstreamer.Caps;
import org.gstreamer.ClockReturn;
import org.gstreamer.ClockTime;
import org.gstreamer.Event;
import org.gstreamer.FlowReturn;
import org.gstreamer.MiniObject;
import org.gstreamer.Pad;
import org.gstreamer.StateChangeReturn;
import org.gstreamer.elements.BaseSink;
import org.gstreamer.lowlevel.GlibAPI.GList;
import org.gstreamer.lowlevel.GstAPI.GstSegmentStruct;
import org.gstreamer.lowlevel.GstElementAPI.GstElementClass;
import org.gstreamer.lowlevel.GstElementAPI.GstElementStruct;
import org.gstreamer.lowlevel.annotations.CallerOwnsReturn;

import com.sun.jna.Callback;
import com.sun.jna.Library;
import com.sun.jna.Pointer;
import com.sun.jna.Union;

public interface BaseSinkAPI extends Library {
	BaseSinkAPI BASESINK_API = GstNative.load("gstbase", BaseSinkAPI.class);
    int GST_PADDING = GstAPI.GST_PADDING;
    int GST_PADDING_LARGE = GstAPI.GST_PADDING_LARGE;
    
    public final static class GstBaseSinkStruct extends com.sun.jna.Structure {
        public GstElementStruct element;
        
        /*< protected >*/
        public volatile Pad sinkpad;
        public volatile ActivateMode pad_mode;

        /*< protected >*/ /* with LOCK */
        public volatile long offset;
        public volatile boolean can_activate_pull;
        public volatile boolean can_activate_push;

        /*< protected >*/ /* with PREROLL_LOCK */
        public volatile Pointer /*GQueue */ preroll_queue;
        public volatile int preroll_queue_max_len;
        public volatile int preroll_queued;
        public volatile int buffers_queued;
        public volatile int events_queued;
        public volatile boolean eos;
        public volatile boolean eos_queued;
        public volatile boolean need_preroll;
        public volatile boolean have_preroll;
        public volatile boolean playing_async;

        /*< protected >*/ /* with STREAM_LOCK */
        public volatile boolean have_newsegment;
        public volatile GstSegmentStruct segment;

        /*< private >*/ /* with LOCK */
        public volatile Pointer /* GstClockID */ clock_id;
        public volatile long /* GstClockTime */  end_time;
        public volatile boolean sync;
        public volatile boolean flushing;

        /*< private >*/
        public volatile GstBaseSinkAbiData abidata;
        public volatile Pointer /* GstBaseSinkPrivate */ priv;
    }

    public final static class GstBaseSinkAbiData extends Union {
        public volatile GstBaseSinkAbi abi;
        public volatile Pointer[] _gst_reserved = new Pointer[GST_PADDING_LARGE - 1];
    }

    public final static class GstBaseSinkAbi extends com.sun.jna.Structure {
        public volatile Pointer /* GstSegment */ clip_segment;
        public volatile long max_lateness;
        public volatile boolean running;
    }
    
    // -------------- Callbacks -----------------
    public static interface GetCaps extends Callback {
        public Caps callback(BaseSink sink);
    }
    public static interface SetCaps extends Callback {
        public boolean callback(BaseSink sink, Caps caps);
    }
	public static interface BufferAlloc extends Callback {
		public FlowReturn callback(BaseSink sink, long offset, int size,
				Caps caps, /* GstBuffer ** */Pointer bufRef);
	}
    public static interface GetTimes extends Callback {
        public void callback(BaseSink sink, Buffer buffer, 
                Pointer start, Pointer end);
    }
    public static interface BooleanFunc1 extends Callback {
        public boolean callback(BaseSink sink);
    }
    public static interface EventNotify extends Callback {
        boolean callback(BaseSink sink, Event event);
    }
    public static interface Render extends Callback {
        public FlowReturn callback(BaseSink sink, Buffer buffer);
    }
    public static interface AsyncPlay extends Callback {
        public StateChangeReturn callback(BaseSink sink);
    }
    public static interface ActivatePull extends Callback {
        public boolean callback(BaseSink sink, boolean active);
    }
    public static interface Fixate extends Callback {
        public void callback(BaseSink sink, Caps caps);
    }
    public static interface RenderList extends Callback {
        public FlowReturn callback(BaseSink sink, GList bufferList);
    }
    
    public final static class GstBaseSinkClass extends com.sun.jna.Structure {
        public GstBaseSinkClass() {}
        public GstBaseSinkClass(Pointer ptr) {
            useMemory(ptr);
            read();
        }
        
        //
        // Actual data members
        //
        public GstElementClass parent_class;
        
        /* get caps from subclass */
        public GetCaps get_caps;

        /* notify subclass of new caps */
        public SetCaps set_caps;        

        /* allocate a new buffer with given caps */
        public BufferAlloc buffer_alloc;
  
        /* get the start and end times for syncing on this buffer */
        public GetTimes get_times;
  
        /* start and stop processing, ideal for opening/closing the resource */
        public BooleanFunc1 start;
        public BooleanFunc1 stop;
  
        /* 
         * unlock any pending access to the resource. subclasses should unlock
         * any function ASAP. 
         */
        public BooleanFunc1 unlock;
  

        /* notify subclass of event, preroll buffer or real buffer */
        public EventNotify event;
        
        public Render preroll;
        public Render render;
 
        /* ABI additions */

        /* when an ASYNC state change to PLAYING happens */ /* with LOCK */
        public AsyncPlay async_play;

        /* start or stop a pulling thread */
        public ActivatePull activate_pull;

        /* fixate sink caps during pull-mode negotiation */
        public Fixate fixate;

        /* Clear a previously indicated unlock request not that unlocking is
         * complete. Sub-classes should clear any command queue or indicator they
         * set during unlock 
         */
        public BooleanFunc1 unlock_stop;
        
        /* Render a BufferList */
        public RenderList render_list;
        
        /*< private >*/
        public volatile byte[] _gst_reserved = new byte[Pointer.SIZE * (GST_PADDING_LARGE-5)];
    }
    
    GType gst_base_sink_get_type();

    FlowReturn gst_base_sink_do_preroll(BaseSink sink, MiniObject obj);
    FlowReturn gst_base_sink_wait_preroll(BaseSink sink);
    
    /* synchronizing against the clock */
    void gst_base_sink_set_sync(BaseSink sink, boolean sync);
    boolean gst_base_sink_get_sync(BaseSink sink);

    /* dropping late buffers */
    void gst_base_sink_set_max_lateness (BaseSink sink, long max_lateness);
    long gst_base_sink_get_max_lateness(BaseSink sink);
    
    /* performing QoS */
    void gst_base_sink_set_qos_enabled(BaseSink sink, boolean enabled);
    boolean gst_base_sink_is_qos_enabled(BaseSink sink);
    
    /* doing async state changes */
    void gst_base_sink_set_async_enabled(BaseSink sink, boolean enabled);
    boolean gst_base_sink_is_async_enabled(BaseSink sink);
    
    /* tuning synchronisation */
    void gst_base_sink_set_ts_offset(BaseSink sink, long offset);
    long gst_base_sink_get_ts_offset(BaseSink sink);

    /* last buffer */
    @CallerOwnsReturn Buffer gst_base_sink_get_last_buffer(BaseSink sink);
    void gst_base_sink_set_last_buffer_enabled(BaseSink sink, boolean enable);
    boolean gst_base_sink_is_last_buffer_enabled(BaseSink sink);

    /* latency */
    boolean gst_base_sink_query_latency(BaseSink sink, boolean live, boolean upstream_live, ClockTime min_latency, ClockTime max_latency);
    ClockTime gst_base_sink_get_latency(BaseSink sink);
        
    /* render delay */
    void gst_base_sink_set_render_delay(BaseSink sink, ClockTime delay);
    ClockTime gst_base_sink_get_render_delay(BaseSink sink);
    
    /* blocksize */
    void gst_base_sink_set_blocksize(BaseSink sink, int blocksize);
    int gst_base_sink_get_blocksize(BaseSink sink);
    
    ClockReturn gst_base_sink_wait_clock(BaseSink sink, ClockTime time, /* GstClockTimeDiff */ Pointer jitter);
    FlowReturn gst_base_sink_wait_eos(BaseSink sink, ClockTime time, /* GstClockTimeDiff */ Pointer jitter);
}
