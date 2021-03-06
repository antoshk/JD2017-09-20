package by.it.likhanau.jd02_05;


import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public enum ResMng {
    INSTANCE;
    private ResourceBundle bundle;
    private String path = "by.it.likhanau.jd02_05.res.message";

    String getString(String key) {
        return bundle.getString(key);
    }

    ResMng() {
        bundle = ResourceBundle.getBundle(path, Locale.getDefault());
    }

    void SetLocale(Locale locale) {
        bundle = ResourceBundle.getBundle(path, locale);
        DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, locale);
        String s = df.format(new Date());
        System.out.println(s);
    }


}
