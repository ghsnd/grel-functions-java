package io.fno.grel;

import org.apache.commons.codec.Encoder;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.language.ColognePhonetic;
import org.apache.commons.codec.language.DoubleMetaphone;
import org.apache.commons.codec.language.Metaphone;
import org.apache.commons.codec.language.Soundex;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringFunctions {

    /**
     * Returns the length of `s` as a number.
     *
     * @param s string
     * @return length
     */
    public static Integer length(String s) {
        return s.length();
    }

    // TODO https://github.com/OpenRefine/OpenRefine/wiki/GREL-String-Functions#tostringvalue

    /**
     * Takes any value type (string, number, date, boolean, error, null) and gives a string version of that value.
     * https://docs.openrefine.org/manual/grelfunctions#tostringo-string-format-optional
     */
    public static String toString(Object valueParameter) {
        if (valueParameter == null) {
            return "null";
        } else {
            return valueParameter.toString();
        }
    }

    /**
     * Returns boolean indicating whether `s` starts with `sub`.
     * For example, `startsWith("food", "foo")` returns `true`, whereas `startsWith("food", "bar")` returns `false`.
     *
     * @param s   string
     * @param sub prefix
     * @return boolean
     */
    public static Boolean startsWith(String s, String sub) {
        return s.startsWith(sub);
    }

    /**
     * Returns boolean indicating whether `s` ends with `sub`.
     * For example, `endsWith("food", "ood")` returns `true`, whereas `endsWith("food", "odd")` returns `false`.
     *
     * @param s   string
     * @param sub suffix
     * @return boolean
     */
    public static Boolean endsWith(String s, String sub) {
        return s.endsWith(sub);
    }

    /**
     * Returns boolean indicating whether s ends with sub.
     * For example, endsWith("food", "ood") returns true, whereas endsWith("food", "odd") returns false.
     * You could also write the first case as "food".endsWith("ood").
     *
     * @param s
     * @param sub
     * @return
     */
    public static Boolean contains(String s, String sub) {
        return s.contains(sub);
    }

    // TODO https://github.com/OpenRefine/OpenRefine/wiki/GREL-String-Functions#indexofstring-s-string-sub

    /**
     * Returns `s` converted to lowercase.
     *
     * @param s string
     * @return lowercase
     */
    public static String toLowercase(String s) {
        return s.toLowerCase();
    }

    /**
     * Returns `s` converted to uppercase.
     *
     * @param s string
     * @return uppercase
     */
    public static String toUppercase(String s) {
        return s.toUpperCase();
    }

    // TODO https://github.com/OpenRefine/OpenRefine/wiki/GREL-String-Functions#totitlecasestring-s

    /**
     * https://docs.openrefine.org/manual/grelfunctions/#totitlecases
     * Returns string s converted into titlecase: a capital letter starting each word, and the rest of the letters lowercase.
     *
     * @param s
     * @return capitalized string
     */
    public static String toTitlecase(String s) {
        return WordUtils.capitalizeFully(s);
    }

    /**
     * https://docs.openrefine.org/manual/grelfunctions#trims
     * Returns a copy of the string, with leading and trailing whitespace removed.
     * For example, `trim(" island ")` returns the string `island`.
     *
     * @param s string
     * @return a copy of the string, with leading and trailing whitespace removed
     */
    public static String trim(String s) {
        return s.trim();
    }

    /**
     * https://docs.openrefine.org/manual/grelfunctions#strips
     * Returns a copy of the string s with leading and trailing whitespace removed.
     * For example, " island ".strip() returns the string “island”. Identical to trim().
     * @param s string
     * @return a copy of the string s with leading and trailing whitespace removed
     */
    public static String strip(String s) {
        return trim(s);
    }

    /**
     * Returns a copy of s with sep removed from the end if s ends with sep; otherwise, just returns s.
     * For example, chomp("hardly", "ly") and chomp("hard", "ly") both return the string hard.
     * https://github.com/OpenRefine/OpenRefine/wiki/GREL-String-Functions#chompstring-s-string-sep
     *
     * @param s   string
     * @param sep sep
     * @return a copy of s with sep removed from the end if s ends with sep; otherwise, just returns s
     */
    public static String chomp(String s, String sep) {
        return StringUtils.chomp(s, sep);
    }

    // TODO fn-xpath:func-substring .

    /**
     * Returns the substring of `s` starting from character index `from` upto the end of the string `s`.
     * For example, `substring("profound", 3)` returns the string `found`.
     * <p>
     * Character indexes start from zero.
     *
     * @param s    string
     * @param from character index from
     * @return substring
     */
    public static String substring(String s, Integer from) {
        return s.substring(from);
    }

    /**
     * Returns the substring of `s` starting from character index `from` and upto character index `to`.
     * For example, `substring("profound", 2, 4)` returns the string `of`.
     * <p>
     * Character indexes start from zero.
     * Negative character indexes are understood as counting from the end of the string.
     * For example, `substring("profound", 1, -1)` returns the string `rofoun`.
     *
     * @param s    string
     * @param from character index from
     * @param to   character index upto
     * @return substring
     */
    public static String substring(String s, Integer from, Integer to) {
        return s.substring(from, to);
    }

    /**
     * https://docs.openrefine.org/manual/grelfunctions#slices-n-from-n-to-optional
     * Identical to substring() in relation to strings.
     * @param s    string
     * @param from character index from
     * @param to   character index upto
     * @return substring
     */
    public static String slice(String s, Integer from, Integer to) {
        return substring(s, from ,to);
    }

    /**
     * https://docs.openrefine.org/manual/grelfunctions#slices-n-from-n-to-optional
     * Identical to substring() in relation to strings.
     * @param s    string
     * @param from character index from
     * @return substring
     */
    public static String slice(String s, Integer from) {
        return substring(s, from);
    }

    /**
     * https://docs.openrefine.org/manual/grelfunctions#slices-n-from-n-to-optional
     * Identical to substring() in relation to strings.
     * @param s    string
     * @param from character index from
     * @param to   character index upto
     * @return substring
     */
    public static String get(String s, Integer from, Integer to) {
        return substring(s, from ,to);
    }

    /**
     * https://docs.openrefine.org/manual/grelfunctions#slices-n-from-n-to-optional
     * Identical to substring() in relation to strings.
     * @param s    string
     * @param from character index from
     * @return substring
     */
    public static String get(String s, Integer from) {
        return substring(s, from);
    }

    /**
     * https://docs.openrefine.org/manual/grelfunctions#indexofs-sub
     * Returns the first character index of sub as it first occurs in s; or, returns -1 if s does not contain sub.
     * @param s
     * @param sub
     * @return character index
     */
    public static Integer indexOf(String s, String sub) {
        return s.indexOf(sub);
    }

    /**
     * https://docs.openrefine.org/manual/grelfunctions/#lastindexofs-sub
     * Returns the first character index of sub as it last occurs in s; or, returns -1 if s does not contain sub.
     *
     * @param s
     * @param sub
     * @return character index
     */
    public static Integer lastIndexOf(String s, String sub) {
        return s.lastIndexOf(sub);
    }

    /**
     * https://docs.openrefine.org/manual/grelfunctions#replaces-s-or-p-find-s-replace
     * Returns the string obtained by replacing the find string with the replace string
     * in the inputted string.
     * @param s string to replace in
     * @param f target substring to replace
     * @param r string to replace target substring with
     * @return s with substring f replaced by string r
     */
    public static String replace(String s, String f, String r) {
        return s.replaceAll(f, r);
    }

    // TODO add unit test for this function
    /**
     * https://docs.openrefine.org/manual/grelfunctions#replacecharss-s-find-s-replace
     * Returns the string obtained by replacing a character in s, identified by find,
     * with the corresponding character identified in replace.
     * You cannot use this to replace a single character with more than one character.
     *
     * For example, replaceChars("Téxt thát was optícálly recógnízéd", "áéíóú", "aeiou")
     * returns the string “Text that was optically recognized”.
     * @param s The string to search and replace characters in
     * @param f A string containing all the chars to replace
     * @param r A string containing all the chars to replace with. The ordering should be
     *          matched with the ordering in argument f
     * @throws Exception when the string of replacement chars is shorter than the string of
     *         characters to replace.
     */
    public static String replaceChars(String s, String f, String r) throws Exception {
        if (f.length() > r.length()) {
            throw new Exception("You must provide as many replacement characters as target characters.");
        }
        for (int i=0; i<f.length(); i++) {
            char find = f.charAt(i);
            char replace = r.charAt(i);
            s = s.replace(find, replace);
        }
        return s;
    }

    /**
     * https://docs.openrefine.org/manual/grelfunctions#matchs-p
     * Attempts to match the string s in its entirety against the regex pattern p and,
     * if the pattern is found, outputs an array of all capturing groups (found in order).
     * @param s string
     * @param p regex pattern
     * @return Array of pattern matches
     */
    public static String[] match(String s, String p) {
        List<String> allMatches = new ArrayList<String>();
        Matcher m = Pattern.compile(p)
                .matcher(s);
        while (m.find()) {
            allMatches.add(m.group());
        }
        return (String[]) allMatches.toArray();
    }

    // NOTE: this was implemented in commit 5161c959985daabc90a53520b00752cc9c69b94d,
    //       but I don't think it implemented the same behavior as described at
    //       https://docs.openrefine.org/manual/grelfunctions#replacecharss-s-find-s-replace
    //       so I have not carried it over for now.
    //       - Maxime Cannoodt
    // TODO https://github.com/OpenRefine/OpenRefine/wiki/GREL-String-Functions#findstring-s-regexp-p

    /**
     * Returns a string converted to a number. Will attempt to convert other formats into a string,
     * then into a number. If the value is already a number, it will return the number.
     * https://docs.openrefine.org/manual/grelfunctions#tonumbers
     */
    public static Integer toNumber(Object o) {
        if (o.getClass().equals(Integer.class)) {
            return (Integer) o;
        }
        String str = o.toString();
        return Integer.parseInt(str);
    }

    /**
     * Returns the array of strings obtained by splitting `s` at wherever `sep` is found in it.
     * `sep` can be either a string or a regular expression.
     * For example, `split("fire, water, earth, air", ",")` returns the array of 4 strings:
     * "fire", " water", " earth" , and " air".
     * The double quotation marks are shown here only to highlight the fact that the spaces are retained.
     *
     * @param s   string
     * @param sep separator
     * @return the array of strings obtained by splitting `s` at wherever `sep` is found in it
     */
    public static List<String> split(String s, String sep) {
        return Arrays.asList(s.split(sep));
    }

    /**
     * https://docs.openrefine.org/manual/grelfunctions#splitbylengthss-n1-n2-
     * Returns the array of strings obtained by splitting s into substrings with the given
     * lengths. For example, "internationalization".splitByLengths(5, 6, 3) returns an array
     * of 3 strings: [ "inter", "nation", "ali" ]. Excess characters are discarded.
     * @param s string
     * @param numbers lengths of subsequent substrings to be extracted
     * @return Array of strings after splitting
     */
    public static String[] splitByLengths(String s, int... numbers) {
        List<String> output = new ArrayList<>();
        int i = 0;
        for (int n : numbers) {
            output.add(s.substring(i, i + n));
            i += n;
        }
        return output.toArray(new String[0]);
    }

    // TODO https://github.com/OpenRefine/OpenRefine/wiki/GREL-String-Functions#splitbylengthsstring-s-number-n1-number-n2-

    /**
     * https://docs.openrefine.org/manual/grelfunctions#smartsplits-s-or-p-sep-optional
     * Returns the array of strings obtained by splitting s by the separator sep. Handles quotes properly.
     * Guesses tab or comma separator if sep is not given.
     * Also, value.escape('javascript') is useful for previewing unprintable chars prior to using smartSplit.
     */
    public static String[] smartSplit(String s) {
        String sep;
        if (StringUtils.countMatches(s, "\t") < StringUtils.countMatches(s, ",")) {
            sep = ",";
        } else {
            sep = "\t";
        }
        return smartSplit(s, sep);
    }

    public static String[] smartSplit(String s, String sep) {
        return s.split(sep);
    }

    /**
     * https://docs.openrefine.org/manual/grelfunctions#splitbychartypes
     * Returns an array of strings obtained by splitting s into groups of consecutive characters
     * each time the characters change Unicode categories. For example, "HenryCTaylor".splitByCharType()
     * will result in an array of [ "H", "enry", "CT", "aylor" ]. It is useful for separating letters
     * and numbers: "BE1A3E".splitByCharType() will result in [ "BE", "1", "A", "3", "E" ].
     */
    public static String[] splitByCharType(String value) {
        return StringUtils.splitByCharacterType(value);
    }

    public static String[] _partition(String s, String frag, Boolean omitFragment, Boolean last) {
        List<String> output = new ArrayList<>();
        int offset = 0;
        int index;
        if (last) {
            index = s.indexOf(frag);
        } else {
            index = s.lastIndexOf(frag);
        }
        if (index == -1) {
            return new String[]{s, "", ""};
        }
        output.add(s.substring(0, index));
        if (! omitFragment) {
            output.add(frag);
            offset += frag.length();
        }
        output.add(s.substring(index + offset));
        return output.toArray(new String[0]);
    }

    /**
     * https://docs.openrefine.org/manual/grelfunctions#partitions-s-or-p-fragment-b-omitfragment-optional
     * Returns an array of strings [ a, fragment, z ] where a is the substring within s before the
     * first occurrence of fragment, and z is the substring after fragment. Fragment can be a
     * string or a regex.
     *
     * For example, "internationalization".partition("nation") returns 3 strings:
     * [ "inter", "nation", "alization" ]. If s does not contain fragment, it returns an array of
     * [ s, "", "" ] (the original unpartitioned string, and two empty strings).
     */
    public static String[] partition(String s, String frag) {
        return partition(s, frag, false);
    }

    /**
     * https://docs.openrefine.org/manual/grelfunctions#partitions-s-or-p-fragment-b-omitfragment-optional
     * Returns an array of strings [ a, fragment, z ] where a is the substring within s before the
     * first occurrence of fragment, and z is the substring after fragment. Fragment can be a
     * string or a regex.
     *
     * For example, "internationalization".partition("nation") returns 3 strings:
     * [ "inter", "nation", "alization" ]. If s does not contain fragment, it returns an array of
     * [ s, "", "" ] (the original unpartitioned string, and two empty strings).
     *
     * If the omitFragment boolean is true, for example with "internationalization".partition("nation", true),
     * the fragment is not returned. The output is [ "inter", "alization" ].
     */
    public static String[] partition(String s, String frag, Boolean omitFragment) {
        return _partition(s, frag, omitFragment, false);
    }

    /**
     * https://docs.openrefine.org/manual/grelfunctions#rpartitions-s-or-p-fragment-b-omitfragment-optional
     * Returns an array of strings [ a, fragment, z ] where a is the substring within s before
     * the last occurrence of fragment, and z is the substring after the last instance of fragment.
     * (Rpartition means “reverse partition.”)
     *
     * For example, "parallel".rpartition("a") returns 3 strings:
     * [ "par", "a", "llel" ]. Otherwise works identically to partition().
     */
    public static String[] rpartition(String s, String frag) {
        return rpartition(s, frag, false);
    }

    /**
     * https://docs.openrefine.org/manual/grelfunctions#rpartitions-s-or-p-fragment-b-omitfragment-optional
     * Returns an array of strings [ a, fragment, z ] where a is the substring within s before
     * the last occurrence of fragment, and z is the substring after the last instance of fragment.
     * (Rpartition means “reverse partition.”)
     *
     * For example, "parallel".rpartition("a") returns 3 strings:
     * [ "par", "a", "llel" ]. Otherwise works identically to partition().
     */
    public static String[] rpartition(String s, String frag, Boolean omitFragment) {
        return _partition(s, frag, omitFragment, true);
    }


    /**
     * https://docs.openrefine.org/manual/grelfunctions#diffs1-s2-s-timeunit-optional
     * Takes two strings and compares them, returning a string. Returns the remainder
     * of s2 starting with the first character where they differ.
     *
     * For example, diff("cacti", "cactus") returns "us".
     */
    public static String diff(String o1, String o2) {
        return StringUtils.difference(o1, o2);
    }

    /**
     * Escapes `s` in the given escaping mode: `html`, `xml`, `csv`, `url`, `javascript`.
     *
     * @param s    string
     * @param mode mode
     * @return escaped
     */
    public static String escape(String s, String mode) {
        String lMode = mode.toLowerCase();
        switch (lMode) {
            case "html":
                return StringEscapeUtils.escapeHtml(s);
            case "xml":
                return StringEscapeUtils.escapeXml(s);
            case "csv":
                return StringEscapeUtils.escapeCsv(s);
            case "url":
                return encodeURIComponent(s);
            case "javascript":
                return StringEscapeUtils.escapeJavaScript(s);
        }
        return s;
    }

    /**
     * https://docs.openrefine.org/manual/grelfunctions#unescapes-s-mode
     * Unescapes s in the given escaping mode. The mode can be one of: "html", "xml",
     * "csv", "url", "javascript". Note that quotes are required around your mode.
     */
    public static String unescape(String valueParameter, String modeParameter) {
        String mode = modeParameter.toLowerCase();
        switch (mode) {
            case "html":
                return StringEscapeUtils.unescapeHtml(valueParameter);
            case "xml":
                return StringEscapeUtils.unescapeXml(valueParameter);
            case "csv":
                return StringEscapeUtils.unescapeCsv(valueParameter);
            case "url":
                return decodeURIComponent(valueParameter);
            case "javascript":
                return StringEscapeUtils.unescapeJavaScript(valueParameter);
        }
        return valueParameter;
    }

    /**
     * Returns the MD5 hash of an object. If fed something other than a string (array, number, date, etc.), md5() will convert it to a string and deliver the hash of the string.
     *
     * @param s
     * @return
     */
    public static String md5(String s) {
        return DigestUtils.md5Hex(s);
    }

    /**
     * Returns the SHA-1 hash of an object. If fed something other than a string (array, number, date, etc.), sha1() will convert it to a string and deliver the hash of the string.
     *
     * @param s
     * @return
     */
    public static String sha1(String s) {
        return DigestUtils.sha1Hex(s);
    }

    // TODO https://github.com/OpenRefine/OpenRefine/wiki/GREL-String-Functions#phoneticstring-s-string-encoding

    /**
     * https://docs.openrefine.org/manual/grelfunctions#phonetics-s-encoding
     * Returns a phonetic encoding of a string, based on an available phonetic algorithm.
     * Can be one of the following supported phonetic methods: metaphone, doublemetaphone, metaphone3, soundex, cologne.
     *
     * @param s string to encode
     * @param mode "doublemetaphone", "metaphone", "metaphone3", "soundex", or "cologne"
     * @return encoded string
     * @throws EncoderException
     */
    public static String phonetic(String s, String mode) throws EncoderException {
        Encoder encoder;
        switch (mode) {
            case "doublemetaphone":
                encoder = new DoubleMetaphone();
                break;
            case "metaphone":
                encoder = new Metaphone();
            case "metaphone3":
                // TODO Find Metaphone 3
                encoder = new Metaphone();
            case "soundex":
                encoder = new Soundex();
            case "cologne":
                encoder = new ColognePhonetic();
            default:
                throw new IllegalStateException("Unexpected value: " + mode);
        }
        return encoder.encode(s).toString();
    }

    /**
     * https://docs.openrefine.org/manual/grelfunctions#reinterprets-s-encodertarget-s-encodersource
     */
    public static String reinterpret(String s, String encoder) {
        return new String(s.getBytes(), Charset.forName(encoder));
    }

    // TODO https://github.com/OpenRefine/OpenRefine/wiki/GREL-String-Functions#fingerprintstring-s

    // TODO https://github.com/OpenRefine/OpenRefine/wiki/GREL-String-Functions#ngramstring-s-number-n

    // TODO https://github.com/OpenRefine/OpenRefine/wiki/GREL-String-Functions#ngramfingerprintstring-s-number-n

    // https://docs.openrefine.org/manual/grelfunctions#unicodes
    // TODO add docstring and write unit test
    public static String[] unicode(String s) {
        return s.chars()
                .mapToObj(c -> (char) c)
                .map(c -> encodeURIComponent(String.valueOf(c)))
                .toArray(String[]::new);
    }

    // TODO https://github.com/OpenRefine/OpenRefine/wiki/GREL-String-Functions#unicodetypestring-s

    // TODO https://github.com/OpenRefine/OpenRefine/wiki/GREL-String-Functions#mqlkeyquotestring-s

    // TODO https://github.com/OpenRefine/OpenRefine/wiki/GREL-String-Functions#mqlkeyunquotestring-key

    private static final String ALLOWED_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_.!~*'()";

    private static String encodeURIComponent(String input) {
        if (StringUtils.isEmpty(input)) {
            return input;
        }

        int l = input.length();
        StringBuilder o = new StringBuilder(l * 3);
        try {
            for (int i = 0; i < l; i++) {
                String e = input.substring(i, i + 1);
                if (!ALLOWED_CHARS.contains(e)) {
                    Byte[] b = ArrayUtils.toObject(e.getBytes("utf-8"));
                    o.append(getHex(b));
                    continue;
                }
                o.append(e);
            }
            return o.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return input;
    }

    private static String decodeURIComponent(String encodedURI) {
        char actualChar;

        StringBuffer buffer = new StringBuffer();

        int bytePattern, sumb = 0;

        for (int i = 0, more = -1; i < encodedURI.length(); i++) {
            actualChar = encodedURI.charAt(i);

            switch (actualChar) {
                case '%': {
                    actualChar = encodedURI.charAt(++i);
                    int hb = (Character.isDigit(actualChar) ? actualChar - '0'
                            : 10 + Character.toLowerCase(actualChar) - 'a') & 0xF;
                    actualChar = encodedURI.charAt(++i);
                    int lb = (Character.isDigit(actualChar) ? actualChar - '0'
                            : 10 + Character.toLowerCase(actualChar) - 'a') & 0xF;
                    bytePattern = (hb << 4) | lb;
                    break;
                }
                case '+': {
                    bytePattern = ' ';
                    break;
                }
                default: {
                    bytePattern = actualChar;
                }
            }

            if ((bytePattern & 0xc0) == 0x80) { // 10xxxxxx
                sumb = (sumb << 6) | (bytePattern & 0x3f);
                if (--more == 0)
                    buffer.append((char) sumb);
            } else if ((bytePattern & 0x80) == 0x00) { // 0xxxxxxx
                buffer.append((char) bytePattern);
            } else if ((bytePattern & 0xe0) == 0xc0) { // 110xxxxx
                sumb = bytePattern & 0x1f;
                more = 1;
            } else if ((bytePattern & 0xf0) == 0xe0) { // 1110xxxx
                sumb = bytePattern & 0x0f;
                more = 2;
            } else if ((bytePattern & 0xf8) == 0xf0) { // 11110xxx
                sumb = bytePattern & 0x07;
                more = 3;
            } else if ((bytePattern & 0xfc) == 0xf8) { // 111110xx
                sumb = bytePattern & 0x03;
                more = 4;
            } else { // 1111110x
                sumb = bytePattern & 0x01;
                more = 5;
            }
        }
        return buffer.toString();
    }

    private static String getHex(Byte[] buf) {
        StringBuilder o = new StringBuilder(buf.length * 3);
        for (byte b : buf) {
            int n = (int) b & 0xff;
            o.append("%");
            if (n < 0x10) {
                o.append("0");
            }
            o.append(Long.toString(n, 16).toUpperCase());
        }
        return o.toString();
    }

}
