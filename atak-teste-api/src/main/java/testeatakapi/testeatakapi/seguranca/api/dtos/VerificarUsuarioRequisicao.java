package testeatakapi.testeatakapi.seguranca.api.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class VerificarUsuarioRequisicao {
    @NotNull
    public String usuario;
    @NotNull
    @Size(min = 6)
    public String senha;
}
