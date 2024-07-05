package test.spisoktest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.spisoktest.entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
}
