import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Dimension {
    public ArrayList<String> alts;
    public String dimension;
    public String hierarchy;
    public String id;
    public String key;
    public Object keywords;
    public String name;
    public String profile;
    public String slug;

    public String getKey() {
        return key;
    }

    public Object getKeywords() {
        return keywords;
    }
}