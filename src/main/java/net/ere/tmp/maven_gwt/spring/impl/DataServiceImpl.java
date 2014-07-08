package net.ere.tmp.maven_gwt.spring.impl;

import net.ere.tmp.maven_gwt.spring.DataService;
import net.ere.tmp.maven_gwt.spring.TimeService;
import net.ere.tmp.maven_gwt.spring.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

// @Named("dataService")
@Service
@Transactional
public class DataServiceImpl implements DataService {

    @PersistenceContext
    private EntityManager entityManager;

    // @Inject
    @Autowired
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
