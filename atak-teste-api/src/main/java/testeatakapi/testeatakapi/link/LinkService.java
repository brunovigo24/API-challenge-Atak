package testeatakapi.testeatakapi.link;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class LinkService {
    @Autowired
    private LinkRepository linkRepository;

    // Extrai títulos e URLs dos links do Google
    public List<Link> extractLinksAndReturnList(String searchTerm) {
        // Verifica se o termo de pesquisa está vazio
        if (searchTerm == null || searchTerm.isEmpty()) {
            throw new RuntimeException("O termo de pesquisa não pode estar vazio");
        }

        // Lista para armazenar os links extraídos
        List<Link> extractedLinks = new ArrayList<>();

        try {
            // Solicitação HTTP para o Google Search
            Document doc = Jsoup.connect("https://www.google.com/search?q=" + searchTerm).get();

            // Extração os elementos de link da página
            for (Element link : doc.select("a[href]")) {
                String title = link.text(); // Extrai o título do link
                String url = link.absUrl("href"); // Extrai  a URL absoluta do link

                // Verifica se o título e a URL são válidos antes de salvar na lista
                if (!title.isEmpty() && !url.isEmpty()) {
                    Link newLink = new Link();
                    newLink.setTitle(title);
                    newLink.setUrl(url);
                    extractedLinks.add(newLink);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Registro de erros, gravar em log, próximo commit...
        }

        // Retorna a lista de links extraídos
        return extractedLinks;
    }

    // Salvar um link no banco de dados
    public void saveLink(Link link) {
        linkRepository.save(link);
    }

    // Mostrar todos os links salvos no banco
    public List<Link> getAllLinks() {
        return linkRepository.findAll();
    }

    // Buscar um link por ID
    public Link getLinkById(Integer id) {
        return linkRepository.findById(id).orElse(null);
    }

    // Deletar um link por ID
    public void deleteLink(Integer id) {
        linkRepository.deleteById(id);
    }
}
