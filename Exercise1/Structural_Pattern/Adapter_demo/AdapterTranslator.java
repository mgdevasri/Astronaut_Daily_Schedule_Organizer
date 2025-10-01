import java.util.*;

interface Translator {
    String translate(String text, String lang);
}


class OldTranslator {
    private Map<String, String> tamilDict;
    private Map<String, String> hindiDict;

    public OldTranslator() {
        tamilDict = new HashMap<>();
        hindiDict = new HashMap<>();

        tamilDict.put("hello", "Vanakkam");
        tamilDict.put("good", "Nall");
        tamilDict.put("morning", "Kaalai");
        tamilDict.put("how", "Eppadi");
        tamilDict.put("are", "Irukkirirgala");
        tamilDict.put("you", "Neenga");

        hindiDict.put("hello", "Namaste");
        hindiDict.put("good", "Achha");
        hindiDict.put("morning", "Subah");
        hindiDict.put("how", "Kaise");
        hindiDict.put("are", "Ho");
        hindiDict.put("you", "Aap");
    }

    public String toTamil(String text) {
        StringBuilder sb = new StringBuilder();
        String[] words = text.toLowerCase().split("\\s+");
        boolean unsupported = false;

        for(String word : words) {
            if(tamilDict.containsKey(word)) {
                sb.append(tamilDict.get(word)).append(" ");
            } else {
                sb.append("[").append(word).append("]").append(" "); // mark unsupported
                unsupported = true;
            }
        }
        String result = sb.toString().trim() + " (Tamil)";
        if(unsupported) result += " -- Some words are not supported!";
        return result;
    }

    public String toHindi(String text) {
        StringBuilder sb = new StringBuilder();
        String[] words = text.toLowerCase().split("\\s+");
        boolean unsupported = false;

        for(String word : words) {
            if(hindiDict.containsKey(word)) {
                sb.append(hindiDict.get(word)).append(" ");
            } else {
                sb.append("[").append(word).append("]").append(" "); // mark unsupported
                unsupported = true;
            }
        }
        String result = sb.toString().trim() + " (Hindi)";
        if(unsupported) result += " -- Some words are not supported!";
        return result;
    }

    public Set<String> getSupportedWords() {
        return tamilDict.keySet(); 
    }
}

// Adapter
class TranslatorAdapter implements Translator {
    private OldTranslator oldTranslator = new OldTranslator();

    @Override
    public String translate(String text, String lang) {
        switch(lang.toLowerCase()) {
            case "tamil":
            case "ta": return oldTranslator.toTamil(text);
            case "hindi":
            case "hi": return oldTranslator.toHindi(text);
            default: return "Language not supported!";
        }
    }

    public Set<String> getSupportedWords() {
        return oldTranslator.getSupportedWords();
    }
}

// Client
public class AdapterTranslator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TranslatorAdapter translator = new TranslatorAdapter();

        System.out.println("Welcome to Tamil-Hindi Translator!");
        System.out.println("Supported words: " + translator.getSupportedWords());
        boolean exit = false;

        while(!exit) {
            System.out.print("\nEnter text to translate (or type 'exit' to quit): ");
            String text = sc.nextLine().trim();

            if(text.equalsIgnoreCase("exit")) {
                exit = true;
                System.out.println("Exiting translator. Goodbye!");
                break;
            }

            System.out.print("Enter language (tamil/hindi): ");
            String lang = sc.nextLine().trim();

            String result = translator.translate(text, lang);
            System.out.println("Translated: " + result);
        }

        sc.close();
    }
}
