package testeatakapi.testeatakapi.seguranca.api;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import testeatakapi.testeatakapi.seguranca.api.dtos.RegistrarUsuarioRequisicao;
import testeatakapi.testeatakapi.seguranca.api.dtos.VerificarUsuarioRequisicao;
import testeatakapi.testeatakapi.seguranca.dominio.Papel;
import testeatakapi.testeatakapi.seguranca.dominio.Usuario;
import testeatakapi.testeatakapi.seguranca.dominio.UsuarioRepositorio;

@RestController
@RequestMapping("/publico/usuarios")
@AllArgsConstructor
public class RegistroUsuarioControlador {

    private UsuarioRepositorio usuarioRepositorio;
    private PasswordEncoder codificadorDeSenhas;

    @PostMapping("/login")
    public ResponseEntity verificar(@RequestBody @Valid VerificarUsuarioRequisicao requisicao) {
        var usuario = usuarioRepositorio.findByUsuario(requisicao.getUsuario());
        if (usuario.isPresent()) {
            if (codificadorDeSenhas.matches(requisicao.getSenha(), usuario.get().getSenha())) {
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid RegistrarUsuarioRequisicao requisicao) {

        if (requisicao.senhasNaoConferem()) {
            return ResponseEntity.badRequest().body("Senha diferente da confirmação");
        }

        if (usuarioRepositorio.findByUsuario(requisicao.getUsuario()).isPresent()) {
            return ResponseEntity.badRequest().body("Nome de usuário indisponível!");
        }

        var usuarioSalvo = usuarioRepositorio.save(Usuario
                .builder()
                .usuario(requisicao.getUsuario())
                .senha(codificadorDeSenhas.encode(requisicao.getSenha()))
                .papel(Papel.USUARIO)
                .build());

        if (usuarioSalvo != null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.internalServerError().build();
    }
}
