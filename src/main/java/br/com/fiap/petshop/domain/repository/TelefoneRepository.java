package br.com.fiap.petshop.domain.repository;

import br.com.fiap.petshop.domain.entity.Telefone;
import br.com.fiap.petshop.domain.entity.servico.Servico;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class TelefoneRepository implements Repository<Telefone, Long> {

    private static final AtomicReference<TelefoneRepository> instance = new AtomicReference<>();
    private EntityManager manager;

    private TelefoneRepository(EntityManager manager) {
        this.manager = manager;
    }

    public static TelefoneRepository build(EntityManager manager) {
        TelefoneRepository result = instance.get();
        if (Objects.isNull( result )) {
            TelefoneRepository repo = new TelefoneRepository( manager );
            if (instance.compareAndSet( null, repo )) {
                result = repo;
            } else {
                result = instance.get();
            }
        }
        return result;
    }


    @Override
    public List<Telefone> findAll() {
        List<Telefone> list = manager.createQuery("FROM Telefone").getResultList();
        return list;
    }

    @Override
    public Telefone findById(Long id) {
        Telefone telefone = manager.find(Telefone.class, id);
        return telefone;
    }

    @Override
    public List<Telefone> findByTexto(String texto) {
        String jpql = "SELECT t FROM Telefone t  where lower(t.numero) LIKE CONCAT('%',lower(:nome),'%')";
        Query query = manager.createQuery(jpql);
        query.setParameter("nome", texto);
        List<Telefone> list = query.getResultList();
        return list;
    }

    @Override
    public Telefone persist(Telefone telefone) {
        manager.getTransaction().begin();
        manager.persist(telefone);
        manager.getTransaction().commit();
        return telefone;
    }

    @Override
    public Telefone update(Telefone telefone) {
        manager.getTransaction().begin();
        telefone = manager.merge(telefone);
        manager.getTransaction().commit();

        return telefone;
    }

    public boolean delete(Telefone telefone) {
        manager.getTransaction().begin();
        try {
            telefone = manager.merge(telefone);
            manager.remove(telefone);
            manager.getTransaction().commit();
            return true; // Indica que a exclusão foi bem-sucedida
        } catch (Exception e) {
            manager.getTransaction().rollback();
            return false; // Indica que a exclusão falhou
        }
    }
}
