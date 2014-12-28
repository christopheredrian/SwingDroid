package edu.slu.swingdroid;

import java.io.File;
import java.util.List;
import java.util.*;

/**
 *
 * @author Avila, Jeremy Raymund T.
 */
public class Requirement {

    private int requirementId;
    private Calendar dueDate;
    private String description;
    private Type type;
    private Subject subject;
    private List<File> attachments;
    private boolean active;

    public Requirement(String description, Calendar dueDate, Type type, Subject subject) {
        this.dueDate = dueDate;
        this.description = description;
        this.type = type;
        this.subject = subject;
        this.attachments = new ArrayList<>();
    } 
    
    

    /**
     * Gets the ID Requirement.
     * @return the requirementId
     */
    public int getRequirementId() {
        return requirementId;
    } 

    /**
     * Sets the ID Requirement.
     * @param requirementId the requirementId to set requirementId
     */
    public void setRequirementId(int requirementId) {
        this.requirementId = requirementId;
    }

    /**
     * Gets the Due Date of the requirement.
     * @return the dueDate
     */
    public Calendar getDueDate() {
        return dueDate;
    }

    /**
     * Sets the Due Date of the requirement.
     * @param dueDate the dueDate to set dueDate
     */
    public void setDueDate(Calendar dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Gets the Description of the requirement.
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the  the Description of the requirement.
     * @param description the description to set description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the Type of the requirement.
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * Sets the Type of the requirement.
     * @param type the type to set type
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * Gets the Subject of the requirement.
     * @return the subject
     */
    public Subject getSubject() {
        return subject;
    }

    /**
     * Sets the Subject of the requirement.
     * @param subject the subject to set subject
     */
    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    /**
     * Adds the File.
     * @param file path of the file to be attach
     */
    public void addAttachment(File file){
        this.attachments.add(file); 
    }

    /**
     * Removes the File.
     * @param index of the attachment
     */
    public void removeAttachment(int index){
        this.attachments.remove(index);
    }
    
    /**
     * Returns active if the requirement is active.
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets active if the requirement is active or not.
     * @param active the active to set active
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    public List<File> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<File> attachments) {
        this.attachments = attachments;
    }

    @Override
    public String toString() {
        return description;
    }
    
}