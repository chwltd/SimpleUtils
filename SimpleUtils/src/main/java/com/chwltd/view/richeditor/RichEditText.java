package com.chwltd.view.richeditor;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.Spannable;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.UnderlineSpan;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;

public class RichEditText extends AppCompatEditText {

    private boolean isBold = false;
    private boolean isItalic = false;
    private boolean isStrikethrough = false;
    private int fontColor = Color.BLACK; // 默认颜色为黑色

    private boolean isSubscript = false; // 下标状态
    private boolean isSuperscript = false; // 上标状态
    private boolean isUnderline = false; // 下划线状态
    private float fontSize = 16f; // 默认字体大小

    // 定义接口
    public interface OnCursorStyleChangeListener {
        void onCursorStyleChanged(String styleInfo);
    }

    // 添加接口监听器
    private OnCursorStyleChangeListener cursorStyleChangeListener;

    // 设置接口监听器
    public void setOnCursorStyleChangeListener(OnCursorStyleChangeListener listener) {
        this.cursorStyleChangeListener = listener;
    }

    public RichEditText(@NonNull Context context) {
        super(context);
        init();
    }

    public RichEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RichEditText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // 监听输入的文本，自动应用样式
        addTextChangedListener(new TextWatcher() {
            int newTextNum = 1;
            int delTextNum = 1;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                newTextNum = count;
                delTextNum = before;
            }

            @Override
            public void afterTextChanged(Editable editable) {
                applyStyles(editable, newTextNum);
            }
        });
    }

    // 方法：设置输入的粗体样式
    public void setBold(boolean bold) {
        isBold = bold;
    }

    // 方法：设置输入的斜体样式
    public void setItalic(boolean italic) {
        isItalic = italic;
    }

    // 方法：设置输入的横线样式
    public void setStrikethrough(boolean strikethrough) {
        isStrikethrough = strikethrough;
    }

    // 方法：设置输入的字体颜色样式
    public void setFontColor(String colorString) {
        fontColor = Color.parseColor(colorString);
    }

    public void setSubscript(boolean subscript) {
        if (subscript) {
            isSuperscript = false; // 禁止共存
        }
        isSubscript = subscript;
    }

    // 方法：设置输入的上标样式
    public void setSuperscript(boolean superscript) {
        if (superscript) {
            isSubscript = false; // 禁止共存
        }
        isSuperscript = superscript;
    }

    // 方法：设置输入的下划线样式
    public void setUnderline(boolean underline) {
        isUnderline = underline;
    }

    // 方法：设置输入的大字体样式
    public void setFontSize(float size) {
        fontSize = size;
    }

    // 方法：清除所有输入样式
    public void clearStyles() {
        isBold = false;
        isItalic = false;
        isStrikethrough = false;
        fontColor = Color.BLACK; // 重置为默认颜色
        isSubscript = false;
        isSuperscript = false;
        isUnderline = false;
        fontSize = 16f; // 重置为默认大小
    }

    // 方法：应用样式到新输入的文本
    private void applyStyles(Editable editable, int count) {
        int selectionStart = getSelectionStart();
        int selectionEnd = getSelectionEnd();

        // 仅对新输入的文本应用样式
        if (selectionStart == selectionEnd) {
            int start = getSelectionStart() - count;
            int end = getSelectionEnd();
            if (start < 0 || end <= start) return;

            // 继承光标前后的样式
            if (start > 0 && end < editable.length()) {
                inheritStyleFromAdjacentChars(editable, start, end);
            }

            // 应用设置的样式
            if (isBold) {
                editable.setSpan(new StyleSpan(Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            if (isItalic) {
                editable.setSpan(new StyleSpan(Typeface.ITALIC), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            if (isStrikethrough) {
                editable.setSpan(new StrikethroughSpan(), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            if (fontColor != Color.BLACK) {
                editable.setSpan(new ForegroundColorSpan(fontColor), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            if (isSubscript) {
                editable.setSpan(new SubscriptSpan(), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            } else if (isSuperscript) {
                editable.setSpan(new SuperscriptSpan(), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            if (isUnderline) {
                editable.setSpan(new UnderlineSpan(), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            if (fontSize != 16f) {
                editable.setSpan(new AbsoluteSizeSpan((int) fontSize, true), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
    }

    private void inheritStyleFromAdjacentChars(Editable editable, int start, int end) {
        // 获取前后字符的样式
        StyleSpan[] beforeStyleSpans = editable.getSpans(start - 1, start, StyleSpan.class);
        StyleSpan[] afterStyleSpans = editable.getSpans(end, end + 1, StyleSpan.class);
        StrikethroughSpan[] beforeStrikeSpans = editable.getSpans(start - 1, start, StrikethroughSpan.class);
        StrikethroughSpan[] afterStrikeSpans = editable.getSpans(end, end + 1, StrikethroughSpan.class);
        ForegroundColorSpan[] beforeColorSpans = editable.getSpans(start - 1, start, ForegroundColorSpan.class);
        ForegroundColorSpan[] afterColorSpans = editable.getSpans(end, end + 1, ForegroundColorSpan.class);
        SubscriptSpan[] beforeSubscriptSpans = editable.getSpans(start - 1, start, SubscriptSpan.class);
        SubscriptSpan[] afterSubscriptSpans = editable.getSpans(end, end + 1, SubscriptSpan.class);
        SuperscriptSpan[] beforeSuperscriptSpans = editable.getSpans(start - 1, start, SuperscriptSpan.class);
        SuperscriptSpan[] afterSuperscriptSpans = editable.getSpans(end, end + 1, SuperscriptSpan.class);
        AbsoluteSizeSpan[] beforeSizeSpans = editable.getSpans(start - 1, start, AbsoluteSizeSpan.class);
        AbsoluteSizeSpan[] afterSizeSpans = editable.getSpans(end, end + 1, AbsoluteSizeSpan.class);

        // 继承粗体和斜体
        if (beforeStyleSpans.length > 0 && afterStyleSpans.length > 0) {
            for (StyleSpan span : beforeStyleSpans) {
                editable.setSpan(new StyleSpan(span.getStyle()), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }

        // 继承删除线
        if (beforeStrikeSpans.length > 0 && afterStrikeSpans.length > 0) {
            editable.setSpan(new StrikethroughSpan(), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        // 继承颜色
        if (beforeColorSpans.length > 0 && afterColorSpans.length > 0) {
            editable.setSpan(new ForegroundColorSpan(beforeColorSpans[0].getForegroundColor()), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        // 继承下划线
        if (beforeStrikeSpans.length > 0 && afterStrikeSpans.length > 0) {
            editable.setSpan(new UnderlineSpan(), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        // 继承字体大小
        if (beforeSizeSpans.length > 0 && afterSizeSpans.length > 0) {
            editable.setSpan(new AbsoluteSizeSpan(beforeSizeSpans[0].getSize(), true), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }

    private boolean isSameSubscriptStyle(Editable editable, int start, int end) {
        // 检查光标左右的下标样式是否相同
        SubscriptSpan[] beforeSubscriptSpans = editable.getSpans(start - 1, start, SubscriptSpan.class);
        SubscriptSpan[] afterSubscriptSpans = editable.getSpans(end, end + 1, SubscriptSpan.class);
        return beforeSubscriptSpans.length > 0 && afterSubscriptSpans.length > 0;
    }

    private boolean isSameSuperscriptStyle(Editable editable, int start, int end) {
        // 检查光标左右的上标样式是否相同
        SuperscriptSpan[] beforeSuperscriptSpans = editable.getSpans(start - 1, start, SuperscriptSpan.class);
        SuperscriptSpan[] afterSuperscriptSpans = editable.getSpans(end, end + 1, SuperscriptSpan.class);
        return beforeSuperscriptSpans.length > 0 && afterSuperscriptSpans.length > 0;
    }

    // 选中文本的样式设置
    public void setSelectionBold() {
        setSpanForSelection(new StyleSpan(Typeface.BOLD));
    }

    public void setSelectionItalic() {
        setSpanForSelection(new StyleSpan(Typeface.ITALIC));
    }

    public void setSelectionStrikethrough() {
        setSpanForSelection(new StrikethroughSpan());
    }

    public void setSelectionFontColor(String colorString) {
        setSpanForSelection(new ForegroundColorSpan(Color.parseColor(colorString)));
    }

    // 设置选中字符上标样式
    public void setSelectionSuperscript() {
        int selectionStart = getSelectionStart();
        int selectionEnd = getSelectionEnd();
        if (selectionStart != selectionEnd) {
            // 先清除下标样式
            clearSelectionSubscript();

            // 遍历选中区域，设置上标样式
            for (int i = selectionStart; i < selectionEnd; i++) {
                SuperscriptSpan[] superscriptSpans = getText().getSpans(i, i + 1, SuperscriptSpan.class);
                if (superscriptSpans.length == 0) {
                    // 如果没有上标样式，设置上标样式
                    getText().setSpan(new SuperscriptSpan(), i, i + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
        }
    }

    // 设置选中字符下标样式
    public void setSelectionSubscript() {
        int selectionStart = getSelectionStart();
        int selectionEnd = getSelectionEnd();
        if (selectionStart != selectionEnd) {
            // 先清除上标样式
            clearSelectionSuperscript();

            // 遍历选中区域，设置下标样式
            for (int i = selectionStart; i < selectionEnd; i++) {
                SubscriptSpan[] subscriptSpans = getText().getSpans(i, i + 1, SubscriptSpan.class);
                if (subscriptSpans.length == 0) {
                    // 如果没有下标样式，设置下标样式
                    getText().setSpan(new SubscriptSpan(), i, i + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
        }
    }

    public void setSelectionUnderline() {
        setSpanForSelection(new UnderlineSpan());
    }

    public void setSelectionFontSize(float size) {
        setSpanForSelection(new AbsoluteSizeSpan((int) size, true));
    }

    // 去除选中字符样式
    public void clearSelectionSubscript() {
        removeSpanForSelection(SubscriptSpan.class, 0);
    }

    public void clearSelectionSuperscript() {
        removeSpanForSelection(SuperscriptSpan.class, 0);
    }

    public void clearSelectionUnderline() {
        removeSpanForSelection(UnderlineSpan.class, 0);
    }

    public void clearSelectionFontSize() {
        removeSpanForSelection(AbsoluteSizeSpan.class, 0);
    }

    // 清除选中文本的指定样式
    public void clearSelectionBold() {
        removeSpanForSelection(StyleSpan.class, Typeface.BOLD);
    }

    public void clearSelectionItalic() {
        removeSpanForSelection(StyleSpan.class, Typeface.ITALIC);
    }

    public void clearSelectionStrikethrough() {
        removeSpanForSelection(StrikethroughSpan.class, 0);
    }

    public void clearSelectionFontColor() {
        removeSpanForSelection(ForegroundColorSpan.class, 0);
    }

    // 清除所有选中文本的样式
    public void clearAllSelectionStyles() {
        clearSelectionBold();
        clearSelectionItalic();
        clearSelectionStrikethrough();
        clearSelectionFontColor();
        clearSelectionFontSize();
        clearSelectionSubscript();
        clearSelectionSuperscript();
        clearSelectionUnderline();
    }

    // 为选中文本应用样式
    private void setSpanForSelection(Object span) {
        int selectionStart = getSelectionStart();
        int selectionEnd = getSelectionEnd();
        if (selectionStart != selectionEnd) {
            getText().setSpan(span, selectionStart, selectionEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
    }

    // 移除选中文本的某种样式
    private <T> void removeSpanForSelection(Class<T> type, int style) {
        int selectionStart = getSelectionStart();
        int selectionEnd = getSelectionEnd();
        if (selectionStart != selectionEnd) {
            T[] spans = getText().getSpans(selectionStart, selectionEnd, type);
            for (T span : spans) {
                int spanStart = getText().getSpanStart(span);
                int spanEnd = getText().getSpanEnd(span);
                getText().removeSpan(span);

                // 如果span开始于选中区域之前，重新应用该span到原始开始位置到选中区域开始位置
                if (spanStart < selectionStart) {
                    getText().setSpan(span, spanStart, selectionStart, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
                // 如果span结束于选中区域之后，重新应用该span从选中区域结束位置到原始结束位置
                if (spanEnd > selectionEnd) {
                    getText().setSpan(span, selectionEnd, spanEnd, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
        }
    }

    // 监听光标位置，返回光标所在位置的样式
    public String getCursorStyleInfo() {
        int cursorPos = getSelectionStart();
        Spannable spannable = getText();

        StringBuilder styleInfo = new StringBuilder();
        if (cursorPos < spannable.length()) {
            StyleSpan[] styleSpans = spannable.getSpans(cursorPos, cursorPos, StyleSpan.class);
            for (StyleSpan span : styleSpans) {
                if (span.getStyle() == Typeface.BOLD) {
                    styleInfo.append("Bold ");
                } else if (span.getStyle() == Typeface.ITALIC) {
                    styleInfo.append("Italic ");
                }
            }

            StrikethroughSpan[] strikeSpans = spannable.getSpans(cursorPos, cursorPos, StrikethroughSpan.class);
            if (strikeSpans.length > 0) {
                styleInfo.append("Strikethrough ");
            }

            ForegroundColorSpan[] colorSpans = spannable.getSpans(cursorPos, cursorPos, ForegroundColorSpan.class);
            if (colorSpans.length > 0) {
                styleInfo.append("Color: #" + Integer.toHexString(colorSpans[0].getForegroundColor()).toUpperCase() + " ");
            }
        }
        return styleInfo.toString();
    }
}