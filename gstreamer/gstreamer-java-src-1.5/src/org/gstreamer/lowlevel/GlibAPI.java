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
import java.util.HashMap;

import org.gstreamer.lowlevel.annotations.CallerOwnsReturn;

import com.sun.jna.Callback;
import com.sun.jna.Library;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;

/**
 *
 */
@SuppressWarnings("serial")
public interface GlibAPI extends Library {
	GlibAPI GLIB_API = GNative.loadLibrary("glib-2.0", GlibAPI.class,
    		new HashMap<String, Object>() {{
    			put(Library.OPTION_TYPE_MAPPER, new GTypeMapper());
    		}});
    Pointer g_main_loop_new(GMainContext context, boolean running);
    void g_main_loop_run(MainLoop loop);
    boolean g_main_loop_is_running(MainLoop loop);
    @CallerOwnsReturn GMainContext g_main_loop_get_context(MainLoop loop);
    void g_main_loop_quit(MainLoop loop);
    void g_main_loop_ref(MainLoop ptr);
    void g_main_loop_unref(MainLoop ptr);
    void g_main_loop_unref(Pointer ptr);
    
    /*
     * GMainContext functions
     */
    
    Pointer g_main_context_new();
    void g_main_context_ref(Pointer context);
    void g_main_context_unref(Pointer context);
    Pointer g_main_context_default();
    boolean g_main_context_pending(GMainContext ctx);
    boolean g_main_context_acquire(GMainContext ctx);
    void g_main_context_release(GMainContext ctx);
    boolean g_main_context_is_owner(GMainContext ctx);
    boolean g_main_context_wait(GMainContext ctx);
    
    @CallerOwnsReturn GSource g_idle_source_new();
    @CallerOwnsReturn GSource g_timeout_source_new(int interval);
    @CallerOwnsReturn GSource g_timeout_source_new_seconds(int interval);
    int g_source_attach(GSource source, GMainContext context);
    void g_source_destroy(Pointer source);
    void g_source_destroy(GSource source);
    Pointer g_source_ref(Pointer source);
    void g_source_unref(Pointer source);
    void g_source_set_callback(GSource source, GSourceFunc callback, Object data, GDestroyNotify destroy);
    boolean g_source_is_destroyed(Pointer source);
    boolean g_source_is_destroyed(GSource source);
    /*
     * GThread functions
     */
    interface GThreadFunc extends Callback {
        Pointer callback(Pointer data);
    }
    Pointer g_thread_create(GThreadFunc func, Pointer data, boolean joinable, PointerByReference error);
    Pointer g_thread_self();
    Pointer g_thread_join(Pointer thread);
    void g_thread_yield();
    void g_thread_set_priority(Pointer thread, int priority);
    void g_thread_exit(Pointer retval);
    
    
    
    interface GSourceFunc extends Callback {
        boolean callback(Pointer data);
    }
    NativeLong g_idle_add(GSourceFunc function, Pointer data);
    interface GDestroyNotify extends Callback {
        void callback(Pointer data);
    }
    
    int g_timeout_add(int interval, GSourceFunc function, Pointer data);
    int g_timeout_add_full(int priority, int interval, GSourceFunc function,
            Pointer data, GDestroyNotify notify);
    int g_timeout_add_seconds(int interval, GSourceFunc function, Pointer data);
    void g_error_free(Pointer error);
    void g_error_free(GstAPI.GErrorStruct error);
    
    void g_source_remove(int id);
    void g_free(Pointer ptr);
    
    Pointer g_date_new();
    Pointer g_date_new_dmy(int day, int month, int year);
    Pointer g_date_new_julian(int julian_day);
    int g_date_get_year(Pointer date);
    int g_date_get_month(Pointer date);
    int g_date_get_day(Pointer date);
    void g_date_free(Pointer date);

    public final static class GList extends com.sun.jna.Structure {
        public volatile Pointer data;
        public volatile Pointer _next;
        public volatile Pointer _prev;
        public GList() {
            clear();
        }
        public GList(Pointer ptr) {
            useMemory(ptr);
            read();
        }
        public static GList valueOf(Pointer ptr) {
            return ptr != null ? new GList(ptr) : null;
        }

        public GList next() {
            return valueOf(_next);
        }
        public GList prev() {
            return valueOf(_prev);
        }
    }
    public final static class GSList extends com.sun.jna.Structure {
        public volatile Pointer data;
        public volatile Pointer _next;
        public GSList() {
            clear();
        }
        public GSList(Pointer ptr) {
            useMemory(ptr);
            read();
        }
        public static GSList valueOf(Pointer ptr) {
            return ptr != null ? new GSList(ptr) : null;
        }

        public GSList next() {
            return valueOf(_next);
        }
    }
}
