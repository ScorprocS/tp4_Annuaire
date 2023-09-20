package fr.mns.tp4_annuaire.repository;

import java.util.ArrayList;
import java.util.List;

import fr.mns.tp4_annuaire.model.Gender;
import fr.mns.tp4_annuaire.model.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class PersonRepositoryCustomImpl implements PersonRepositoryCustom{
	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public List<Person> findallCriteria( Gender gender) {
	     
	      CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	      CriteriaQuery<Person> q = cb.createQuery(Person.class);
	      Root<Person> person = q.from(Person.class);
	      q.select(person);
	      List<Predicate> predicates = new ArrayList<>();
	      predicates.add(cb.equal(person.get("gender"), gender)); //nani
	    
	      q.where(predicates.toArray(predicates.toArray(new Predicate[1])));
	      cb.desc(person.get("firstname"));
	      TypedQuery<Person> query = entityManager.createQuery(q);
	      List<Person> results = query.getResultList();
	      return results;
	   }
}
