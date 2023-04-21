package com.barbarysoftware.linkifier;

public class UsageDemo {

    public static void main(String[] args) {
        String input = "Many websites use example.com in their docs";
        var linkified = Linkifier.linkify(input);
        System.out.println(linkified);
        // Output: Many websites use <a href='http://example.com' target='_blank'>example.com</a> in their docs
    }
}
