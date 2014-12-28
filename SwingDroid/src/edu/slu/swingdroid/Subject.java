package edu.slu.swingdroid;

import java.util.Objects;

/**
 *
 * @author JanKenneth
 */
public class Subject {

    private String subjectName;

    public Subject(String name) {
        subjectName = name;
    }

    public String getSubjectName() {
        return subjectName;
    }

    @Override
    public String toString() {
        return subjectName;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.subjectName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Subject other = (Subject) obj;
        if (!Objects.equals(this.subjectName, other.subjectName)) {
            return false;
        }
        return true;
    }

}
