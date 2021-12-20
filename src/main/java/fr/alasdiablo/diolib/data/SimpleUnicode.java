package fr.alasdiablo.diolib.data;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

@SuppressWarnings("unused")
public record SimpleUnicode(String text) {
    private static final Map<String, String> LETTER_TO_UNICODE;

    static {
        ImmutableMap.Builder<String, String> builder = new ImmutableMap.Builder<>();

        builder.put("É", "\u00c9");
        builder.put("à", "\u00e0");
        builder.put("á", "\u00e1");
        builder.put("â", "\u00e2");
        builder.put("ä", "\u00e4");
        builder.put("ç", "\u00e7");
        builder.put("è", "\u00e8");
        builder.put("é", "\u00e9");
        builder.put("ê", "\u00ea");
        builder.put("ë", "\u00eb");
        builder.put("í", "\u00ed");
        builder.put("î", "\u00ee");
        builder.put("ï", "\u00ef");
        builder.put("ñ", "\u00f1");
        builder.put("ó", "\u00f3");
        builder.put("ô", "\u00f4");
        builder.put("ö", "\u00f6");
        builder.put("ù", "\u00f9");
        builder.put("ú", "\u00fa");
        builder.put("û", "\u00fb");
        builder.put("ü", "\u00fc");

        LETTER_TO_UNICODE = builder.build();
    }

    public static String encodeOf(String text) {
        return new SimpleUnicode(text).getEncodedText();
    }

    public String getBaseText() {
        return this.text;
    }

    public String getEncodedText() {
        return StringUtils.replaceEach(
                this.text,
                LETTER_TO_UNICODE.keySet().toArray(new String[]{ }),
                LETTER_TO_UNICODE.values().toArray(new String[]{ })
        );
    }
}
