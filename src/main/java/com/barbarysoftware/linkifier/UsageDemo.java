package com.barbarysoftware.linkifier;

/**
 * A simple example of how to use Linkifier that you can copy and paste to get started.
 */
public class UsageDemo {

    /**
     * Shows the most basic use of Linkifier
     */
    public static void main(String[] args) {
        String input = "Many websites use example.com in their docs";
        var linkified = new Linkifier().linkify(input);
        System.out.println(linkified);
        // Output: Many websites use <a href='http://example.com' target='_blank'>example.com</a> in their docs
    }
}
