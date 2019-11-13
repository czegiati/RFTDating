package hu.unideb.RFTDatingSite.repository;

import hu.unideb.RFTDatingSite.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Integer> {
//queries go here
@Query("select u from User u where u.username  = :uname ")
User getUsersByUsername(@Param("uname")String uname);

@Query("select u from User u where u.email  = :email ")
User getUsersByEmail(@Param("email")String email);

@Query("select u from User u where u.birthdate>= :mindate and u.birthdate <= :maxdate ")
Set<User> getUsersBornBetween(@Param("mindate") java.util.Date mindate, @Param("maxdate")java.util.Date maxdate);
}
