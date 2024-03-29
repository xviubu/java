/* 
 * Copyright (C) 2009 Tamas Korodi <kotyo@zamba.fm>
 * Copyright (C) 2007 Wayne Meissner
 * Copyright (C) 1999,2000 Erik Walthinsen <omega@cse.ogi.edu>
 *                    2000 Wim Taymans <wtay@chello.be>
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

package org.gstreamer;

import org.gstreamer.lowlevel.GstNative;
import org.gstreamer.lowlevel.GstPadAPI;
import org.gstreamer.lowlevel.GstAPI.GstCallback;
import org.gstreamer.lowlevel.GstPadAPI.PadBlockCallback;
import org.gstreamer.lowlevel.annotations.CallerOwnsReturn;

import com.sun.jna.Pointer;

/**
 * Object contained by elements that allows links to other elements.
 * <p>
 * A {@link Element} is linked to other elements via "pads", which are extremely
 * light-weight generic link points.
 * After two pads are retrieved from an element with {@link Element#getPad},
 * the pads can be link with {@link #link}. (For quick links,
 * you can also use {@link Element#link}, which will make the obvious
 * link for you if it's straightforward.)
 * <p>
 * Pads are typically created from a {@link PadTemplate} with {@link #Pad(PadTemplate, String)}.
 * <p>
 * Pads have {@link Caps} attached to it to describe the media type they are
 * capable of dealing with.  {@link #getCaps} and {@link #setCaps} are
 * used to manipulate the caps of the pads.
 * Pads created from a pad template cannot set capabilities that are
 * incompatible with the pad template capabilities.
 * <p>
 * Pads without pad templates can be created with gst_pad_new(),
 * which takes a direction and a name as an argument.  If the name is NULL,
 * then a guaranteed unique name will be assigned to it.
 * <p>
 * {@link #getParentElement} will retrieve the Element that owns the pad.
 * <p>
 * An Element creating a pad will typically use the various
 * gst_pad_set_*_function() calls to register callbacks for various events
 * on the pads.
 * <p>
 * GstElements will use gst_pad_push() and gst_pad_pull_range() to push out
 * or pull in a buffer.
 * <p>
 * To send an Event on a pad, use {@link #sendEvent} and {@link #pushEvent}.
 * 
 * @see PadTemplate
 * @see Element
 * @see Event
 */
public class Pad extends GstObject {
    private static interface API extends GstPadAPI {
        @CallerOwnsReturn Pointer ptr_gst_pad_new(String name, PadDirection direction);
        @CallerOwnsReturn Pointer ptr_gst_pad_new_from_template(PadTemplate templ, String name);
    }
    private static final API gst = GstNative.load(API.class);
    
    /**
     * Creates a new instance of Pad
     */
    public Pad(Initializer init) { 
        super(init); 
    }
    /**
     * Creates a new pad with the given name in the given direction.
     * If name is null, a guaranteed unique name (across all pads)
     * will be assigned.
     * 
     * @param name The name of the new pad.
     * @param direction The direction of the new pad.
     */
    public Pad(String name, PadDirection direction) {
        this(initializer(gst.ptr_gst_pad_new(name, direction)));
    }
    
    /**
     * Creates a new pad with the given name from the given template.
     * 
     * If name is null, a guaranteed unique name (across all pads)
     * will be assigned.
     *
     * @param template The pad template to use.
     * @param name The name of the new pad.
     */
    public Pad(PadTemplate template, String name) {
        this(initializer(gst.ptr_gst_pad_new_from_template(template, name)));
    }
    
    /**
     * Get the capabilities this pad can produce or consume.
     * 
     * This method returns all possible caps a pad can operate with, using
     * the pad's get_caps function; not just the Caps as set by {@link #setCaps}.
     * 
     * This returns the pad template caps if not explicitly set.
     *
     * MT safe.
     * @return a newly allocated copy of the {@link Caps} of this pad.
     */
    public Caps getCaps() {
        return gst.gst_pad_get_caps(this);
    }
    
