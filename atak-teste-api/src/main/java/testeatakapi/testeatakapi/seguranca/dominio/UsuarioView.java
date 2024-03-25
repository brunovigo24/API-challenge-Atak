package testeatakapi.testeatakapi.seguranca.dominio;

import lombok.*;
import testeatakapi.testeatakapi.helpers.EntityId;

@NoArgsConstructor
@Getter
@Setter
public class UsuarioView extends EntityId {

    private String usuario;
    private String papel;

    public UsuarioView(Integer id, String usuario, String name) {
        super();
    }
}
