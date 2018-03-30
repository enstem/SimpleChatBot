import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

import static javax.swing.UIManager.put;

/**
 * Created by дмитро on 29.03.2018.
 */
public class SimpleBot {
    Pattern pattern; // for regexp
    Random random; // for random answers
    Date date; // for date and time

    public SimpleBot() {
        random = new Random();
        date = new Date();
    }

    final String[] COMMON_PHRASES = {
            "Не можу підтримати дану розмову",
            "Перед тим як писати/говорити завжди краще подумати",
            "Приємно коли текст без орфографічних помилок",
            "Боюсь що Ви щось не договорюєте"};
    final String[] ELUSIVE_ANSWERS = {
            "Це не просте запитання",
            "Не впевнений, що зможу відповісти на це запитання.",
            "Може поговоримо про щось інше?",
            "Не впевнений, що вам сподобається моя відповідь",
            "Повірте, я і сам хотів би це знати.",
            "Вы дійсно хочете це знати ?",
            "Впевнений, що ви самі знаєте відповідь",
            "Давайте збережем інтригу?"};
    final Map<String, String> PATTERNS_FOR_ANALYSIS = new HashMap<String, String>() {{
        // hello
        put("хай", "hello");
        put("привіт", "hello");
        put("добрий\\s.*день", "hello");

        // who
        put("хто\\s.*ти", "who");
        put("ти\\s.*хто", "who");
        // name
        put("як\\s.*звати", "name");
        put("как\\s.*ім'я", "name");
        put("є\\s.*ім'я", "name");
        put("яке\\s.*ім'я", "name");
        // howareyou
        put("як\\s.*справи", "howareyou");
        put("як\\s.*життя", "howareyou");
        put("як\\s.*ти", "howareyou");
        // whatdoyoudoing
        put("чому\\s.*тут", "whatdoyoudoing");
        put("причина\\s.*тут", "whatdoyoudoing");
        put("що\\s.*робиш", "whatdoyoudoing");
        put("чим\\s.*займаєшся", "whatdoyoudoing");
        // whatdoyoulike
        put("що\\s.*подобається", "whatdoyoulike");
        put("що\\s.*любиш", "whatdoyoulike");
        // iamfeelling
        put("здається", "iamfeelling");
        put("відчуваю", "iamfeelling");

        // yes
        put("^так", "yes");
        put("^та", "yes");
        put("згідний", "yes");
        // whattime
        put("яка\\s.*година", "whattime");
        put("який\\s.*час", "whattime");
        put("котра\\s.*година", "whattime");
        put("котрий\\s.*час", "whattime");
        // bye
        put("прощавай", "bye");
        put("побачимось", "bye");
        put("до\\s.*побачення", "bye");
    }};
    final Map<String, String> ANSWERS_BY_PATTERNS = new HashMap<String, String>() {{
        put("hello", "Привіт, радий Вас бачити.");
        put("who", "Я звичайний чат-бот.");
        put("name", "Називайте мене Чатті :)");
        put("howareyou", "Дякую, що цікавитесь. В мене все добре.");
        put("whatdoyoudoing", "Я пробую спілкуватись з людьми.");
        put("whatdoyoulike", "Мені подобається помагати людям");
        put("iamfeelling", "Як давно це почалось? Роскажіть детальніше.");
        put("yes", "Я прийняв вашу відповідь");
        put("bye", "До побачення. Надіюсь, що скоро побачимось.");
    }};


    public String sayInReturn(String msg, boolean ai) {
        String say = (msg.trim().endsWith("?"))?
                ELUSIVE_ANSWERS[random.nextInt(ELUSIVE_ANSWERS.length)]:
                COMMON_PHRASES[random.nextInt(COMMON_PHRASES.length)];
        if (ai) {
            String message =
                    String.join(" ", msg.toLowerCase().split("[ {,|.}?]+"));
            for (Map.Entry<String, String> o : PATTERNS_FOR_ANALYSIS.entrySet()) {
                pattern = Pattern.compile(o.getKey());
                if (pattern.matcher(message).find())
                    if (o.getValue().equals("whattime")) return date.toString();
                    else return ANSWERS_BY_PATTERNS.get(o.getValue());
            }
        }
        return say;
    }
}
