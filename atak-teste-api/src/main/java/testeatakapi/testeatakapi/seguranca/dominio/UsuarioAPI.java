package testeatakapi.testeatakapi.seguranca.dominio;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/admin/usuarios")
@AllArgsConstructor
public class UsuarioAPI {

    private UsuarioRepositorio usuarioRepositorio;

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioView> findById(@PathVariable Integer id) {
        return usuarioRepositorio.findById(id)
                .map(record -> new UsuarioView(record.getId(), record.getUsuario(), record.getPapel().name()))
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        usuarioRepositorio.deleteById(id);
    }
}
