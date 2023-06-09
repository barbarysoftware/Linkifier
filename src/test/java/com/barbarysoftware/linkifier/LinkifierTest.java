package com.barbarysoftware.linkifier;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LinkifierTest {

    @Test
    public void testNoLinks() {
        assertEquals("Hello world", new Linkifier().linkify("Hello world"));
    }

    @Test
    public void testMixture() {
        assertEquals("Any links to <a href='http://github.com' target='_blank'>github.com</a> here? If not, contact test@example.com",
                new Linkifier().linkify("Any links to github.com here? If not, contact test@example.com"));
    }

    @Test
    public void testNothingButLink() {
        assertEquals("<a href='http://example.com' target='_blank'>example.com</a>", new Linkifier().linkify("example.com"));
    }

    @Test
    public void testWithPath() {
        assertEquals("<a href='https://featureupvote.com/blog/sort-suggestions-by-trending/' target='_blank'>https://featureupvote.com/blog/sort-suggestions-by-trending/</a>",
                new Linkifier().linkify("https://featureupvote.com/blog/sort-suggestions-by-trending/"));
    }

    @Test
    public void testWithHttpSchema() {
        assertEquals(
                "Hello world at <a href='http://example.com/' target='_blank'>http://example.com/</a>",
                new Linkifier().linkify("Hello world at http://example.com/"));
    }

    @Test
    public void testWithHttpsSchema() {
        assertEquals(
                "Hello world at <a href='https://example.com/' target='_blank'>https://example.com/</a>",
                new Linkifier().linkify("Hello world at https://example.com/"));
    }


    @Test
    public void testEmailAddress() {
        // email addresses shouldn't match
        assertEquals(
                "Contact test@example.com",
                new Linkifier().linkify("Contact test@example.com"));
    }

    @Test
    public void testWithPathQueryParams() {
        assertEquals("<a href='https://featureupvote.com/blog/sort-suggestions-by-trending/?test=123&hello' target='_blank'>https://featureupvote.com/blog/sort-suggestions-by-trending/?test=123&hello</a>",
                new Linkifier().linkify("https://featureupvote.com/blog/sort-suggestions-by-trending/?test=123&hello"));
    }

    @Test
    public void testWithSubdomain() {
        assertEquals(
                "Hello world at <a href='http://www.example.com' target='_blank'>http://www.example.com</a>",
                new Linkifier().linkify("Hello world at http://www.example.com"));
    }

    @Test
    public void testWithHttps() {
        assertEquals(
                "<a href='https://example.com' target='_blank'>example.com</a>",
                new Linkifier(true, true).linkify("example.com"));
    }

    @Test
    public void testWithLinksInSameWindow() {
        assertEquals(
                "<a href='https://example.com'>example.com</a>",
                new Linkifier(true, false).linkify("example.com"));
    }

    @Test
    public void testWithSubdomainTldNet() {
        assertEquals(
                "Hello world at <a href='http://www.example.net' target='_blank'>http://www.example.net</a>",
                new Linkifier().linkify("Hello world at http://www.example.net"));
    }

    @Test
    public void testWithSubdomainTldAcademy() {
        assertEquals(
                "Hello world at <a href='http://www.example.academy' target='_blank'>http://www.example.academy</a>",
                new Linkifier().linkify("Hello world at http://www.example.academy"));
    }

    @Test
    public void testWithSubdomainTldCoUk() {
        assertEquals(
                "Hello world at <a href='http://www.example.co.uk' target='_blank'>http://www.example.co.uk</a>",
                new Linkifier().linkify("Hello world at http://www.example.co.uk"));
    }

    @Test
    public void testWithSubdomainAndPathQueryParams() {
        assertEquals(
                "Hello world at <a href='http://www.example.com?test=123&hello' target='_blank'>http://www.example.com?test=123&hello</a>",
                new Linkifier().linkify("Hello world at http://www.example.com?test=123&hello"));
    }

    @Test
    public void testWithSubdomainCaseInsensitiveAndPathQueryParams() {
        assertEquals(
                "Hello world at <a href='HTTP://www.ExamplE.COM?test=123&hello' target='_blank'>HTTP://www.ExamplE.COM?test=123&hello</a>",
                new Linkifier().linkify("Hello world at HTTP://www.ExamplE.COM?test=123&hello"));
    }

    @Test
    public void testWithSubdomainAndForwardSlash() {
        assertEquals(
                "Hello world at <a href='http://www.example.com/' target='_blank'>http://www.example.com/</a>",
                new Linkifier().linkify("Hello world at http://www.example.com/"));
    }

    @Test
    public void testWithNestedSubdomain() {
        assertEquals(
                "Hello world at <a href='https://test.test2.featureupvote.com' target='_blank'>https://test.test2.featureupvote.com</a>",
                new Linkifier().linkify("Hello world at https://test.test2.featureupvote.com"));
    }

    @Test
    public void testWithNestedSubdomainAndQueryParams() {
        assertEquals(
                "Hello world at <a href='http://www.test1.example.com?test=123&amp;hello' target='_blank'>http://www.test1.example.com?test=123&amp;hello</a>",
                new Linkifier().linkify("Hello world at http://www.test1.example.com?test=123&amp;hello"));
    }

    @Test
    public void testWithNestedSubdomainAndForwardSlash() {
        assertEquals(
                "Hello world at <a href='https://test.test2.featureupvote.com/' target='_blank'>https://test.test2.featureupvote.com/</a>",
                new Linkifier().linkify("Hello world at https://test.test2.featureupvote.com/"));
    }

    @Test
    public void testWithNestedSubdomainAndPath() {
        assertEquals(
                "Hello world at <a href='https://test.test2.featureupvote.com/suggestions/1234test/testing1-test' target='_blank'>https://test.test2.featureupvote.com/suggestions/1234test/testing1-test</a>",
                new Linkifier().linkify("Hello world at https://test.test2.featureupvote.com/suggestions/1234test/testing1-test"));
    }

    @Test
    public void testWithNestedSubdomainAndPathQueryParams() {
        assertEquals(
                "Hello world at <a href='https://test.test2.featureupvote.com/suggestions/1234test/testing1-test?test=123&amp;hello' target='_blank'>https://test.test2.featureupvote.com/suggestions/1234test/testing1-test?test=123&amp;hello</a>",
                new Linkifier().linkify("Hello world at https://test.test2.featureupvote.com/suggestions/1234test/testing1-test?test=123&amp;hello"));
    }

    @Test
    public void testWithNestedSubdomainTldCoUkAndPathQueryParams() {
        assertEquals(
                "Hello world at <a href='https://test.test2.featureupvote.co.uk/suggestions/1234test/testing1-test?test=123&amp;hello' target='_blank'>https://test.test2.featureupvote.co.uk/suggestions/1234test/testing1-test?test=123&amp;hello</a>",
                new Linkifier().linkify("Hello world at https://test.test2.featureupvote.co.uk/suggestions/1234test/testing1-test?test=123&amp;hello"));
    }

    @Test
    public void testNothingButSubdomainLink() {
        assertEquals("<a href='http://test.example.com' target='_blank'>test.example.com</a>", new Linkifier().linkify("test.example.com"));
    }

    @Test
    public void testNothingButNestedSubdomainLink() {
        assertEquals("<a href='http://test1.test.example.com' target='_blank'>test1.test.example.com</a>", new Linkifier().linkify("test1.test.example.com"));
    }

    @Test
    public void testWithNestedSubdomainMixture() {
        assertEquals(
                "Any links to <a href='http://github.com' target='_blank'>github.com</a> here? If not, contact test@example.com and this is another one that should work "
                + "Hello world at <a href='https://test.test2.featureupvote.com/suggestions/1234test/testing1-test' target='_blank'>https://test.test2.featureupvote.com/suggestions/1234test/testing1-test</a> "
                + "along with another one at <a href='http://www.example.com/' target='_blank'>http://www.example.com/</a> and content maybe should include hello@test.com "
                + "Hello world at <a href='http://www.example.com' target='_blank'>http://www.example.com</a> and a last one",
                new Linkifier().linkify("Any links to github.com here? If not, contact test@example.com and this is another one that should work "
                                        + "Hello world at https://test.test2.featureupvote.com/suggestions/1234test/testing1-test "
                                        + "along with another one at http://www.example.com/ and content maybe should include hello@test.com "
                                        + "Hello world at http://www.example.com and a last one"));
    }

    @Test
    public void testWithNestedSubdomainWithQueryStarParam() {
        assertEquals(
                "Hello world at <a href='http://www.test1.example.com?test=123&amp;*hello' target='_blank'>http://www.test1.example.com?test=123&amp;*hello</a>",
                new Linkifier().linkify("Hello world at http://www.test1.example.com?test=123&amp;*hello"));
    }

    @Test
    public void testWithNestedSubdomainWithQueryRoundBracketsParam() {
        assertEquals(
                "Hello world at <a href='http://www.test1.example.com?test=123&amp;(hello)' target='_blank'>http://www.test1.example.com?test=123&amp;(hello)</a>",
                new Linkifier().linkify("Hello world at http://www.test1.example.com?test=123&amp;(hello)"));
    }

    @Test
    public void testWithNestedSubdomainWithQueryDollarSignParam() {
        assertEquals(
                "Hello world at <a href='http://www.test1.example.com?test=123&amp;h$ello' target='_blank'>http://www.test1.example.com?test=123&amp;h$ello</a>",
                new Linkifier().linkify("Hello world at http://www.test1.example.com?test=123&amp;h$ello"));
    }

    @Test
    public void testWithNestedSubdomainWithQueryExclamationMarkSignParam() {
        assertEquals(
                "Hello world at <a href='http://www.test1.example.com?test=123&amp;hello=!test' target='_blank'>http://www.test1.example.com?test=123&amp;hello=!test</a>",
                new Linkifier().linkify("Hello world at http://www.test1.example.com?test=123&amp;hello=!test"));
    }

    @Test
    public void testWithNestedSubdomainWithQuerymixedCharacterParams() {
        assertEquals(
                "Hello world at <a href='http://www.test1.example.com?test=123$&amp;h*e)ll(o=!test' target='_blank'>http://www.test1.example.com?test=123$&amp;h*e)ll(o=!test</a> test",
                new Linkifier().linkify("Hello world at http://www.test1.example.com?test=123$&amp;h*e)ll(o=!test test"));
    }

    @Test
    public void testWithTrailingPeriod() {
        assertEquals(
                "<a href='http://example.com' target='_blank'>example.com</a>.",
                new Linkifier().linkify("example.com."));
    }

    @Test
    public void testWithTrailingPeriodAndWord() {
        assertEquals(
                "<a href='http://example.com' target='_blank'>example.com</a>.test",
                new Linkifier().linkify("example.com.test"));
    }

    @Test
    public void testWithTrailingPeriodAndWords() {
        assertEquals(
                "Testing 123 <a href='http://example.com' target='_blank'>example.com</a>.test example 1 2 3",
                new Linkifier().linkify("Testing 123 example.com.test example 1 2 3"));
    }

    @Test
    public void testWithExclamationMark() {
        assertEquals(
                "<a href='http://example.com' target='_blank'>example.com</a>!",
                new Linkifier().linkify("example.com!"));
    }

    @Test
    public void testWithExclamationMarkAndWord() {
        assertEquals(
                "<a href='http://example.com' target='_blank'>example.com</a>!test",
                new Linkifier().linkify("example.com!test"));
    }

    @Test
    public void testWithExclamationMarkAndWords() {
        assertEquals(
                "Testing 123 <a href='http://example.com' target='_blank'>example.com</a>!test example 1 2 3",
                new Linkifier().linkify("Testing 123 example.com!test example 1 2 3"));
    }

}