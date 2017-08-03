package com.joseadilson.news_app.extras;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import java.util.HashMap;

/**
 * Created by Sanny on 2/18/2017.
 */

public class FontTextView extends android.support.v7.widget.AppCompatTextView {

    public static final String ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android";

    public FontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        applyCustomFont(context, attrs);
    }

    public FontTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        applyCustomFont(context, attrs);
    }

    private void applyCustomFont(Context context, AttributeSet attrs) {
        int textStyle = attrs.getAttributeIntValue(ANDROID_SCHEMA, "textStyle", Typeface.NORMAL);
        Typeface customFont = selectTypeface(context, textStyle);
        setTypeface(customFont);
    }

    private Typeface selectTypeface(Context context, int textStyle) {
        switch (textStyle) {
            case Typeface.BOLD: // bold

               // return FontCache.getTypeface("Helvetica-Neu-Bold.ttf", context);
                return FontCache.getTypeface("Lato-Bold.ttf", context);
            case Typeface.ITALIC: // medium

              //  return FontCache.getTypeface("HelveticaNeueMedium.ttf", context);
                return FontCache.getTypeface("Lato-Black.ttf", context);
            case Typeface.BOLD_ITALIC: // bold italic
              //  return FontCache.getTypeface("SourceSansPro-BoldItalic.ttf", context);
                return FontCache.getTypeface("Roboto-Medium.ttf", context);
            case Typeface.NORMAL: // regular
                return FontCache.getTypeface("Lato-Regular.ttf", context);

            default:
                return FontCache.getTypeface("Roboto-Medium.ttf", context);
              //  return FontCache.getTypeface("HelveticaNeue.ttf", context);
        }
    }

    private static class FontCache {
        private static HashMap<String, Typeface> fontCache = new HashMap<>();
        static Typeface getTypeface(String fontname, Context context) {
            Typeface typeface = fontCache.get(fontname);

            if (typeface == null) {
                try {
                    typeface = Typeface.createFromAsset(context.getAssets(), fontname);
                } catch (Exception e) {
                    return null;
                }
                fontCache.put(fontname, typeface);
            }

            return typeface;
        }
    }
}