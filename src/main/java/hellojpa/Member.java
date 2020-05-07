package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
// 엔터티 테이블 맵핑
// @Table(name = "User")
public class Member {

    @Id
    private Long id;
    // 테이블 칼럼이 다를 때 맵핑
    // @Column(name= "username")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
