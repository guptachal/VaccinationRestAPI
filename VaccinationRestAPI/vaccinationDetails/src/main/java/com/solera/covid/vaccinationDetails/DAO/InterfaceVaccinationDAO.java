package com.solera.covid.vaccinationDetails.DAO;

import java.util.Date;
import java.util.List;

import com.solera.covid.vaccinationDetails.entity.Vaccination;

public interface InterfaceVaccinationDAO {
	
	public List<Vaccination> showAll();

	public Vaccination showById(int healthId);

	public String saveOrUpdate(Vaccination vaccination);

	public List<Vaccination> vaccinatedList(int noOfDose);

	public int delete(int noOfDoses);

	public void deleteById(int healthId);

	public List<Vaccination> vaccineByType(String vaccineName);

	public List<Vaccination> vaccineCountReprt();
	
}
