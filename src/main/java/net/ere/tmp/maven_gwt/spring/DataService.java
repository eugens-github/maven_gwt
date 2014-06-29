package net.ere.tmp.maven_gwt.spring;


import net.ere.tmp.maven_gwt.spring.model.Author;

import java.util.List;

public interface DataService {

    Author findAuthor(Long id);

    void persistAuthor(Author author);

    List<Author> getAllAuthors();
}
