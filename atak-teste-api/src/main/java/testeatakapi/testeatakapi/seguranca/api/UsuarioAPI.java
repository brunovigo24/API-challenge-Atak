package testeatakapi.testeatakapi.seguranca.api;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import testeatakapi.testeatakapi.seguranca.api.dtos.UsuarioView;
import testeatakapi.testeatakapi.seguranca.dominio.UsuarioRepositorio;

@RestController
@RequestMapping("/admin/usuarios")
@AllArgsConstructor
public class UsuarioAPI {

    private UsuarioRepositorio usuarioRepositorio;

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioView> findById(@PathVariable long id) {
        return usuarioRepositorio.findById(id)
                .map(record -> new UsuarioView(record.getId(), record.getUsuario(), record.getPapel().name()))
                .map(record -> ResponseEntity.ok(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        usuarioRepositorio.deleteById(id);
    }
}
