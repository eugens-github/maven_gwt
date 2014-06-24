package net.ere.tmp.maven_gwt.spring;


import net.ere.tmp.maven_gwt.spring.model.Author;

public interface DataService {

    Author findAuthor(Long id);

    void persistAuthor(Author author);
}
