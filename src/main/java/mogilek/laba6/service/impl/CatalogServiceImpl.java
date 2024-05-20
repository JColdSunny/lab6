package mogilek.laba6.service.impl;

import mogilek.laba6.entity.Book;
import mogilek.laba6.entity.Catalog;
import mogilek.laba6.repository.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogServiceImpl {

    @Autowired
    private CatalogRepository catalogRepository;

    public List<Book> getBooksByCatalogId(Long catalogId, UserDetails user) {
        Catalog catalog = catalogRepository.findById(catalogId).orElseThrow();
        if (!catalog.getUser().getUsername().equals(user.getUsername())) {
            throw new AccessDeniedException("You do not have permission to access this catalog");
        }

        return catalog.getBooks();
    }

}
