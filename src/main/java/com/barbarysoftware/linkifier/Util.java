package com.barbarysoftware.linkifier;

public class Util {
    static boolean startsWithIgnoreCase(final String str, final String prefix) {
        return prefix.length() <= str.length() && str.regionMatches(true, 0, prefix, 0, prefix.length());

    }
}
