package net.ere.tmp.maven_gwt.spring.impl;

import net.ere.tmp.maven_gwt.spring.DataService;
import net.ere.tmp.maven_gwt.spring.TimeService;
import net.ere.tmp.maven_gwt.spring.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class DataServiceImpl implements DataService {

    @PersistenceContext
    private EntityManager entityManager;

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

        entityManager.flush();
        entityManager.clear();

        try {
            Author pa = entityManager.createQuery("select a from Author a where a.id = :id", Author.class) //
                    .setParameter("id", author.getId()).getSingleResult();

//            throwException();
        } catch (RuntimeException e) {
            System.out.println("------> " + e.getMessage());
            throw e;
        }
    }

    private void throwException() {
        throw new NullPointerException("Sollte ein Rollback provozeren");
    }

    @Override
    public List<Author> getAllAuthors() {
        return entityManager.createQuery("select a from Author a join fetch a.blogs", Author.class).getResultList();
    }
}
