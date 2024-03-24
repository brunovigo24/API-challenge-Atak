package testeatakapi.testeatakapi.link;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/link")
public class LinkAPI {

    @Autowired
    private final LinkService linkService;

    public LinkAPI(LinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping("/search")
    public void extractLinks() {
        linkService.extractLinksFromGoogle();
    }
}
