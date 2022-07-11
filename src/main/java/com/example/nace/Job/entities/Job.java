package com.example.nace.Job.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Job {
    public Job(){ }

    @Id
    private Long id;

    private String level;
    private String code;
    private String parent;

    private String description;

    @Column(length = 5000)
    private String thisItemIncludes;
    @Column(length = 10000)
    private String thisItemAlsoIncludes;

    private String rulings;
    @Column(length = 5000)
    private String thisItemExcludes;
    private String referenceToISIC4;

    public Job(Long id, String level, String code, String description){
        this.id = id;
        this.level = level;
        this.code = code;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRulings() {
        return rulings;
    }

    public void setRulings(String rulings) {
        this.rulings = rulings;
    }

    public String getThisItemExcludes() {
        return thisItemExcludes;
    }

    public void setThisItemExcludes(String thisItemExcludes) {
        this.thisItemExcludes = thisItemExcludes;
    }

    public String getReferenceToISIC4() {
        return referenceToISIC4;
    }

    public void setReferenceToISIC4(String referenceToISIC4) {
        this.referenceToISIC4 = referenceToISIC4;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThisItemIncludes() {
        return thisItemIncludes;
    }

    public void setThisItemIncludes(String thisItemIncludes) {
        this.thisItemIncludes = thisItemIncludes;
    }

    public String getThisItemAlsoIncludes() {
        return thisItemAlsoIncludes;
    }

    public void setThisItemAlsoIncludes(String thisItemAlsoIncludes) {
        this.thisItemAlsoIncludes = thisItemAlsoIncludes;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Job)) return false;

        Job job = (Job) o;

        if (!id.equals(job.id)) return false;
        if (getLevel() != null ? !getLevel().equals(job.getLevel()) : job.getLevel() != null) return false;
        if (getCode() != null ? !getCode().equals(job.getCode()) : job.getCode() != null) return false;
        return getParent() != null ? getParent().equals(job.getParent()) : job.getParent() == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (getLevel() != null ? getLevel().hashCode() : 0);
        result = 31 * result + (getCode() != null ? getCode().hashCode() : 0);
        result = 31 * result + (getParent() != null ? getParent().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Job{" +
                "Id =" + id +
                ", level='" + level + '\'' +
                ", code='" + code + '\'' +
                ", parent='" + parent + '\'' +
                '}';
    }
}
