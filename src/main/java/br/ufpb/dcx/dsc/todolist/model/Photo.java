package br.ufpb.dcx.dsc.todolist.model;

import javax.persistence.*;

@Entity
@Table(name = "tb_photo")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "photo_id")
    private Long photoId;

    @Column(name = "url")
    private String URL;

    @OneToOne(mappedBy = "photo")
    private User user;

    public Photo(String URL) {
        this.URL = URL;
    }

    public Photo() {
    }

    public Long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + photoId +
                ", URL='" + URL + '\'' +
                '}';
    }
}