    /**
     * Sets the capabilities of this pad. 
     * 
     * The caps must be fixed. Any previous caps on the pad will be destroyed. 
     *
     * It is possible to set null caps, which will make the pad unnegotiated
     * again.
     *
     * MT safe.
     * @param caps The {@link Caps} to set.
     * @return true if the caps could be set. false if the caps were not fixed
     * or bad parameters were provided to this function.
     */
    public boolean setCaps(Caps caps) {
        return gst.gst_pad_set_caps(this, caps);
    }
    
    /**
     * Gets the capabilities of the allowed media types that can flow through this pad and its peer.
     *
     * The allowed capabilities is calculated as the intersection of the results of
     * calling {@link #getCaps} on this pad and its peer. 
     * 
     * MT safe.
     * @return The allowed {@link Caps} of the pad link, or null if this pad has no peer.
     */
    public Caps getAllowedCaps() {
        return gst.gst_pad_get_allowed_caps(this);
    }
    
    /**
     * Get the capabilities of the media type that currently flows through this pad
     * and its peer.
     *
     * This function can be used on both src and sink pads. Note that srcpads are
     * always negotiated before sinkpads so it is possible that the negotiated caps
     * on the srcpad do not match the negotiated caps of the peer.
     *
     * MT safe.
     * @return the negotiated #GstCaps of the pad link, or null if this pad has
     * no peer, or is not negotiated yet
     * 
     */
    public Caps getNegotiatedCaps() {
        return gst.gst_pad_get_negotiated_caps(this);
    }
    
    /**
     * Get the peer of this pad.
     *
     * MT safe.
     * @return The peer Pad of this Pad.
     */
    public Pad getPeer() {
        return gst.gst_pad_get_peer(this);
    }
    /**
     * Get the capabilities of the peer connected to this pad.
     *
     * @return the {@link Caps} of the peer pad, or null if there is no peer pad.
     */
    public Caps getPeerCaps() {
        return gst.gst_pad_peer_get_caps(this);
    }
    
    /**
     * Check if the pad accepts the given caps.
     *
     * @param caps a {@link Caps} to check on the pad.
     * @return true if the pad can accept the caps.
     */
    public boolean acceptCaps(Caps caps) {
        return gst.gst_pad_accept_caps(this, caps);
    }
    
    /**
     * Check if the peer of this pad accepts the caps.
     * If this pad has no peer, this method returns true.
     *
     * @param caps {@link Caps} to check on the pad
     * @return true if the peer pad can accept the caps or this pad no peer.
     */
    public boolean peerAcceptCaps(Caps caps) {
        return gst.gst_pad_peer_accept_caps(this, caps);
    }
    
    /**
     * Links this pad and a sink pad.
     *
     * MT Safe.
     * @param pad the sink Pad to link.
     * @return A result code indicating if the connection worked or what went wrong.
     */
    public PadLinkReturn link(Pad pad) {
        return gst.gst_pad_link(this, pad);
    }
    
    /**
     *
     * Unlinks the source pad from the sink pad. 
     * Will emit the "unlinked" signal on both pads.
     *
     * MT safe.
     * 
     * @param pad the sink Pad to unlink.
     * @return true if the pads were unlinked. This function returns false if
     * the pads were not linked together.
     */
    public boolean unlink(Pad pad) {
        return gst.gst_pad_unlink(this, pad);
    }
    
    /**
     * Check if this pad is linked to another pad or not.
     *
     * @return true if the pad is linked, else false.
     */
    public boolean isLinked() {
        return gst.gst_pad_is_linked(this);
    }
    
    /**
     * Get the direction of the pad. 
     * The direction of the pad is decided at construction time so this function
     * does not take the LOCK.
     *
     * @return The {@link PadDirection} of the pad.
     */
    public PadDirection getDirection() {
        return gst.gst_pad_get_direction(this);
    }
    
    /**
     * Get the parent of this pad, cast to a {@link Element}. 
     * If this pad has no parent or its parent is not an element, returns null.
     *
     * @return The parent of the pad.
     */
    public Element getParentElement() {
        return gst.gst_pad_get_parent_element(this);
    }
    
