import org.apache.commons.collections4.keyvalue.MultiKey;

import java.util.HashMap;

public class Example {

    private static HashMap codeAndLangToText;

    public static void main(String args[]) {

        codeAndLangToText = new HashMap();
        addMultiKeyAndValue("", "GM", "Good Morning");
        addMultiKeyAndValue("en", "GE", "Good Evening");
        addMultiKeyAndValue("en", "GN", "Good Night");
        addMultiKeyAndValue("de", "GM", "Guten Morgen");
        addMultiKeyAndValue("de", "GE", "Guten Abend");
        addMultiKeyAndValue("de", "GN", "Guten Nacht");

        System.err.println("Good Evening in English: " +
                codeAndLangToText.getOrDefault(new MultiKey("", "GM"), null));
        System.err.println("Good Night in German: " +
                codeAndLangToText.get(new MultiKey("de", "GN")));
    }

    private static void addMultiKeyAndValue(
            Object key1, Object key2, Object value) {

        MultiKey key = new MultiKey(key1, key2);
        codeAndLangToText.put(key, value);
    }
}