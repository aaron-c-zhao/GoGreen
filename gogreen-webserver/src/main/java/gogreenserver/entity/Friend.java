package gogreenserver.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "friend_add")
public class Friend {

    @Id
    @Column(name = "add_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id;

    @Column(name = "add_data")
    private LocalDateTime addTime;

    @Column(name = "username1")
    private String userName;

    @Column(name = "username2")
    private String friendName;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public LocalDateTime getAddTime() {
        return addTime;
    }

    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    @Override
    public String toString() {
        return "Friend{"
            + "Id=" + Id
            + ", addTime=" + addTime
            + ", userName='" + userName + '\''
            + ", friendName='" + friendName + '\''
            + '}';
    }
}