    /**
     * Activates or deactivates the given pad.
     * Normally called from within core state change functions.
     *
     * If active is true, makes sure the pad is active. If it is already active, either in
     * push or pull mode, just return. Otherwise dispatches to the pad's activate
     * function to perform the actual activation.
     *
     * If not @active, checks the pad's current mode and calls
     * gst_pad_activate_push() or gst_pad_activate_pull(), as appropriate, with a
     * FALSE argument.
     *
     * @param active whether or not the pad should be active.
     * @return true if the operation was successful.
     */
    public boolean setActive(boolean active) {
        return gst.gst_pad_set_active(this, active);
    }
    
    /**
     *
     * Blocks or unblocks the dataflow on a pad. 
     * 
     * MT safe.
     * @param blocked boolean indicating we should block or unblock
     * @return true if the pad could be blocked. This function can fail if the
     * wrong parameters were passed or the pad was already in the requested state.
     */
    public boolean setBlocked(boolean blocked) {
        return gst.gst_pad_set_blocked(this, blocked);
    }
         
    /**
     * Checks if the pad is blocked or not.
     * This function returns the last requested state of the pad. It is not 
     * certain that the pad is actually blocking at this point (see {@link #isBlocking}).
     *
     * @return true if the pad is blocked.
     */
    public boolean isBlocked() {
        return gst.gst_pad_is_blocked(this);
    }
    
    public boolean setBlockedAsync(boolean blocked, PadBlockCallback callback) {
    	return gst.gst_pad_set_blocked_async(this, blocked, callback, null);
    }
    
    /**
     * Checks if the pad is blocking or not. This is a guaranteed state
     * of whether the pad is actually blocking on a {@link Buffer} or a {@link Event}.
     * 
     * @return true if the pad is blocking.
     */
    public boolean isBlocking() {
        return gst.gst_pad_is_blocking(this);
    }
    
    /**
     * Signal emitted when new data is available on the {@link Pad}
     * 
     * @see #connect(HAVE_DATA)
     * @see #disconnect(HAVE_DATA)
     */
    public static interface HAVE_DATA {
        /**
         * Called when a {@link Pad} has data available.
         * 
         * @param pad the pad which emitted the signal.
         * @param data the new data.
         */
        public void haveData(Pad pad, MiniObject data);
    }

    /**
     * Signal emitted when new this {@link Pad} is linked to another {@link Pad}
     * 
     * @see #connect(LINKED)
     * @see #disconnect(LINKED)
     */
    public static interface LINKED {
        /**
         * Called when a {@link Pad} is linked to another Pad.
         * 
         * @param pad the pad that emitted the signal.
         * @param peer the peer pad that has been connected.
         */
        public void linked(Pad pad, Pad peer);
    }
    
    /**
     * Signal emitted when new this {@link Pad} is disconnected from a peer {@link Pad}
     * 
     * @see #connect(UNLINKED)
     * @see #disconnect(UNLINKED)
     */
    public static interface UNLINKED {
        /**
         * Called when a {@link Pad} is unlinked from another Pad.
         * 
         * @param pad the pad that emitted the signal.
         * @param peer the peer pad that has been connected.
         */
        public void unlinked(Pad pad, Pad peer);
    }
    
    /**
     * Signal emitted when a connection to a peer {@link Pad} is requested.
     * 
     * @see #connect(REQUEST_LINK)
     * @see #disconnect(REQUEST_LINK)
     */
    public static interface REQUEST_LINK {
        /**
         * Called when a pad connection has been requested.
         * @param pad the pad that emitted the signal.
         * @param peer the peer pad for which a connection is requested.
         */
        public void requestLink(Pad pad, Pad peer);
    }
    
    /**
     * Signal emitted when an event passes through this <tt>Pad</tt>.
     * 
     * @see #addEventProbe(EVENT_PROBE)
     * @see #removeEventProbe(EVENT_PROBE)
     */
    public static interface EVENT_PROBE {
        public boolean eventReceived(Pad pad, Event event);
    }
    
