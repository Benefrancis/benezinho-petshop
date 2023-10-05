package br.com.fiap.petshop.domain.repository;

import br.com.fiap.petshop.domain.entity.Documento;
import br.com.fiap.petshop.domain.entity.animal.Animal;
import br.com.fiap.petshop.domain.entity.servico.Servico;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class DocumentoRepository implements Repository<Documento, Long> {

    private static final AtomicReference<DocumentoRepository> instance = new AtomicReference<>();
    private EntityManager manager;

    private DocumentoRepository(EntityManager manager) {
        this.manager = manager;
    }

    public static DocumentoRepository build(EntityManager manager) {
        DocumentoRepository result = instance.get();
        if (Objects.isNull( result )) {
            DocumentoRepository repo = new DocumentoRepository( manager );
            if (instance.compareAndSet( null, repo )) {
                result = repo;
            } else {
                result = instance.get();
            }
        }
        return result;
    }


    @Override
    public List<Documento> findAll() {
        List<Documento> list = manager.createQuery("FROM Documento").getResultList();
        return list;
    }

    @Override
    public Documento findById(Long id) {
        Documento documento = manager.find(Documento.class, id);
        return documento;
    }

    @Override
    public List<Documento> findByTexto(String texto) {
        String jpql = "SELECT d FROM Documento d  where lower(d.numero) LIKE CONCAT('%',lower(:nome),'%')";
        Query query = manager.createQuery(jpql);
        query.setParameter("nome", texto);
        List<Documento> list = query.getResultList();
        return list;
    }

    @Override
    public Documento persist(Documento documento) {
        manager.getTransaction().begin();
        manager.persist(documento);
        manager.getTransaction().commit();
        return documento;
    }

    @Override
    public Documento update(Documento documento) {
        manager.getTransaction().begin();
        documento = manager.merge(documento);
        manager.getTransaction().commit();

        return documento;
    }

    @Override
    public boolean delete(Documento documento) {
        manager.getTransaction().begin();
        try {
            documento = manager.merge(documento);
            manager.remove(documento);
            manager.getTransaction().commit();
            return true; // Indica que a exclusão foi bem-sucedida
        } catch (Exception e) {
            manager.getTransaction().rollback();
            return false; // Indica que a exclusão falhou
        }
    }
}
