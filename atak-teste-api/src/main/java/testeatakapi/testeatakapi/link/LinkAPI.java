package testeatakapi.testeatakapi.link;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/links")
public class LinkAPI {

    @Autowired
    private final LinkService linkService;

    public LinkAPI(LinkService linkService) {
        this.linkService = linkService;
    }

    /*@GetMapping("/extract-links")
    public void extractLinks(@RequestParam String searchTerm) {
        linkService.extractLinksFromGoogle(searchTerm);
    }
    Envie uma solicitação GET para http://localhost:8080/api/links/extract-links?searchTerm=termo_de_pesquisa,
    onde termo_de_pesquisa é o termo que você deseja pesquisar no Google.
    Isso vai acionar o processo de extração de links do Google e os salvará no banco de dados.
     */

    @GetMapping("/extract-links")
    public List<LinkDTO> extractLinks(@RequestParam String searchTerm) {
        return linkService.extractLinks(searchTerm);
    }

    @GetMapping
    public List<Link> getAllLinks() {
        return linkService.getAllLinks();
    }
    /*Envie uma solicitação GET para http://localhost:8080/api/links para recuperar todos os links salvos no banco de dados.
     */

    @GetMapping("/{id}")
    public Link getLinkById(@PathVariable Integer id) {
        return linkService.getLinkById(id);
    }
    /*Envie uma solicitação GET para http://localhost:8080/api/links/{id}, onde {id} é o ID do link que você deseja recuperar.
     */

    @DeleteMapping("/{id}")
    public void deleteLink(@PathVariable Integer id) {
        linkService.deleteLink(id);
    }
    /*Envie uma solicitação DELETE para http://localhost:8080/api/links/{id}, onde {id} é o ID do link que você deseja excluir.
     */
}
