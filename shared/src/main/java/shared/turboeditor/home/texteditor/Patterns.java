

package shared.turboeditor.home.texteditor;

import java.util.regex.Pattern;

public class Patterns {
    public static final Pattern GENERAL_STRINGS = Pattern.compile("\"(.*?)\"|'(.*?)'");

    public static final Pattern NUMBERS = Pattern.compile(
            "(\\b(\\d*[.]?\\d+)\\b)");

    public static final Pattern SYMBOLS = Pattern.compile(
            "(!|,|\\(|\\)|\\+|\\-|\\*|<|>|=|\\.|\\?|;|\\{|\\}|\\[|\\]|\\|)");

    public static final Pattern NUMBERS_OR_SYMBOLS = Pattern.compile(NUMBERS.pattern()+"|"+SYMBOLS.pattern());

    public static final Pattern GENERAL_REGISTERS = Pattern.compile(
            "(?<=\\b)((eax)|(ax)|(ah)|(al)|(ebx)|(bx)|(bh)|(bl)|(ecx)|(cx)|(ch)|(cl)|("
                    + "edx)|(dx)|(dh)|(dl)|(esi)|(edi)|(esp)|(ebp))(?=\\b)", Pattern.CASE_INSENSITIVE);

    public static final Pattern INSTRUCTIONS = Pattern.compile(
            "(?<=\\b)((mov)|(call)|(add)|(xor)|(sub)|(cmp)|(jge)|(neg)|(div)|(mul)|(jb)|("
                    + "jmp)|(inc)|(dec)|(je)|(loop)|(ja)|(ret)|(push)|(pop))(?=\\b)", Pattern.CASE_INSENSITIVE);

    public static final Pattern CAPS_INSTRUCTIONS = Pattern.compile(
            "(?<=\\b)((EXTERN)|(PROC)|(ENDP)|(END))(?=\\b)");

    public static final Pattern GENERAL_COMMENTS = Pattern.compile(
            "/\\*(?:.|[\\n\\r])*?\\*/|(?<!:)//.*|#.*");

    public static final Pattern GENERAL_COMMENTS_NO_SLASH = Pattern.compile(
            "/\\*(?:.|[\\n\\r])*?\\*/|#.*");

    public static final Pattern LINK = android.util.Patterns.WEB_URL;
}
