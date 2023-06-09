package com.barbarysoftware.linkifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Converts text so that any website links in the text is wrapped in an HTML A tag.
 * <ul>
 * <li> Only works on URLs. Email addresses, etc are not converted
 * <li> By default, links open in new tab.
 * <li> By default, if a scheme has to be added, http is used. eg: example.com links to http://example.com
 * </ul>
 *
 * This class is thread-safe. Instances are immutable, and can be safely reused and shared by multiple threads.
 */
public class Linkifier {

    // Regexp that matches things that look like links in a String. It matches the following examples:
    //
    // http://example.com
    // https://example.com
    // https://example.com/
    // example.com
    // abe@example.com
    // http://test.example.com
    // http://test.example.co.uk
    // http://EXAmplE.co.uk
    // http://test.example.academy
    // http://test.example.com/example/
    // http://example.com/example
    // http://test.example.com/?queryparams=
    // http://test.example.com/path/anotherpath/?queryparams=
    //
    // Many common top-level domains (tlds) are recognised, but not all.
    //
    // There's things that will incorrectly match, and things that will incorrectly not match. We'll fix these
    // as we become aware of them
    private final static Pattern REGEX_URL_PATTERN =
            Pattern.compile("(https?://)?([a-z0-9-@]+\\.)+(" + getTLDs() + ")+(/([-a-zA-Z0-9@:%._+~#?&/=;()!$*])*)?+(\\?([-a-zA-Z0-9@:%._+~#?&/=;()!$*])*)?", Pattern.CASE_INSENSITIVE);

    private final boolean useHttps;
    private final boolean openLinksInNewWindow;

    /**
     * Creates an instance that uses http on links that are a missing the scheme, and opens links
     * in new window.
     */
    public Linkifier() {
        this(false, true);
    }

    /**
     * @param useHttps if a scheme needs to be added to a link, use https instead of http
     * @param openLinksInNewWindow if true, a target='_blank' attribute is added to the link
     */
    public Linkifier(boolean useHttps, boolean openLinksInNewWindow) {
        this.useHttps = useHttps;
        this.openLinksInNewWindow = openLinksInNewWindow;
    }

    public String linkify(String input) {
        Objects.requireNonNull(input);
        var links = findUrls(input);
        return convertLinks(input, links);
    }

    private String convertLinks(String input, List<Link> links) {
        final StringBuilder sb = new StringBuilder();

        int nextStart = 0;
        for (Link link : links) {
            sb.append(input, nextStart, link.start());
            sb.append(toLinkTag(link));
            nextStart = link.end();
        }

        sb.append(input, nextStart, input.length());

        return sb.toString();
    }

    private String toLinkTag(Link link) {
        if (openLinksInNewWindow) {
            return "<a href='" + link.href() + "' target='_blank'>" + link.value() + "</a>";
        } else {
            return "<a href='" + link.href() + "'>" + link.value() + "</a>";
        }
    }

    private List<Link> findUrls(String input) {
        List<Link> result = new ArrayList<>();

        Matcher matcher = REGEX_URL_PATTERN.matcher(input);
        while (matcher.find()) {
            if (matcher.group().contains("@")) {
                // probably an email address - so ignore it
                continue;
            }
            var href = toHref(matcher.group());
            result.add(new Link(matcher.group(), href, matcher.start(), matcher.end()));
        }

        return result;
    }

    private String toHref(String matchedString) {
        if (Util.startsWithIgnoreCase(matchedString, "http://")
            || Util.startsWithIgnoreCase(matchedString, "https://")) {
            return matchedString;
        }
        if (useHttps) {
            return "https://" + matchedString;

        } else {
            return "http://" + matchedString;
        }
    }

    /**
     * @return String of common top-level domains (TLDs) in a form suitable for use in our regular expression
     */
    private static String getTLDs() {
        return Stream.of("com", "org", "net", "edu", "gov", "io", "info", "top", "academy", "af", "ax", "al", "dz", "as", "ad", "ao", "ai", "aq", "ag", "ar", "am", "aw", "ac", "au", "at", "az",
                        "bs", "bh", "bd", "bb", "eus", "by", "be", "bz", "bj", "bm", "bt", "bo", "bq", "an", "nl", "ba", "bw", "bv", "br", "io", "vg", "bn", "bg", "bf", "mm", "bi", "kh", "cm", "ca", "cv", "cat", "ky",
                        "cw", "cy", "cz", "dk", "dj", "dm", "do", "tl", "tp", "ec", "eg", "sv", "gq", "er", "ee", "et", "eu", "fk", "fo", "fm", "fj", "fi", "fr", "gf", "pf", "tf", "ga", "gal", "gm", "ps", "ge", "de",
                        "gh", "gi", "gr", "gl", "gd", "gp", "gu", "gt", "gg", "gn", "gw", "gy", "ht", "hm", "hn", "hk", "hu", "is", "in", "id", "ir", "iq", "ie", "im", "il", "it", "jm", "jp", "je", "jo", "kz", "ke",
                        "ki", "kw", "kg", "la", "lv", "lb", "ls", "lr", "ly", "li", "lt", "lu", "mo", "mk", "mg", "mw", "my", "mv", "ml", "mt", "mh", "mq", "mr", "mu", "yt", "mx", "md", "mc", "mn", "me", "ms", "ma",
                        "mz", "mm", "na", "nr", "np", "nl", "nc", "nz", "ni", "ne", "ng", "nu", "nf", "nc", "tr", "kp", "mp", "no", "om", "pk", "pw", "ps", "pa", "pg", "py", "pe", "ph", "pn", "pl", "pt", "pr", "qa",
                        "ro", "ru", "rw", "re", "bq", "an", "bl", "gp", "fr", "sh", "kn", "lc", "mf", "gp", "fr", "pm", "vc", "ws", "sm", "st", "sa", "sn", "rs", "sc", "sl", "sg", "bq", "an", "nl", "sx", "an", "sk",
                        "si", "sb", "so", "so", "za", "gs", "kr", "ss", "es", "lk", "sd", "sr", "sj", "sz", "se", "ch", "sy", "tw", "tj", "tz", "th", "tg", "tk", "to", "tt", "tn", "tr", "tm", "tc", "tv", "ug", "ua",
                        "cf", "td", "cl", "cn", "cx", "cc", "co", "km", "cd", "cg", "ck", "cr", "ci", "hr", "cu", "ae", "uk", "us", "vi", "uy", "uz", "vu", "va", "ve", "vn", "wf", "eh", "ma", "ye", "zm", "zw")
                .map(tld -> "\\b" + tld + "\\b")
                .collect(Collectors.joining("|"));
    }


}
