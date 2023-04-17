# linkify-java

A Java library detect URLs in plain-text strings and convert them to HTML <a> anchor tags.

Inspired by linkifyjs.

* Extremely light
* Zero dependencies
* 100% test coverage
* Simple to use: Input is a Java String, output is a Java String.

linkify-java has not yet reached version 1. The basic API is likely to be modified somewhat before we settle on version 1.

## Example

```
    public static void main(String[] args) {
        String input = "Many websites use example.com in their docs";
        String linkified = Linkify.linkify(input);
        System.out.println(linkified);
        // Output: Many websites use <a href='http://example.com' target='_blank'>example.com</a> in their docs
    }
```
