package testeatakapi.testeatakapi.link;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import testeatakapi.testeatakapi.helpers.EntityId;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "link")
public class Link extends EntityId {

    private String title;
    private String url;

}
