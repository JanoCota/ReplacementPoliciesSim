package replacementpoliciessim.replacer;

import java.util.Objects;

/**
 * @author JanoCota
 */
public class Page {

    public String ID;

    public Page(String ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return ID;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.ID);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Page other = (Page) obj;
        if (!Objects.equals(this.ID, other.ID)) {
            return false;
        }
        return true;
    }

}
