package agrobank.task.entity;

import javax.persistence.*;

@Entity
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "city_id_seq")
    private Long id;
    private Integer code;
    private String name;

    public City() {
    }

    public City(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public City(Long id, Integer code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
