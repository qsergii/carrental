package com.epam.carrental.tags;

import com.epam.carrental.LanguageBundle;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Random;

/**
 * Class for tag
 * to generate a quote of car
 */
public class Quote extends TagSupport {

    private static final String[][] quotesEn = {
            {"The cars we drive say a lot about us.", "Alexandra Paul"},
            {"If all the cars in the United States were placed end to end, it would probably be Labor Day Weekend.", "Doug Larson"},
            {"I spent a lot of money on booze, birds and fast cars. The rest I just squandered.", "George Best"},
            {"Everything in life is somewhere else, and you get there in a car.", "E. B. White"},
            {"It's a never ending battle of making your cars better and also trying to be better yourself.", "Dale Earnhardt"},
            {"Cars and cameras are the two things I let myself be materialistic about. I don't care about other stuff.", "Louis C. K."},
            {"There's three things men always talk about - women, sports, and cars.", "Mario Lopez"},
            {"We aren't addicted to oil, but our cars are.", "James Woolsey"},
            {"A car for every purse and purpose.", "Alfred P. Sloan"},
            {"It's like driving a car at night. You never see further than your headlights, but you can make the whole trip that way.", "E. L. Doctorow"},
    };

    private static final String[][] quotesUa = {
            {"Самий правдивий тест на визначення темпераменту - це жовте світло світлофора.", "Олександр Пауль"},
            {"Коли я став водієм, то став зовсім іншим пішоходом.", "Максим Лауненко"},
            {"Багато хто вміє кататися на автомобілі, але мало хто вміє на ньому їздити...", "Жорж Бест"},
            {"Водій, пам'ятай: навіть в бардачку повинен бути порядок!", "Гельмульд Вайт"},
            {"Немає більш обережного водія, чим той, що забув вдома документи.", "Даль Ешгарт"},
            {"Найулюбленіший автомобіль той, який ще не створений", "Енцо Феррарі"},
            {"Ми не обираємо дорогу. Щось всередині нас змушує нас обрати той чи інший шлях.", "О. Генрі"},
            {"Жодної хвилини життя неможливо купити за готівку.", "О. Генрі"},
            {"Авто для кожного гаманця і цілі.", "Альфред Слоан"},
            {"Це як подоружвати вночі - ти на бачиш далі світла фар, проте так здійснюєш мрію!", "І. Лікарський"},
    };

    @Override
    public int doStartTag() {
        String[][] quotes;
        switch (LanguageBundle.getLanguage()) {
            case "en":
                quotes = quotesEn;
                break;
            case "ua":
                quotes = quotesUa;
                break;
            default:
                quotes = quotesEn;
        }

        int quoteSize = quotes.length;
        int quoteIndex = new Random().nextInt(quoteSize);
        String quote = quotes[quoteIndex][0];
        String quoteAuthor = quotes[quoteIndex][1];

        JspWriter out = pageContext.getOut();
        try {
            out.print("    <section>\n" +
                    "        <div class=\"container blockquote bg-light\">\n" +
                    "            <p id=\"quote\">\"" + quote + "\"</p>\n" +
                    "            <p id=\"quote_author\">" + quoteAuthor + "</p>\n" +
                    "        </div>\n" +
                    "    </section>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return SKIP_BODY;
    }
}
