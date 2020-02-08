package com.paincker.eventbus;

/*
 * Copyright (c) 2020, jzj
 * Author: jzj
 * Website: www.paincker.com
 */

public interface EventMapAction<Handler, Result> {

    Result run(Handler handler);
}
