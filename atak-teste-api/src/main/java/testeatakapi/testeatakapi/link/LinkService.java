package testeatakapi.testeatakapi.link;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;

@Service
public class LinkService {
    @Autowired
    private LinkRepository linkRepository;

    // Extrai títulos e URLs dos links do Google
    public void extractLinksFromGoogle() {
        try {
            // Solicitação HTTP para o Google Search
            Document doc = Jsoup.connect("https://www.google.com/search?q=termo_de_pesquisa").get();

            // Extração os elementos de link da página
            for (Element link : doc.select("a[href]")) {
                String title = link.text(); // Extrair o título do link
                String url = link.absUrl("href"); // Extrair a URL absoluta do link

                // Verificar se o título e a URL são válidos antes de salvar no banco de dados
                if (!title.isEmpty() && !url.isEmpty()) {
                    // Salvar os dados no banco de dados (você precisa criar a entidade Link para isso)
                    Link newLink = new Link();
                    newLink.setTitle(title);
                    newLink.setUrl(url);
                    saveLink(newLink);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Lidar com erros de conexão
            // Por exemplo, você pode registrar o erro, enviar notificações, etc.
        }
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
