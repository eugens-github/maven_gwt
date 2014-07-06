package net.ere.tmp.maven_gwt.spring.impl;

import net.ere.tmp.maven_gwt.spring.DataService;
import net.ere.tmp.maven_gwt.spring.TimeService;
import net.ere.tmp.maven_gwt.spring.model.Author;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Named("dataService")
@Transactional
public class DataServiceImpl implements DataService {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private TimeService timeService;

    @Override
    public Author findAuthor(Long id) {
        return entityManager.find(Author.class, id);
    }

    @Override
    public void persistAuthor(Author author) {
        author.setCreatedAt(timeService.getTime());
        entityManager.persist(author);
    }

    @Override
    public List<Author> getAllAuthors() {
        return entityManager.createQuery("select a from Author a join fetch a.blogs", Author.class).getResultList();
    }
}
