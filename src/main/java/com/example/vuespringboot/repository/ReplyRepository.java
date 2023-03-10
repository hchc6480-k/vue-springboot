package com.example.vuespringboot.repository;

import com.example.vuespringboot.domain.Reply;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class ReplyRepository {

    private final EntityManager em;

    public void save(Reply reply) {
        if (reply.getReplyNo() == null) em.persist(reply);
        else em.merge(reply);

    }

    public void delete(Reply reply) {
        em.createQuery("delete from Reply r where reply_no = :replyNo and doc_no = :docNo")
                .setParameter("replyNo", reply.getReplyNo())
                .setParameter("docNo", reply.getDocNo())
                .executeUpdate();

    }

    public List<Reply> findAll(Long id) {
        return em.createQuery("select r from Reply r where doc_no = :id", Reply.class)
                .setParameter("id",id)
                .getResultList();
    }

}