    /**
     * Add a listener for the <code>have-data</code> signal on this {@link Pad}
     * 
     * @param listener The listener to be called when data is available.
     */
    public void connect(final HAVE_DATA listener) {
        connect(HAVE_DATA.class, listener, new GstCallback() {
            @SuppressWarnings("unused")
            public boolean callback(Pad pad, Buffer buffer) {
                listener.haveData(pad, buffer);
                return true;
            }
        });
    }
    
    /**
     * Remove a listener for the <code>have-data</code> signal on this {@link Pad}
     * 
     * @param listener The listener previously added for this signal.
     */
    public void disconnect(HAVE_DATA listener) {
        disconnect(HAVE_DATA.class, listener);
    }
    
    /**
     * Add a listener for the <code>linked</code> signal on this {@link Pad}
     * 
     * @param listener The listener to be called when a peer {@link Pad} is linked.
     */
    public void connect(final LINKED listener) {
        connect(LINKED.class, listener, new GstCallback() {
            @SuppressWarnings("unused")
            public boolean callback(Pad pad, Pad peer) {
                listener.linked(pad, peer);
                return true;
            }
        });
    }
    
    /**
     * Remove a listener for the <code>linked</code> signal on this {@link Pad}
     * 
     * @param listener The listener previously added for this signal.
     */
    public void disconnect(LINKED listener) {
        disconnect(LINKED.class, listener);
    }
    
    /**
     * Add a listener for the <code>unlinked</code> signal on this {@link Pad}
     * 
     * @param listener The listener to be called when when a peer {@link Pad} is unlinked.
     */
    public void connect(final UNLINKED listener) {
        connect(UNLINKED.class, listener, new GstCallback() {
            @SuppressWarnings("unused")
            public boolean callback(Pad pad, Pad peer) {
                listener.unlinked(pad, peer);
                return true;
            }
        });
    }
    /**
     * Remove a listener for the <code>unlinked</code> signal on this {@link Pad}
     * 
     * @param listener The listener previously added for this signal.
     */
    public void disconnect(UNLINKED listener) {
        disconnect(UNLINKED.class, listener);
    }
    
    /**
     * Add a listener for the <code>request-link</code> signal on this {@link Pad}
     * 
     * @param listener The listener to be called when a peer {@link Pad} requests
     * to be linked to this one.
     */
    public void connect(final REQUEST_LINK listener) {
        connect(REQUEST_LINK.class, listener, new GstCallback() {
            @SuppressWarnings("unused")
            public boolean callback(Pad pad, Pad peer) {
                listener.requestLink(pad, peer);
                return true;
            }
        });
    }
    
    /**
     * Remove a listener for the <code>request-link</code> signal on this {@link Pad}
     * 
     * @param listener The listener previously added for this signal.
     */
    public void disconnect(REQUEST_LINK listener) {
        disconnect(REQUEST_LINK.class, listener);
    }

    public void addEventProbe(final EVENT_PROBE listener) {
        final GstPadAPI.PadEventProbe probe = new GstPadAPI.PadEventProbe() {
            public boolean callback(Pad pad, Event ev, Pointer unused) {
            	//We have to negate the return value to keep consistency with gstreamer's API
                return !listener.eventReceived(pad, ev);
            }
        };
        GCallback cb = new GCallback(gst.gst_pad_add_event_probe(this, probe, null), probe) {
            @Override
            protected void disconnect() {
                gst.gst_pad_remove_event_probe(Pad.this, id);
            }
        };
        addCallback(EVENT_PROBE.class, listener, cb);
    }

    public void removeEventProbe(EVENT_PROBE listener) {
        removeCallback(EVENT_PROBE.class, listener);
    }

