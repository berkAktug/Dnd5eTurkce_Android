package tr.dnd5e.dnd5eturkce;

import android.support.annotation.NonNull;
@Deprecated
public class Feats implements Comparable<Feats>{

    private String name;
    private String description;

    public Feats(String name, String description) {
        this.setName(name);
        this.setDescription(description);
    }

    @Override
    public int compareTo(@NonNull Feats o) {
        return 0;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
