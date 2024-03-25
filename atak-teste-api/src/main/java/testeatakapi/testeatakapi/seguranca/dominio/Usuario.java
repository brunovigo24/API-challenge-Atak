package testeatakapi.testeatakapi.seguranca.dominio;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import testeatakapi.testeatakapi.helpers.EntityId;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario extends EntityId  {

    @Column(nullable = false, unique = true)
    private String usuario;
    @Column(nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    private Papel papel;
}
