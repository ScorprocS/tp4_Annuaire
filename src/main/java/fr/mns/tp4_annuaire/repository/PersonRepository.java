package fr.mns.tp4_annuaire.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.mns.tp4_annuaire.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>,PersonRepositoryCustom{
	 @EntityGraph(value="Person.address",type = EntityGraphType.LOAD)
     Person findOneWithAddressById(@Param("id") Long id);
	 
	 @EntityGraph(value="Person.address")
	 Person findOneWithAddressByLastName(@Param("lastName") String lastName);

	@Query("select p from Person p left join fetch p.address where p.id =:id")
	 Person findOneWithAddressByIdJpql(@Param("id") Long id);
	
	//Person findByFirstName(String firstname);
}




