package com.solera.covid.vaccinationDetails.restController;

import java.sql.Date;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solera.covid.vaccinationDetails.entity.Vaccination;
import com.solera.covid.vaccinationDetails.exceptionHandling.GenericException;
import com.solera.covid.vaccinationDetails.exceptionHandling.IdNotFoundException;
import com.solera.covid.vaccinationDetails.serviceLayer.InterfaceVaccinationServiceLayer;

@RestController
@RequestMapping("/api")
public class RestControllerVaccination {
	private InterfaceVaccinationServiceLayer vaccinationService;
	
	public RestControllerVaccination(InterfaceVaccinationServiceLayer theVaccinationService)
	{
		vaccinationService = theVaccinationService;
	}
	

	@GetMapping("/vaccination")
	public List<Vaccination> show(){
		return vaccinationService.showAll();
	}
	
	@GetMapping("/vaccination/{healthId}")
	public Vaccination showById(@PathVariable int healthId) {
		Vaccination vac = vaccinationService.showById(healthId);
		if(vac == null) {
			throw new IdNotFoundException("ID not found!"+healthId);
		}
		
		return vac;
	}
	
	@PostMapping("/vaccination")
	public Vaccination addNew(@RequestBody Vaccination vaccination) {
		vaccinationService.saveOrUpdate(vaccination);
		return vaccination;
	}
	
	@PutMapping("/vaccination")
	public Vaccination update(@RequestBody Vaccination vaccination) {
		int uidai = vaccination.getUidai();
		if(uidai == 0 || vaccination.getUidai() == 0) {
			throw new GenericException("INVALID UIDAI!!");
		}
		if(vaccination.getBoosterDoseDate()!=null)
			throw new GenericException("Person with fully vaccinated along with booster cannot be updated further!!");
		
		if(vaccination.getHealthId() == 0)
			throw new GenericException("Invalid Health id OR Primary key exception!");
		vaccinationService.saveOrUpdate(vaccination);
		return vaccination;
		
		
	}
	
	
	@GetMapping("/Vaccinated/{noOfDose}")
	public List<Vaccination> vaccinatedList(@PathVariable int noOfDose){
		if(noOfDose > 2) {
			throw new RuntimeException("Doses cannot exceed more than two");
		}
		return vaccinationService.vaccinatedList(noOfDose);
	}
	
	@GetMapping("/vaccineName/{vaccineName}")
	public List<Vaccination> vaccineByType(@PathVariable String vaccineName){
		List<Vaccination> vac = vaccinationService.vaccineByType(vaccineName);
		if(vac.size() == 0)
			throw new GenericException("Either No person with such vaccinated exist or No such vaccination exist!!");
		return vac;
		
	}
	@GetMapping("/vaccineReport")
	public List<Vaccination> vaccineCountReprt(){
		List<Vaccination> vac = vaccinationService.vaccineCountReprt();
		return vac;
	}
	
	@DeleteMapping("/Vaccinated/{noOfDoses}")
	public void delete(@PathVariable int noOfDoses) {
		if(noOfDoses!=2) {
			throw new GenericException("ONLY FULLY VACCINATED PEOPLE CAN BE DELETED");
		}
		vaccinationService.delete(noOfDoses);
	}
	
	@DeleteMapping("/vaccination/{healthId}")
	public void deleteById(@PathVariable int healthId) {
		vaccinationService.deleteById(healthId);
	}
	
	
}
