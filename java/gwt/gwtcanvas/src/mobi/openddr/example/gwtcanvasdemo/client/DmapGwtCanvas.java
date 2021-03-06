/*
 * Copyright (c) 2011-2017 OpenDDR LLC and others. All rights reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package mobi.openddr.example.gwtcanvasdemo.client;
import com.google.gwt.user.client.Window;
import com.google.gwt.useragent.client.UserAgent;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.CssColor;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Touch;
import com.google.gwt.event.dom.client.GestureStartEvent;
import com.google.gwt.event.dom.client.GestureStartHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.TouchEndEvent;
import com.google.gwt.event.dom.client.TouchEndHandler;
import com.google.gwt.event.dom.client.TouchMoveEvent;
import com.google.gwt.event.dom.client.TouchMoveHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;	

public class DmapGwtCanvas implements EntryPoint {
    private static final String HOLDER_ID = "canvasholder";
    private static final String UPGRADE_MESSAGE = "Your browser does not support the HTML5 Canvas. Please upgrade your browser to view this demo.";

    // timer refresh rate, in milliseconds
    private static final int REFRESH_RATE = 25;

    private Canvas canvas;
    private Canvas backBuffer;
    private LogoGroup logoGroup;
    private BallGroup ballGroup;
    private Lens lens;

    // mouse positions relative to canvas
    private int mouseX;
    private int mouseY;

    // canvas size, in px
    private int height;
    private int width;

    final CssColor redrawColor = CssColor.make("rgba(255,255,255,0.6)");
    Context2d context;
    Context2d backBufferContext;

    public void onModuleLoad() {
	UserAgent impl = GWT.create(UserAgent.class);
	Window win = GWT.create(Window.class);
	
	String compileTimeValue = impl.getCompileTimeValue();
	String runtimeValue = impl.getRuntimeValue();
	System.out.println(compileTimeValue + "->" + runtimeValue);
	height = win.getClientHeight() - 100;
	width = win.getClientWidth() - 80;

	canvas = Canvas.createIfSupported();
	backBuffer = Canvas.createIfSupported();
	if (canvas == null) {
	    RootPanel.get(HOLDER_ID).add(new Label(UPGRADE_MESSAGE));
	    return;
	}

	// init the canvases
	canvas.setWidth(width + "px");
	canvas.setHeight(height + "px");
	canvas.setCoordinateSpaceWidth(width);
	canvas.setCoordinateSpaceHeight(height);
	backBuffer.setCoordinateSpaceWidth(width);
	backBuffer.setCoordinateSpaceHeight(height);
	RootPanel.get(HOLDER_ID).add(canvas);
	context = canvas.getContext2d();
	backBufferContext = backBuffer.getContext2d();

	// init the objects
	logoGroup = new LogoGroup(width, height, 18, 165);
	ballGroup = new BallGroup(width, height);
	lens = new Lens(35, 15, width, height, new Vector(320, 150),
		new Vector(1, 1));

	// init handlers
	initHandlers();

	// setup timer
	final Timer timer = new Timer() {
	    @Override
	    public void run() {
		doUpdate();
	    }
	};
	timer.scheduleRepeating(REFRESH_RATE);
    }

    void doUpdate() {
	// update the back canvas
	backBufferContext.setFillStyle(redrawColor);
	backBufferContext.fillRect(0, 0, width, height);
	logoGroup.update(mouseX, mouseY);
	ballGroup.update(mouseX, mouseY);
	logoGroup.draw(backBufferContext);
	ballGroup.draw(backBufferContext);

	// update the front canvas
	lens.update();
	lens.draw(backBufferContext, context);
    }

    void initHandlers() {
	canvas.addMouseMoveHandler(new MouseMoveHandler() {
	    public void onMouseMove(MouseMoveEvent event) {
		mouseX = event.getRelativeX(canvas.getElement());
		mouseY = event.getRelativeY(canvas.getElement());
	    }
	});

	canvas.addMouseOutHandler(new MouseOutHandler() {
	    public void onMouseOut(MouseOutEvent event) {
		mouseX = -200;
		mouseY = -200;
	    }
	});

	canvas.addTouchMoveHandler(new TouchMoveHandler() {
	    public void onTouchMove(TouchMoveEvent event) {
		event.preventDefault();
		if (event.getTouches().length() > 0) {
		    Touch touch = event.getTouches().get(0);
		    mouseX = touch.getRelativeX(canvas.getElement());
		    mouseY = touch.getRelativeY(canvas.getElement());
		}
		event.preventDefault();
	    }
	});

	canvas.addTouchEndHandler(new TouchEndHandler() {
	    public void onTouchEnd(TouchEndEvent event) {
		event.preventDefault();
		mouseX = -200;
		mouseY = -200;
	    }
	});

	canvas.addGestureStartHandler(new GestureStartHandler() {
	    public void onGestureStart(GestureStartEvent event) {
		event.preventDefault();
	    }
	});
    }
}
