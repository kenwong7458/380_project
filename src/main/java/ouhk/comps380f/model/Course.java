package ouhk.comps380f.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Map;


@Entity
@Table (name = "courses")
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "courseId")
    private long id;

    //@Column(name = "name")
    private String courseName;

    private String courseDescription;

    

    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL, orphanRemoval = true)
     private Map <String, Attachment> attachments = new Hashtable<>();
    
    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL, orphanRemoval = true)
     private List <CourseLecturer> lecturer = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

   public Attachment getAttachment(String name) {
        return this.attachments.get(name);
    }

    public Collection<Attachment> getAttachments() {
        return this.attachments.values();
    }

    public void addAttachment(Attachment attachment) {
        this.attachments.put(attachment.getName(), attachment);
    }

    public int getNumberOfAttachments() {
        return this.attachments.size();
    }

    public boolean hasAttachment(String name) {
        return this.attachments.containsKey(name);
    }
    
     public Attachment deleteAttachment(String name) {
        return this.attachments.remove(name);
    }
     
     
//    public void deleteAttachment(Attachment attachment) {
//        attachment.setCourseId(null);
//        this.attachments.remove(attachment);
//    }

    public List<CourseLecturer> getLecturer() {
        return lecturer;
    }

    public void setLecturer(List<CourseLecturer> lecturer) {
        this.lecturer = lecturer;
    }
}


