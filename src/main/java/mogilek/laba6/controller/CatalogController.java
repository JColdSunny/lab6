package mogilek.laba6.controller;

import mogilek.laba6.entity.Book;
import mogilek.laba6.service.impl.CatalogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/catalogs")
public class CatalogController {

    @Autowired
    private CatalogServiceImpl catalogService;

    @GetMapping("/{id}/books")
    public List<Book> getBooksByCatalogId(
            @AuthenticationPrincipal UserDetails user,
            @PathVariable String id
    ) {
        return catalogService.getBooksByCatalogId(Long.valueOf(id), user);
    }

}
