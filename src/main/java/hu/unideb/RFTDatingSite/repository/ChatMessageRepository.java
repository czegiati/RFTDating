package hu.unideb.RFTDatingSite.repository;

import hu.unideb.RFTDatingSite.Model.ChatList;
import hu.unideb.RFTDatingSite.Model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Integer> {
    @Query("select c from ChatMessage c where (c.sender  = :uname and c.receiver = :uname2) or ( c.sender  = :uname2 and c.receiver = :uname)")
    Set<ChatMessage> getChatMessagesByUsers(@Param("uname")String uname, @Param("uname2")String uname2);
}

