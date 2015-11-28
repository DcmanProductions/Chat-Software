/*
 * Decompiled with CFR 0_110.
 */
package com.dcmanproductions.vid_eo.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UniqueIdentifier {
    private static List<Integer> ids = new ArrayList<Integer>();
    private static final int RANGE = 10000;
    private static int index = 0;

    static {
        int i = 0;
        while (i < 10000) {
            ids.add(i);
            ++i;
        }
        Collections.shuffle(ids);
    }

    private UniqueIdentifier() {
    }

    public static int getIdentifier() {
        if (index > ids.size() - 1) {
            index = 0;
        }
        return ids.get(index++);
    }
}

