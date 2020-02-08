package com.paincker.eventbus.test;

import com.paincker.eventbus.EventAction;
import com.paincker.eventbus.EventBus;
import com.paincker.eventbus.EventMapAction;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

/*
 * Copyright (c) 2020, jzj
 * Author: jzj
 * Website: www.paincker.com
 */

public class EventBusTest {

    public TestHandler[] createSpyHandlers() {
        TestHandler spy0 = Mockito.spy(new TestHandler() {
            @Override
            public String getName() {
                return "0";
            }
        });
        TestHandler spy1 = Mockito.spy(new TestHandler() {
            @Override
            public String getName() {
                return "1";
            }
        });
        TestHandler spy2 = Mockito.spy(new TestHandler() {
            @Override
            public String getName() {
                return "2";
            }
        });
        return new TestHandler[]{spy0, spy1, spy2};
    }

    /**
     * 直接调用所有Handler。find方法返回的是非空动态代理对象，调用方不需要判空，也不需要遍历。
     */
    public void testCall() {
        EventBus bus = new EventBus();
        TestHandler[] spy = createSpyHandlers();

        Assert.assertNotNull(bus.find(TestHandler.class));

        bus.register(spy[0]);
        bus.register(spy[1]);

        bus.find(TestHandler.class).getName();
        Mockito.verify(spy[0], Mockito.times(1)).getName();
        Mockito.verify(spy[1], Mockito.times(1)).getName();
        Mockito.verify(spy[2], Mockito.times(0)).getName();
    }

    /**
     * 使用findAll().each()调用
     */
    public void testEach() {
        EventBus bus = new EventBus();
        TestHandler[] spy = createSpyHandlers();
        bus.register(spy[0]);
        bus.register(spy[1]);

        bus.findAll(TestHandler.class).each(new EventAction<TestHandler>() {
            @Override
            public void run(TestHandler testHandler) {
                testHandler.getName();
            }
        });
        Mockito.verify(spy[0], Mockito.times(1)).getName();
        Mockito.verify(spy[1], Mockito.times(1)).getName();
        Mockito.verify(spy[2], Mockito.times(0)).getName();
    }

    /**
     * 使用findAll().map()调用
     */
    @Test
    public void testMap() {
        EventBus bus = new EventBus();
        TestHandler[] spy = createSpyHandlers();
        bus.register(spy[0]);
        bus.register(spy[1]);

        List<String> list = bus.findAll(TestHandler.class).map(new EventMapAction<TestHandler, String>() {
            @Override
            public String run(TestHandler handler) {
                return handler.getName();
            }
        });

        Mockito.verify(spy[0], Mockito.times(1)).getName();
        Mockito.verify(spy[1], Mockito.times(1)).getName();
        Mockito.verify(spy[2], Mockito.times(0)).getName();

        Assert.assertArrayEquals(new String[]{"0", "1"}, list.toArray(new String[0]));
    }

    public interface TestHandler {

        String getName();
    }
}
