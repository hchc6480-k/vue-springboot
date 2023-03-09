package com.example.vuespringboot.repository;

import com.example.vuespringboot.domain.Reply;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReplyRepository {

    private final EntityManager em;

    public void save(Reply reply) {
        em.persist(reply);
    }

    public List<Reply> findAll(Long id) {
        return em.createQuery("select r from Reply r where doc_no = :id", Reply.class)
                .setParameter("id",id)
                .getResultList();
    }

}
