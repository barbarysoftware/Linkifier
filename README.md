# Linkifier

A Java library to detect URLs in plain-text strings and convert them to HTML <a> anchor tags.

Inspired by linkifyjs.

* Extremely light
* Zero dependencies
* 100% test coverage
* Simple to use: Input is a Java String, output is a Java String.

Linkifier has not yet reached version 1. The basic API is likely to change somewhat before we settle on version 1.

Linkifier is maintained by the dev team at [Feature Upvote](https://featureupvote.com/).

## Example

```java
    public static void main(String[] args) {
        String input = "Many websites use example.com in their docs";
        var linkified = new Linkifier().linkify(input);
        System.out.println(linkified);
        // Output: Many websites use <a href='http://example.com' target='_blank'>example.com</a> in their docs
    }
```