    /**
     * Sends the event to this pad. 
     * <p> This function can be used by applications to send events in the pipeline.
     *
     * <p>If this pad is a source pad, <tt>event</tt> should be an upstream event. 
     * If this pad is a sink pad, <tt>event<tt> should be a downstream event. 
     * <p>For example, you would not send a {@link EventType#EOS} on a src pad; 
     * EOS events only propagate downstream.
     * 
     * <p>Furthermore, some downstream events have to be serialized with data flow,
     * like EOS, while some can travel out-of-band, like {@link EventType#FLUSH_START}. If
     * the event needs to be serialized with data flow, this function will take the
     * pad's stream lock while calling its event function.
     *
     * @param event the event to send.
     * @return <tt>true</tt> if the event was handled.
     */
    public boolean sendEvent(Event event) {
        return gst.gst_pad_send_event(this, event);
    }
    
    /**
     * Sends the event to the peer of this pad. 
     * 
     * <p>This function is mainly used by elements to send events to their peer elements.
     * 
     * @param event the event to send
     * @return true if the event was handled
     */
    public boolean pushEvent(Event event) {
        return gst.gst_pad_push_event(this, event);
    }
    
    /**
     * Chain a buffer to pad.
     * <p>
     * The function returns {@link org.gstreamer.FlowReturn#WRONG_STATE} if the pad was flushing.
     * <p>
     * If the caps on buffer are different from the current caps on pad, this function will call any 
     * function installed on pad (see gst_pad_set_setcaps_function()). 
     * If the new caps are not acceptable for pad, this function returns 
     * {@link org.gstreamer.FlowReturn#NOT_NEGOTIATED}.
     * <p>
     * The function proceeds calling the chain function installed on pad and the return value of that function is 
     * returned to the caller. {@link org.gstreamer.FlowReturn#NOT_SUPPORTED} is returned if pad has no chain function.
     * <p>
     * In all cases, success or failure, the caller loses its reference to buffer after calling this function.
     * 
     * @param buffer the Buffer, returns {@link org.gstreamer.FlowReturn#ERROR} if NULL.
     * @return a org.gstreamer.FlowReturn
     */
    public FlowReturn chain(Buffer buffer) {
    	return gst.gst_pad_chain(this, buffer);
    }
    
    /**
     * When pad is flushing this function returns {@link org.gstreamer.FlowReturn#WRONG_STATE} immediatly.
     * <p>
     * Calls the getRange function of pad, see GstPadGetRangeFunction for a description of a getRange function. 
     * If pad has no getRange function installed (see gst_pad_set_getrange_function()) this function returns 
     * {@link FlowReturn#NOT_SUPPORTED}.
     * 
     * This is a lowlevel function. Usualy {@link Pad#pullRange} is used.
     * 
     * @param offset The start offset of the buffer
     * @param size The length of the buffer
     * @param buffer the Buffer, returns {@link FlowReturn#ERROR} if NULL.
     * @return a FlowReturn from the peer pad. When this function returns OK, buffer will contain a valid Buffer.
     */
    public FlowReturn getRange(long offset, int size, Buffer[] buffer) {
    	return gst.gst_pad_get_range(this, offset, size, buffer);
    }

    /**
     * Pulls a buffer from the peer pad.
     * <p>
     * This function will first trigger the pad block signal if it was installed.
     * <p>
     * When pad is not linked {@link FlowReturn#NOT_LINKED} is returned else this function returns 
     * the result of {@link Pad#getRange} on the peer pad. See {@link Pad#getRange} for a list of return values and for 
     * the semantics of the arguments of this function.
     * <p>
     * buffer's caps must either be unset or the same as what is already configured on pad. 
     * Renegotiation within a running pull-mode pipeline is not supported.
     * @param offset The start offset of the buffer
     * @param size The length of the buffer
     * @param buffer the Buffer, returns {@link FlowReturn#ERROR} if NULL.
     * @return a FlowReturn from the peer pad. When this function returns OK, buffer will contain a valid Buffer.
     * MT safe.
     */
    public FlowReturn pullRange(long offset, int size, Buffer[] buffer) {
    	return gst.gst_pad_pull_range(this, offset, size, buffer);
    }
}
