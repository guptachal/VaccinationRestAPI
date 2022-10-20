package com.solera.covid.vaccinationDetails.serviceLayer;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.solera.covid.vaccinationDetails.DAO.InterfaceVaccinationDAO;
import com.solera.covid.vaccinationDetails.entity.Vaccination;
@Service
public class ImplementationVaccinationServiceLayer implements InterfaceVaccinationServiceLayer{

	private InterfaceVaccinationDAO vaccinationDAO;
	@Autowired
	public ImplementationVaccinationServiceLayer(InterfaceVaccinationDAO theVaccinationDAO) {
		vaccinationDAO = theVaccinationDAO;
	}
	@Override
	@Transactional
	public List<Vaccination> showAll()
	{
		return vaccinationDAO.showAll();
		
	}
	@Override
	public Vaccination showById(@PathVariable int healthId) {
		// TODO Auto-generated method stub
		return vaccinationDAO.showById(healthId);
		
	}
	@Override
	@Transactional
	public String saveOrUpdate(Vaccination vaccination) {
		// TODO Auto-generated method stub
		vaccinationDAO.saveOrUpdate(vaccination);
		return "Sucessfully updated!";
	}
	@Override
	public List<Vaccination> vaccinatedList(int noOfDose) {
		// TODO Auto-generated method stub
		return vaccinationDAO.vaccinatedList(noOfDose);
	}
	@Override
	@Transactional
	public int delete(int noOfDoses) {
		// TODO Auto-generated method stub
		return vaccinationDAO.delete(noOfDoses);
	}
	@Override
	@Transactional
	public void deleteById(int healthId) {
		// TODO Auto-generated method stub
		vaccinationDAO.deleteById(healthId);
	}
	@Override
	public List<Vaccination> vaccineByType(String vaccineName) {
		// TODO Auto-generated method stub
		List<Vaccination> vac = vaccinationDAO.vaccineByType(vaccineName);
		return vac;
	}
	@Override
	public List<Vaccination> vaccineCountReprt() {
		// TODO Auto-generated method stub
		return vaccinationDAO.vaccineCountReprt();
	}

	
}
