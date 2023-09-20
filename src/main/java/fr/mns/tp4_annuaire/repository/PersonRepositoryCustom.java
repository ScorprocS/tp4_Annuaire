package fr.mns.tp4_annuaire.repository;

import java.util.List;

import fr.mns.tp4_annuaire.model.Gender;
import fr.mns.tp4_annuaire.model.Person;

public interface PersonRepositoryCustom {
	List<Person> findallCriteria( Gender gender);
}
