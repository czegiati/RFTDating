package hu.unideb.RFTDatingSite.repository;

import hu.unideb.RFTDatingSite.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
//queries go here
}
