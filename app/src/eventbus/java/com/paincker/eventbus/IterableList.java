package com.paincker.eventbus;

import java.util.ArrayList;
import java.util.List;

/*
 * Copyright (c) 2020, jzj
 * Author: jzj
 * Website: www.paincker.com
 */

public class IterableList<Handler> extends ArrayList<Handler> {

    public void each(EventAction<? super Handler> action) {
        for (Handler handler : this) {
            action.run(handler);
        }
    }

    public <Result> List<Result> map(EventMapAction<? super Handler, Result> mapAction) {
        ArrayList<Result> list = new ArrayList<>(this.size());
        for (Handler handler : this) {
            list.add(mapAction.run(handler));
        }
        return list;
    }
}
